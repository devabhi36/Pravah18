package com.example.abhinay.pravah;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Web_view extends AppCompatActivity {
    WebView web;
    String url = "http://www.google.com";
    public static int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        web = (WebView) findViewById(R.id.web);
        web.setWebViewClient(new myWebClient());
        web.getSettings().setJavaScriptEnabled(true);
        if (num == 1) {
            web.loadUrl("https://aktu.ac.in/pravah/");
        } else if(num == 2){
            web.loadUrl("https://www.facebook.com/aktu.pravah/");
        } else if(num == 3){
            web.loadUrl("https://www.youtube.com/channel/UCRb5zwmzj8VFdi5FRhZyTyg");
        } else if(num == 4){
            web.loadUrl("https://www.instagram.com/pravah.aktu/");
        } else if(num == 5){
            web.loadUrl("https://aktu.ac.in/vice-chancellor-profile.html");
        } else if(num == 6){
            web.loadUrl("https://www.linkedin.com/in/h-k-paliwal-bb07ba67/");
        } else if(num == 7){
            web.loadUrl("https://www.ietlucknow.ac.in/people/jbsrivastava");
        } else if(num == 8){
            web.loadUrl("https://www.ietlucknow.ac.in/people/pshukla");
        } else if(num == 9){
            web.loadUrl("https://www.linkedin.com/in/as1echamp9/");
        } else if(num == 10){
            web.loadUrl("https://www.linkedin.com/in/anushka-awasthi-635b89124/");
        } else if(num == 11){
            web.loadUrl("https://www.linkedin.com/in/abhinay-s-4bb60894/");
        } else if(num == 12){
            web.loadUrl("https://www.linkedin.com/in/harshal-dev-39778b147/");
        }
    }
    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
// TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
// TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
