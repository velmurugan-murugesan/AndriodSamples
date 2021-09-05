package com.example.velm.iot.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;
import com.example.velm.iot.R;
import com.example.velm.iot.api.AppApiImpl;
import com.example.velm.iot.model.Schedule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by velmmuru on 8/11/2017.
 */

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView textViewRange;
    private LinearLayout repeatLayout;
    private Switch scheduleSwithch;

    private Button btnSave;
    private Button btnDelete;
    private Button btnSun;
    private Button btnMon;
    private Button btnTues;
    private Button btnWed;
    private Button btnThu;
    private Button btnFri;
    private Button btnSat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);
        RangeBar rangebar = (RangeBar) findViewById(R.id.rangebar);
        textViewRange = (TextView)findViewById(R.id.textViewRange);

        repeatLayout = (LinearLayout)findViewById(R.id.repeatLayout);

        scheduleSwithch = (Switch)findViewById(R.id.switchSchedule);


        btnSave = (Button)findViewById(R.id.saveSchedule);
        btnSave.setOnClickListener(this);

        btnDelete = (Button)findViewById(R.id.buttonDeleteSchedule);
        btnDelete.setOnClickListener(this);

        btnSun = (Button)findViewById(R.id.buttonSun);
        btnSun.setOnClickListener(this);
        btnMon = (Button)findViewById(R.id.buttonMon);
        btnMon.setOnClickListener(this);
        btnTues = (Button)findViewById(R.id.buttonTues);
        btnTues.setOnClickListener(this);
        btnWed = (Button)findViewById(R.id.buttonWed);
        btnWed.setOnClickListener(this);
        btnThu = (Button)findViewById(R.id.buttonThu);
        btnThu.setOnClickListener(this);
        btnFri = (Button)findViewById(R.id.buttonFri);
        btnFri.setOnClickListener(this);
        btnSat = (Button)findViewById(R.id.buttonSat);
        btnSat.setOnClickListener(this);



        scheduleSwithch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    repeatLayout.setVisibility(View.VISIBLE);
                } else {
                    repeatLayout.setVisibility(View.GONE);
                }
            }
        });
        repeatLayout.setVisibility(View.GONE);

        rangebar.setEnabled(true);
        //rangebar.setTemporaryPins(true);
        rangebar.setRangePinsByIndices(3, 9);

        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex,
                                              String leftPinValue, String rightPinValue) {

                textViewRange.setText("Left Pin = "+leftPinValue+" Right Pin = "+rightPinValue);
            }

        });

        //getSchedule();
    }


    private void getSchedule(){

        int id = 2;
        Call<Schedule> call = AppApiImpl.getInstance().getSchedule(2);

        call.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                Schedule schedule = response.body();
                Log.d("schedule get",schedule.toString());
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                Log.d("schedule failed",t.toString());
                call.cancel();
            }
        });
    }


    private void saveSchedule(){
        String startTime = "6:00:00";
        String endTime = "12:00:00";
        String nodeName="cnode8";
        final String resource="LightCtrl/0/onOff";
        String enabled ="y";
        String[] day = {
                "Monday"
        };


        Schedule schedule = new Schedule(startTime,endTime,nodeName,resource,enabled,day);

        Call<Schedule> call = AppApiImpl.getInstance().createSchedule(schedule);

        call.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {

                Schedule schedule1 = response.body();

                Log.d("schedule created",schedule1.toString());

            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                Log.d("schedule failed",t.toString());
                call.cancel();
            }
        });

    }


    private void deleteSchedule(){

        int id = 2;

        Call<Schedule> call = AppApiImpl.getInstance().deleteSchedule(id);

        call.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                Schedule schedule1 = response.body();
                Log.d("schedule deleted",schedule1.toString());
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {

                Log.d("schedule failed",t.toString());
                call.cancel();
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.buttonDeleteSchedule:
                //deleteSchedule();
                break;

            case R.id.saveSchedule:
                //saveSchedule();
                break;

            case R.id.buttonSun:
                if(btnSun.isSelected()){
                    btnSun.setSelected(false);
                } else {
                    btnSun.setSelected(true);
                }
                break;
            case R.id.buttonMon:
                if(btnMon.isSelected()){
                    btnMon.setSelected(false);
                } else {
                    btnMon.setSelected(true);
                }
                break;
            case R.id.buttonTues:
                if(btnTues.isSelected()){
                    btnTues.setSelected(false);
                } else {
                    btnTues.setSelected(true);
                }
                break;
            case R.id.buttonWed:
                if(btnWed.isSelected()){
                    btnWed.setSelected(false);
                } else {
                    btnWed.setSelected(true);
                }
                break;
            case R.id.buttonThu:
                if(btnThu.isSelected()){
                    btnThu.setSelected(false);
                } else {
                    btnThu.setSelected(true);
                }
                break;
            case R.id.buttonFri:
                if(btnFri.isSelected()){
                    btnFri.setSelected(false);
                } else {
                    btnFri.setSelected(true);
                }
                break;
            case R.id.buttonSat:
                if(btnSat.isSelected()){
                    btnSat.setSelected(false);
                } else {
                    btnSat.setSelected(true);
                }
                break;

        }
    }
}
