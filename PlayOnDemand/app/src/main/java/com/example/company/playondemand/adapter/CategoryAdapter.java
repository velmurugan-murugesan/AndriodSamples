package com.example.company.playondemand.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.company.playondemand.R;
import com.example.company.playondemand.utils.Singleton;
import com.example.company.playondemand.utils.Utils;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by velmmuru on 9/4/2016.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<String> categoryList;
    private LayoutInflater inflater = null;

   /* public CategoryAdapter(Context context,List<Category> categoryList){
        this.context = context;
        this.categoryList = categoryList;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
*/
    public CategoryAdapter(Context context, List<String> keyFromMap) {
        this.context = context;
        this.categoryList = keyFromMap;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        Log.d("Utils","Size = "+categoryList.size());
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{

        //public TextView tvName;
        public ImageView image;
        public TextView name;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if(convertView==null){
            view = inflater.inflate(R.layout.category_adapter, null);
            holder = new ViewHolder();
            view.setTag( holder );
            //holder.tvName = (TextView) view.findViewById(R.id.tvCategoryName);
            holder.image = (ImageView)view.findViewById(R.id.ivCategory);
            holder.name = (TextView)view.findViewById(R.id.tvCategoryName);
        }
        else
            holder=(ViewHolder)view.getTag();
        if(categoryList.size()<=0) {
        }
        else{
            holder.name.setTypeface(Singleton.getUtilInstance().getTypeface(context));
            holder.name.setText(categoryList.get(position));
        }
        return view;
    }
}
