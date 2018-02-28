package ru.medvedovo.tablayouttest.presentation.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.medvedovo.tablayouttest.other.FragmentFabric;

public class TabsPager extends FragmentStatePagerAdapter {

    private int tabCount;

    public TabsPager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Bundle catsBundle = new Bundle();
                catsBundle.putString("", "cat");
                return (Fragment) FragmentFabric.create(FragmentFabric.Type.LIST, catsBundle);
            case 1:
                Bundle dogsBundle = new Bundle();
                dogsBundle.putString("", "dog");
                return (Fragment) FragmentFabric.create(FragmentFabric.Type.LIST, dogsBundle);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
