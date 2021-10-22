package com.fun.simple;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Temp {

    public static void main(String[] args) {
        Long count = 1L;
        count = count == null ? 1 : count++;
        System.out.println(count);
    }

    @Test
    public void testTaskList() throws IOException {
        try {
            System.out.println(System.getenv("windir"));
            String line;
            Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /v");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(new String(line.getBytes("GBK"), "UTF-8")); //<-- Parse data here.
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    @Test
    public void testWindowsShow() {

        Map<String, WinDef.HWND> windowsMap = new HashMap<>();
        User32 user32 = User32.INSTANCE;
        user32.EnumWindows(new WinUser.WNDENUMPROC() {
            @Override
            public boolean callback(WinDef.HWND hWnd, Pointer data) {
                char[] windowText = new char[512];
                user32.GetWindowText(hWnd, windowText, 512);
                String wText = Native.toString(windowText);

                // get rid of this if block if you want all windows regardless of whether
                // or not they have text
                if (wText.isEmpty()) {
                    return true;
                }
                System.out.println("Found window with text " + hWnd + " Text: " + wText);
                windowsMap.put(wText, hWnd);
                return true;
            }
        }, null);


        String windowName = "Chrome";
//        String windowName = "Sublime";
//        String windowName = "TeamTalk";

        windowsMap.forEach((k, v) -> {
            if (k.toLowerCase().contains(windowName.toLowerCase())) {
                WinDef.HWND hwnd = v;
                //        WinDef.HWND hwnd = user32.FindWindow(null, windowName);
                if (hwnd == null) {
                    System.out.println("Miss!");
                } else {
                    System.out.println("Hit!");
                    boolean showed = user32.ShowWindow(hwnd, WinUser.SW_SHOW);
                    System.out.println(windowName + (showed ? "窗口之前可见." : "窗口之前不可见."));
                    user32.SetForegroundWindow(hwnd);
                }
                return;
            }
        });


    }



}
