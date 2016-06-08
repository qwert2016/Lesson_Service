package com.example.lesson_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyCustomService extends Service {
	
	public MyCustomService() {
	}

	@Override
	public void onCreate() {// 创建-只执行一次
		System.out.println("MyCustomService.onCreate()"+Thread.currentThread().getName());
		super.onCreate();
	}

	@Override
	public void onDestroy() {// 销毁
		System.out.println("MyCustomService.onDestroy()");
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {// 每次调用startService的时候调用
		System.out.println("MyCustomService.onStartCommand()"+Thread.currentThread().getName());
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {// 以bindService的方式启动的时候调用
		System.out.println("MyCustomService.onBind()"+Thread.currentThread().getName());
		return new MyBinder();
	}

	class MyBinder extends Binder {

		int sum(int a, int b) {
			return (a + b);
		}

	}

	@Override
	public boolean onUnbind(Intent intent) {// 以bindService启动，unbind方式关闭服务时调用
		System.out.println("MyCustomService.onUnbind()");
		return super.onUnbind(intent);
	}

}
