package com.example.domin;

public class Dnote {
private String title;
private String context;
private String time;
public Dnote(){}
public Dnote(String name, String context, String time) {
	// TODO Auto-generated constructor stub
	this.title = name;
	this.context = context;
	this.time = time;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContext() {
	return context;
}
public void setContext(String context) {
	this.context = context;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}

}
