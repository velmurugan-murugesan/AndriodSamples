package com.example.snewapp;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity{
	
	private Context context_;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		context_ = this;
		init();
	}
	
	private void init() {
		splashLoad();
	}
	
	private void splashLoad(){
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if (AppConfig.getInstance().isInternetOn(context_)) {
					AppConfig.getInstance().moveAndFinishAcitivity(context_, MainActivity.class);
					finish();
				} else {
					
				}

			}
		}, Constants.TIME_DELAY);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	

}