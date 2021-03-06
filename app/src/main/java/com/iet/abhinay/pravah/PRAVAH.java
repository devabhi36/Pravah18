package com.iet.abhinay.pravah;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.iet.abhinay.pravah._sliders.FragmentSlider;
import com.iet.abhinay.pravah._sliders.SliderIndicator;
import com.iet.abhinay.pravah._sliders.SliderPagerAdapter;
import com.iet.abhinay.pravah._sliders.SliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Abhinay on 04-02-2018.
 */

public class PRAVAH extends Fragment {
    ImageButton ib1, ib2, ib3, ib4, ib5, ib6, ib7, ib8;
    ViewPager mViewPager;

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v1 = inflater.inflate(R.layout.pravah, container, false);
        sliderView = (SliderView)v1.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout)v1.findViewById(R.id.pagesContainer);
        setupSlider();

        return v1;
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://files.dtornado.com/headerimages/wG9Yymvug415189717578aJ6uRl3Jz_header.jpeg"));
        //fragments.add(FragmentSlider.newInstance("https://image.ibb.co/gKjp0H/img_4.jpg"));
        fragments.add(FragmentSlider.newInstance("https://img.collegepravesh.com/2016/05/IET-Lucknow.jpg"));
        fragments.add(FragmentSlider.newInstance("https://www.kclimo.com/wp-content/uploads/2015/02/hot-KC-concert-crowd.jpg"));
        fragments.add(FragmentSlider.newInstance("http://xcessjeans.com/img/uttar-pradesh.jpg"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("प्रवाह");
        getActivity().setTitleColor(R.color.colorPrimary);

        BottomNavigationView navigation = (BottomNavigationView)view.findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
         navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                 switch (item.getItemId()){
                case R.id.bweb:
                    //Web_view.num = 1;
                    //Intent i1 = new Intent(getActivity(), Web_view.class);
                    //startActivity(i1);

                    Uri webpage1 = Uri.parse("https://aktu.ac.in/pravah/");
                    Intent webIntent1 = new Intent(Intent.ACTION_VIEW, webpage1);
                    startActivity(webIntent1);
//                    String web = "https://aktu.ac.in/pravah/";
//                    WebView website = new WebView(getContext());
//                    website.loadUrl(web);
                    return true;
                case R.id.facebook:
                    //Web_view.num = 2;
                    Uri webpage2 = Uri.parse("https://www.facebook.com/aktu.pravah/");
                    Intent webIntent2 = new Intent(Intent.ACTION_VIEW, webpage2);
                    startActivity(webIntent2);
                    return true;
                case R.id.youtube:
                    //Web_view.num = 3;
                    Uri webpage3 = Uri.parse("https://www.youtube.com/channel/UCRb5zwmzj8VFdi5FRhZyTyg");
                    Intent webIntent3 = new Intent(Intent.ACTION_VIEW, webpage3);
                    startActivity(webIntent3);
                    return true;
                case R.id.insta:
                    //Web_view.num = 4;
                    Uri webpage4 = Uri.parse("https://www.instagram.com/pravah.aktu/");
                    Intent webIntent4 = new Intent(Intent.ACTION_VIEW, webpage4);
                    startActivity(webIntent4);
                    return true;
            }
                 return true;
             }
         });
    }
}
