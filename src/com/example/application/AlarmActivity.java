package com.example.application;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;

public class AlarmActivity extends Activity implements OnCompletionListener {
	private MediaPlayer mediaPlayer;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	mediaPlayer = MediaPlayer.create(this, R.raw.ls); 
	mediaPlayer.setOnCompletionListener(this);
	if(mediaPlayer!=null){
		mediaPlayer.stop();
	}
	try {
		mediaPlayer.prepare();
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//System.out.println("直接上马");
	mediaPlayer.start();
	//显示对话框
	new AlertDialog.Builder(AlarmActivity.this).
	setTitle("闹钟").//设置标题
	setMessage("这么快就到了！").//设置内容
	setPositiveButton("吵你妹",new OnClickListener(){//设置按钮
		public void onClick(DialogInterface dialog, int which) {
			if(mediaPlayer!=null){
				mediaPlayer.stop();
			}		
			AlarmActivity.this.finish();//关闭Activity
			}
			}).create().show();
	}

	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		mp.release();
		setTitle("资源已经释放啦");
	}
}
