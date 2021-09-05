package com.example.velm.iot.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.velm.iot.model.Favorites;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by velmmuru on 8/21/2017.
 */

public class SharedPreference {

    private static final String PREFS_NAME = "FELIX_APP";
    private static final String FAVORITES = "Favorite";
    private static final String NODE_NAME = "node_name";




    public SharedPreference() {
        super();
    }

    public void saveFavorites(Context context, List<Favorites> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.apply();
    }

    public String getClientNode(Context context){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);


        return settings.getString(NODE_NAME, null);
    }

    public void setClientNode(Context context,String clientNode){
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(NODE_NAME, clientNode);
        editor.apply();
    }


    public void addFavorite(Context context, Favorites product) {
        List<Favorites> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Favorites>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }


    public ArrayList<Favorites> getFavorites(Context context) {
        SharedPreferences settings;
        List<Favorites> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Favorites[] favoriteItems = gson.fromJson(jsonFavorites,
                    Favorites[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Favorites>(favorites);
        } else
            return null;

        return (ArrayList<Favorites>) favorites;
    }
}
