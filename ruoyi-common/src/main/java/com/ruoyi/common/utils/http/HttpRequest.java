package com.ruoyi.common.utils.http;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * httprequest
 * 秋枫：2020.5.6
 */
public class HttpRequest {
    //    网址
    private String url;
    //    类型[POST,GET]
    private String mode;
    //    协议头
    private HashMap<String, String> headers;
    //    cookie
    private String cookies;
    //    提交数据
    private HashMap<String, String> submitdata;

    //    返回数据
    private String data;
//    Post(String url, String param, HashMap<String, String> headers, String cookie) {

    public HttpRequest(String url, String mode) {
        /**
         * 只有url和访问类型
         */
        this.url = url;
        this.mode = mode;
        if (mode.equals("POST")) {
            this.Post(this.url, null, null, null);
        } else if (mode.equals("GET")) {
            this.sendGet(this.url, null, null, null);
        }

    }

    public HttpRequest(String url, String mode, HashMap<String, String> submitdata) {
        /**
         * url
         * 访问类型
         * 提交数据
         */
        this.url = url;
        this.mode = mode;
        this.submitdata = submitdata;
        if (mode.equals("POST")) {
            this.Post(this.url, this.Processingdata(this.submitdata), null, null);
        } else if (mode.equals("GET")) {
            this.sendGet(this.url, this.Processingdata(this.submitdata), null, null);
        }
    }

    public HttpRequest(String url, String mode, HashMap<String, String> headers, HashMap<String, String> submitdata) {

        /**
         * url
         * 提交类型
         * 协议头
         * 提交数据
         *
         */
        this.url = url;
        this.mode = mode;
        this.headers = headers;
        this.submitdata = submitdata;
        if (mode.equals("POST")) {
            this.Post(this.url, this.Processingdata(this.submitdata), this.headers, null);

        } else if (mode.equals("GET")) {
            this.sendGet(this.url, this.Processingdata(this.submitdata), this.headers, null);
        }
    }

    public HttpRequest(String url, String mode, String cookies, HashMap<String, String> submitdata) {
        /**
         * url
         * 访问类型
         * cookie
         * 提交数据
         */
        this.url = url;
        this.mode = mode;
        this.cookies = cookies;
        this.submitdata = submitdata;
        if (mode.equals("POST")) {
            this.Post(this.url, this.Processingdata(this.submitdata), null, this.cookies);

        } else if (mode.equals("GET")) {
            this.sendGet(this.url, this.Processingdata(this.submitdata), null, this.cookies);
        }
    }

    public HttpRequest(String url, String mode, HashMap<String, String> headers, String cookies, HashMap<String, String> submitdata) {

        /**
         * url
         * 访问类型
         * 协议头
         * cookie
         * 提交数据
         */
        this.url = url;
        this.mode = mode;
        this.headers = headers;
        this.cookies = cookies;
        this.submitdata = submitdata;
        if (mode.equals("POST")) {
            this.Post(this.url, this.Processingdata(this.submitdata), this.headers, this.cookies);

        } else if (mode.equals("GET")) {
            this.sendGet(this.url, this.Processingdata(this.submitdata), this.headers, this.cookies);
        }


    }

    private String Processingdata(HashMap<String, String> submitdata) {

        String retu = "";

        for (Map.Entry<String, String> entry : submitdata.entrySet()) {
            retu = retu + "&" + entry.getKey() + "=" + entry.getValue();

        }
        return retu;
    }

    public String getCookies() {
        return this.cookies;
    }


    /**
     * 向指定URL发送GET方式的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数
     **/
    private void sendGet(String url, String param, HashMap<String, String> headers, String cookie) {
        StringBuilder result = new StringBuilder();
        String urlName = url + "?" + param;

        try {
            URL realUrl = new URL(urlName);


            URLConnection conn = realUrl.openConnection();
            //设置通用的请求属性
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.addRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            if (cookie != null) {
                conn.setRequestProperty("Cookie", cookie);
            }

            //建立实际的连接
            conn.connect();
            //获取所有的响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            //遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "-->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //取cookie
            StringBuilder sessionId = new StringBuilder();
            String key = null;
            for (int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++) {
                System.out.println(key + "-->" );
                if (key.equalsIgnoreCase("set-cookie")) {
                    sessionId.append(conn.getHeaderField(i)).append(";");
                }
            }
            this.cookies = sessionId.toString();


            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

        } catch (Exception e) {
            System.out.println("发送GET请求出现异常" + e);
            e.printStackTrace();
        }
        this.data = result.toString();
    }


    /**
     * 向指定URL发送POST方式的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数
     */
    private void Post(String url, String param, HashMap<String, String> headers, String cookie) {

        /**
         * 参数HashMap<String,String> headers说明：
         * HashMap<String,String> headers =new HashMap<String, String>();
         * headers.put("键", "键值");
         */

        StringBuilder result = new StringBuilder();
        String line;
        try {
            URL realUrl = new URL(url);
            //打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            //设置通用的请求属性
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String k = entry.getKey();
                    String v = entry.getValue();
                    conn.addRequestProperty(k, v);
                }
            }


            if (cookie != null) {
                conn.setRequestProperty("Cookie", cookie);
            }

            //发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            //flush输出流的缓冲
            out.flush();
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            //取cookie
            String sessionId = "";
            String cookieVal = "";
            String key = null;
            for (int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++) {
                if (key.equalsIgnoreCase("set-cookie")) {
                    cookieVal = conn.getHeaderField(i);
                    sessionId = sessionId + cookieVal + ";";
                }
            }
            this.cookies = sessionId;


            while ((line = in.readLine()) != null) {
                result.append("\n").append(line);
            }


        } catch (Exception e) {
            System.out.println("发送POST请求出现异常" + e);
            e.printStackTrace();
        }

        this.data = result.toString();
//        System.out.println(this.data);

    }

    public String getData() {
        return this.data;
    }


    //测试发送GET和POST请求
    public static void main(String[] args) throws Exception {
        //发送POST请求
        //协议头
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("X-mssvc-sec-ts", "1587820160");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("X-mssvc-access-id", "HQQ7P0SxOla0n7yg");
        headers.put("X-app-machine", "iPhone 7");
        headers.put("X-app-system-version", "13.3");
        headers.put("X-device-code", "C88A0C48-C02C-401D-BDD3-BD1775F78D9D");
        headers.put("Accept", "application/json");
       // headers.put("Cookie","userName_cc=dd_itm4hbep; clientId=70d1f116-1ede-41d4-92f2-7091a357e280; loginToken=ac9fc61e-a372-4a2b-a4a5-1daf0edf64df; refreshToken=601b9900-daff-46d4-8262-3f4f4cecc294; login.dd373.com=85248d0b-c1e1-42f0-9fa0-659a0db1f5f7; newpay.dd373.com=90ec3de0-5987-4495-932c-8cb19431ced3; goods.dd373.com=1d088add-bea1-4c21-bd83-401f6e009805; point.dd373.com=a6ece75d-05bb-4718-a481-275b349f2584; newuser.dd373.com=5e9556bd-e15a-4459-8775-eb83c48936e5; mission.dd373.com=bec05890-3fec-4429-982a-e9da1760024f; thirdbind.dd373.com=8d5e30fb-0419-402a-8492-99a551c61a06; imservice.dd373.com=1245034d-3ec8-45c0-aae7-1eb447016563");
//        //请求数据
//        HashMap<String, String> data = new HashMap<String, String>();
//        data.put("userName", "");
//        data.put("userPwd", "");
//        data.put("verifyCode", "");
//
//        HttpRequest post_text = new HttpRequest("https://网址", "POST", data);
//        System.out.println(post_text.getData()); //获取请求返回数据
//        System.out.println(post_text.getCookies()); //获取返回的cookie


        HashMap<String, String> submitdata  = new HashMap<String, String>();;
        submitdata.put("StartDate","");
        submitdata.put("EndDate","");
        submitdata.put("Keyword","DH2022111102052314082");
        submitdata.put("Classify","2");
        submitdata.put("Type","0");
        submitdata.put("PageSize","20");
        submitdata.put("PageIndex","1");

        //String cookie = "userName_cc=dd_itm4hbep; clientId=70d1f116-1ede-41d4-92f2-7091a357e280; refreshToken=601b9900-daff-46d4-8262-3f4f4cecc294; loginToken=2ba66f10-7893-4e51-a2ff-07e22de5665b; newpay.dd373.com=b69117f2-9076-4ac1-a298-d04374f8c1d1; acw_tc=76b20fe916697417249981735e4f19e07f03b95afa13fbb2d7bdafd2b157d5;SERVERID=a278729d2ae086497be277567757b907|1669743724|1669741725;";
        String cookie = "newCookie:uoken=2ba66f10-7374f8c1d1; ;SERVERID=892afa290ce7bed84795cafc25e9c37c|1669745974|1669745974";
        HttpRequest gettext = new HttpRequest("https://openapi.alipay.com/gateway.do?charset=UTF-8", "POST",headers,cookie,submitdata);
        StringBuffer sb = new StringBuffer();
        String newCookie = "";
        String requestCookie = gettext.getCookies();
        String[] newCookies = requestCookie.split(";");
        for (int i=0;i<newCookies.length;i++) {
            if(newCookies[i].indexOf("SERVERID=")>-1){
                newCookie = newCookies[i];
            }
        }
        System.out.println("newCookie："+newCookie);
        if(cookie.indexOf("SERVERID=")!=-1){
            String[] cookies = cookie.split(";");
            for (int i=0;i<cookies.length;i++) {
                if(cookies[i].indexOf("SERVERID=")==-1){
                    sb.append(cookies[i]+";");
                }else{
                    sb.append(newCookie+";");
                }
            }
        }else{
            sb.append(cookie+";");
            sb.append(newCookie);
        }
        System.out.println(gettext.getData());  //获取返回数据
        System.out.println(gettext.getCookies());  //返回cookie
        System.out.println("oldCookie:"+cookie);  //返回cookie
        System.out.println("newCookie:"+sb.toString());  //返回cookie

    }
}










