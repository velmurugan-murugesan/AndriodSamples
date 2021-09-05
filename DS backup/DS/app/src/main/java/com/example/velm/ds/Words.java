package com.example.velm.ds;

/**
 * Created by velmmuru on 9/17/2017.
 */

public class Words {

    private String word;

    public Words(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Words{" +
                "word='" + word + '\'' +
                '}';
    }
}
