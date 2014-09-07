package com.example.application;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {
      TabHost tabHost;
      ImageButton imageBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }
    private void Init(){
        imageBtn = (ImageButton) findViewById(R.id.buttonman);
        tabHost = (TabHost) this.findViewById(R.id.tabhost);
        tabHost.setup();
        
        TabSpec tabSpec = tabHost.newTabSpec("page1");
        tabSpec.setIndicator("生活小助手",getResources().getDrawable(R.drawable.shzs));
        tabSpec.setContent(R.id.page1);
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec("page2");
        tabSpec.setIndicator("娱乐",getResources().getDrawable(R.drawable.yl));
        tabSpec.setContent(R.id.page2);
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec("page3");
        tabSpec.setIndicator("405真男人",getResources().getDrawable(R.drawable.man405));
        tabSpec.setContent(R.id.page3);
        tabHost.addTab(tabSpec);
        
        tabHost.setCurrentTab(0);
    }
   public void buttonman(View view){
	  Intent it = new Intent(MainActivity.this,Manshow.class);
	  startActivity(it);
	//  finish();以后统一界面再加上去
   }
   public void queryEnglish(View view){
	   Intent it = new Intent(MainActivity.this,QueryEnglish.class);
	   startActivity(it);
   }
   public void queryPhone(View view){
	   Intent it = new Intent(MainActivity.this,Phone.class);
	   startActivity(it);
   }
   public void clock(View view){
	   Intent it = new Intent(MainActivity.this,NaozhongActivity.class);
	   startActivity(it);
   }
   public void privateDaynote(View view){
	   Intent it = new Intent(MainActivity.this,RijiActivity.class);
	   startActivity(it);
   }
   public void leadDirection(View view){
	   Intent it = new Intent(MainActivity.this,Orien.class);
	   startActivity(it);
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
