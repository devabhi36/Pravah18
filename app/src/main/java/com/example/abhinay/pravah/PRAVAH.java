package com.example.abhinay.pravah;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Abhinay on 04-02-2018.
 */

public class PRAVAH extends Fragment {
    ImageButton ib1, ib2, ib3, ib4, ib5, ib6, ib7, ib8;
    ViewPager mViewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v1= inflater.inflate(R.layout.pravah, container, false);

//        BottomNavigationView navigation = (BottomNavigationView)v1.findViewById(R.id.navigation);
//        Menu menuBottomNav=navigation.getMenu();
//        MenuItem home = menuBottomNav.findItem(R.id.bottomhome);
//        home.setChecked(true);

        mViewPager = (ViewPager) v1.findViewById(R.id.viewPage);
        final SlideShow adapterView = new SlideShow(getContext());
        mViewPager.setAdapter(adapterView);
        // code for slide show in fragment starts here
        final long delay = 4000;// timer for an image
        final Handler handler = new Handler();
         final int[] pagerIndex = {-1};
         Runnable swipeTask = new Runnable() {
            @Override
            public void run() {

                pagerIndex[0]++;
                if (pagerIndex[0] >= adapterView.getCount()) {
                    pagerIndex[0] = 0;
                }

                mViewPager.setCurrentItem(pagerIndex[0]);
                handler.postDelayed(this, delay);
            }
        };
        swipeTask.run(); //to start the slide show
        // ends here

        return v1;
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("प्रवाह'18");



        BottomNavigationView navigation = (BottomNavigationView)view.findViewById(R.id.navigation);
         navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                 switch (item.getItemId()){
                case R.id.web:
                    Web_view.num = 1;
                    Intent i1 = new Intent(getActivity(), Web_view.class);
                    startActivity(i1);
//                    String web = "https://aktu.ac.in/pravah/";
//                    WebView website = new WebView(getContext());
//                    website.loadUrl(web);
                    return true;
                case R.id.facebook: Web_view.num = 2;
                    Intent i2 = new Intent(getActivity(), Web_view.class);
                    startActivity(i2);
                    return true;
                case R.id.youtube: Web_view.num = 3;
                    Intent i3 = new Intent(getActivity(), Web_view.class);
                    startActivity(i3);
                    return true;
                case R.id.insta: Web_view.num = 4;
                    Intent i4 = new Intent(getActivity(), Web_view.class);
                    startActivity(i4);
                    return true;
            }
                 return false;
             }
         });
    }
}
