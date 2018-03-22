package com.encore.abhinay.pravah;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class ChangePass extends Fragment {
    EditText oldpass, newpass, conpass;
    Button changePas;

    private class smallBundle {
        String o, p, e;
        private smallBundle ( String o, String p, String e ) { this.o = o; this.p = p; this.e = e; }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.change_pass, container, false);
        oldpass = (EditText)v.findViewById(R.id.oldpass);
        newpass = (EditText)v.findViewById(R.id.newpass);
        conpass = (EditText)v.findViewById(R.id.conpass);
        changePas = (Button)v.findViewById(R.id.pass);

        changePas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String oldPassword = oldpass.getText().toString();
                String newPassword = newpass.getText().toString();
                String conPassword = conpass.getText().toString();

                if ( newPassword.equals(conPassword) ) {

                    if ( newPassword.length() >= 8 )
                    {
                        smallBundle bundle = new smallBundle( oldPassword, newPassword, MainActivity._email );
                        new phpCaller().execute(bundle);
                    }
                    else {
                        Toast.makeText(getContext(), "Password must be atleast 8 characters long.", Toast.LENGTH_SHORT).show();
                    }
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.setCustomAnimations(R.anim.slide_outb, R.anim.fadeout);
//            ft.replace(R.id.content_frame, new Dashboard(), "fragment");
//            ft.commit();
                }
                else
                {
                    Toast.makeText(getContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();

                    conpass.setText(null);
                    newpass.setText(null);
                }
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("प्रवाह");
        MainActivity.forback = 1;
    }

    private class phpCaller extends AsyncTask<smallBundle, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(smallBundle... data) {
            String old = data[0].o;
            String pas = data[0].p;
            String email = data[0].e;

            try {

                String link = MainActivity.host + "change.php";
                String data_ = URLEncoder.encode("oldpassword", "UTF-8") + "=" + URLEncoder.encode(old, "UTF-8") + "&" + URLEncoder.encode("newpassword", "UTF-8") + "=" + URLEncoder.encode(pas, "UTF-8") + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
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
            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}
