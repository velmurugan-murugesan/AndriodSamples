package com.example.velm.domainsearchmvp.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.velm.domainsearchmvp.R;
import com.example.velm.domainsearchmvp.model.Words;
import com.example.velm.domainsearchmvp.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by velmmuru on 10/11/2017.
 */

public class HomeActivity extends AppCompatActivity implements HomeMvpView {

    private HomePresenterImpl homePresenterImpl;

    private ProgressDialog mDialog;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @BindView(R.id.editTextDomainName)
    TextView textViewDomainName;

    @BindViews({R.id.radioButtonSS,R.id.radioButtonFS,R.id.radioButtonPS}) List<RadioButton> radioButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        homePresenterImpl = new HomePresenterImpl(this,this);

        if(isDbEmpty()){
            homePresenterImpl.loadDataIntoDb();
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i == R.id.radioButtonFS){
                    Toast.makeText(getApplicationContext(),"radioButtonFS",Toast.LENGTH_SHORT).show();

                } else if (i == R.id.radioButtonPS){
                    Toast.makeText(getApplicationContext(),"radioButtonPS",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(),"radioButtonSS",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @OnClick(R.id.buttonSearch)
    public void search(){
        String domainName = textViewDomainName.getText().toString();
        Utils.printLog("Domain Name",domainName);
        checkAndMoveActivity();
    }

    private void checkAndMoveActivity(){

    }

    private boolean isDbEmpty(){

        List<Words> wordsList = homePresenterImpl.showDataFromDb();
        if(wordsList == null || wordsList.size() == 0 ){
            return true;
        }
        return false;
    }

    @Override
    public void showProgressbar() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Doing something...");
        mDialog.setCancelable(false);
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDialog.setMax(4000);
        mDialog.show();
    }

    @Override
    public void hideProgressbar() {
        mDialog.dismiss();
    }

    @Override
    public void updateprogressbar(int value) {
        mDialog.setProgress(value);
    }
}
