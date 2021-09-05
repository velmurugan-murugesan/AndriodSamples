package com.example.velm.retrofitexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by velmmuru on 8/19/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    private final Context context;

    private final List<Movie> movieList;

    public RecyclerAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_layout,parent,false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, int position) {

        holder.tvMovieName.setText(movieList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        final TextView tvMovieName;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.textViewMovieName);
        }

    }
}
