package com.gongoliers.coffey.herb;

import com.gongoliers.coffey.herb.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Gongolierium extends Activity {

	GameView view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new GameView(this);
		setContentView(view);
	}
	
	public void onPause() {
		super.onPause();
		view.stop();
	}
	
	public void onStop() {
		super.onStop();
		view.stop();
	}
	
	public void onDestroy() {
		super.onDestroy();
		view.stop();
	}
	
	public void onResume() {
		super.onResume();
		view = new GameView(this);
		setContentView(view);
	}
	
	public void onRestart() {
		super.onRestart();
		view = new GameView(this);
		setContentView(view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gongolierium, menu);
		return true;
	}

}
