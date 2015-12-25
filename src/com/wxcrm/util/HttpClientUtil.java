package com.wxcrm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtil 
{
	private static Logger log = Logger.getLogger(HttpClientUtil.class);
	
	public static String doGet(String url) throws Exception 
	{
		StringBuilder builder = new StringBuilder("\n").append(url).append("\n");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String result = null;
		try 
        {
			HttpGet httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity()).trim();
        } 
        finally 
        {
        	if( response != null )
        	{
        		response.close();
        	}
        	httpClient.close();
        }
        builder.append("结果:").append(result);
        log.info(builder);
        return result;
	}
	
	public static String doPost(String url, Map<String, String> map) throws Exception 
	{
		StringBuilder builder = new StringBuilder("\n").append(url).append("\n");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String result = null;
        try 
        {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> set = map.keySet();
            for( String key : set )
            {
            	nvps.add(new BasicNameValuePair(key, map.get(key)));
            	builder.append(key).append(": ").append(map.get(key)).append("\n");
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity()).trim();
        } 
        finally 
        {
        	if( response != null )
        	{
        		response.close();
        	}
        	httpClient.close();
        }
        builder.append("结果:").append(result);
        log.info(builder);
        return result;
	}
	
	
	public static String doPost(String url, Map<String, String> map,String charset) throws Exception 
	{
		StringBuilder builder = new StringBuilder("\n").append(url).append("\n");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String result = null;
		try 
        {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> set = map.keySet();
            for( String key : set )
            {
            	nvps.add(new BasicNameValuePair(key, map.get(key)));
            	builder.append(key).append(": ").append(map.get(key)).append("\n");
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(),charset).trim();
        } 
        finally 
        {
        	if( response != null )
        	{
        		response.close();
        	}
        	httpClient.close();
        }
        builder.append("结果:").append(result);
        log.info(builder);
        return result;
	}
}
