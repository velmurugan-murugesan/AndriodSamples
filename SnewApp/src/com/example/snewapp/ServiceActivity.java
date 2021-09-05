package com.example.snewapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class ServiceActivity extends Activity {
	
	private ListView serviceListView;
	private Context context;
	private List<ServiceModel> serviceList;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_layout);
		context = this;
		serviceList =new ArrayList<ServiceModel>();

		serviceListView =(ListView)findViewById(R.id.serviceListview);
		 Bundle extras = getIntent().getExtras();  
		    String service = extras.getString("service");  
		    int position = extras.getInt("position");
		    
		 String[] subServiceList = ServiceList.getserviceMap().get(new Integer(position));   
		 
		 Toast.makeText(getApplicationContext(), subServiceList.length +"-- "+position , Toast.LENGTH_SHORT).show();
		
		 subService(subServiceList);
		 serviceListView.setAdapter(new MyAdapter(context,serviceList));
		 
	}
	
	private void subService(String[] subservice){
		
		for (int i = 0; i < subservice.length; i++) {
			ServiceModel model1 = new ServiceModel(subservice[i],R.drawable.ic_launcher);
			serviceList.add(model1);

			
		}
		
	}
	
	

}
