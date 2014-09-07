package com.example.application;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Http extends Thread{
    /** 
     * 手机号段归属地查询 
     *  
     * @param phoneSec 手机号段 
     */  
	private String number;
	private Handler mHandler;
	void setHandler(Handler h){
		mHandler = h;
	}
	public Http(String number){
		this.number = number;
	}
	public void run(){
		getRemoteInfo(number);
	}
    public void getRemoteInfo(String phoneSec) {  
        // 定义待请求的URL  
        String requestUrl = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";  
        // 创建HttpClient实例  
        HttpClient client = new DefaultHttpClient();  
        // 根据URL创建HttpPost实例  
        HttpPost post = new HttpPost(requestUrl);  
  
        List<NameValuePair> params = new ArrayList<NameValuePair>();  
        // 设置需要传递的参数  
        params.add(new BasicNameValuePair("mobileCode", phoneSec));  
        params.add(new BasicNameValuePair("userId", ""));  
        try {  
            // 设置URL编码  
            post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));  
            // 发送请求并获取反馈  
            HttpResponse response = client.execute(post);  
  
            // 判断请求是否成功处理  
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                // 解析返回的内容  
                String result = EntityUtils.toString(response.getEntity());  
                // 将查询结果经过解析后显示在TextView中  
             String strResult = filterHtml(result); 
             System.out.println(strResult);
             Message msg = new Message();
		     msg.what =1;  //设置一个状态，处理消息时用
		     Bundle bundle = new Bundle();  
             bundle.putString("str", strResult);  
		     msg.setData(bundle); //这就是你要传送的字符串
		     mHandler.sendMessage(msg);
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 使用正则表达式过滤HTML标记 
     *  
     * @param source 待过滤内容 
     * @return 
     */  
    private String filterHtml(String source) {  
        if(null == source){  
            return "";  
        }  
        return source.replaceAll("</?[^>]+>","").trim();  
    }  
}
