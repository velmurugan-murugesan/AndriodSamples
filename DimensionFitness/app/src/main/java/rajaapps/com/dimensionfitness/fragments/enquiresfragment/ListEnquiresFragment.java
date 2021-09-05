package rajaapps.com.dimensionfitness.fragments.enquiresfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rajaapps.com.dimensionfitness.activities.AddEnquireActivity;
import rajaapps.com.dimensionfitness.MyApplication;
import rajaapps.com.dimensionfitness.R;
import rajaapps.com.dimensionfitness.adapters.ListEnquiresRecyclarviewAdapter;
import com.example.velm.testlib.asynctask.AsyCallback;

import com.example.velm.testlib.model.Enquires;


/**
 * Created by Velmurugan on 7/17/2017.
 */

public class ListEnquiresFragment extends Fragment implements AsyCallback {

    private static final String TAG = "ListEnquiresFragment";
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ListEnquiresRecyclarviewAdapter adapter;
    List<Enquires> enquiresList;


    public ListEnquiresFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_enquires_fragment,container,false);

        recyclerView = (RecyclerView)v.findViewById(R.id.list_enquires_recyculerview);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = (FloatingActionButton)view.findViewById(R.id.list_enquires_fab);
        enquiresList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        try {
            MyApplication.getEnquiresDaoImpl().getAllEnquires(this,getContext());
        }catch (Exception e){
            Log.d("Exce",e.toString());
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("callback received",enquiresList.toString());
                Intent intent = new Intent(getContext(),AddEnquireActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onTaskComplete(Object result) {

        enquiresList = (List<Enquires>) result;
        Log.d("callback received",enquiresList.toString());
        //adapter.notifyDataSetChanged();
        adapter = new ListEnquiresRecyclarviewAdapter(getContext(),enquiresList);
        recyclerView.setAdapter(adapter);

    }
}


