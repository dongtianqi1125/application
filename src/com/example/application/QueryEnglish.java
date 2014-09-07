package com.example.application;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QueryEnglish extends Activity implements OnClickListener{
	 private EditText name, zkzh; 
	 private Thread clockThread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);
        name = (EditText) this.findViewById(R.id.xm); 
        zkzh = (EditText) this.findViewById(R.id.zkzh); 
        Button btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }  
    Handler handler = new Handler() {  
        public void handleMessage(Message msg) {  
            if (msg.what == 1) {  
                // 动态更新UI界面  
                String str = msg.getData().getString("str");     
            	Intent it = new Intent();
    			it.setClass( QueryEnglish.this, Show.class);
    			it.putExtra("jg", str);
    			startActivity(it);
            }  
        };  
    };  
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnSubmit:{
			String zkzhStr = zkzh.getText().toString();
			String xmStr = name.getText().toString();
			if(xmStr.equals("znb")){
				xmStr="张能波";
			}
			  HttpEnglish a = new HttpEnglish(zkzhStr,xmStr,handler);
			clockThread = new Thread(a);
			 clockThread.start(); /* 启动线程 */	
		            }  
		} 
	 
}}