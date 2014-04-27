package com.example.bespiel;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ShellService extends Service {
	

	private int locVar = 0;
	private List<Integer> ll = new ArrayList<Integer>();
	
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
		ll.add(locVar);
	
		String jstring = makeJsonFromFunky(4,"funky",6);
		Intent i = new Intent(Constants.fooBar);
		i.putExtra("ctr", locVar);
		i.putExtra("json", jstring);
		Log.i("foo",jstring);
		sendBroadcast(i);
		return Service.START_STICKY;
	}
	
	private String makeJsonFromFunky(int i, String s, int size) {
		List<FunkyRecord> frl = new ArrayList<FunkyRecord>();
		for (int ctr=0;  ctr<size;ctr+=1) {
			frl.add(new FunkyRecord(ctr,s,size));
		}
		return frl.toString();
	}
	
	
	
}
