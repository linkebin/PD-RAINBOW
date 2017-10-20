package com.yidusoft.utils;

import java.lang.reflect.Field;

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
}
