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
     * �ֻ��Ŷι����ز�ѯ 
     *  
     * @param phoneSec �ֻ��Ŷ� 
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
        // ����������URL  
        String requestUrl = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";  
        // ����HttpClientʵ��  
        HttpClient client = new DefaultHttpClient();  
        // ����URL����HttpPostʵ��  
        HttpPost post = new HttpPost(requestUrl);  
  
        List<NameValuePair> params = new ArrayList<NameValuePair>();  
        // ������Ҫ���ݵĲ���  
        params.add(new BasicNameValuePair("mobileCode", phoneSec));  
        params.add(new BasicNameValuePair("userId", ""));  
        try {  
            // ����URL����  
            post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));  
            // �������󲢻�ȡ����  
            HttpResponse response = client.execute(post);  
  
            // �ж������Ƿ�ɹ�����  
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                // �������ص�����  
                String result = EntityUtils.toString(response.getEntity());  
                // ����ѯ���������������ʾ��TextView��  
             String strResult = filterHtml(result); 
             System.out.println(strResult);
             Message msg = new Message();
		     msg.what =1;  //����һ��״̬��������Ϣʱ��
		     Bundle bundle = new Bundle();  
             bundle.putString("str", strResult);  
		     msg.setData(bundle); //�������Ҫ���͵��ַ���
		     mHandler.sendMessage(msg);
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * ʹ��������ʽ����HTML��� 
     *  
     * @param source ���������� 
     * @return 
     */  
    private String filterHtml(String source) {  
        if(null == source){  
            return "";  
        }  
        return source.replaceAll("</?[^>]+>","").trim();  
    }  
}
