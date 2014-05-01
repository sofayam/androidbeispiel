package com.example.bespiel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
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
	public void onCreate(){
		Log.i("foo","created service");
	}
	public void onDestroy(){
		Log.i("foo","destroyed service");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Bundle extras = intent.getExtras();
		boolean gothread = false;
		if (extras != null) {
			gothread = extras.getBoolean("gothread", false);
		}
		if (gothread) {
			Log.i("foo","doing nothing yet");
			// do crazy threadstarting stuff
		} else { 

			locVar +=1 ;
			Log.i("foo","started service with locVar: " + Integer.toString(locVar));
			ll.add(locVar);

			String jstring = makeJsonFromFunky(4,"funky",6);
			Intent i = new Intent(Constants.fooBar);
			String tname = Thread.currentThread().getName();
			NestedMap nm = new NestedMap();
			nm.putNested("d:e:f:g","whoops");
			FunkyRecord fr = new FunkyRecord(2,"foo",3);
			i.putExtra("ctr", locVar);
			i.putExtra("json", jstring);
			i.putExtra("thread", tname);
			i.putExtra("nestedMap", nm);
			i.putExtra("funkySerial", fr);

			Log.i("foo",jstring);
			sendBroadcast(i);


			
		}
		return Service.START_STICKY;
	}

	private String makeJsonFromFunky(int i, String s, int size) {
		Gson gson = new Gson();

		List<FunkyRecord> frl = new ArrayList<FunkyRecord>();
		for (int ctr=0;  ctr<size;ctr+=1) {
			frl.add(new FunkyRecord(ctr,s,size));
		}
		String jstring = gson.toJson(frl);
		return jstring;
	}
	
	
	
}
