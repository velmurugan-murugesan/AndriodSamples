package com.example.company.playondemand.model;

/**
 * Created by velmmuru on 9/4/2016.
 */
public class SubCategory {

    private String name;
    private String desc;
    private String imagePath;
    private String videoPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
