package com.example.lesson_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.lesson_service.aidl.IMedia;

public class MediaService extends Service {
	public MediaService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new MediaBinder();
	}

	MediaPlayer mediaPlayer;

	@Override
	public void onCreate() {
		mediaPlayer = MediaPlayer.create(this, R.raw.mo);
		super.onCreate();
	}

	class MediaBinder extends IMedia.Stub {

		@Override
		public void play() throws RemoteException {
			mediaPlayer.start();
		}

	}
}
