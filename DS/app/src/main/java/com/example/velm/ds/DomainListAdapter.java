package com.example.velm.ds;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by velmmuru on 9/18/2017.
 */

public class DomainListAdapter extends RecyclerView.Adapter<DomainListAdapter.ViewHolder> {


    private Context context;

    private Domains domains;

    public DomainListAdapter(Context context, Domains domains) {
        this.context = context;
        this.domains = domains;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.domainlist_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvDomainName.setText(domains.getStatus().get(position).getName());

        if(domains.getStatus().get(position).isAvailability()){
            holder.tvDomainStatus.setText("Available");
            holder.tvDomainStatus.setTextColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            holder.tvDomainStatus.setTextColor(context.getResources().getColor(R.color.black));

            holder.tvDomainStatus.setText("Not Available");
        }

    }

    @Override
    public int getItemCount() {
        return domains.getStatus().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDomainName,tvDomainStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDomainName = (TextView)itemView.findViewById(R.id.textViewDomainName);
            tvDomainStatus = (TextView)itemView.findViewById(R.id.textViewDomainStatus);

        }
    }
}
