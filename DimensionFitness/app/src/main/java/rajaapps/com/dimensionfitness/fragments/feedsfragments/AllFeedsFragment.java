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
import rajaapps.com.dimensionfitness.adapters.AllFeedsRecyclerViewAdapter;

import com.example.velm.testlib.daoimpl.FeedsDaoImpl;
import com.example.velm.testlib.model.Feeds;

/**
 * Created by Velmurugan on 7/16/2017.
 */

public class AllFeedsFragment extends Fragment {


    RecyclerView recyclerView;
    AllFeedsRecyclerViewAdapter recyclerViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.all_feeds_fragment,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.all_feeds_recyculerview);

        List<Feeds> feedsList = new FeedsDaoImpl().getAllFeeds();


        recyclerViewAdapter = new AllFeedsRecyclerViewAdapter(getContext(),feedsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
