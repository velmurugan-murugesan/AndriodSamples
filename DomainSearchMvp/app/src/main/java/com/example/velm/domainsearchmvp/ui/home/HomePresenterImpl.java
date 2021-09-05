package com.example.velm.domainsearchmvp.ui.home;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.velm.domainsearchmvp.data.AppDatabase;
import com.example.velm.domainsearchmvp.model.DomainName;
import com.example.velm.domainsearchmvp.model.Words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by velmmuru on 10/11/2017.
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeMvpView homeMvpView;
    private AppDatabase appDatabase;
    private Context context;

    HomePresenterImpl(Context context,HomeMvpView homeMvpView){
        this.homeMvpView = homeMvpView;
        appDatabase = AppDatabase.getAppDatabase(context);
        this.context = context;
    }

    @Override
    public void loadDataIntoDb() {
        //homeMvpView.showProgressbar();
        //Words words = new Words("time");

        //appDatabase.wordDao().insertAll(words);

        //homeMvpView.hideProgressbar();
        new WordsTask(context).execute();

    }

    @Override
    public List<Words> showDataFromDb() {
        return appDatabase.wordDao().getAllWords();
    }

   private class WordsTask extends AsyncTask<Void, Integer, Void> {


        Context context;

        public WordsTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            homeMvpView.showProgressbar();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String[] toInsert = {"top_domains.txt","general_domains.txt","country_domains.txt","useful_words.txt"};
            int[] domainId = {1,2,1};

            for (int i = 0 ; i < toInsert.length;i++){
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(context.getAssets().open(toInsert[i])));
                    StringBuilder sb = new StringBuilder();
                    String mLine = reader.readLine();
                    int line = 0;
                    while (mLine != null) {
                        sb.append(mLine);
                        mLine = reader.readLine();
                        if(mLine != null){
                            if(toInsert[i] == "useful_words.txt"){
                                line = line + 1;
                                Words words = new Words(mLine);
                                homeMvpView.updateprogressbar(line);
                                appDatabase.wordDao().insertAll(words);
                            } else {
                                DomainName domainName = new DomainName(mLine,domainId[i]);
                                appDatabase.domainDao().insertDomain(domainName);
                            }
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Insert top domains


            /*try {
                reader = new BufferedReader(new InputStreamReader(context.getAssets().open("general_domains.txt")));
                StringBuilder sb = new StringBuilder();
                String mLine = reader.readLine();
                while (mLine != null) {
                    sb.append(mLine); // process line
                    mLine = reader.readLine();
                    if(mLine != null){
                        Log.d(TAG,mLine);
                        //Words words = new Words(mLine);
                        DomainName domainName = new DomainName(mLine,2);
                        appDatabase.domainDao().insertDomain(domainName);
                        //db.addDomains(mLine,2);
                    }

                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            // Country Domains
            try {
                reader = new BufferedReader(new InputStreamReader(context.getAssets().open("country_domains.txt")));
                StringBuilder sb = new StringBuilder();
                String mLine = reader.readLine();
                while (mLine != null) {
                    sb.append(mLine); // process line
                    mLine = reader.readLine();
                    if(mLine != null){
                        Log.d(TAG,mLine);
                        //Words words = new Words(mLine);
                        //db.addDomains(mLine,1);
                        DomainName domainName = new DomainName(mLine,1);
                        appDatabase.domainDao().insertDomain(domainName);
                    }

                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Words
            try {
                reader = new BufferedReader(new InputStreamReader(context.getAssets().open("useful_words.txt")));
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
                        //db.addUser(words);
                        appDatabase.wordDao().insertAll(words);
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
*/
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //mDialog.setProgress(Integer.parseInt(values[0].toString()));
            //
            homeMvpView.updateprogressbar(Integer.parseInt(values[0].toString()));

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            homeMvpView.hideProgressbar();
            Log.d(TAG,"Completed");
        }
    }
}
