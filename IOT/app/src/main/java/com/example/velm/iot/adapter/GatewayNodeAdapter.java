package com.example.velm.iot.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.velm.iot.R;
import com.example.velm.iot.activities.DetailsActivity;
import com.example.velm.iot.activities.ScheduleActivity;
import com.example.velm.iot.model.LightCtrl;
import com.example.velm.iot.model.Nodes;
import com.example.velm.iot.utils.Constants;


/**
 * Created by Velmurugan on 7/13/2017.
 */

public class GatewayNodeAdapter extends RecyclerView.Adapter<GatewayNodeAdapter.MyViewHolder>{

    private final Context context;

    private final Nodes currentNode;

    private final String clientName;

    public GatewayNodeAdapter(Context context, Nodes node, String clientName){
        this.context = context;
        this.currentNode = node;
        this.clientName = clientName;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gateway_node_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MyViewHolder mainHolder = (MyViewHolder) holder;

        final LightCtrl lightCtrl = currentNode.getLightCtrl().get(position);

        mainHolder.textViewNodeName.setText(lightCtrl.getCtrlName()+" "+lightCtrl.getNodeNumber() );
        if(lightCtrl.getCtrlName().equals(Constants.WATER_LEVEL)){
            holder.textView.setText(lightCtrl.getSensorValue()+" %");
            holder.lightSwitch.setVisibility(View.GONE);
            holder.textView.setVisibility(View.VISIBLE);

        } else {
            holder.lightSwitch.setChecked(lightCtrl.isOnOff());
            holder.lightSwitch.setVisibility(View.VISIBLE);
            holder.textView.setVisibility(View.GONE);
        }






        mainHolder.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent detailsActivity = new Intent(context, DetailsActivity.class);
                detailsActivity.putExtra("nodeName",clientName);
                detailsActivity.putExtra("nodeNumber",lightCtrl.getNodeNumber());
                detailsActivity.putExtra("controllerName",lightCtrl.getCtrlName());
                context.startActivity(detailsActivity);

                /*if(position == 0){
                    Intent detailsActivity = new Intent(context, DetailsActivity.class);
                    detailsActivity.putExtra("nodeName","node0");
                    detailsActivity.putExtra("nodeNumber",0);
                    detailsActivity.putExtra("controllerName","waterLevel");
                    context.startActivity(detailsActivity);
                } else {
                    Intent detailsActivity = new Intent(context, DetailsActivity.class);

                    detailsActivity.putExtra("nodeName","node0");
                    detailsActivity.putExtra("nodeNumber",0);
                    detailsActivity.putExtra("controllerName","lightCtrl");
                    context.startActivity(detailsActivity);
                }*/

            }
        });

        mainHolder.buttonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scheduleActivity = new Intent(context, ScheduleActivity.class);
                context.startActivity(scheduleActivity);
            }
        });
    }


    @Override
    public int getItemCount() {
        return currentNode.getLightCtrl().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        final TextView textViewNodeName;
        final Button buttonDetails;

        final Button buttonSchedule;

        final TextView textView;

        final Switch lightSwitch;
        public MyViewHolder(View itemView) {
            super(itemView);

            lightSwitch = (Switch)itemView.findViewById(R.id.switch1);

            textView = (TextView)itemView.findViewById(R.id.textView5);
            buttonDetails = (Button)itemView.findViewById(R.id.buttonDetails);
            buttonSchedule = (Button)itemView.findViewById(R.id.buttonSchedule);
            textViewNodeName = (TextView)itemView.findViewById(R.id.textViewNodename);

        }
    }
}
