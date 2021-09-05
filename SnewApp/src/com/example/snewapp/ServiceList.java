package com.example.snewapp;

import java.util.HashMap;
import java.util.Map;

import android.R.integer;

public class ServiceList {
	
	public static String[] service1 = {"sub service 1","sub service 2","sub service 3"};
	
	public static String[] service2 = {"sub service 1","sub service 2","sub service 3"};
	
	public static String[] service3 = {"sub service 1","sub service 2","sub service 3"};
	
	public static String[] service4 = {"sub service 1","sub service 2","sub service 3"};
	
	
	
	public static Map<Integer, String[]> getserviceMap(){
		
		Map<Integer, String[]> serviceMap = new HashMap<Integer, String[]>();
		
		serviceMap.put(new Integer(0), service1);
		serviceMap.put(new Integer(1), service2);
		serviceMap.put(new Integer(2), service3);
		serviceMap.put(new Integer(3), service4);
		
		return serviceMap;
		
		
	}
	
	
	
}
