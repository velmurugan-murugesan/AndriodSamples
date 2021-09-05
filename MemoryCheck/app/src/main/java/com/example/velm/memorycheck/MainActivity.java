package com.example.velm.memorycheck;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("Hello");
        setContentView(tv);

        Context context = this;

        if(context != null){

            Log.d("CON",context.getPackageName().toString());
        }

        Context context2 = getApplicationContext();

        if(context2 != null){
            Log.d("context2",context2.getPackageName().toString());
        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("OnResume","OnResume");
    }
}
