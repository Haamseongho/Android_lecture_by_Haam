package com.example.haams.material_design.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.haams.material_design.main_tabs.ChatFragment;
import com.example.haams.material_design.main_tabs.Spot_Tracking;
import com.example.haams.material_design.main_tabs.TimeTable;

/**
 * Created by haams on 2017-08-22.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private int pageNum;

    public MainViewPagerAdapter(FragmentManager fm, Context context, int pageNum) {
        super(fm);
        this.context = context;
        this.pageNum = pageNum;
    }

    public MainViewPagerAdapter(FragmentManager fm, int pageNum) {
        super(fm);
        this.pageNum = pageNum;
    }

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public static Fragment newFragmentInstance(int pageNum){
        Fragment fragment = null;
        switch (pageNum){
            case 0:
                fragment = TimeTable.newInstance("time","table");
                break;
            case 1:
                fragment = Spot_Tracking.newInstance("spot","tracking");
                break;
            case 2:
                fragment = ChatFragment.newInstance("chat","paper");
                break;
        }
        return fragment;
    }
    @Override
    public Fragment getItem(int position) {
        return newFragmentInstance(position);
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
