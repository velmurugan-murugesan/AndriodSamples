package com.example.velm.customviewexample;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.velview)
    VelView velView;
    List<String> valList;
    float lastval = 0.0f;
    int time =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }
    @OnClick(R.id.velview)
    public void velclick(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //int height = dm.heightPixels;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        Log.d("HEIGHT",height+"");
        //int height = getWindowManager().getDefaultDisplay().getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,-(1000));
         valList = new ArrayList<>();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                float aa = valueAnimator.getCurrentPlayTime();

                //3
                float value = (float) valueAnimator.getAnimatedValue();
                valList.add(String.valueOf(value));
                //4
                Log.d("VALUE = ",value+"");
                float x = value;
                float y = value;


                if(valList.size() > 0 && valList.size() < 10){
                    float dif = lastval - value;
                    time = time + 1;
                    Log.d("diff high",dif+" last va"+lastval);
                    goup(x,(dif * time));
                    if(valList.size() == 9){
                        time = 1;
                    }
                } else {
                    float dif = lastval - value;
                    Log.d("diff low",dif+" last va"+lastval);
                    time = time + 1;
                    Log.d("value",value+"");
                    y = value + (dif * time);
                    Log.d("y",y+"");
                    godown(x,y);
                    if(valList.size() == 19){
                        valList.clear();
                        time = 1;
                    }
                }

                /*if( valList.size() % 10 == 0 && (valList.size() / 10) % 2 == 0 && valList.size() > 0){

                    float dif = lastval - value;

                    changeDir(x,y);
                }*/

                /*if(y < -400){
                    float n = y - -400;
                    y = -400 - n;
                }

                if(y < 0){
                    float n = y - -400;
                    y = -400 - n;
                }*/

                lastval = value;
            }

            private void goup(float x, float y) {

                velView.setTranslationY(y);
                velView.setTranslationX(x);
            }

            private void godown(float x,float y){
                velView.setTranslationY(y);
                velView.setTranslationX(x);
            }


        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.d("A nima","End"+valList.size());


            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(15000);
        valueAnimator.start();
    }
}
