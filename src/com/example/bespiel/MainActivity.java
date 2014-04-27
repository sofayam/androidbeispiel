package com.example.bespiel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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

	}

	private void sayit(String s) { tv.append(s + "\n"); }
}
