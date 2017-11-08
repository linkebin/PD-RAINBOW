package com.yidusoft.pay.alipay.domain;

/**
 * @desc 支付宝网站支付接口常量类
 * @create 2017-10-19 10:45
 **/
public class AlipayConfig {

    //支付宝应用ID，收款账号即APP_ID对应的支付宝账号
    public static String APP_ID = "2016081600255170";

    // 商户私钥，PKCS8格式RSA2私钥
    public static String MERCHANT_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDF73mVIkFev8/623ziWstmHAxfTc9MQAVsK2ZgLMbZFAu3tFg1AxM6oZcW4URl11W3UmSKvOxICh6yTBJ56AlbwLHfcvAS0tH5ol/LQOfZv8dve5n/EzMOeVIDkDTJi3i4/mru0FG4snxKYuRPF1NtG00+YzeRsUHH8T7Wm8Mlo23S1MHofUldGq0dZ/otQIZof1yJh+MhSA48lka88MGabum3D/bVdfjT8qUTlluEIsAEKNDTmPeAMOjkxKEatfWjCRpm79J7e6NiBEjV6eINZCSZFXQbHAlNyDGPe3GCCz2UqgkedpngMP7FKBrHrb2Gk2xOErcALJyPb1Is86MLAgMBAAECggEAGqgHnbgBSbtXTRdmGC3WGqhIWkVmV4R/tnOZ0/XzyaRTd6gIAQuq+Uhtyp0H0xdO6eNkuvJ6d7E1NRndmyFOsVagHdGi10YhFRdzAzJ4hvC28SKaOKnjRF2+V9CB62qnFXLd/896Uf9nkbUDvksL5zO4EWDXSP2wODcbGVfcziKZqC9zF5j1HzY/6uJHkuE1QECgp4bCIWzwhfyg5KbFwlUDYWxUauzwdaufR+NhXZEMn0HOkb5eGPS2JqqdjtxRjsxRvkgjOIG5TdSVnaVAFE4jeX11vlvbXhXHnkKFeEMoYPhN+jN+d5DFwwfOh3BlBNF1QOnyDttr5jt89Sj8IQKBgQDqH3890J1P7fzHIc45IjHw4gTFrM+vk1CnsW/RXfjv7SrtEvUc3zDe0HIdhNAFh/aTjHKYt3qh3abEG9VACpj0aKmL4CVu5sJ4l3OsAJBQKhNpaewdl8jyP1XNvY/O/jh4aNczW8oZEg0iJMAIWQf6uZmQwWAwVlSARMeuFL5zaQKBgQDYblPaydl00TsLe5DdfiCBaU2a3EXBu5buQtX49aF81WLqLlolOR3YrR9Gxjaqa7bt3ZDOt8qScxBKKWJZa7cgWOPru1n6re4xtRqxiVTDU2Ka0z7wpuWELPwsXGMk5k6LvLa7AMKkzImwi1x7kai0yPWe6vzPJzhFFT65P+N4UwKBgBRotH3d/qimFWACiWifm3zlJwdYXi5vCBvmMtosd/PqV9Cc03M13Zu+zgPWvQ+OX6dTGfZIIE+V8+CqkHdptRWxPf5shrbZNOCwSywWTH5AfLq2uheGUMUVUlSCDtQ7gw60DPZ0LPTshuqQC5FuuWxY25Mr2Ee+btVTqZh6RHPpAoGAAJnd1b+1wONL3K5NKrBM+s8GDiYKc41H0AWTfouAFU6qZagXS/8qnA1vBs1KHZMLAQIagthz0ZBMPUh8I29vJMHacC8nAtE01iKAnnmZKKm0nmVzyUtNW96cHKttAw84rJ6riz1nTPnccAcTDKzM4SOluvlAFnsvZtG2SOFqik0CgYBqhdev3RyEIf9mW98ePYYXy5TsMGrnxk+Tg2JYvFw2DiHU77et6ieZJwV4VnbCBm+dgFi9TPW1STC4vK28H1P01xf6/D3KJxThxjSaMEuwjaoFU8wjC3H/z0rf7eTQHE4HxarzcEXIxoCxze8wTtde2tXO6iWaLtn86vNlqj1PFA==";

    // 支付宝公钥，APP_ID对应的支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApSMVUCKzCtt341d7e0YT6rdLAyG8YAlLcpSbkl+trlZfva+Kjiy/PYvcG41oJDyepQC0u2iFt1Dm1Sqm0692M6XVW+SyyPVu9Dp8s8wQeOIxFq3bzijkG1j57PHrhCPDWG9YK2SaN9ENAloVbE9VvAwKjKfoLtX8s91grwiHjLXa+IKF8bicMTEQwthIqkg89QQcFXGd2CvK5AQghN1rf5xisGNeQdJ3E9wX/Yp5rjaCFiw3X8ThkK1YigCdqTNk3Fq3B62qSnVFkSlIggVe4SmVOyewv/SX+xJka0SQzmq1YV8jv8kJoRDb/DlMaBSFD7s1syx+ighRNaMZwRmh8wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String NOTIFY_URL = "http://119.23.245.226:8088/alipay/notify/notifyUrl";

    // 页面跳转同步通知页面路径
    //网页重定向通知，由客户端浏览器触发的通知，可本地调试，若客户去网银支付，也会受银行接口影响，由于各种影响因素特别多，所以该种类型的通知支付宝不保证其到达率。
    //买家付款成功后,会跳到 return_url所在的页面,这个页面可以展示给客户看,这个页面只有付款成功才会跳转,并且只跳转一次
    public static String RETURN_URL = "http://119.23.245.226:8088/alipay/notify/returnUrl";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关
    public static String GATEWAY_URL = "https://openapi.alipay.com/gateway.do";
    //沙箱环境测试网关
    //https://openapi.alipaydev.com/gateway.do
}

