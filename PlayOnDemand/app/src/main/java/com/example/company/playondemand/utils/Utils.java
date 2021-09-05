package com.example.company.playondemand.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.company.playondemand.model.SubCategory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.company.playondemand.utils.Constants.filePath;
import static com.example.company.playondemand.utils.Constants.parentPath;

/**
 * Created by velmmuru on 9/7/2016.
 */
public class Utils {

    String TAG ="Utils";
    SharedPreferences.Editor editor = null;
    SharedPreferences pref = null;

    public HashMap<String, ArrayList<SubCategory>> loadDocFromSDcard(Context context){
        String row = "";
        String csvFilePath = getSharedFilePath(context).toString();
        Log.d(TAG,"csv file to load = "+csvFilePath);
        String cvsSplitBy = ",";
        int lineNumber = 0;
        HashMap<String, ArrayList<SubCategory>> map = null;
        if(csvFilePath !=  null){
            map = new HashMap<String, ArrayList<SubCategory>>();
            try {
                BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
                while ((row = br.readLine()) != null) {
                    //To skip the header
                    if(lineNumber == 0){
                        lineNumber = 1;
                    } else {
                        //get single row items into string[] data
                        String[] data = row.split(cvsSplitBy);
                        //Store the title into String to setup the key for hashmap
                        String title = data[0];

                        //Check if the map is null or not for the current key
                        //It will be NULL for the first time for the key
                        ArrayList<SubCategory> al = null;
                        if(map.get(title) != null){
                            //If not null get the current list of subcategory and add the new
                            //subcategory into the list and push it back to map with the same key.
                            al = map.get(title);
                        } else {
                            //else create new subcategory array list add the items into list and map.
                            al = new ArrayList<SubCategory>();
                        }
                        if(al != null){
                            //Read data and set it into subcategory list and push to map
                            map.put(title, addDataToList(al,data));
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG,"csvFilePath is NULL");
        }
        return map;
    }

    public void printMap(Context context,HashMap<String, ArrayList<SubCategory>> map){
        Log.d(TAG,"printing map");
        System.out.println(map.size());
        for (String string : map.keySet()) {
            Log.d(TAG, "Key = " + string);
            List<SubCategory> sub = map.get(string);
            for (SubCategory subCategory : sub) {
                Log.d(TAG,"sub title ="+subCategory.getName());
                Log.d(TAG,"Desc = "+subCategory.getDesc());
                Log.d(TAG, "Image path = " + subCategory.getImagePath());
                Log.d(TAG,"Video path = "+subCategory.getVideoPath());
            }
        }
    }


    public ArrayList<SubCategory> addDataToList(ArrayList<SubCategory> al,String[] data){
        if(al != null && data != null){
            SubCategory sub = new SubCategory();
            sub.setName(data[1]);
            sub.setDesc(data[2]);
           // sub.setImagePath(data[3]);
           // sub.setVideoPath(data[4]);
            al.add(sub);
            return al;
        } else {
            return null;
        }
    }

    /*public String getSDcardPath(){
        String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        if(sdcardPath != null){
            return sdcardPath;
        } else {
            return null;
        }
    }*/

    /*public File getCSVPath(){

        File file = new File(getSDcardPath(),"/MyApp/Book1.csv");
        Log.d(TAG,"File = "+file);
        if (file.exists()){
            Log.d(TAG, "CSV file EXIST");
            return file;
        } else {
            Log.d(TAG, "CSV file NOT EXIST");
            return null;
        }
    }*/
    public List<String> getKeyFromMap(HashMap<String, ArrayList<SubCategory>> map){
        List<String> keyList = null;
        if(map != null){
            keyList = new ArrayList<String>();
            for (String key : map.keySet()) {
                keyList.add(key);
            }
            return keyList;
        } else {
            Log.w(TAG, "Map NULL in getKeyFromMap");
            return null;
        }
    }

    public String buildImagePathFromName(Context context,String imageName){
        String imageFolderPath = getImagePath(context);
        String imagePath = null;
        if(imageFolderPath != null){
            imagePath = imageFolderPath+"/"+imageName+".png";
            Log.d(TAG,"imagePath PNG = "+imagePath);
            File imagePathFile = new File(imagePath);
            if(imagePathFile.exists()){
                return imagePathFile.getAbsolutePath();
            } else {
                imagePath = imageFolderPath+"/"+imageName+".jpg";
                Log.d(TAG,"imagePath JPG = "+imagePath);
                imagePathFile = new File(imagePath);
                if(imagePathFile.exists()){
                    return imagePathFile.getAbsolutePath();
                } else {
                    Log.w(TAG,"IMAGE NOT FOUND");
                    return null;
                }
            }
        } else {
            Log.w(TAG, "NULL in buildImagePathFromName");
            return null;
        }


    }

    public String buildVideoPathFromName(Context context,String videoName){
        {
            String videoFolderPath = getVideoPath(context);
            String videoPath = null;
            if(videoFolderPath != null){
                videoPath = videoFolderPath+"/"+videoName+".mp4";
                Log.d(TAG,"VideoPath MP4 = "+videoPath);
                File videoPathFile = new File(videoPath);
                if(videoPathFile.exists()){
                    return videoPathFile.getAbsolutePath();
                } else {
                    videoPath = videoFolderPath+"/"+videoName+".mp4";
                    Log.d(TAG,"VideoPath MP4 = "+videoPath);
                    videoPathFile = new File(videoPath);
                    if(videoPathFile.exists()){
                        return videoPathFile.getAbsolutePath();
                    } else {
                        Log.w(TAG,"VIDEO NOT FOUND");
                        return null;
                    }
                }
            } else {
                Log.w(TAG,"NULL in buildVideoPathFromName");
                return null;
            }


        }
    }

    /*public String getAppPath(){
        File appPath = new File(getSDcardPath(),"/MyApp");

        if(appPath.exists()){
            return appPath.getAbsolutePath();
        } else {
            Log.w(TAG,"Application PATH NOT FOUND");
            return null;
        }
    }

    public String getImagePath(){

        File imagePath = new File(getAppPath(),"/Images");

        if(imagePath.exists()){
            return imagePath.getAbsolutePath();
        } else {
            Log.w(TAG,"Image PATH NOT FOUND");
            return null;
        }
    }*/

    public String getImagePath(Context context){
        File imagePath = new File(getSharedParentPath(context),"/Images");
        Log.d(TAG,"image Path = "+imagePath);
        if(imagePath.exists()){
            return imagePath.getAbsolutePath();
        } else {
            Log.w(TAG,"Image PATH NOT FOUND");
            return null;
        }
    }

    public String getVideoPath(Context context){
        File videoPath = new File(getSharedParentPath(context),"/Videos");
        Log.d(TAG,"video Path = "+videoPath);
        if(videoPath.exists()){
            return videoPath.getAbsolutePath();
        } else {
            Log.w(TAG, "Image PATH NOT FOUND");
            return null;
        }
    }

    public Typeface getTypeface(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/bebas.ttf");
        if(typeface != null){
            return typeface;
        } else {
            Log.d(TAG, "typeface is NULL");
            return null;
        }

    }

    public String getSharedFilePath(Context context){
        pref = context.getSharedPreferences("MyPref", context.MODE_PRIVATE);
        return pref.getString(Constants.filePath,"");
    }


    public void setSharedFilePath(Context context,String filePath){

        String[] path = filePath.split("/storage");


        if(path[1] != null){

            filePath = "/storage"+path[1];

        } else {

            filePath = path[0];
        }
        Log.d("Utils", "Path to push =" + filePath);
        Toast.makeText(context,"Path = "+filePath,Toast.LENGTH_SHORT).show();
        pref = context.getSharedPreferences("MyPref", context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString(Constants.filePath,filePath);
        editor.commit();
    }

    public String getSharedParentPath(Context context){
        pref = context.getSharedPreferences("MyPref", context.MODE_PRIVATE);
        return pref.getString(Constants.parentPath,"");
    }


    public void setSharedParentPath(Context context,String parentPath){
        String[] path = parentPath.split("/storage");


        if(path[1] != null){

            parentPath = "/storage"+path[1];

        } else {

            parentPath = path[0];
        }
        Log.d("Utils","Path to push ="+parentPath);
        Toast.makeText(context,"parent path = "+parentPath,Toast.LENGTH_SHORT).show();
        pref = context.getSharedPreferences("MyPref", context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString(Constants.parentPath,parentPath);
        editor.commit();
    }



}
