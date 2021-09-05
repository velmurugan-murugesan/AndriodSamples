package com.example.company.playondemand.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.company.playondemand.R;
import com.example.company.playondemand.adapter.CategoryAdapter;
import com.example.company.playondemand.adapter.SubCategoryAdapter;
import com.example.company.playondemand.model.SubCategory;
import com.example.company.playondemand.utils.Constants;
import com.example.company.playondemand.utils.Singleton;
import com.example.company.playondemand.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity {

    private String TAG = "MainActivity";
    private Context context = null;
    private GridView gridView;
    private ListView listView;
    static List<String> categoryList = null;
    static Utils utils = null;
    static HashMap<String, ArrayList<SubCategory>> map = null;
    private List<SubCategory> subCategoryList = null;
    private TextView tvSubCategory,videoTitle,videoDesc,tvTitle;
    //int images[]  = {R.drawable.slider2, R.drawable.slider3,R.drawable.slider4};
    //RelativeLayout relativeLayout;
    private Button btnPlayVideo;
    private ImageView ivVideoThumb;
    String path = null;
    String categoryName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        loadInitData();
        //init all the views
        initView();
    }
    private void loadInitData(){
        utils = Singleton.getUtilInstance();
        //convert the .csv into map from sdcard
        map = utils.loadDocFromSDcard(context);
        // get all the key's from map for the category.
        categoryList = Singleton.getUtilInstance().getKeyFromMap(map);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void initView(){
        //relativeLayout = (RelativeLayout)findViewById(R.id.sliderLayout);
        gridView = (GridView)findViewById(R.id.gridView);
        tvSubCategory = (TextView)findViewById(R.id.tvSubTitle);
        tvSubCategory.setTypeface((utils.getTypeface(context)));
        btnPlayVideo = (Button)findViewById(R.id.btnPlay);
        btnPlayVideo.setTypeface(utils.getTypeface(context));
        videoTitle = (TextView)findViewById(R.id.videoTitle);
        listView = (ListView)findViewById(R.id.listView);
        videoDesc = (TextView)findViewById(R.id.videoDesc);
        ivVideoThumb = (ImageView)findViewById(R.id.imVideoThumb);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setTypeface(utils.getTypeface(context));
        tvTitle.setText(getResources().getText(R.string.title).toString().toUpperCase());
    }

    @Override
    protected void onStart() {
        super.onStart();
        // print the map for test
        if(map != null){
            utils.printMap(context,map);
        }
        //Timer for the slider
        //startTimer(relativeLayout);
        //Enable video desc scroll
        videoDesc.setMovementMethod(new ScrollingMovementMethod());
        //Check the category not null && empty and set it into gridview

        gridView.setOnItemClickListener(gridviewitemClickListener);
        btnPlayVideo.setOnClickListener(clickListener);
        listView.setOnItemClickListener(listviewClickListener);
        videoTitle.setTypeface(utils.getTypeface(context));
        videoDesc.setTypeface(utils.getTypeface(context));
        subCategoryInitSetup();
    }

    private void subCategoryInitSetup(){
        if(categoryList != null && categoryList.size() > 0) {
            Log.d(TAG, "categoryList size = " + categoryList.size());
            CategoryAdapter categoryAdapter = new CategoryAdapter(context,categoryList);
            gridView.setAdapter(categoryAdapter);
            categoryName = categoryList.get(0);
            ivVideoThumb.setBackground(getResources().getDrawable(R.drawable.img1));
            tvSubCategory.setText(categoryName);
            SubCategory subCategory = map.get(categoryList.get(0)).get(0);
            videoTitle.setText(subCategory.getName());
            videoDesc.setText(subCategory.getDesc());
            //path = "android.resource://" + getPackageName() + "/" + R.raw.adho_mukha_vrksasana;
            SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(context,map.get(categoryList.get(0)));
            listView.setAdapter(subCategoryAdapter);
        } else {

        }
        }


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnPlay:
                    if(path != null){
                        File file = new File(path);
                        if(file.exists()){
                            Intent intent = new Intent(context, VideoActivity.class);
                            intent.putExtra("path",path);
                            startActivity(intent);
                        } else {
                            Log.d(TAG,"Video Path = "+file.getAbsolutePath());
                            Toast.makeText(context,"Video not found !",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d(TAG,"Video Path NULL");
                        Toast.makeText(context,"Video not available !",Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener gridviewitemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d(TAG,position+" gridviewitemClickListener");
            categoryName = categoryList.get(position);
            tvSubCategory.setText(categoryName);
            SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(context,map.get(categoryList.get(position)));
            listView.setAdapter(subCategoryAdapter);
        }
    };

    private AdapterView.OnItemClickListener listviewClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            SubCategory subCategory = map.get(categoryName).get(position);
            videoTitle.setText(subCategory.getName());
            Log.d(TAG,"subCategory.getDesc() = "+subCategory.getDesc());
            videoDesc.setText(subCategory.getDesc().toLowerCase());

            Utils utils = Singleton.getUtilInstance();
            String response = utils.buildImagePathFromName(context,subCategory.getName().toLowerCase());
            Log.d(TAG,"Image Path = "+response);
            Bitmap bmp = BitmapFactory.decodeFile(response);
            //ivVideoThumb.setImageBitmap(bmp);
            path = utils.buildVideoPathFromName(context,subCategory.getName().toLowerCase());
            Log.d(TAG,"Video Path = "+path);
            /*if(position == 0){
                ivVideoThumb.setBackground(getResources().getDrawable(R.drawable.img1));
                path = "android.resource://" + getPackageName() + "/" + R.raw.adho_mukha_vrksasana;
            } else if(position == 1){
                ivVideoThumb.setBackground(getResources().getDrawable(R.drawable.img2));
                path = "android.resource://" + getPackageName() + "/" + R.raw.anantasana;
            } else {
                ivVideoThumb.setBackground(getResources().getDrawable(R.drawable.img3));
                path = "android.resource://" + getPackageName() + "/" + R.raw.ardha_chandrasana;
            }*/
        }
    };

}


