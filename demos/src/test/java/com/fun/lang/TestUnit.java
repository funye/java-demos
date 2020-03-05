package com.fun.lang;

import com.fun.excel.ExcelFinancialFunction;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author huanye
 * @date: 2017/6/30 下午3:23
 */
public class TestUnit {

    @Test
    public void testExcelFinancialFunction() {
        {
            Double pv = 12000d;
            Double pmt = 1013.2d;
            Double nper = 18d;

            //计算200次，比Excel20次要精确，误差精确到小数点后10位
            double rate = ExcelFinancialFunction.rate(pv,pmt,nper,200,10);
            System.out.printf("rate=%f,rate is %d%s\n",rate,Math.round(rate*100),"%");

            double newPv = ExcelFinancialFunction.pv(rate,18,900,1,0);
            System.out.println("roundDown(pv)=" + (int)ExcelFinancialFunction.roundDown(newPv,-2));
            System.out.println("pv=" + newPv);
            System.out.println("###########################");

            double ppp = ExcelFinancialFunction.pv(0.08/12,20*12,50000,0,0);
            System.out.println(ppp);

        }
    }


    @Test
    public void testList() {

        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(200);
        list.add(500);
        list.add(300);

        Collections.sort(list,(a,b)->{
            if (a.intValue() > b.intValue())
                return -1;
            else
                return 1;
        });

        System.out.println(list.toString());
    }

    @Test
    public void testSize(){


        char[] b = {' '};

        System.out.println((int)Character.MIN_VALUE);
        System.out.println((int)(b[0]));
        System.out.println(Integer.MIN_VALUE);

    }


    @Test
    public void testByteBuffer() {
        System.out.println("------Test get-------------");
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put((byte) 'a').put((byte) 'b').put((byte) 'c').put((byte) 'd')
            .put((byte) 'e').put((byte) 'f');
        System.out.println("before flip()" + buffer);
        // 转换为读取模式
        buffer.flip();
        System.out.println("before get():" + buffer);
        System.out.println((char) buffer.get());
        System.out.println("after get():" + buffer);
        // get(index)不影响position的值
        System.out.println((char) buffer.get(2));
        System.out.println("after get(index):" + buffer);
    }

    @Test
    public void testByteRead() throws Exception {
        InputStream in = new FileInputStream(new File("/Users/huanye/Documents/coding/learing-demo/java_code/demos/src/test/java/com/fun/lang/file.txt"));

        byte[] buf = new byte[3];
        ByteArrayOutputStream bo = new ByteArrayOutputStream(32);
        int len = -1;
        while((len = in.read(buf)) > 0) {
            bo.write(buf,0,len);
        }
        bo.close();
        String result = bo.toString(StandardCharsets.UTF_8.name());
        System.out.println("file content is: " + result);

        String str = "我是中国人";
        System.out.println(str.getBytes().length);


        System.out.println();
    }

}