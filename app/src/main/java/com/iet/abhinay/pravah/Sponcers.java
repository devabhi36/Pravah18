package com.iet.abhinay.pravah;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class Sponcers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponcers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void sp1(View s1){
        String url = "http://www.uptourism.gov.in/";
        WebView webView = new WebView(getApplicationContext());
        webView.loadUrl(url);
    }
    public void sp2(View s2){
        String url = "http://swachhbharaturban.gov.in/ihhl/";
        WebView webView = new WebView(getApplicationContext());
        webView.loadUrl(url);
    }
    public void sp3(View s3){
        String url = "https://www.indiamart.com/proddetail/lucknow-central-gomti-nagar-6771064612.html";
        WebView webView = new WebView(getApplicationContext());
        webView.loadUrl(url);
    }
    public void sp4(View s4){
        String url = "http://blueworld.co.in/packages.php";
        WebView webView = new WebView(getApplicationContext());
        webView.loadUrl(url);
    }
}
