package com.example.snewapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	
	private ListView listView;
	private Context context;
	private List<ServiceModel> serviceList;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		context = this;
		listView = (ListView) findViewById(R.id.listView1);
		
		serviceList =new ArrayList<ServiceModel>();
		
		loadData();
		
        listView.setAdapter(new MyAdapter(context,serviceList));
        
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);  
				intent.putExtra("service", serviceList.get(position).getServiceName());  
				intent.putExtra("position", position);  
				startActivity(intent);
		          
		          
				
				
			}
		});
	}
	
	
	private void loadData(){
		ServiceModel model1 = new ServiceModel("Service 1",R.drawable.ic_launcher);
		
		ServiceModel model2 = new ServiceModel("Service 2",R.drawable.ic_launcher);
		ServiceModel model3 = new ServiceModel("Service 3",R.drawable.ic_launcher);
		ServiceModel model4 = new ServiceModel("Service 4",R.drawable.ic_launcher);
		
		serviceList.add(model1);
		serviceList.add(model2);
		serviceList.add(model3);
		serviceList.add(model4);
		
	}
}
