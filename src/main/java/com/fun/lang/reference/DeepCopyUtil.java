package com.fun.lang.reference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator
 *
 * @date 2017/4/27.
 */
public class DeepCopyUtil {

    public static Object copy(Object obj){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object newObj = ois.readObject();

            return newObj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
