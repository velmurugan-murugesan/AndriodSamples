package com.example.velm.instantappdemo.mainfeature;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by velmmuru on 8/22/2017.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyviewHolder> {


    private Context context;
    private List<Movie> movieList;

    public RecyclerviewAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public RecyclerviewAdapter() {
    }

    @Override
    public RecyclerviewAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerviewAdapter.MyviewHolder holder, int position) {

        Movie movie = movieList.get(position);

        holder.textViewName.setText(movie.getName());
        holder.textViewDirector.setText(movie.getDirector());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewDirector;
        private ImageView imageViewImage;

        public MyviewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView)itemView.findViewById(R.id.textviewName);
            textViewDirector = (TextView)itemView.findViewById(R.id.textViewDirector);
            imageViewImage = (ImageView)itemView.findViewById(R.id.imageViewImage);

        }
    }
}
