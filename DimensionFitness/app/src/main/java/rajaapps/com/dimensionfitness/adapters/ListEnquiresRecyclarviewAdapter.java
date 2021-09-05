package rajaapps.com.dimensionfitness.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rajaapps.com.dimensionfitness.R;
import com.example.velm.testlib.model.Enquires;

import static rajaapps.com.dimensionfitness.adapters.ListEnquiresRecyclarviewAdapter.ACCOUNT_SID;
import static rajaapps.com.dimensionfitness.adapters.ListEnquiresRecyclarviewAdapter.AUTH_TOKEN;

/**
 * Created by Velmurugan on 7/20/2017.
 */

public class ListEnquiresRecyclarviewAdapter extends RecyclerView.Adapter<ListEnquiresRecyclarviewAdapter.MyViewHolder> {

    Context context;
    List<Enquires> enquires;

    public static final String ACCOUNT_SID = "AC1bcc6dab8a8ffe63fdc2fecc92895fd6";
    public static final String AUTH_TOKEN = "f5bb0eabd39015c3208edef8fe02a10e";


    public ListEnquiresRecyclarviewAdapter(Context context, List<Enquires> enquires) {
        this.context = context;
        this.enquires = enquires;
    }

    @Override
    public ListEnquiresRecyclarviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_enquires_recyclar_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListEnquiresRecyclarviewAdapter.MyViewHolder holder, final int position) {
        holder.tvName.setText(enquires.get(position).getEnq_name());
        holder.textViewAddress.setText(enquires.get(position).getEnq_address());

        holder.buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phnum = enquires.get(position).getEnq_phone();
                new Threaded(phnum).start();
            }
        });

        holder.buttonDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String phnum = enquires.get(position).getEnq_phone();
                Log.d("Dial", "PhoneNUmber" + phnum);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phnum));

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(callIntent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return enquires.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,textViewAddress;
        Button buttonDial,buttonMessage;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.enq_name);
            textViewAddress = (TextView)itemView.findViewById(R.id.enq_address);
            buttonDial = (Button)itemView.findViewById(R.id.buttonCall);
            buttonMessage = (Button)itemView.findViewById(R.id.buttonMessage);
        }
    }


}

class Threaded extends Thread{

    String number;

    public Threaded(String phnum) {

        this.number = phnum;
    }

    @Override
    public void run() {

        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(
                "https://api.twilio.com/2010-04-01/Accounts/AC1bcc6dab8a8ffe63fdc2fecc92895fd6/SMS/Messages");
        String base64EncodedCredentials = "Basic "
                + Base64.encodeToString(
                (ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(),
                Base64.NO_WRAP);

        httppost.setHeader("Authorization",
                base64EncodedCredentials);
        try {

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("From",
                    "+15304410884"));
            nameValuePairs.add(new BasicNameValuePair("To",
                    "+918122835316"));
            nameValuePairs.add(new BasicNameValuePair("Body",
                    "Welcome to Twilio"));

            httppost.setEntity(new UrlEncodedFormEntity(
                    nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            System.out.println("Entity post is: "
                    + EntityUtils.toString(entity));


        } catch (ClientProtocolException e) {

        } catch (IOException e) {

        }

    }
}