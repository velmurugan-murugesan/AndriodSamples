package rajaapps.com.dimensionfitness.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import rajaapps.com.dimensionfitness.R;
import com.example.velm.testlib.model.Feeds;

/**
 * Created by Velmurugan on 7/13/2017.
 */

public class AllFeedsRecyclerViewAdapter extends RecyclerView.Adapter<AllFeedsRecyclerViewAdapter.MyViewHolder>{

    Context context;
    List<Feeds> feedsList;


    public AllFeedsRecyclerViewAdapter(Context context, List<Feeds> feedsList){
        this.context = context;
        this.feedsList = feedsList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_feeds_adapter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MyViewHolder mainHolder = (MyViewHolder) holder;
        mainHolder.tvTitle.setText(feedsList.get(position).getTitle());
        mainHolder.imgView.setImageResource(feedsList.get(position).getImages());
    }


    @Override
    public int getItemCount() {
        return feedsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDate;
        private ImageView imgView;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.titleTextView);
            imgView = (ImageView)itemView.findViewById(R.id.coverImageView);
        }
    }
}
