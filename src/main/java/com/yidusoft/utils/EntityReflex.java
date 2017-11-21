package com.yidusoft.utils;

import com.yidusoft.core.Result;
import com.yidusoft.project.business.domain.ActiveParticipant;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
     *  params 里面存放的为参数的值
     *  参数的类型必须对应传入参数的类型 如： List<ActiveParticipant> activeParticipants=new ArrayList<>();
     *  activeParticipants 的类型为java.util.ArrayList  在定义的方法里 必须以ArrayList<ActiveParticipant>接收
     *  不能以List 接收
     * @param entity
     * @param method
     * @param params
     */
    public static void   getMethod(Class<?> entity,String method,Object...  params)throws  Exception{
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

    public static Result getMethods(Class<?> entity, String method, Object...  params)throws  Exception{
        //String className = "com.runqianapp.ngr.alias.example.FunClass";
        //Class clz = Class.forName(className);
        Result result=null;
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
            result=(Result) m.invoke(obj,params);
        } catch (Exception e) {
            e.printStackTrace();
            result=null;
        }
        return  result;
    }

    public void sayHello( ArrayList<ActiveParticipant>  params,String  params1){
    //   System.out.println(params.getAge());
       System.out.println(params1);
    }


    public static void main(String[] args) {
        List<ActiveParticipant> activeParticipants=new ArrayList<>();
        ActiveParticipant activeParticipant=new ActiveParticipant();
        activeParticipant.setAge(10);
        activeParticipants.add(activeParticipant);
        List list=new ArrayList<>();
        try {
            EntityReflex.getMethod(EntityReflex.class,"sayHello",activeParticipants,"张安");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
