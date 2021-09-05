package com.example.pvr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	private Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		btn = (Button)findViewById(R.id.button1);
		
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				new DownloadWebPageTask().execute();
				
				
			}
		});
	
	}
	
	
	private class DownloadWebPageTask extends AsyncTask<Void, Void, Void>{

		String url = "";
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			
			
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try  
	        {  
	        HttpClient httpClient=new DefaultHttpClient();  
	        HttpPost httpPost=new HttpPost("http://www.pvrcinemas.com/dwr/call/plaincall/dwrService.getAddSeats.dwr");  
	          
	        List<NameValuePair> list=new ArrayList<NameValuePair>();  
	        list.add(new BasicNameValuePair("callCount", "1"));  
	        list.add(new BasicNameValuePair("c0-scriptName","dwrService"));  
	        list.add(new BasicNameValuePair("c0-methodName", "getAddSeats"));  
	        list.add(new BasicNameValuePair("c0-id","0"));  
	        list.add(new BasicNameValuePair("c0-param0", "string:HO00009220"));  
	        list.add(new BasicNameValuePair("c0-param1","string:VELA"));  
	        list.add(new BasicNameValuePair("c0-param2", "string:2078"));  
	        list.add(new BasicNameValuePair("c0-param3","string:1457877600000"));  
	        list.add(new BasicNameValuePair("c0-param4", "string:AUDI%2001"));  
	        list.add(new BasicNameValuePair("c0-param5","string:0002"));  
	        
	        list.add(new BasicNameValuePair("c0-param6", "string:0000000001"));  
	        list.add(new BasicNameValuePair("c0-param7","string:0002"));  
	        list.add(new BasicNameValuePair("c0-param8", "number:1"));  
	        list.add(new BasicNameValuePair("batchId","3"));
	        
	        list.add(new BasicNameValuePair("instanceId", "0"));  
	        list.add(new BasicNameValuePair("page","%2F"));  
	        list.add(new BasicNameValuePair("scriptSessionId", "w$*TX1JOyvsoovIG8kmidixFLdl/ZgbQLdl-E84GN0eer")); 
	        
	        
	        httpPost.setEntity(new UrlEncodedFormEntity(list));  
	        HttpResponse httpResponse=  httpClient.execute(httpPost);  
	      
	        HttpEntity httpEntity=httpResponse.getEntity();  
	        String res = httpResponse.toString();  
	    
	        Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
	        }  
	        catch(Exception exception)  {}  
	      
			
			
			 return null;
		}
		
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
		
	}
		
}

