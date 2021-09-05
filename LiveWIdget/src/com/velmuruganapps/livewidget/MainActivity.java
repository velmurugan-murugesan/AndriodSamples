package com.velmuruganapps.livewidget;

import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;

public class MainActivity extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		// initializing widget layout
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
		Constants.responseModel = new ArrayList<ResponseModel>();
		new MyTask().execute();
		// register for button event
		remoteViews.setOnClickPendingIntent(R.id.btnNext,
				buildButtonPendingIntent(context));
		
		
		if(Constants.responseModel.size() != 0){
			remoteViews.setTextViewText(R.id.title, Constants.responseModel.get(0).getTitle());
			remoteViews.setTextViewText(R.id.desc, Constants.responseModel.get(0).getDescription());
		} else {
			new MyTask().execute();
		}

		// request for widget update
		pushWidgetUpdate(context, remoteViews);
	}

	public static PendingIntent buildButtonPendingIntent(Context context) {

		// initiate widget update request
		Intent intent = new Intent();
		intent.setAction(Constants.WIDGET_UPDATE_ACTION);
		return PendingIntent.getBroadcast(context, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
	}


	public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
		ComponentName myWidget = new ComponentName(context,
				MainActivity.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(myWidget, remoteViews);
	}
	
	
	
	
	
	
	private class MyTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			
			
		}

		@Override
		protected String doInBackground(String... params) {

			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpGet httpGet = new HttpGet("https://www.hackerearth.com/api/events/upcoming/?format=json");
			try {
			HttpResponse response = httpClient.execute(httpGet, localContext);


			 String result = EntityUtils.toString(response.getEntity());

	            // CONVERT RESPONSE STRING TO JSON ARRAY
	            
	            JSONObject obj = new JSONObject(result);
	            
	            JSONArray arr = obj.getJSONArray("response");
	            
	            
	            
	            int n = arr.length();
	            for (int i = 0; i < n; i++) {
	                // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
	            	JSONObject jo = arr.getJSONObject(i);

	            	ResponseModel res = new ResponseModel();
	                // RETRIEVE EACH JSON OBJECT'S FIELDS
	                res.setStatus(jo.getString("status"));
	                res.setChallengeType(jo.getString("challenge_type"));
	                res.setStartTimestamp(jo.getString("start_timestamp"));
	                res.setDescription(jo.getString("description"));
	                res.setEndDate(jo.getString("end_date"));
	                res.setTitle(jo.getString("title"));
	                res.setUrl(jo.getString("url"));
	                res.setIsHackerearth(jo.getBoolean("is_hackerearth"));
	                res.setEnd_tz(jo.getString("end_tz"));
	                res.setEnd_utc_tz(jo.getString("end_utc_tz"));
	                res.setSubscribe(new URL(jo.getString("subscribe")));
	                res.setCollege(jo.getBoolean("college"));
	                res.setEndTime(jo.getString("end_time"));
	                res.setTime(jo.getString("time"));
	                res.setDate(jo.getString("date"));
	                res.setStart_utc_tz(jo.getString("start_utc_tz"));
	                res.setStart_tz(jo.getString("start_tz"));
	                res.setEnd_timestamp(jo.getString("end_timestamp"));
	                if(jo.has("cover_image")){
		                res.setCoverImage(jo.getString("cover_image"));
	                } else {
	                	res.setCoverImage("");
	                }
	                Constants.responseModel.add(res);
	                
	                // CONVERT DATA FIELDS TO CLUB OBJECT
	                //Club c = new Club(id, name, address, country, zip, clat, clon, url, number);
	                //ret.add(c);
	            }
	            
			//text = getASCIIContentFromEntity(entity);

				return null;
			} catch (Exception e) {
			return e.getLocalizedMessage();
			}
			
		}

		protected void onPostExecute(String result) {
		
		Log.d("Click count", "0");
		Log.d("main Title = ", Constants.responseModel.get(0).getTitle());
		Log.d("main Description = ", Constants.responseModel.get(0).getDescription());
		
		}
	
	
}
}
