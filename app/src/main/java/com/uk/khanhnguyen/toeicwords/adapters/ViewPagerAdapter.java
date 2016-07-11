package com.uk.khanhnguyen.toeicwords.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Khanh Nguyen on 7/9/2016.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private static final String TAG = "ViewPagerAdapter";
    private LayoutInflater layoutInflater;

    private WeakReference<Context> mContext;
    private int[] mLayoutsList;

    public ViewPagerAdapter(Context context, int[] layouts) {
        Log.d(TAG, "ViewPagerAdapter: layout.length: " + layouts.length);
        this.mContext =  new WeakReference<Context>(context);
        mLayoutsList = layouts;
        Log.d(TAG, "ViewPagerAdapter: mLayoutsList.length: " + mLayoutsList.length);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) mContext.get().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(mLayoutsList[position], container, false);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mLayoutsList.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
