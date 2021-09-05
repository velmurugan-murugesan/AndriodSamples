package com.example.snewapp;


import android.app.Application;

public class AppConfig extends Application{

	private static Constants constants_;
	
	public static Constants getInstance() {
		
		if (constants_ == null) {
			constants_ = new Constants();
		}
		return constants_;
	}
	

}
