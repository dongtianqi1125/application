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
	final int DIALOG_TIME = 0; //���öԻ���id

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
showDialog(DIALOG_TIME);//��ʾʱ��ѡ��Ի���
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
Calendar c=Calendar.getInstance();//��ȡ���ڶ��� 
long firstTime = SystemClock.elapsedRealtime(); // ����֮�����ڵ�����ʱ��(����˯��ʱ��)  
long systemTime = System.currentTimeMillis();
c.setTimeInMillis(System.currentTimeMillis()); //����Calendar����
c.set(Calendar.HOUR, hourOfDay); //��������Сʱ��
c.set(Calendar.MINUTE, minute); //�������ӵķ�����
c.set(Calendar.SECOND, 0); //�������ӵ�����
c.set(Calendar.MILLISECOND, 0); //�������ӵĺ�����
textView.setText(hourOfDay+"ʱ"+minute+"��");
long selectTime = c.getTimeInMillis(); 
System.out.println(selectTime+"==");
//�����ǰʱ��������õ�ʱ�䣬��ô�ʹӵڶ�����趨ʱ�俪ʼ  
if(systemTime > selectTime) {  
Toast.makeText(NaozhongActivity.this,"���õ�ʱ��С�ڵ�ǰʱ��", Toast.LENGTH_SHORT).show();  
c.add(Calendar.DAY_OF_MONTH, 1);  
selectTime = c.getTimeInMillis();  
}  
Intent intent = new Intent(NaozhongActivity.this, AlarmReceiver.class); //����Intent����
PendingIntent pi = PendingIntent.getBroadcast(NaozhongActivity.this, 0, intent, 0); //����PendingIntent
long time = selectTime - systemTime;  
firstTime += time;  
alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, DAY, pi);
//alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi); //��������
//alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi); //�������ӣ���ǰʱ��ͻ���
Toast.makeText(NaozhongActivity.this, "�������óɹ�", Toast.LENGTH_LONG).show();//��ʾ�û�
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
