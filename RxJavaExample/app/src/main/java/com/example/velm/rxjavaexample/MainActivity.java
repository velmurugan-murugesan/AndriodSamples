package com.example.velm.rxjavaexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    private Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Integer> observable = Observable.just(1,2,3,4,5);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                print("Completed");
            }

            @Override
            public void onError(Throwable e) {
                print(e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
               print(integer+"");
            }
        };

        subscription = observable.subscribe(observer);

        print(subscription.toString());
    }

    public void print(String message){
        Log.d("MainActivity",message);
        //Toast.makeText(getApplicationContext(),"value = "+message,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }
}
