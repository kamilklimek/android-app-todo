package com.locatetasks.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.locatetasks.ui.main.add.AddProjectFormFragment;
import com.locatetasks.ui.main.priorities.PrioritiesListFragment;
import com.locatetasks.ui.main.project.ProjectListFragment;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String[] TAB_TITLES = new String[]{"Priorytety", "Projekty"};

    private final Context mContext;


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PrioritiesListFragment();
            case 1:
                return new ProjectListFragment();
            default:
                return new ProjectListFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}