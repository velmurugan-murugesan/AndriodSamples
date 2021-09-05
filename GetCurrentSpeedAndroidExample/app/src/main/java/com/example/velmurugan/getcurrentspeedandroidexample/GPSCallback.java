package com.example.velmurugan.getcurrentspeedandroidexample;

import android.location.Location;

public interface GPSCallback
{
        public abstract void onGPSUpdate(Location location);
}