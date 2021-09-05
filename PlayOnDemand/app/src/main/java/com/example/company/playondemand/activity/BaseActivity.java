package com.example.company.playondemand.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.company.playondemand.R;
import com.example.company.playondemand.model.SubCategory;
import com.example.company.playondemand.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by velmmuru on 9/4/2016.
 */
public class BaseActivity extends AppCompatActivity {
    Timer timer;
    //int images[]  = {R.drawable.slider2, R.drawable.slider3,R.drawable.slider4};
    protected List<SubCategory> loadSubCategory(){

        List<SubCategory> subCategoryList = new ArrayList<SubCategory>();
        List<String> names = Arrays.asList(Constants.subCategory);

        for(int i =0; i < names.size(); i++){
            SubCategory subCategory = new SubCategory();
            subCategory.setName(names.get(i));
            subCategoryList.add(subCategory);
        }

        return subCategoryList;
    }

    /**
     * Retrieve video frame image from given video path
     *
     * @param p_videoPath
     *            the video file path
     *
     * @return Bitmap - the bitmap is video frame image
     *
     * @throws Throwable
     */
    @SuppressLint("NewApi")
    public static Bitmap retriveVideoFrameFromVideo(String p_videoPath)
            throws Throwable
    {
        Bitmap m_bitmap = null;
        MediaMetadataRetriever m_mediaMetadataRetriever = null;
        try
        {
            m_mediaMetadataRetriever = new MediaMetadataRetriever();
            m_mediaMetadataRetriever.setDataSource(p_videoPath);
            m_bitmap = m_mediaMetadataRetriever.getFrameAtTime();
        }
        catch (Exception m_e)
        {
            throw new Throwable(
                    "Exception in retriveVideoFrameFromVideo(String p_videoPath)"
                            + m_e.getMessage());
        }
        finally
        {
            if (m_mediaMetadataRetriever != null)
            {
                m_mediaMetadataRetriever.release();
            }
        }
        return m_bitmap;
    }

   /* protected void startTimer(RelativeLayout layout){
        if(timer != null){
            timer.cancel();
        }

        timer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask(layout);

        //delay 1000ms, repeat in 5000ms
        timer.schedule(myTimerTask, 1000, 3000);
    }*/


    /*class MyTimerTask extends TimerTask {
        RelativeLayout layout = null;
        MyTimerTask(RelativeLayout layout){
            this.layout = layout;
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    layout.setBackgroundResource(images[getRandomNumber()]);
                }});
        }

        private int getRandomNumber() {
            //Note that general syntax is Random().nextInt(n)
            //It results in range 0-4
            //So it should be equal to number of images in images[] array
            return new Random().nextInt(3);
        }}*/

}
