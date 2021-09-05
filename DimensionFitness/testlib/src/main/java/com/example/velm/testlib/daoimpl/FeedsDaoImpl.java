package com.example.velm.testlib.daoimpl;

import com.example.velm.testlib.R;
import com.example.velm.testlib.dao.FeedsDao;
import com.example.velm.testlib.model.Feeds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velmmuru on 8/1/2017.
 */

public class FeedsDaoImpl implements FeedsDao {

    String[] titles = {"12 Best Free Workout Videos","Isometric Exercises","Workout Routines for Women","Women's fitness"};
    int[] images = {R.drawable.one,R.drawable.two,R.drawable.three, R.drawable.four};


    @Override
    public List<Feeds> getAllFeeds() {

        List<Feeds> feedsList = new ArrayList<>();
        for (int i =0; i < images.length; i++){
            Feeds feeds = new Feeds();
            feeds.setTitle(titles[i]);
            feeds.setImages(images[i]);
            feedsList.add(feeds);
        }

        return feedsList;

    }
}
