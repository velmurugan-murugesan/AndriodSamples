package rajaapps.com.dimensionfitness.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import rajaapps.com.dimensionfitness.R;
import rajaapps.com.dimensionfitness.adapters.DashboardAdapter;
import com.example.velm.testlib.model.Dashboard;

/**
 * Created by velmmuru on 7/28/2017.
 */

public class DashboardFragment extends Fragment {

    RecyclerView recyclerView;
    List<Dashboard> dashboardList;
    DashboardAdapter dashboardAdapter;
    Context context;

    DatabaseReference databaseReference;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.dashboard_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = this.getContext();
        recyclerView = (RecyclerView)view.findViewById(R.id.dashboard_recyculerview);

        dashboardList = new ArrayList<>();

        final Dashboard dashboard1 = new Dashboard("Emp",2);
        dashboardList.add(dashboard1);

        Dashboard dashboard2 = new Dashboard("Trainer",3);
        dashboardList.add(dashboard2);

        dashboardAdapter = new DashboardAdapter(context,dashboardList);

        GridLayoutManager layoutManager = new GridLayoutManager(context,2);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(dashboardAdapter);



        databaseReference = FirebaseDatabase.getInstance().getReference().child("enquires");
        Query query = databaseReference.orderByChild("enq_dateTime").startAt(getTodaysDateString()).endAt(getTodaysDateString()+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String item = "NEW ENQUIRES";
                int count = (int) dataSnapshot.getChildrenCount();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query1 = databaseReference.orderByChild("enq_dateTime").startAt(getFollowupSmsDateString()).endAt(getFollowupSmsDateString()+"\uf8ff");

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dashboardList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    Dashboard dashboard = dataSnapshot1.getValue(Dashboard.class);

                    dashboardList.add(dashboard);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query2 = databaseReference.orderByChild("enq_dateTime").startAt(getFollowupCallsDateString()).endAt(getFollowupCallsDateString()+"\uf8ff");

        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    Dashboard dashboard = dataSnapshot1.getValue(Dashboard.class);

                    dashboardList.add(dashboard);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







    }


    private String getFollowupSmsDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        GregorianCalendar yesterday = new GregorianCalendar();
        yesterday.add(Calendar.DATE, -1);
        Date date =  yesterday.getTime();

        return dateFormat.format(date);
    }

    private String getFollowupCallsDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        GregorianCalendar yesterday = new GregorianCalendar();
        yesterday.add(Calendar.DATE, -1);
        Date date =  yesterday.getTime();

        return dateFormat.format(date);
    }

    private String getTodaysDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        GregorianCalendar yesterday = new GregorianCalendar();
        yesterday.add(Calendar.DATE,0);
        Date date =  yesterday.getTime();

        return dateFormat.format(date);
    }

}
