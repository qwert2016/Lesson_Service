package com.example.lesson_service;

import com.example.lesson_service.aidl.ICalculator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;


public class MyAidlService extends Service {
	public MyAidlService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}

	class MyBinder extends ICalculator.Stub {

		@Override
		public int sum(int a, int b) throws RemoteException {
			System.out.println("MyAidlService.MyBinder.sum()"+android.os.Process.myPid());
			return (a + b);
		}

		@Override
		public int mul(int a, int b) throws RemoteException {
			System.out.println("MyAidlService.MyBinder.mul()"+android.os.Process.myPid());
			return a * b;
		}

	}
}
