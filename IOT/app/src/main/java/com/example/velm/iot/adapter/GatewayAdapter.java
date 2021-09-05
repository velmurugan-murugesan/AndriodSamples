package com.example.velm.iot.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.velm.iot.R;
import com.example.velm.iot.activities.GatewayNodeListActivity;
import com.example.velm.iot.model.Gateway;

import java.util.List;

/**
 * Created by velmmuru on 9/18/2017.
 */

public class GatewayAdapter extends RecyclerView.Adapter<GatewayAdapter.ViewHolder> {

    private final Context context;

    private final List<Gateway> getGatewayList;

    public GatewayAdapter(Context context, List<Gateway> getGatewayList) {
        this.context = context;
        this.getGatewayList = getGatewayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.gateway_adapter,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvGatewayName.setText(getGatewayList.get(position).getClientName());

        holder.tvGatewayName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gatewayNodeList = new Intent(context, GatewayNodeListActivity.class);
                context.startActivity(gatewayNodeList);

            }
        });
    }



    @Override
    public int getItemCount() {
        return getGatewayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvGatewayName;
        public ViewHolder(View itemView) {
            super(itemView);
            tvGatewayName = (TextView)itemView.findViewById(R.id.textViewGateway);
        }
    }
}
