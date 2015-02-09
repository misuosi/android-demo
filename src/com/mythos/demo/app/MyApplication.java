package com.mythos.demo.app;

import org.apache.mina.core.session.IoSession;

import android.app.Application;


public class MyApplication extends Application {

	public IoSession session;
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
}
