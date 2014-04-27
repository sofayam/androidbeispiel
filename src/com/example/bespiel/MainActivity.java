package com.example.bespiel;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
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
	private String fooBar = "com.example.beispiel.FOO_BAR";
	private BroadcastReceiver bReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			//if(intent.getAction().equals("com.example.beispiel.FOO_BAR")) {
			//String serviceJsonString = intent.getExtra("json");
			//Do something with the string
			Log.i("foo","received intent foo_bar");

		}
	};

	protected void onResume() {
		Log.i("foo","!!!resuming");
		IntentFilter filter = new IntentFilter();
		filter.addAction(fooBar);
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


		LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("com.example.beispiel.FOO_BAR");
		bManager.registerReceiver(bReceiver, intentFilter);

	}

	private void sayit(String s) { tv.append(s + "\n"); }
}
