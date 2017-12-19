package com.yidusoft.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**对象操作类
 * Created by smy on 2017/10/19.
 */
public class JavaBeanUtil {

    /**
     * 同类对象，将非空属性赋值给另一个对象
     * @param origin
     * @param destination
     * @param <T>
     */
    public static  <T>  void mergeObject(T origin, T destination) {
        if (origin == null || destination == null)
            return;
        if (!origin.getClass().equals(destination.getClass()))
            return;

        Field[] fields = origin.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                Object value = fields[i].get(origin);
                if (null != value) {
                    fields[i].set(destination, value);
                }
                fields[i].setAccessible(false);
            } catch (Exception e) {
            }
        }
    }

    //通过属性名判断对象的属性值是否有空
    public static boolean checkFieldValueNull(Object bean,String[] judgeFields) {
        boolean result = true;
        if (bean == null) {
            return true;
        }
        Class<?> cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (String name : judgeFields){
            boolean fg = false;
            for (Field field : fields) {
                if (field.getName().equals(name)){
                    try {
                        String fieldGetName = parGetName(field.getName());
                        if (!checkGetMet(methods, fieldGetName)) {
                            continue;
                        }
                        Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
                        Object fieldVal = fieldGetMet.invoke(bean, new Object[]{});
                            if ("".equals(fieldVal) || fieldVal ==null) {
                                result = true;
                                fg = true;
                                break;
                            } else {
                                result = false;
                            }

                    } catch (Exception e) {
                        continue;
                    }
                }
            }
            if (fg){
                break;
            }
        }
        return result;
    }

    /**
     * 拼接某属性的 get方法
     *
     * @param fieldName
     * @return String
     */
    public static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 判断是否存在某属性的 get方法
     *
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }


}
