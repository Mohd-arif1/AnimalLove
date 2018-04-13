package com.dev.arif.collapseimagetransitions;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mohdarif on 11/04/18.
 */

public class ViewPagerAdapter  extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Lion", "Tiger","Panther","Dinosaur","Cat","Elephant","Dog","Zebra"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }
    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
     return FragmentImages.newInstance(position+1);
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }
}