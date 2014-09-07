package com.example.application;

import com.example.dao.DairynoteDao;
import com.example.domin.Dnote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Displaynote extends Activity {
	private DairynoteDao dbDao = new DairynoteDao(Displaynote.this);
	private  TextView  eTitle; 
	private  TextView  eContext;
	private  TextView  eTime;
	private Dnote note ;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.display);
	        init();
	    }
	 private void init(){
		 eTitle = (TextView) findViewById(R.id.titledisplay);
		 eContext = (TextView) findViewById(R.id.contextdisplay);
		 Dnote note = display();
		 eTitle.setTextSize(20);
		 eTitle.setText("标题是："+note.getTitle());
		 eContext.setText("内容是："+note.getContext());
	 }
	 public Dnote display(){
		 String name = getIntent().getStringExtra("name");
		 note = dbDao.findone(name);
		return note;	 
	 }
	 public void delete(View view){
		 String name = note.getTitle();
		 System.out.println(name);
		 dbDao.delete(name);
		 Toast.makeText(this, "恭喜你！删除成功了！！！", 3).show();
		 Intent it = new Intent(Displaynote.this,RijiActivity.class);
		 startActivity(it);	 
		 finish();
	 }
	 
}
