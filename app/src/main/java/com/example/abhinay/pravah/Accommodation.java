package com.example.abhinay.pravah;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Abhinay on 05-02-2018.
 */

public class Accommodation extends Fragment {
    CheckBox confirm;
    TextView message, upi_no, ini_status, fi_status, message1, message2;
    EditText trans_id;
    Button submit;

    String transactionID;
    boolean isChecked;

    String[ ] UPI = { "ujwlshukla@paytm (Ujjwal Shukla)", "9598162131@upi (Markanday Upadhyay)", "9170000857@ybl (Anshu Chaturvedi)", "9918753148@paytm (Shubham Jaiswal)", "priyanshujaiswal42@okaxis (Priyanshu Jaiswal)", "7985235897@paytm (Prashant Singh)", "9457178883@ybl (Ashwani Sharma)", "9457178883@ybl (Ashwani Sharma)" };

    private class smallBundle {
        String t, e;
        private smallBundle ( String t, String e ) { this.t = t; this.e = e; }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.accommodation, container, false);
        confirm = (CheckBox)v.findViewById(R.id.confirm);
        message = (TextView)v.findViewById(R.id.message);
        upi_no = (TextView)v.findViewById(R.id.upi_no);
        ini_status = (TextView)v.findViewById(R.id.ini_status);
        fi_status = (TextView)v.findViewById(R.id.fi_status);
        message1 = (TextView)v.findViewById(R.id.message1);
        message2 = (TextView)v.findViewById(R.id.message2);
        trans_id = (EditText)v.findViewById(R.id.trans_id);
        submit = (Button)v.findViewById(R.id.submitID);

        if ( Integer.parseInt(MainActivity._transactionStatus) < 2 ) {

            if ( MainActivity._zone.equals(" LUCKNOW") )
            {
                confirm.setVisibility(View.VISIBLE);
            }
            else
            {
                message.setVisibility(View.VISIBLE);
                upi_no.setVisibility(View.VISIBLE);

                message2.setVisibility(View.VISIBLE);
                trans_id.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
            }

            if ( Integer.parseInt(MainActivity._transactionStatus) == 1 )
            {
                ini_status.setText("Payment Verification Pending.");
                message1.setText("Filled in Transaction ID: "+MainActivity._transactionID);
            }
        }
        else
        {
            fi_status.setText("Payment Verified.");
        }

        isChecked = confirm.isChecked();
        confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ( !isChecked ) {
                    message.setVisibility(View.VISIBLE);
                    upi_no.setVisibility(View.VISIBLE);

                    message2.setVisibility(View.VISIBLE);
                    trans_id.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.VISIBLE);

                    isChecked = true;
                }
                else
                {
                    message.setVisibility(View.INVISIBLE);
                    upi_no.setVisibility(View.INVISIBLE);

                    message2.setVisibility(View.INVISIBLE);
                    trans_id.setVisibility(View.INVISIBLE);
                    submit.setVisibility(View.INVISIBLE);

                    isChecked = false;
                }
            }
        });

        if ( Integer.parseInt(MainActivity._transactionStatus) == 1 ) {
            submit.setText("UPDATE");
        }

        if ( MainActivity._zone.equals(" AGRA")){
            upi_no.setText(UPI[0]);
        }
        else if ( MainActivity._zone.equals(" ALLAHABAD")){
            upi_no.setText(UPI[1]);
        }
        else if ( MainActivity._zone.equals(" BAREILLY")){
            upi_no.setText(UPI[2]);
        }
        else if ( MainActivity._zone.equals(" GAUTAM BUDH NAGAR")){
            upi_no.setText(UPI[3]);
        }
        else if ( MainActivity._zone.equals(" GHAZIABAD")){
            upi_no.setText(UPI[4]);
        }
        else if ( MainActivity._zone.equals(" GORAKHPUR")){
            upi_no.setText(UPI[5]);
        }
        else if ( MainActivity._zone.equals(" LUCKNOW")){
            upi_no.setText(UPI[6]);
        }
        else {
            upi_no.setText(UPI[7]);
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transactionID = trans_id.getText().toString();

                if ( transactionID.length() > 0 ) {

                    smallBundle bundle = new smallBundle(transactionID, MainActivity._email);
                    new phpCaller().execute(bundle);
                }
                else
                {
                    Toast.makeText(getContext(), "Empty Transaction ID.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private class phpCaller extends AsyncTask<smallBundle, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(smallBundle... data) {
            String trans = data[0].t;
            String email = data[0].e;

            try {

                String link = MainActivity.host + "transaction.php";
                String data_ = URLEncoder.encode("transactionID", "UTF-8") + "=" + URLEncoder.encode(trans, "UTF-8") + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data_);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if ( result.equals("Transaction ID saved.") ) {

                MainActivity._transactionStatus = "1";
                MainActivity._transactionID = transactionID;

                ini_status.setText("Payment Verification Pending.");
                message1.setText("Filled in Transaction ID: "+transactionID);
                submit.setText("UPDATE");

                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("ACCOMMODATION");
        MainActivity.forback = 1;
    }
}
