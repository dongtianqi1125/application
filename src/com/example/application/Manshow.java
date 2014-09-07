package com.example.application;
import android.app.Activity;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
public class Manshow extends Activity implements OnTouchListener, 
android.view.GestureDetector.OnGestureListener{
   
 //创建一个识别手势的GestureDetector  的对象
 private GestureDetector gestureDetector = new GestureDetector(this);
 //定义一个数组，用于存放照片
 int[] image = new int[]{R.drawable.man405,R.drawable.manshow,R.drawable.yl};
 private  int i;
 private ImageView lv;
 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manshow);
       
        lv = (ImageView) findViewById(R.id.image);
        lv.setImageResource(image[i]);
        lv.setOnTouchListener(this);
       lv.setLongClickable(true);
        gestureDetector.setIsLongpressEnabled(true);
       
    }
 
 
 //切换下一个图片的方法
 public void  goNext(){
  i++;
  i = Math.abs(i%image.length);
  lv.setImageResource(image[i]);
 }
 
 //切换上一个图片
 public void goPrevious(){
  i--;
  i = Math.abs(i%image.length);
  lv.setImageResource(image[i]);
 }
 
 
 public boolean onTouch(View v, MotionEvent event) {
  // TODO Auto-generated method stub
  gestureDetector.onTouchEvent(event);
  return true;
 }
 public boolean onDown(MotionEvent e) {
  // TODO Auto-generated method stub
  return false;
 }
 
 //在抛掷动作时被调用
 public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
   float velocityY) {
  // TODO Auto-generated method stub
  if(velocityX<0){
   goNext();
  }else if(velocityX>0){
   goPrevious();
  }
  return false;
 }
 public void onLongPress(MotionEvent e) {
  // TODO Auto-generated method stub
  
 }
 public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
   float distanceY) {
  // TODO Auto-generated method stub
//  if(distanceX<0){
//   goNext();
//  }else if(distanceX>0){
//   goPrevious();
//  }
  return false;
 }
 public void onShowPress(MotionEvent e) {
  // TODO Auto-generated method stub
  
 }
 public boolean onSingleTapUp(MotionEvent e) {
  // TODO Auto-generated method stub
  return false;
 }
}
