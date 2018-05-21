package com.yidusoft.utils;


import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smy on 2017/10/12.
 */
public class IpAddressUtils {



    /**
     * 获取地址
     * @param params
     * @param encoding
     * @return
     * @throws Exception
     */
    public static Map<String,Object> getAddress(String params, String encoding) throws Exception{

        String path = "http://ip.taobao.com/service/getIpInfo.php";

        String returnStr = getRs(path, params, encoding);

        JSONObject json=null;
        Map<String,Object> map = new HashMap<String,Object>();
        if(returnStr != null){
            json = new JSONObject(returnStr);
            if("0".equals(json.get("code").toString())){

                String country =decodeUnicode(json.optJSONObject("data").getString("country"));//国家
                map.put("country",country);

                String area =decodeUnicode(json.optJSONObject("data").getString("area"));//地区
                map.put("area",area);

                String region =decodeUnicode(json.optJSONObject("data").getString("region"));//省份
                map.put("region",region);

                String city =decodeUnicode(json.optJSONObject("data").getString("city"));//市区
                map.put("city",city);


                String county =decodeUnicode(json.optJSONObject("data").getString("county"));//县
                map.put("county",county);

                String isp =decodeUnicode(json.optJSONObject("data").getString("isp"));//ISP公司
                map.put("isp",isp);
                return map;

            }else{
                return map;
            }
        }
        return null;
    }

    /**
     * 从url获取结果
     * @param path
     * @param params
     * @param encoding
     * @return
     */
    public static String getRs(String path, String params, String encoding){

        URL url = null;
        HttpURLConnection connection = null;

        try {

            url = new URL(path);

            connection = (HttpURLConnection)url.openConnection();// 新建连接实例

            connection.setConnectTimeout(5000);// 设置连接超时时间，单位毫�?

            connection.setReadTimeout(5000);// 设置读取数据超时时间，单位毫�?

            connection.setDoInput(true);// 是否打开输出�? true|false

            connection.setDoOutput(true);// 是否打开输入流true|false

            connection.setRequestMethod("POST");// 提交方法POST|GET

            connection.setUseCaches(false);// 是否缓存true|false

            connection.connect();// 打开连接端口

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            out.writeBytes(params);

            out.flush();

            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),encoding));

            StringBuffer buffer = new StringBuffer();

            String line = "";

            while ((line = reader.readLine())!= null) {

                buffer.append(line);

            }

            reader.close();

            return buffer.toString();

        } catch (Exception e) {

            e.printStackTrace();

        }finally{
            connection.disconnect();// 关闭连接

        }
        return null;
    }

    /**
     * 转换短网址 http://www.alifeifei.net/
     * @return
     */
    public static String getShortUrl(String url){
        String path = "http://api.kks.me/api.php?url="+url;
        return  getRs(path,"{}","utf-8");
    }

    /**
     * 字符转码
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString){

        char aChar;

        int len = theString.length();

        StringBuffer buffer = new StringBuffer(len);

        for (int i = 0; i < len;) {

            aChar = theString.charAt(i++);

            if(aChar == '\\'){

                aChar = theString.charAt(i++);

                if(aChar == 'u'){

                    int val = 0;

                    for(int j = 0; j < 4; j++){

                        aChar = theString.charAt(i++);

                        switch (aChar) {

                            case '0':

                            case '1':

                            case '2':

                            case '3':

                            case '4':

                            case '5':

                            case '6':

                            case '7':

                            case '8':

                            case '9':

                                val = (val << 4) + aChar - '0';

                                break;

                            case 'a':

                            case 'b':

                            case 'c':

                            case 'd':

                            case 'e':

                            case 'f':

                                val = (val << 4) + 10 + aChar - 'a';

                                break;

                            case 'A':

                            case 'B':

                            case 'C':

                            case 'D':

                            case 'E':

                            case 'F':

                                val = (val << 4) + 10 + aChar - 'A';

                                break;

                            default:

                                throw new IllegalArgumentException(

                                        "Malformed      encoding.");
                        }

                    }

                    buffer.append((char) val);

                }else{

                    if(aChar == 't'){

                        aChar = '\t';
                    }

                    if(aChar == 'r'){

                        aChar = '\r';
                    }

                    if(aChar == 'n'){

                        aChar = '\n';
                    }

                    if(aChar == 'f'){

                        aChar = '\f';

                    }

                    buffer.append(aChar);
                }

            }else{

                buffer.append(aChar);

            }

        }

        return buffer.toString();

    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static String getIpAddr() {
        String ip = IpAddressUtils.sendGet("http://www.ip138.com/ip2city.asp", "");
        if(ip==null||"".equals(ip)){
            return "未知IP";
        }
        int start = ip.indexOf("[") + 1;
        int end = ip.indexOf("]");
        ip = ip.substring(start,end);
        if(ip==null||"".equals(ip)){
            return "未知IP";
        }
        return ip;
    }

    /**
     * 根据ip地址获取地理位置：如城市
     * @param url
     * @param param
     * @return
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);

            URLConnection connection = realUrl.openConnection();

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();

            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
