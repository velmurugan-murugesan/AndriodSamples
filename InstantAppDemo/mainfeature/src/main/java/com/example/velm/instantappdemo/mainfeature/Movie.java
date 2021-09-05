package com.example.velm.instantappdemo.mainfeature;

/**
 * Created by velmmuru on 8/22/2017.
 */

class Movie {


    private String name;

    private String year;

    private int image;

    private String director;


    public Movie(String name, String year, int image, String director) {
        this.name = name;
        this.year = year;
        this.image = image;
        this.director = director;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", image=" + image +
                ", director='" + director + '\'' +
                '}';
    }
}
