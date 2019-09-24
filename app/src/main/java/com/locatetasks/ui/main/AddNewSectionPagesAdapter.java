package com.locatetasks.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.locatetasks.ui.main.add.AddProjectFormFragment;
import com.locatetasks.ui.main.add.AddTaskFormFragment;
import com.locatetasks.ui.main.priorities.PrioritiesListFragment;
import com.locatetasks.ui.main.project.ProjectListFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class AddNewSectionPagesAdapter extends FragmentPagerAdapter {

    private static final String[] TAB_TITLES = new String[]{"Projekt", "Zadanie"};

    private final Context mContext;


    public AddNewSectionPagesAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AddProjectFormFragment();
            case 1:
            default:
                return new AddTaskFormFragment();
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