package com.example.application;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Orien extends Activity {
	private ImageView imageView;
	private SensorManager manager;
	private SensorListener listener = new SensorListener();
	private TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orien);
        textView = (TextView )findViewById(R.id.textView);
        imageView = (ImageView) this.findViewById(R.id.imageView);
        imageView.setKeepScreenOn(true);
        textView.setText("温馨提示，当你看到这个可能你手机不支持方向传感器或者你root过！");
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

	@Override
	protected void onResume() {
		Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
		super.onResume();
	}

	@Override
	protected void onPause() {
		manager.unregisterListener(listener);
		super.onPause();
	}
	
	private final class SensorListener implements SensorEventListener{
		private float predegree = 0;
		public void onSensorChanged(SensorEvent event) {
			float degree = event.values[0];//存放了方向值 90
			textView.setText(degree+"度");
			RotateAnimation animation = new RotateAnimation(predegree, -degree, 
					Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setDuration(200);
			imageView.startAnimation(animation);
			predegree = -degree;
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	}
    
    
}