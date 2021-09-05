package com.example.snewapp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class MyAdapter extends BaseAdapter
{
  List<ServiceModel> serviceList;
  
  private Context mContext;
  
  MyAdapter(Context context,List<ServiceModel> list)
  {
	  this.mContext = context;
	  this.serviceList = list;
  }
	public int getCount() {
		// TODO Auto-generated method stub
		return serviceList.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return serviceList.get(arg0);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater =LayoutInflater.from(mContext);
		View view=inflater.inflate(R.layout.adapterview,parent,false);
		TextView tv=(TextView)view.findViewById(R.id.textView1);
		ImageView iv=(ImageView)view.findViewById(R.id.imageView1);
		
		ServiceModel serviceModel = serviceList.get(position);
		
		tv.setText(serviceModel.getServiceName());
		iv.setImageResource(serviceModel.getServiceImage());
		return view;
	}
}