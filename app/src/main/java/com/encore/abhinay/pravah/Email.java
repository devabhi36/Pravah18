package com.encore.abhinay.pravah;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Email extends AppCompatActivity {
    EditText subject, content;
    String gsubject, gcontent;
    Toolbar email_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_email);

        subject = (EditText) findViewById(R.id.subject);
        content = (EditText) findViewById(R.id.content);
        email_toolbar = (Toolbar) findViewById(R.id.email_toolbar);
        email_toolbar.setTitle("Compose");
        email_toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        Button startBtn = (Button) findViewById(R.id.sendEmail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    public void message(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("We will get back to you soon :) ")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Email.this, PRAVAH.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(" ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });
        android.app.AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Thanks for your feedback..!");
        alert.show();
    }
    protected void sendEmail() {
        gsubject = String.valueOf(subject.getText());
        gcontent = String.valueOf(content.getText());
        Log.i("Send email", "");
        String[] TO = {"pravah@aktu.ac.in"};
        String[] CC = {"abhinayshrivatsav2@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_BCC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, gsubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, gcontent);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.i("Finished send email...", "");
            message();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    public void back(View back){
        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Email.this, PRAVAH.class);
        startActivity(intent);
    }

}
