package com.example.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Message;

public class LocatingCity {
	//ip查询地址
	    public static String getIpUrl = "http://www.cz88.net/ip/viewip778.aspx";

	    public static void getWebIp() {
	        new Thread() {
	            public void run() {
	                String strForeignIP = "";
	                try {
	                    URL url = new URL(getIpUrl);

	                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

	                    String s = "";
	                    StringBuffer sb = new StringBuffer("");
	                    while ((s = br.readLine()) != null) {
	                        sb.append(s + "\r\n");
	                    }
	                    br.close();

	                    String webContent = "";
	                    webContent = sb.toString();
	                    String flagofForeignIPString = "IPMessage";
	                    int startIP = webContent.indexOf(flagofForeignIPString)
	                            + flagofForeignIPString.length() + 2;
	                    int endIP = webContent.indexOf("</span>", startIP);
	                    strForeignIP = webContent.substring(startIP, endIP);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            };
	        }.start();

	    }
	    //跟据ip获得城市名称等信息
	    public static final String sGetAddrUrl = "http://ip-api.com/json/";

	    public static void locateCityName(final String foreignIPString) {
	    	 
	        new Thread() {

				public void run() {
	                try {
	                    HttpClient httpClient = new DefaultHttpClient();
	                    String requestStr = sGetAddrUrl + foreignIPString;
	                    HttpGet request = new HttpGet(requestStr);
	                    HttpResponse response = httpClient.execute(request);
	                    if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
	                    	String cityName = EntityUtils.toString(response.getEntity());
	                    	Bundle mBundle = new Bundle();
	                    	Message msg = new Message();
	                    	mBundle.putString("city", cityName);
	                    	msg.setData(mBundle);
//下一步查找隔activity 调用mhandler的方法
	                    
	                    }
	                } catch (ClientProtocolException e) {
	                    e.printStackTrace();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            };
	        }.start();

	    }
}


