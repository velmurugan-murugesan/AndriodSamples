package com.example.velm.ds;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by velmmuru on 9/19/2017.
 */

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.MyViewHolder> {

    Context context;
    List<Words> wordsList;
    DomainSearch domainSearch;
    boolean isPs;

    List<String> selectedDomains;
    StringBuilder searchUrl = null;
    DatabaseHandler db;

    public WordsAdapter(Context context, List<Words> wordsList, DomainSearch domainSearch, boolean isPs) {
        this.context = context;
        this.wordsList = wordsList;
        this.domainSearch = domainSearch;
        this.isPs = isPs;
        selectedDomains = new ArrayList<>();
        db = new DatabaseHandler(context);
    }

    @Override
    public WordsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.word_adapter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WordsAdapter.MyViewHolder holder, int position) {

        if(isPs){
            holder.textViewWord.setText(domainSearch.getName()+wordsList.get(position).getWord());
        } else {
            holder.textViewWord.setText(wordsList.get(position).getWord()+domainSearch.getName());
        }

        holder.buttonCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDomains.clear();
                selectedDomains.add(".com");

                checkStatus(holder.textViewWord.getText().toString(),selectedDomains);

            }
        });

        holder.buttonTopD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDomains = db.getAllDomains(1);
                checkStatus(holder.textViewWord.getText().toString(),selectedDomains);

            }
        });

        holder.buttonGend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDomains = db.getAllDomains(2);
                checkStatus(holder.textViewWord.getText().toString(),selectedDomains);
            }
        });

        holder.textViewWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // selectDomainDialog(holder.textViewWord.getText().toString());

            }
        });
    }

    private void checkStatus(String name, List<String> selectedDomains) {

        searchUrl = new StringBuilder();
        for (int i = 0; i < selectedDomains.size(); i++){
            String domain = "";

            if(i == selectedDomains.size() -1){
                domain = name+selectedDomains.get(i);
            } else {
                domain = name+selectedDomains.get(i)+",";
            }
            searchUrl.append(domain);
        }
        Log.d(TAG,searchUrl.toString());

        callToRest(name,searchUrl);


    }

    private void callToRest(String name, StringBuilder searchUrl){
        {
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

                    //domainListAdapter = new DomainListAdapter(context,domains);

                    //recyclerView.setAdapter(domainListAdapter);

                    //domainListAdapter.notifyDataSetChanged();

                    showDomainStatus(domains);

                }

                @Override
                public void onFailure(Call call, Throwable t) {

                    Log.d("Response",t.toString());
                }
            });
        }


    }




    private void showDomainStatus(Domains domains) {

            View inflater = LayoutInflater.from(context).inflate(R.layout.status_window,null);

            Button buttonWindowClose = (Button)inflater.findViewById(R.id.buttonWindowClose);

            RecyclerView recyclerViewWindow = (RecyclerView)inflater.findViewById(R.id.recyclerviewWindow);


            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);

            recyclerViewWindow.setLayoutManager(layoutManager);

            DomainListAdapter domainListAdapter = new DomainListAdapter(context,domains);

            recyclerViewWindow.setAdapter(domainListAdapter);

            /*TextView textViewWindowName = (TextView)inflater.findViewById(R.id.textViewWindowName);
            textViewWindowName.setText(name);

            TextView textViewWindowStatus = (TextView)inflater.findViewById(R.id.textViewWindowStatus);
            textViewWindowStatus.setText(status);
*/
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setView(inflater);
            alert.setCancelable(true);
            final AlertDialog dialog = alert.create();

            buttonWindowClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();
                }
            });

            dialog.show();

    }


    private void selectDomainDialog(final String name) {

        View inflater = LayoutInflater.from(context).inflate(R.layout.select_domain_dialog,null);

        Button buttonSelectSearch = (Button)inflater.findViewById(R.id.buttonSelectSearch);

        TextView textViewSelectName = (TextView)inflater.findViewById(R.id.textViewSelectName);
        textViewSelectName.setText(name);

        RadioGroup radioGroup = (RadioGroup)inflater.findViewById(R.id.radioGroupSelect);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                if(i == R.id.radioButtonSelectCom){

                    selectedDomains.clear();
                    selectedDomains.add(".com");

                } else if (i == R.id.radioButtonSelectTopD){
                    selectedDomains = db.getAllDomains(1);

                } else if(i == R.id.radioButtonSelectGenD){
                    selectedDomains = db.getAllDomains(2);
                }
            }
        });
        /*TextView textViewWindowStatus = (TextView)inflater.findViewById(R.id.textViewWindowStatus);
        textViewWindowStatus.setText(status);*/

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(inflater);
        alert.setCancelable(true);
        final AlertDialog dialog = alert.create();

        buttonSelectSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                checkStatus(name,selectedDomains);
            }
        });

        dialog.show();

    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewWord;

        Button buttonCom,buttonTopD,buttonGend;
        public MyViewHolder(View itemView) {
            super(itemView);

            textViewWord = (TextView)itemView.findViewById(R.id.textViewWord);

            buttonCom = (Button)itemView.findViewById(R.id.buttonCom);
            buttonTopD = (Button)itemView.findViewById(R.id.buttonTopD);
            buttonGend = (Button)itemView.findViewById(R.id.buttonGenD);

        }
    }
}
