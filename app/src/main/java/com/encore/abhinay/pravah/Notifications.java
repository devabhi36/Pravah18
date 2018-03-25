package com.encore.abhinay.pravah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.messaging.RemoteMessage;

public class Notifications extends AppCompatActivity {
    TextView title, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        title = (TextView) findViewById(R.id.ntitle);
        message = (TextView) findViewById(R.id.nmessage);
    }
}
