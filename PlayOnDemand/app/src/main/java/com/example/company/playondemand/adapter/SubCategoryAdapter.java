package com.example.company.playondemand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.company.playondemand.R;
import com.example.company.playondemand.model.SubCategory;
import com.example.company.playondemand.utils.Singleton;

import java.util.List;

/**
 * Created by velmmuru on 9/4/2016.
 */
public class SubCategoryAdapter extends BaseAdapter {
    private Context context;
    private List<SubCategory> subCategoryList = null;
    private LayoutInflater inflater = null;
    private SubCategory subCategory;

    public SubCategoryAdapter(Context context,List<SubCategory> subCategoryList){
        this.context = context;
        this.subCategoryList = subCategoryList;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return subCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return subCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{

        public TextView tvName;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(convertView==null){
            view = inflater.inflate(R.layout.sub_category_adapter, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) view.findViewById(R.id.tvSubCategoryName);
            view.setTag( holder );
        }
        else
            holder=(ViewHolder)view.getTag();
        if(subCategoryList.size()<=0) {
        }
        else{
            subCategory = null;
            subCategory = (SubCategory)subCategoryList.get(position);
            holder.tvName.setTypeface(Singleton.getUtilInstance().getTypeface(context));
            holder.tvName.setText(subCategory.getName());
        }
        return view;
    }
}
