package com.yidusoft.core;

/**
 * 项目常量
 */
public final class ProjectConstant {

    public static final String BASE_PACKAGES = "com.yidusoft.project.transaction";//项目基础包名称
    public static final String BASE_PACKAGE = "com.yidusoft";//项目基础包名称
    public static final String BASE_SERVICE = "pd-fainbow";//服务名称

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".project.transaction.domain";//Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".project.transaction.dao";//Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".project.transaction.service";//Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".project.transaction.controller";//Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";//Mapper插件基础接口的完全限定名
}
