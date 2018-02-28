package com.example.abhinay.pravah;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Abhinay on 22-01-2018.
 */

public class SlideShow extends PagerAdapter {
    Context mContext;
    LayoutInflater layoutInflater;

    SlideShow(Context context){
        this.mContext=context;
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view==((ImageView)object);
    }

    private int[] imagesliderID=new int[]{R.drawable.img3, R.drawable.img4, R.drawable.img2};


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView imageView = new ImageButton(mContext);
        imageView.setScaleType(ImageButton.ScaleType.FIT_START);
        imageView.setImageResource(imagesliderID[position]);


        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return imagesliderID.length;
    }
}
