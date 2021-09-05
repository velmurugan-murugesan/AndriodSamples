package com.example.cus;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	ListView lvpaintingview;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		/*
		 * Here we are creating the row object for each of the items
		 */
		ListView lvpaintingview=getListView();
		Paintings painting_content[] = new Paintings[] {
				new Paintings(R.drawable.pcw, "RssFeed"),
				new Paintings(R.drawable.ic_launcher, "Explode"),
				new Paintings(R.drawable.ic_launcher, "Fan"),
				new Paintings(R.drawable.ic_launcher, "Fire in the sky"),

		};

		/*
		 * Instantiating the adapter object
		 */

		PaintingAdapter adapter = new PaintingAdapter(this, painting_content);

		//lvpaintingview = (ListView) findViewById(R.id.listView1);

		/*
		 * Binding the ListView with the cutom adapter
		 */
		lvpaintingview.setAdapter(adapter);
		lvpaintingview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,
	          int position, long id) {
	        	
	        Intent i =new Intent(getApplicationContext(),RssFeesd.class);
	        startActivity(i);
	      //Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
	        }
	    });
	}
		

}



