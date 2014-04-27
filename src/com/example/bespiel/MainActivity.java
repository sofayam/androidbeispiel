package com.example.bespiel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;


import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {


	private TextView tv;
	private Activity me;

	private BroadcastReceiver bReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(Constants.fooBar)) {
			//String serviceJsonString = intent.getExtra("json");
			//Do something with the string
				Log.i("foo","received intent foo_bar back in activity");
				Integer ctr = (Integer) intent.getExtras().get("ctr");
				sayit("hacked " + ctr.toString() + "\n");
				String funky = (String)intent.getExtras().get("json");
				List<FunkyRecord> ll = (ArrayList<FunkyRecord>)fromString(funky); 
				sayit("funky " + funky);
				
			}

		}
	};

	   private static Object fromString( String s ) throws IOException ,
       ClassNotFoundException {
byte [] data = Base64Coder.decode( s );
ObjectInputStream ois = new ObjectInputStream( 
new ByteArrayInputStream(  data ) );
Object o  = ois.readObject();
ois.close();
return o;
}
	
	protected void onResume() {
		Log.i("foo","!!!resuming");
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.fooBar);
		registerReceiver(bReceiver, filter);
		super.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupInterface();
		me = this;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	};


	private void setupInterface () {
		tv = (TextView) findViewById(R.id.bucket);
		tv.setMovementMethod(new ScrollingMovementMethod());
		tv.setText("Anfang");
		Button clickButton = (Button) findViewById(R.id.clickButton);
		clickButton.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("foo","clicked!!!");

				sayit("clacked");
			}
		});
		Button startServiceButton = (Button) findViewById(R.id.startServiceButton);
		startServiceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("foo", "starting Service");
				Intent intent = new Intent(me,ShellService.class);
				startService(intent);

			}
		});

/*        
		Log.i("foo", "Setting up breceiver" );
		LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Constants.fooBar);
		bManager.registerReceiver(bReceiver, intentFilter);*/

	}

	private void sayit(String s) { tv.append(s + "\n"); }
}
