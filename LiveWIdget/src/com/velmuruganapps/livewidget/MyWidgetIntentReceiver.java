package com.velmuruganapps.livewidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MyWidgetIntentReceiver extends BroadcastReceiver {
	

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Constants.WIDGET_UPDATE_ACTION)) {
			updateWidgetPictureAndButtonListener(context);
		}
	}

	private void updateWidgetPictureAndButtonListener(Context context) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);

		// updating view
		
		updateView(context,remoteViews);
		
		

		// re-registering for click listener
		remoteViews.setOnClickPendingIntent(R.id.btnNext,
				MainActivity.buildButtonPendingIntent(context));

		MainActivity.pushWidgetUpdate(context.getApplicationContext(),
				remoteViews);
	}

	
	private void updateView(Context context,final RemoteViews remoteViews){
		Constants.clickCount = Constants.clickCount + 1 ;
		Log.d("Click count", String.valueOf(getCount()));
		Log.d("Title = ", Constants.responseModel.get(getCount()).getTitle());
		Log.d("Description = ", Constants.responseModel.get(getCount()).getDescription());
		remoteViews.setTextViewText(R.id.title, Constants.responseModel.get(getCount()).getTitle());
		remoteViews.setTextViewText(R.id.desc, Constants.responseModel.get(getCount()).getDescription());
	
		
		
		if(Constants.responseModel.get(getCount()).getCoverImage().length() > 0){
			
			String[] url = Constants.responseModel.get(getCount()).getCoverImage().split("\\?");
			
			
			
			
	/*		Picasso.with(context)
		    .load(url[0])
		    .into(new Target() {
		        @Override
		        public void onBitmapLoaded (final Bitmap bitmap, Picasso.LoadedFrom from){
		             Save the bitmap or do something with it here 

		            //Set it in the ImageView
		    		remoteViews.setImageViewBitmap(R.id.imageView1, bitmap);
		        }

				@Override
				public void onBitmapFailed(Drawable arg0) {
					// TODO Auto-generated method stub
		    		remoteViews.setImageViewResource(R.id.imageView1, R.drawable.ic_launcher);

				}

				@Override
				public void onPrepareLoad(Drawable arg0) {
					// TODO Auto-generated method stub
		    		remoteViews.setImageViewResource(R.id.imageView1, R.drawable.ic_launcher);

				}
		});*/
			
			ImageLoader imageLoader = ImageLoader.getInstance();
			DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
							.cacheOnDisc(true).resetViewBeforeLoading(true)
							.showImageForEmptyUri(R.drawable.ic_launcher)
							.showImageOnFail(R.drawable.ic_launcher)
							.showImageOnLoading(R.drawable.ic_launcher).build();
			
			Log.d("url", url[0]);
			//Bitmap bitmap = imageLoader.loadImageSync(url[0], options);
    		//remoteViews.setImageViewBitmap(R.id.imageView1, bitmap);
    		
    		imageLoader.loadImage(url[0], new SimpleImageLoadingListener(){
    			
    			 @Override
    			    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) 
    			    {
    			         // Do whatever you want with Bitmap
    		    		remoteViews.setImageViewBitmap(R.id.imageView1, loadedImage);

    			    }
    		});

		}
		
			
		
		
	
	}
	
	private int getCount(){
		
		int length = Constants.responseModel.size();
		
		if(Constants.clickCount >= length){
			return Constants.clickCount = 0;
		} else {
			return Constants.clickCount;
		}
	}
	
}
