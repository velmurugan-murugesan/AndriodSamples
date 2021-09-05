package com.example.snewapp;


import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

public class Constants {

	private static String userName;
	public static int TIME_DELAY = 1000;
	
	
	public void moveAndFinishAcitivity(Context context,Class<?> nextClass){
		Intent intent = new Intent(context,nextClass);
		context.startActivity(intent);
	}
	
	
	public String getDate() {
		 Date date = new Date(System.currentTimeMillis());
		    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		   return formatter.format(date);
	}
	
	
	public void setUser(String user) {
		userName = user;
	}
	
	public String getUser() {
		return userName;
	}
	
	
	public String getBaseURL() {
		return "http://www.mya2zlearnings.com/dailyselfie/webservice/";
	}
	
	
	
	
	public final boolean isInternetOn(Context context) {

		// get Connectivity Manager object to check connection
		ConnectivityManager connec =  
				(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

		// Check for network connections
		if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
				connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
				connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
				connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
			Log.d("Internet check = "," Internet connected ");
			return true;

		} else if ( 
				connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
				connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
			Log.d("Internet check = ", " Internet not connected ");
			return false;
		}
		return false;
	}
	
}
