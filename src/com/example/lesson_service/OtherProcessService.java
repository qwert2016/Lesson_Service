package com.example.lesson_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class OtherProcessService extends Service {
	public OtherProcessService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
