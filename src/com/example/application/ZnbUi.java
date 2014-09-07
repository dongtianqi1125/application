package com.example.application;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ZnbUi extends Activity{
	private static final int GO_HOME= 1000;
	private static final long DE_LAY= 3000;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				GO_HOME();
				break;
			}
			super.handleMessage(msg);
		}
	};
	  public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState); 
		   setContentView(R.layout.flash);
		   init();
		  
	  }
	  public void init(){
		  handler.sendEmptyMessageDelayed(GO_HOME, DE_LAY);
	  }
	  public void GO_HOME(){
		  ZnbUi.this.startActivity(new Intent(ZnbUi.this, MainActivity.class));  
		  ZnbUi.this.overridePendingTransition(R.anim.fade, R.anim.hold); 
		  ZnbUi.this.finish();
	  }
}
