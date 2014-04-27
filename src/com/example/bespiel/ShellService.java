package com.example.bespiel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ShellService extends Service {
	

	private int locVar = 0;
	
	public ShellService() {
	}

	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		// throw new UnsupportedOperationException("Not yet implemented");
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		locVar +=1 ;
		Log.i("foo","started service with locVar: " + Integer.toString(locVar));

		return Service.START_STICKY;
	}
	
}
