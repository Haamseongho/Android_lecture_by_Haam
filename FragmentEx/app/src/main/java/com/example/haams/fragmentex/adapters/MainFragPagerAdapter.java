package com.example.haams.fragmentex.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.haams.fragmentex.mainFragments.FirstMainFragment;
import com.example.haams.fragmentex.mainFragments.FourthMainFragment;
import com.example.haams.fragmentex.mainFragments.SecondMainFragment;
import com.example.haams.fragmentex.mainFragments.ThirdMainFragment;

/**
 * Created by haams on 2017-06-27.
 */

public class MainFragPagerAdapter extends FragmentStatePagerAdapter {
    private int pageNum;

    public MainFragPagerAdapter(FragmentManager fm, int pageNum) {
        super(fm);
        this.pageNum = pageNum;
    }

    public static Fragment getFragmentInstance(int pageNum) {
        Fragment fragment = null;
        switch (pageNum) {
            case 0:
                fragment = FirstMainFragment.newInstance("first", "fragment");
                break;
            case 1:
                fragment = SecondMainFragment.newInstance("second", "fragment");
                break;
            case 2:
                fragment = ThirdMainFragment.newInstance("third", "fragment");
                break;
            case 3:
                fragment = FourthMainFragment.newInstance("fourth", "fragment");
                break;

        }
        return fragment;
    }

    public MainFragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return getFragmentInstance(position);
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
