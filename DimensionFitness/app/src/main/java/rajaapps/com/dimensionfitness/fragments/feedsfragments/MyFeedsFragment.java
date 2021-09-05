package rajaapps.com.dimensionfitness.fragments.feedsfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rajaapps.com.dimensionfitness.R;
import rajaapps.com.dimensionfitness.adapters.MyFeedsRecycularViewAdapter;
import com.example.velm.testlib.model.Feeds;

/**
 * Created by Velmurugan on 7/16/2017.
 */

public class MyFeedsFragment extends Fragment {
    String[] titles = {"12 Best Free Workout Videos","Isometric Exercises","Workout Routines for Women","Women's fitness"};
    int[] images = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four};
    RecyclerView recyclerView;

    MyFeedsRecycularViewAdapter recyclerViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_feeds_fragment,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_feeds_recyculerview);

        List<Feeds> feedsList = new ArrayList<>();
        for (int i =0; i < images.length; i++){
            Feeds feeds = new Feeds();
            feeds.setTitle(titles[i]);
            feeds.setImages(images[i]);
            feedsList.add(feeds);
        }


        recyclerViewAdapter = new MyFeedsRecycularViewAdapter(getContext(),feedsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

}
