package com.example.velm.iot.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.velm.iot.R;
import com.example.velm.iot.activities.GatewayNodeActivity;
import com.example.velm.iot.model.Gateway;
import com.example.velm.iot.utils.SharedPreference;

import java.util.List;

/**
 * Created by velmmuru on 8/11/2017.
 */

public class GatewayNodeListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Gateway> items;

    public GatewayNodeListAdapter(Context context, List<Gateway> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.gateway_node_list_adapter,viewGroup,false);


        TextView textViewGatewayName = (TextView)view.findViewById(R.id.textViewGatewayName);
        textViewGatewayName.setText(items.get(i).getClientName());

        textViewGatewayName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SharedPreference().setClientNode(context,items.get(i).getClientName());
                Intent gatewayIntent = new Intent(context, GatewayNodeActivity.class);
                context.startActivity(gatewayIntent);
            }
        });

        TextView textViewGatewayStatus = (TextView)view.findViewById(R.id.textViewGatewayStatus);

        textViewGatewayStatus.setText(items.get(i).getStatus());


        /*Button buttonGatewayConnect = (Button)view.findViewById(buttonGatewayConnect);
        buttonGatewayConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SharedPreference().setClientNode(context,items.get(i).getClientName());
                Intent gatewayIntent = new Intent(context, GatewayNodeActivity.class);
                context.startActivity(gatewayIntent);
            }
        });

        if(items.get(i).getStatus().equals("online")){
            buttonGatewayConnect.setEnabled(true);
            buttonGatewayConnect.setText("ONLINE");
        } else {
            buttonGatewayConnect.setEnabled(false);
            buttonGatewayConnect.setText("OFFLINE");
        }*/

        return view;
    }
}
