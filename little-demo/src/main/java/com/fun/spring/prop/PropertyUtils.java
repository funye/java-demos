package com.fun.spring.prop;

import java.util.Properties;

public class PropertyUtils {

    private static Properties properties;

    public static String getProperty(String key) {
        if (properties == null) {
            properties = new Properties();
            try {
                Properties props = new Properties();
                props.load(PropertyUtils.class.getClassLoader().getResourceAsStream("auth-config.properties"));
                properties.putAll(props);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty(key);
    }

}
