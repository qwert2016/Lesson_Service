package com.example.lesson_service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.example.lesson_service.MyCustomService.MyBinder;

public class MainActivity extends Activity {

	MyBinder myBinder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void startService(View v) {
		Intent service = new Intent();
		service.setClass(this, MyCustomService.class);
		startService(service);
	}

	public void stopService(View v) {
		Intent service = new Intent();
		service.setClass(this, MyCustomService.class);
		stopService(service);
	}

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("ServiceConnection.onServiceDisconnected");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("ServiceConnection.onServiceConnected");
			myBinder = (MyBinder) service;
		}
	};

	public void bindService(View v) {
		Intent service = new Intent();
		service.setClass(this, MyCustomService.class);
		bindService(service, conn, Context.BIND_AUTO_CREATE);
	}

	public void unbindService(View v) {
		Intent service = new Intent();
		service.setClass(this, MyCustomService.class);
		unbindService(conn);
	}

	public void serviceWork(View v) {
		System.out.println("MainActivity.serviceWork()" + myBinder.sum(12, 28));
	}
	
	
	/**
	 * 
	 * @param v
	 */
	public void IntentServiceDemo(View v) {
		startService(new Intent(this,MyIntentService.class));
//		stopService(new Intent(this,MyIntentService.class));
	}
	/**
	 *启动另辟进程的Service 
	 */
	public void OpenProcessDemo(View v) {
		startService(new Intent(this,OtherProcessService.class));
	}

	@Override
	protected void onDestroy() {
		unbindService(conn);
		super.onDestroy();
	}

}
