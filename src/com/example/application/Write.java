package com.example.application;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.dao.DairynoteDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Write extends Activity {
	private DairynoteDao dbDao = new DairynoteDao(Write.this);
	private  EditText  editTitle; 
	private  EditText  editContext;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.write);
	        init();
	    }
	 private void init(){
		 editTitle = (EditText) findViewById(R.id.title);
		 editContext = (EditText) findViewById(R.id.context);
	 }
	 public void save(View view){
		 String name = editTitle.getText().toString();
		 String context = editContext.getText().toString();
		 Date date = new Date(System.currentTimeMillis());
		 DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,Locale.CHINA);
		 String dt = df.format(date);
		 System.out.println(dt);
		 dbDao.insert(name, context, dt);
		 Toast.makeText(this, "恭喜你！保存成功了！！！", 3).show();
		 Intent it = new Intent(Write.this,RijiActivity.class);
		 startActivity(it);	 
		 finish();
	 }
	 public void find(View view){
		 String name = editTitle.getText().toString();
		 System.out.println("我操你你妹的咋就这个不运行啊"+name);
		// dbDao.find(name);
		 Intent it = new Intent(Write.this,Displaynote.class);
		 it.putExtra("name", name);
		 startActivity(it);	 
	 }
}
