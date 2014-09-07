package com.example.application;

import java.io.IOException;

import java.io.BufferedReader;  
// java.io.IOException;
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.HttpStatus;  
import org.apache.http.NameValuePair;
import org.apache.http.client.*;  
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;  
import org.apache.http.impl.client.*;  
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;  
import org.apache.http.params.HttpConnectionParams;  
import org.apache.http.params.HttpParams;  
import org.apache.http.util.EntityUtils;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpEnglish implements Runnable{
	private String xm;
	private String zkzh; 
	private String  strResult;
	private Handler handler;
	public HttpEnglish(){}
	public HttpEnglish(String azkzh,String axm,Handler handler){
		this.zkzh = azkzh;
		this.xm = axm;
		this.handler = handler;
	}
    private static String filterHtml(String source) {  
	        if(null == source){  
	            return "";  
	        }  
	        return source.replaceAll("</?[^>]+>","").trim();  
	    }  

         public void run(){  
        	 String httpUrl;
   		  StringBuilder buf = null;			
				 buf = new StringBuilder("http://www.chsi.com.cn/cet/query"); 			 
				  List<NameValuePair> params = new ArrayList<NameValuePair>();  
			        params.add(new BasicNameValuePair("zkzh",zkzh));  
			        params.add(new BasicNameValuePair("xm", xm));  
			httpUrl=buf.toString();
   		      //  HttpGet httpRequest = new HttpGet(httpUrl);  
			   HttpPost post = new HttpPost(httpUrl);    		    
   		        try{  
   		            //取得HttpClient对象   		        	
   		            HttpClient httpclient = new DefaultHttpClient();  
   		            //请求HttpClient，取得HttpResponse  
   		          post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
   		          post.setHeader("referer", "http://www.chsi.com.cn/cet/");
   		            HttpResponse httpResponse = httpclient.execute(post);  
   		            //请求成功  
   		            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)  
   		            {  
   		                //取得返回的字符串  
   		                 strResult = EntityUtils.toString(httpResponse.getEntity());
   		                 strResult = strResult.substring(5351, 6000);
   		             strResult = filterHtml(strResult);
   		                 byte[] a =  strResult.getBytes();
   		             System.out.println(a.length+" "+a[100]);
   		             strResult =  strResult.replaceAll(" ", "");
   		          byte[] b =  strResult.getBytes();
   		          System.out.println(b.length+" "+a[100]);
   		           Message msg = new Message();
   		        msg.what =1;  //设置一个状态，处理消息时用
   		     Bundle bundle = new Bundle();  
             bundle.putString("str", strResult);  
   		        msg.setData(bundle); //这就是你要传送的字符串
   		        handler.sendMessage(msg);
   		            }  
   		            else  
   		            {  
   		             //   mTextView.setText("请求错误!");  
   		            }  
   		        }  
   		        catch (ClientProtocolException e)  
   		        {  
   		           // mTextView.setText(e.getMessage().toString());  
   		        	System.out.println("CP");
   		        }  
   		        catch (IOException e)  
   		        {  
   		        	System.out.println("IOE");
   		          //  mTextView.setText(e.getMessage().toString());  
   		        }  
   		        catch (Exception e)  
   		        {  
   		        	System.out.println("Exception");
   		        	e.printStackTrace();
   		           // mTextView.setText(e.getMessage().toString());  
   		        }         	  
         }
}