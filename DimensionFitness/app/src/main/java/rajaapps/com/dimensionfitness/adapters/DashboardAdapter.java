package rajaapps.com.dimensionfitness.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import rajaapps.com.dimensionfitness.R;
import com.example.velm.testlib.model.Dashboard;

/**
 * Created by velmmuru on 7/28/2017.
 */

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {

    Context context;

    List<Dashboard> dashboardList;

    public DashboardAdapter(Context context, List<Dashboard> dashboardList) {
        this.context = context;
        this.dashboardList = dashboardList;
    }

    @Override
    public DashboardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_adapter,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DashboardAdapter.MyViewHolder holder, int position) {

        Dashboard dashboard = dashboardList.get(position);

        holder.textViewCount.setText(dashboard.getItem());

        holder.buttonRound.setText(dashboard.getCount()+"");

    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Button buttonRound;
        TextView textViewCount;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewCount = (TextView)itemView.findViewById(R.id.textViewCount);
            buttonRound = (Button) itemView.findViewById(R.id.buttonRound);
        }
    }
}
