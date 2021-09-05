package com.example.velm.ds;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button btnSearch;

    private RadioGroup radioGroup,radioGroupDomains;

    private RadioButton radioButtonFS,radioButtonPS,radioButtonSS;

    LinearLayout psLayout;

    EditText editTextDomainName;

    public String TAG ="MainActivity";

    DatabaseHandler db ;

    String[] letrers = { "2", "3", "4", "5", "6","7","8","9"};

    Spinner spinner;

    String selectedType;

    List<String> selectedDomains;

    RecyclerView recyclerViewMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = (Button)findViewById(R.id.buttonSearch);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        psLayout = (LinearLayout)findViewById(R.id.ps_layout);
        radioGroupDomains = (RadioGroup)findViewById(R.id.radioGroupDomains);

        spinner = (Spinner)findViewById(R.id.spinner);

        recyclerViewMain = (RecyclerView)findViewById(R.id.recyclerviewMain);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewMain.setLayoutManager(layoutManager);




        spinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,letrers);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);

        radioButtonFS = (RadioButton)findViewById(R.id.radioButtonFS);
        radioButtonPS = (RadioButton)findViewById(R.id.radioButtonPS);
        radioButtonSS = (RadioButton)findViewById(R.id.radioButtonSS);

        editTextDomainName = (EditText)findViewById(R.id.editTextDomainName);
        selectedDomains = new ArrayList<>();


        selectedType = "fs";
        db = new DatabaseHandler(this);

        //List<Words> words  = db.getAllWords();

        /*if(words != null && words.size() > 0 ){

            Log.d(TAG,words.size()+"");
        } else {
            Log.d(TAG,"GetNodes");
            new GetNodes().execute();
        }*/

        if(db.getAllDomains(1) == null || db.getAllDomains(1).size() == 0){
            new GetNodes(this).execute();
        }


        /*try {
            readFromAssets(getApplicationContext(),"words.txt");
        } catch (IOException e) {
            e.printStackTrace();

        }*/

        //startService(new Intent(getApplicationContext(),InsertService.class));



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                if(i == R.id.radioButtonFS){
                    Log.d(TAG,"radioButtonFS");
                    psLayout.setVisibility(View.GONE);
                    selectedType = "fs";
                    radioGroupDomains.setVisibility(View.VISIBLE);

                } else if (i == R.id.radioButtonPS){
                    Log.d(TAG,"radioButtonPS");
                    selectedType = "ps";
                    psLayout.setVisibility(View.VISIBLE);
                    radioGroupDomains.setVisibility(View.GONE);

                } else {
                    selectedType = "ss";
                    Log.d(TAG,"radioButtonSS");
                    psLayout.setVisibility(View.VISIBLE);
                    radioGroupDomains.setVisibility(View.GONE);
                }
            }
        });

        radioGroupDomains.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                if(i == R.id.radioButtonTopD){

                    selectedDomains = db.getAllDomains(1);

                } else if (i == R.id.radioButtonCom){
                    selectedDomains.clear();
                    selectedDomains.add(".com");
                } else if(i == R.id.radioButtonGenD){
                    selectedDomains = db.getAllDomains(2);
                }
            }
        });

    }

    public void readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine); // process line
            mLine = reader.readLine();
            Log.d(TAG,mLine);
        }
        reader.close();
    }






    public void searchDomain(View view){

        Intent domainListIntent = new Intent(getApplicationContext(),DomainListActivity.class);


        db = new DatabaseHandler(this);
        //List<String> extension = db.getAllDomains(1);
        /*extension.add("com");
        extension.add("info");
        extension.add("in");*/

        String selected = spinner.getSelectedItem().toString();


        if(selectedType == "fs"){
            DomainSearch domainSearch = new DomainSearch(editTextDomainName.getText().toString(),"fs",0,selectedDomains);
            domainListIntent.putExtra("search", domainSearch);
            startActivity(domainListIntent);
        } else if(selectedType == "ps"){
            DomainSearch domainSearch = new DomainSearch(editTextDomainName.getText().toString(),"ps",Integer.parseInt(selected),selectedDomains);
            domainListIntent.putExtra("search", domainSearch);
            startActivity(domainListIntent);
        } else {
            DomainSearch domainSearch = new DomainSearch(editTextDomainName.getText().toString(),"ss",Integer.parseInt(selected),selectedDomains);
            domainListIntent.putExtra("search", domainSearch);
            startActivity(domainListIntent);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(getApplicationContext(),letrers[i] ,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private class GetNodes extends AsyncTask<Void, Integer, Void> {


        Context context;
        private ProgressDialog mDialog;

        public GetNodes(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog = new ProgressDialog(context);
            mDialog.setMessage("Doing something...");
            mDialog.setCancelable(false);
            mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mDialog.setMax(4000);
            mDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            //Insert top domains
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open("top_domains.txt")));
                StringBuilder sb = new StringBuilder();
                String mLine = reader.readLine();
                while (mLine != null) {
                    sb.append(mLine); // process line
                    mLine = reader.readLine();
                    if(mLine != null){
                        Log.d(TAG,mLine);
                        //Words words = new Words(mLine);
                        db.addDomains(mLine,1);
                    }

                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                reader = new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open("general_domains.txt")));
                StringBuilder sb = new StringBuilder();
                String mLine = reader.readLine();
                while (mLine != null) {
                    sb.append(mLine); // process line
                    mLine = reader.readLine();
                    if(mLine != null){
                        Log.d(TAG,mLine);
                        //Words words = new Words(mLine);
                        db.addDomains(mLine,2);
                    }

                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            // Country Domains
            try {
                reader = new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open("country_domains.txt")));
                StringBuilder sb = new StringBuilder();
                String mLine = reader.readLine();
                while (mLine != null) {
                    sb.append(mLine); // process line
                    mLine = reader.readLine();
                    if(mLine != null){
                        Log.d(TAG,mLine);
                        //Words words = new Words(mLine);
                        db.addDomains(mLine,1);
                    }

                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Words
            try {
                reader = new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open("useful_words.txt")));
                StringBuilder sb = new StringBuilder();
                String mLine = reader.readLine();
                int line = 0;

                while (mLine != null) {
                    line = line + 1;
                    sb.append(mLine); // process line
                    mLine = reader.readLine();
                    if(mLine != null){
                        Log.d(TAG,mLine);
                        Words words = new Words(mLine);
                        publishProgress(line);
                        db.addUser(words);
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mDialog.setProgress(Integer.parseInt(values[0].toString()));
            //

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
            Log.d(TAG,"Completed");
            Toast.makeText(getApplicationContext(),"Completed",Toast.LENGTH_SHORT).show();
        }
    }
}
