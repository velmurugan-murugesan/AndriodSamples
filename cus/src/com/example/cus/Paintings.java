package com.example.cus;

public class Paintings {

   /*
    * Decalre the variables that will hold the resource id for the image and the imagetitle
    * Image will be bound to ImageView and imagetitle to the TextView for an item in the 
    * ListView
    */
	public int drawableresid;
    public String imagetitle;

	public Paintings() {
		super();
	}

	/*
	 * Constructor that will be used to create the row object for the ListView
	 */
	public Paintings(int drawable, String imagetitle) {
		super();
		this.drawableresid = drawable;
		this.imagetitle = imagetitle;
	}
}
