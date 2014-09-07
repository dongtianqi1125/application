package com.example.application;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Phone extends Activity { 
	    private EditText phoneSecEditText;  
	    private TextView resultView;  
	    private Button queryButton;  
	  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_phone);  
	  
	        phoneSecEditText = (EditText) findViewById(R.id.phone_sec);  
	        resultView = (TextView) findViewById(R.id.result_text);  
	        queryButton = (Button) findViewById(R.id.query_btn);  
	        queryButton.setOnClickListener(new  OnClickListener(){

				public void onClick(View v) {
					// TODO Auto-generated method stub
					 String phoneSec = phoneSecEditText.getText().toString().trim();  
		                // 简单判断用户输入的手机号码（段）是否合法  
		                if ("".equals(phoneSec) || phoneSec.length() < 7) {  
		                    // 给出错误提示  
		                    phoneSecEditText.setError("您输入的手机号码（段）有误！");  
		                    phoneSecEditText.requestFocus();  
		                    // 将显示查询结果的TextView清空  
		                    resultView.setText("");  
		                    return;  
		                }  
		                // 查询手机号码（段）信息  
		                Http ht = new Http(phoneSec);
		                ht.setHandler(handler);
		                ht.start();
				}
	        	
	        });
	       
	    }  
	    Handler handler = new Handler(){   
	       	 public void handleMessage(Message msg) {          
	       	  if (msg.what == 1) {// 动态更新UI界面    	               
	       	 String str = msg.getData().getString("str"); 
	       	 resultView.setText(str);
	       	    }      	       
	       	 };  	    
	       	};  
	       	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
   
}
