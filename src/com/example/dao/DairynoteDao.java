package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.db.Dairynote;
import com.example.domin.Dnote;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DairynoteDao {
    private Dairynote dairy;
	public DairynoteDao(Context context){
		dairy = new Dairynote(context);
	}
	public void insert(String name,String context,String time){
		SQLiteDatabase db = dairy.getWritableDatabase();
		db.execSQL("insert into user (name,context,time) values(?,?,?)", new Object[]{name,context,time});
		System.out.println("+++插入成功+++还不确定");
		db.close();
	}
	
	public boolean find(String name){
		SQLiteDatabase db = dairy.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from user where name=?", new String[]{name});
		boolean result = cursor.moveToNext();
		cursor.close();
		db.close();
		System.out.println(result+"为ture貌似插入成功啦");
		return result;
	}
	
	public void update(String name,String time){
		SQLiteDatabase db = dairy.getWritableDatabase();
		db.execSQL("update user set name=? where", new Object[]{});
	    db.close();
	}
	
	public Dnote findone(String name){
		SQLiteDatabase db = dairy.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from user where name=?", new String[]{name});
		Dnote note = null;
	    while(cursor.moveToNext()){
	    	String namea = cursor.getString(cursor.getColumnIndex("name"));
	    	String context = cursor.getString(cursor.getColumnIndex("context"));
	    	String time = cursor.getString(cursor.getColumnIndex("time"));
	    	note = new Dnote(namea,context,time);
	    }
	    cursor.close();
	    db.close();
		return note;
	}
	public List<Dnote> findAll(){
		SQLiteDatabase db = dairy.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from user ", null);
		List<Dnote> dnotes = new ArrayList<Dnote>();
		  while(cursor.moveToNext()){
		 String namea = cursor.getString(cursor.getColumnIndex("name"));
		 String context = cursor.getString(cursor.getColumnIndex("context"));
		 String time = cursor.getString(cursor.getColumnIndex("time"));
		 Dnote note = new Dnote(namea,context,time);
		 dnotes.add(note);
		  }
		  cursor.close();
		  db.close();
		return dnotes;
	}
	public void delete(String name){
		SQLiteDatabase db = dairy.getReadableDatabase();
		db.execSQL("delete from user where name =?", new Object[]{name});
		db.close();
	}
}
