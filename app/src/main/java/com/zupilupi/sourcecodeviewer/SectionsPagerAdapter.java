package com.zupilupi.sourcecodeviewer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zupilupi.sourcecodeviewer.fragments.HtmlResourcesFragment;
import com.zupilupi.sourcecodeviewer.fragments.PreviewFragment;
import com.zupilupi.sourcecodeviewer.fragments.SourceCodeFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    int activeFragments;

    public SectionsPagerAdapter(FragmentManager fm, int activeFragments) {
        super(fm);
        this.activeFragments = activeFragments;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment;
        switch (position) {
            default:
            case 0:
                fragment = new SourceCodeFragment();
                break;
            case 1:
                fragment = new HtmlResourcesFragment();
                break;
            case 2:
                fragment = new PreviewFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return this.activeFragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "source-code";
            case 1:
                return "resources";
            case 2:
                return "preview";
            default:
                return "";
        }
    }
}
