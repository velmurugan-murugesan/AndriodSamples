package com.example.cus;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RssFeesd extends ListActivity {

	List headlines;
	List links;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rssfeed);
		// Initializing instance variables
		headlines = new ArrayList();
		links = new ArrayList();

		try {
			URL url = new URL("http://www.nytimes.com/services/xml/rss/userland/HomePage.xml");

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(false);
			XmlPullParser xpp = factory.newPullParser();

		        // We will get the XML from an input stream
			xpp.setInput(getInputStream(url), "UTF_8");

		        /* We will parse the XML content looking for the "<title>" tag which appears inside the "<item>" tag.
		         * However, we should take in consideration that the rss feed name also is enclosed in a "<title>" tag.
		         * As we know, every feed begins with these lines: "<channel><title>Feed_Name</title>...."
		         * so we should skip the "<title>" tag which is a child of "<channel>" tag,
		         * and take in consideration only "<title>" tag which is a child of "<item>"
		         *
		         * In order to achieve this, we will make use of a boolean variable.
		         */
			boolean insideItem = false;

		        // Returns the type of current event: START_TAG, END_TAG, etc..
			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {

					if (xpp.getName().equalsIgnoreCase("item")) {
						insideItem = true;
					} else if (xpp.getName().equalsIgnoreCase("title")) {
						if (insideItem)
							headlines.add(xpp.nextText()); //extract the headline
					} else if (xpp.getName().equalsIgnoreCase("link")) {
						if (insideItem)
							links.add(xpp.nextText()); //extract the link of article
					}
				}else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
					insideItem=false;
				}

				eventType = xpp.next(); //move to next element
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Binding data
		
		ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, headlines);

		setListAdapter(adapter);

	}

public InputStream getInputStream(URL url) {
   try {
       return url.openConnection().getInputStream();
   } catch (IOException e) {
       return null;
     }
}

@Override
protected void onListItemClick(ListView l, View v, int position, long id) {
   Uri uri = Uri.parse((String) links.get(position));
   Intent intent = new Intent(Intent.ACTION_VIEW, uri);
   startActivity(intent);
}

}

