package com.example.abhinay.pravah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("PRAVAH 18");
         BottomNavigationView navigation = (BottomNavigationView)view.findViewById(R.id.navigation);
         navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                 switch (item.getItemId()){
                case R.id.iet: Toast.makeText(getContext(), "IET", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.facebook: String fb = "https://www.facebook.com/aktu.pravah/";
                    WebView facebook = new WebView(getContext());
                    facebook.loadUrl(fb);
                    return true;
                case R.id.youtube: String yt = "https://www.youtube.com/channel/UCRb5zwmzj8VFdi5FRhZyTyg";
                    WebView youtube = new WebView(getContext());
                    youtube.loadUrl(yt);
                    return true;
                case R.id.insta: String insta = "https://www.instagram.com/pravah.aktu/";
                    WebView instagram = new WebView(getContext());
                    instagram.loadUrl(insta);
                    return true;
            }
                 return false;
             }
         });
    }

}
