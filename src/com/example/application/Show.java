package com.example.application;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Show extends Activity{
	private TextView gResult;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_grade);
        gResult = (TextView) findViewById(R.id.gradeResult);
        String res = getIntent().getStringExtra("jg");
      //  System.out.println("�ֵ�����ô");    
        gResult.setText("�ļ��ɼ���ϸ���£�"+res);          
       // System.out.println("�����ж೤��"+res.length());
       // b.replaceAll(" ", "");
    }
}
