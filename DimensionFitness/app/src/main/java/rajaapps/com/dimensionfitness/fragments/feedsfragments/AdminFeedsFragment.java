package rajaapps.com.dimensionfitness.fragments.feedsfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;
import java.util.List;

import rajaapps.com.dimensionfitness.R;
import rajaapps.com.dimensionfitness.adapters.AllFeedsRecyclerViewAdapter;
import rajaapps.com.dimensionfitness.adapters.SampleAdapter;
import com.example.velm.testlib.model.Feeds;

/**
 * Created by Velmurugan on 7/16/2017.
 */

public class AdminFeedsFragment extends Fragment {
    private StaggeredGridView mGridView;
    SampleAdapter mAdapter;
    private List<Feeds> mData;
    String[] titles = {"12 Best Free Workout Videos","Isometric Exercises","Workout Routines for Women","Women's fitness"};
    int[] images = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four};
    RecyclerView recyclerView;
    AllFeedsRecyclerViewAdapter recyclerViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.admin_feeds_fragment,container,false);
        getActivity().setTitle("Admin Feeds");
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mGridView = (StaggeredGridView)getView().findViewById(R.id.grid_view);
        mAdapter = new SampleAdapter(getContext(),android.R.layout.simple_list_item_1, generateData());
        // do we have saved data?
        if (savedInstanceState != null) {
            //mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
        }

        if (mData == null) {
            mData = generateData();
        }

        for (Feeds data : mData) {
            mAdapter.add(data);
        }

        mGridView.setAdapter(mAdapter);
    }
    private List<Feeds> generateData() {
        List<Feeds> feedsList = new ArrayList<>();
        for (int i =0; i < images.length; i++){
            Feeds feeds = new Feeds();
            feeds.setTitle(titles[i]);
            feeds.setImages(images[i]);
            feedsList.add(feeds);
        }

        return feedsList;
    }


    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.admin_feeds_recyculerview);

        List<Feeds> feedsList = new ArrayList<>();
        for (int i =0; i < images.length; i++){
            Feeds feeds = new Feeds();
            feeds.setTitle(titles[i]);
            feeds.setImages(images[i]);
            feedsList.add(feeds);
        }


        recyclerViewAdapter = new AllFeedsRecyclerViewAdapter(getContext(),feedsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

    }*/
}
