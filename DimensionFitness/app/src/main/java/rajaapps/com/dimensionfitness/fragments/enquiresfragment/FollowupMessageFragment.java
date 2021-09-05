package rajaapps.com.dimensionfitness.fragments.enquiresfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.velm.testlib.asynctask.AsyCallback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import rajaapps.com.dimensionfitness.MyApplication;
import rajaapps.com.dimensionfitness.R;
import rajaapps.com.dimensionfitness.adapters.ListEnquiresRecyclarviewAdapter;
import com.example.velm.testlib.model.Enquires;

/**
 * Created by velmmuru on 7/28/2017.
 */

public class FollowupMessageFragment  extends Fragment implements AsyCallback {

    private static final String TAG = "FollowupMessageFragment";
    RecyclerView recyclerView;
    ListEnquiresRecyclarviewAdapter adapter;
    List<Enquires> enquiresList;

    DatabaseReference databaseReference;



    public FollowupMessageFragment() {
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
        enquiresList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        try{
            MyApplication.getEnquiresDaoImpl().getFollowupSmsEnquires(this,getContext());
        }catch (Exception e){
            Log.d(TAG,e.toString());
        }
    }



    @Override
    public void onTaskComplete(Object result) {
        enquiresList = (List<Enquires>) result;
        Log.d("callback received",enquiresList.toString());
        adapter = new ListEnquiresRecyclarviewAdapter(getContext(),enquiresList);
        recyclerView.setAdapter(adapter);
    }
}
