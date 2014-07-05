package com.miniboss.util 

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright lr.
 * User: Daocren
 * Date: 2008-6-11
 * Time: 21:21:39
 */
public class HttpUtil {
    private static HttpClientParams clientParams = new HttpClientParams();
    private static int readtimeout = 3 * 1000;
    private static int connectiontimeout = 10 * 1000;
    private static final String default_string = "utf-8";

    public static HttpSendResult executeGet(String url) {
        return executeGet(url, default_string);
    }

    public static HttpSendResult executeGet(String url, String charset) {
        HttpSendResult sendResult = new HttpSendResult();
        clientParams.setSoTimeout(readtimeout);
        HttpClient hc = new HttpClient(clientParams);
        hc.setConnectionTimeout(connectiontimeout);
        GetMethod getMethod = new GetMethod(url);
        getMethod.setRequestHeader("Connection", "close");
        getMethod.setRequestHeader("Proxy-Connection", "close");
        try {
            int responseCode = hc.executeMethod(getMethod);
            InputStream inputStream = getMethod.getResponseBodyAsStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int read = -1;
            while ((read = inputStream.read()) != -1) {
                out.write(read);
            }
            String response = new String(out.toByteArray(), charset);
            sendResult.setStatusCode(responseCode);
            sendResult.setResponse(response);
        } catch (Exception e) {
            sendResult.setException(e);
        }
        getMethod.releaseConnection();
        return sendResult;
    }
    public static HttpSendResult executePost(String url, String str) {
        return executePost(url, str, default_string);
    }

    public static HttpSendResult executePost(String url, String str, String charset) {
        HttpSendResult sendResult = new HttpSendResult();
        HttpClient hc = new HttpClient(clientParams);
        hc.setConnectionTimeout(connectiontimeout);
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Connection", "close");
        postMethod.setRequestHeader("Proxy-Connection", "close");
        postMethod.setRequestEntity(new ByteArrayRequestEntity(str.getBytes(charset)));
        postMethod.setRequestHeader("Content-type", "text/xml; charset=" + charset);
        try {
            int responseCode = hc.executeMethod(postMethod);
            InputStream inputStream = postMethod.getResponseBodyAsStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int read = -1;
            while ((read = inputStream.read()) != -1) {
                out.write(read);
            }
            String response = new String(out.toByteArray(), charset);
            sendResult.setStatusCode(responseCode);
            sendResult.setResponse(response);
        } catch (Exception e) {
            sendResult.setException(e);
        }
        postMethod.releaseConnection();
        return sendResult;
    }
    public static String getPost(HttpServletRequest request) {
      return getPost(request, default_string);
    }

    public static String getPost(HttpServletRequest request, String charset) {
        String get = "";
        InputStream inputStream;
        try {
            inputStream = request.getInputStream();
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            int b;
            while ((b = inputStream.read()) != -1) {
                o.write(b);
            }
            o.close();
            inputStream.close();
            byte[] bs = o.toByteArray();
            get = new String(bs, charset);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return get;
    }

    public static void main(String[] args) {
        try {
          for(int i=0;i<100000000;i++){
            HttpSendResult st = HttpUtil.executeGet("http://192.168.12.119:10001/IBoss/billingCycle/jsonList");
            System.err.println(st.getResponse());
            Thread.sleep(10);
          }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }


    public static void setReadtimeout(int readtimeout) {
        HttpUtil.readtimeout = readtimeout;
    }


    public static void setConnectiontimeout(int connectiontimeout) {
        HttpUtil.connectiontimeout = connectiontimeout;
    }
}