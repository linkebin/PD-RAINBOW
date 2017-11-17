package com.yidusoft.utils;

import com.yidusoft.project.business.domain.ActiveParticipant;

import java.lang.reflect.Method;

/**
 * Created by zcb on 2017/11/17.
 * 反射机制  通过字符串得到类 和方法
 */

public  class EntityReflex {

    /**
     * 反射方法调用
     *  entity：对应的实体类
     *  method：对应的方法名
     *  ***特别注意 params***
     *   params 里面存放的为参数的值
     * @param entity
     * @param method
     * @param params
     */
    public static void   getMethod(Class<?> entity,String method,Object...  params){
        //String className = "com.runqianapp.ngr.alias.example.FunClass";
       //Class clz = Class.forName(className);
        try {
             Class[] classes=new Class[params.length];
            for(int i=0;i<params.length;i++){
                //classes[i]=(Class)params[1][i];
                //通过反射机制得到值属于类型  如：字符串  String
                classes[i]=params[i].getClass();
             }
            Object obj=entity.newInstance();
            //获取方法
            Method m= obj.getClass().getDeclaredMethod(method,classes);
            //调用方法
            m.invoke(obj,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sayHello(ActiveParticipant  params,String  params1){
       System.out.println(params.getAge());
       System.out.println(params1);
    }


    public static void main(String[] args) {
        ActiveParticipant activeParticipant=new ActiveParticipant();
        activeParticipant.setAge(10);
        EntityReflex.getMethod(EntityReflex.class,"sayHello",activeParticipant,"张安");

    }

}
