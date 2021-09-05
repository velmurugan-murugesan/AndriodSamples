package rajaapps.com.dimensionfitness.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rajaapps.com.dimensionfitness.activities.FeedDetailsActivity;
import rajaapps.com.dimensionfitness.R;

import com.example.velm.testlib.model.Feeds;

/**
 * Created by Velmurugan on 7/16/2017.
 */

public class MyFeedsRecycularViewAdapter  extends RecyclerView.Adapter<MyFeedsRecycularViewAdapter.MyViewHolder>{

    Context context;
    List<Feeds> feedsList;


    public MyFeedsRecycularViewAdapter(Context context, List<Feeds> feedsList){
        this.context = context;
        this.feedsList = feedsList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_feeds_adapter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MyViewHolder mainHolder = (MyViewHolder) holder;
        mainHolder.tvTitle.setText(feedsList.get(position).getTitle());
        mainHolder.imgView.setImageResource(feedsList.get(position).getImages());
        mainHolder.imgCmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(context, FeedDetailsActivity.class);
                context.startActivity(detailIntent);
            }
        });

        mainHolder.imgViewOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(mainHolder.imgViewOptions);

            }
        });

    }
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.feed_options, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    @Override
    public int getItemCount() {
        return feedsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDate;
        private ImageView imgView,imgViewOptions,imgCmd;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.titleTextView);
            imgView = (ImageView)itemView.findViewById(R.id.coverImageView);
            imgViewOptions = (ImageView)itemView.findViewById(R.id.MoreImageView);
            imgCmd = (ImageView)itemView.findViewById(R.id.shareImageView);
        }
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_delete:
                    Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                    return true;

                default:
            }
            return false;
        }


    }

}
