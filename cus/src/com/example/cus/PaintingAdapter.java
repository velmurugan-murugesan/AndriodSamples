package com.example.cus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PaintingAdapter extends ArrayAdapter<Paintings> {

	Context c;
	// create a reference of the Paintings class, this class when instantiated
	// will hold the values for the image and text view elements for each item
	// in the ListView
	Paintings content[] = null;

	public PaintingAdapter(Context c, Paintings[] content) {

		super(c, R.layout.te, content);
		this.c = c;
		this.content = content;
		// TODO Auto-generated constructor stub
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
	 * android.view.ViewGroup) This method will be called for creating the view
	 * for each item in the ListView. It takes the below input parameters
	 * itempos - position of the item in the ListView convertview - view of the
	 * item parent - this is the container/root of the generate view(ListView in
	 * our example)
	 */
	public View getView(int itempos, View convertView, ViewGroup parent) {

		/*
		 * Creating an inflater object which will inflate the or parse the xml
		 * layout file for the listview items
		 */
		LayoutInflater inflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View listrowView = inflater.inflate(R.layout.te,
				parent, false);

		TextView txtcontent = (TextView) listrowView
				.findViewById(R.id.textView1);
		ImageView imgcontent = (ImageView) listrowView
				.findViewById(R.id.imageView1);

		// storing the content object value for a particular item in the
		// paintingcontent obj
		Paintings paintingcontent = content[itempos];

		// Setting the values for textview and imageview for each item in
		// listview
		txtcontent.setText(paintingcontent.imagetitle);
		imgcontent.setImageResource(paintingcontent.drawableresid);

		// return the view for a single item in the listview
		return listrowView;
	}
}
