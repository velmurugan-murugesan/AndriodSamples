package com.example.velm.ds;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by velmmuru on 9/17/2017.
 */

public class DomainListActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    String TAG = "DomainListActivity";

    StringBuilder searchUrl = null;

    DomainListAdapter domainListAdapter;

    Context context;

    DatabaseHandler db;

    DomainSearch domainSearch;


    Domains domainsAll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.domainlist_activity);
        ButterKnife.bind(this);
        context = this;
        domainsAll = new Domains();
        List<Status> statuses = new ArrayList<>();
        domainsAll.setStatus(statuses);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        db = new DatabaseHandler(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Bundle bundle = getIntent().getExtras();

        domainSearch = bundle.getParcelable("search");

        if (domainSearch != null){

            searchUrl = new StringBuilder();

            Log.d(TAG,domainSearch.toString());

            for (int i = 0; i < domainSearch.getDomains().size(); i++){
                String domain = "";

                if(i == domainSearch.getDomains().size() -1){
                    domain = domainSearch.getName()+domainSearch.getDomains().get(i);
                } else {
                    domain = domainSearch.getName()+domainSearch.getDomains().get(i)+",";
                }
                searchUrl.append(domain);
            }
            Log.d(TAG,searchUrl.toString());

            if(domainSearch.getSearchType().equals("fs")){
                fsDomain();
            } else if(domainSearch.getSearchType().equals("ps")){
                psDomain(domainSearch,true);
            } else {
                psDomain(domainSearch,false);
            }
        }

    }
    @OnClick(R.id.buttonAllCom)
    public void getAllCom(){
        List<Words> wordsList = db.getWordsByLetterCount(domainSearch.getLetterCount());


        searchUrl = new StringBuilder();

        Log.d(TAG,wordsList.toString());


        Log.d(TAG,"Word Size = "+wordsList.size());

        for (int i = 0; i < wordsList.size(); i++){

            if(i % 15 == 0){
                getDOaminsForCom(searchUrl);
                searchUrl = new StringBuilder();
            }
            String domain = "";
            if(i == wordsList.size() -1){
                domain = wordsList.get(i).getWord()+".com";
            } else {
                domain = wordsList.get(i).getWord()+".com"+",";
            }
            searchUrl.append(domain);
        }
        Log.d(TAG,searchUrl.toString());
        getDOaminsForCom(searchUrl);


    }

    private void psDomain(DomainSearch domainSearch,boolean isPs){

        List<Words> wordsList = db.getWordsByLetterCount(domainSearch.getLetterCount());

        WordsAdapter wordsAdapter = new WordsAdapter(context,wordsList,domainSearch,isPs);

        recyclerView.setAdapter(wordsAdapter);

        wordsAdapter.notifyDataSetChanged();


    }


    private void fsDomain(){
        AppApi appApi = ApiClient.getClient().create(AppApi.class);

        Call call1 = appApi.getDomains(searchUrl.toString());

        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                Log.d(TAG,call.toString());
                Log.d(TAG,response.toString());
                Log.d("response",response.body().toString());

                Domains domains = (Domains) response.body();
                Log.d(TAG,domains.toString());

                domainListAdapter = new DomainListAdapter(context,domains);

                recyclerView.setAdapter(domainListAdapter);

                domainListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Log.d("Response",t.toString());
            }
        });
    }

    private void getDOaminsForCom(StringBuilder searchUrl){
        AppApi appApi = ApiClient.getClient().create(AppApi.class);

        Call call1 = appApi.getDomains(searchUrl.toString());
        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                Log.d(TAG,call.toString());
                Log.d(TAG,response.toString());
                Log.d("response",response.body().toString());

                Domains domains = (Domains) response.body();
                Log.d(TAG,domains.toString());

                //updateData(domains);

                List<Status> statuses = domainsAll.getStatus();

                statuses.addAll(domains.getStatus());

                domainsAll.setStatus(statuses);

                Log.d(TAG,"Updated status"+domainsAll.getStatus().toString());
                domainListAdapter = new DomainListAdapter(context,domainsAll);
                recyclerView.setAdapter(domainListAdapter);
                domainListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Log.d("Response",t.toString());
            }
        });
    }


    private void updateData(Domains domains){


    }

}
