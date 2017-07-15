package com.kteam.cardenglishgamer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Mo on 2017/7/7.
 */

public class HttpUtil {

    private static final String DEFAULT_CHARSET = "UTF-8"; // 默认字符集
    private static final String _GET = "GET"; // GET字段
    private static final String _POST = "POST";// POST字段

    public static int StatusCode;//Http协议状态码
    private static boolean StatusCodeDescriptionOff = true;//状态码描述文本关标志

    public static boolean isStatusCodeDescriptionOff() {
        return StatusCodeDescriptionOff;
    }

    /**
     * 初始化http请求参数
     * @param address 服务器接入API，不可为null
     * @param method 请求方式，参选参数：_GET和_POST，不可为null
     * @param headers 与服务器协议的头字段表，可为null
     * @return HttpURLConnection实例
     * @throws IOException 异常
     */
    private static HttpURLConnection initHttp(String address, String method, Map<String, String> headers)
            throws IOException {
        URL url = new URL(address);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(25000);
        // 设置请求方式
        http.setRequestMethod(method);
        // 设置头字段信息
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                http.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        // 允许输出
        http.setDoOutput(true);
        // 允许输入
        http.setDoInput(true);
        // 建立连接
        http.connect();
        return http;
    }

    /**
     *初始化Https请求参数
     * @param address 服务器接入API，不可为null
     * @param method 请求方式，可选参数_GET和_POST
     * @param headers 与服务器协议的头字段表，可为null
     * @return HttpsURLConnection实例
     * @throws IOException URL处理抛出的异常
     * @throws NoSuchAlgorithmException 获取SLLContext单例失败抛出的异常
     * @throws KeyManagementException Key管理抛出的异常
     */
    private static HttpsURLConnection initHttps(String address, String method, Map<String, String> headers)
            throws IOException, NoSuchAlgorithmException, KeyManagementException {
        // 创建证书管理类
        TrustManager[] tm = { new MyX509TrustManager() };
        // 获取SSLContext单例
        SSLContext sslContext = SSLContext.getInstance("SSL");
        // 初始化SSLContext参数（Key管理类，证书管理类，SecureRandom）
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        URL url = new URL(address);

        HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
        // 设置域名校验
        https.setHostnameVerifier(new HttpUtil().new TrustAnyHostnameVerifier());
        // 连接超时
        https.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时间
        https.setReadTimeout(25000);
        // 设置请求方式
        https.setRequestMethod(method);
        // 设置头字段信息
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                https.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        https.setSSLSocketFactory(ssf);
        https.setDoOutput(true);
        https.setDoInput(true);
        https.connect();
        return https;
    }

    /**
     * get请求
     * @param address 无参数服务器接口API
     * @param params API参数表
     * @param headers 头字段表
     * @return 服务器返回的信息
     */
    public static String get(String address, Map<String, String> params, Map<String, String> headers) {
        try {
            HttpURLConnection connection;
            // 验证是否为https
            if (isHttps(address)) {
                connection = initHttps(initParams(address, params), _GET, headers);
            } else {
                connection = initHttp(initParams(address, params), _GET, headers);
            }
            StatusCode = connection.getResponseCode();
            if(StatusCode<200||StatusCode>=300){
                return StatusCode+HttpStatusCode.getDescription(StatusCode,StatusCodeDescriptionOff);
            }else {
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                if (connection != null) {
                    connection.disconnect();// 关闭连接
                }
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get请求
     * @param url 无参数服务器接口API
     * @param params API参数表
     * @return 服务器返回的信息
     */
    public static String get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    /**
     * get请求
     * @param url 带参数的服务器接口API
     * @return 服务器返回的信息
     */
    public static String get(String url) {
        return get(url, null);
    }


    /**
     * post请求
     * @param address 无参数服务器接口API
     * @param params 参数信息
     * @param headers 头字段表
     * @return 服务器返回的信息
     */
    public static String post(String address, String params, Map<String, String> headers) {
        try {
            HttpURLConnection connection;
            if (isHttps(address)) {
                connection = initHttps(address, _POST, headers);
            } else {
                connection = initHttp(address, _POST, headers);
            }
            OutputStream out = connection.getOutputStream();
            out.write(params.getBytes(DEFAULT_CHARSET));
            out.flush();
            out.close();

            StatusCode = connection.getResponseCode();
            if(StatusCode<200||StatusCode>=300){
                return StatusCode+HttpStatusCode.getDescription(StatusCode,StatusCodeDescriptionOff);
            }else {
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                if (connection != null) {
                    connection.disconnect();// 关闭连接
                }
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * post请求
     * @param address 无参数服务器接口API
     * @param params 参数信息
     * @param headers 头字段表
     * @return 服务器返回的信息
     */
    public static String post(String address, Map<String, String> params, Map<String, String> headers) {
        return post(address, map2Url(params), headers);
    }

    /**
     * post请求
     * @param address 无参数服务器接口API
     * @param params 参数表
     * @return 服务器返回的信息
     */
    public static String post(String address, Map<String, String> params) {
        return post(address, map2Url(params), null);
    }


    /**
     *API合并参数表
     * @param address 无参数服务器接口API
     * @param params 参数表
     * @return 完整参数服务器API
     */
    public static String initParams(String address, Map<String, String> params) {
        if (null == params || params.isEmpty()) {
            return address;
        }
        StringBuilder sb = new StringBuilder(address);
        if (address.indexOf("?") == -1) {
            sb.append("?");
        }
        sb.append(map2Url(params));
        return sb.toString();
    }

    /**
     * map格式转为String信息
     * @param paramMap 需要转化为String的参数表
     * @return 返回参数信息，例如 abc=1&cde=2&ade=3
     */
    public static String map2Url(Map<String, String> paramMap) {
        if (null == paramMap || paramMap.isEmpty()) {
            return null;
        }
        StringBuffer url = new StringBuffer();
        boolean isfist = true;
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (isfist) {
                isfist = false;
            } else {
                url.append("&");
            }
            url.append(entry.getKey()).append("=");
            String value = entry.getValue();
            if (!value.isEmpty()) {
                try {
                    url.append(URLEncoder.encode(value, DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return url.toString();
    }

    /**
     * 检测是否https
     */
    private static boolean isHttps(String url) {
        return url.startsWith("https");
    }

    /**
     * 域名校验
     */
    public class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;// 直接返回true
        }
    }
}

// 证书管理
class MyX509TrustManager implements X509TrustManager {

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }
}
