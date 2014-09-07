package com.example.application;


import java.io.IOException;
import java.util.Calendar;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class NaozhongActivity extends Activity{
	protected static final long DAY = 86400000;
	private TextView textView = null;
	private Button btn = null;
	private AlarmManager alarmManager = null;
	Calendar cal=Calendar.getInstance();
	final int DIALOG_TIME = 0; //设置对话框id

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_naozhong);

alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
btn=(Button)findViewById(R.id.btn);
textView= (TextView)findViewById(R.id.timeview);
System.out.println("---------------------");
btn.setOnClickListener(new View.OnClickListener(){
public void onClick(View view) {
showDialog(DIALOG_TIME);//显示时间选择对话框
}
});

}

@Override
protected Dialog onCreateDialog(int id) {
Dialog dialog=null;

switch (id) {
case DIALOG_TIME:
dialog=new TimePickerDialog(
this,
new TimePickerDialog.OnTimeSetListener(){
public void onTimeSet(TimePicker timePicker, int hourOfDay,int minute) {
Calendar c=Calendar.getInstance();//获取日期对象 
long firstTime = SystemClock.elapsedRealtime(); // 开机之后到现在的运行时间(包括睡眠时间)  
long systemTime = System.currentTimeMillis();
c.setTimeInMillis(System.currentTimeMillis()); //设置Calendar对象
c.set(Calendar.HOUR, hourOfDay); //设置闹钟小时数
c.set(Calendar.MINUTE, minute); //设置闹钟的分钟数
c.set(Calendar.SECOND, 0); //设置闹钟的秒数
c.set(Calendar.MILLISECOND, 0); //设置闹钟的毫秒数
textView.setText(hourOfDay+"时"+minute+"分");
long selectTime = c.getTimeInMillis(); 
System.out.println(selectTime+"==");
//如果当前时间大于设置的时间，那么就从第二天的设定时间开始  
if(systemTime > selectTime) {  
Toast.makeText(NaozhongActivity.this,"设置的时间小于当前时间", Toast.LENGTH_SHORT).show();  
c.add(Calendar.DAY_OF_MONTH, 1);  
selectTime = c.getTimeInMillis();  
}  
Intent intent = new Intent(NaozhongActivity.this, AlarmReceiver.class); //创建Intent对象
PendingIntent pi = PendingIntent.getBroadcast(NaozhongActivity.this, 0, intent, 0); //创建PendingIntent
long time = selectTime - systemTime;  
firstTime += time;  
alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, DAY, pi);
//alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi); //设置闹钟
//alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi); //设置闹钟，当前时间就唤醒
Toast.makeText(NaozhongActivity.this, "闹钟设置成功", Toast.LENGTH_LONG).show();//提示用户
}
},
cal.get(Calendar.HOUR_OF_DAY),
cal.get(Calendar.MINUTE),
true);

break;
}
return dialog;
}
}
