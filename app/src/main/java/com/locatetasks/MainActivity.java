package com.locatetasks;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.locatetasks.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("MAIN ACTIVITY", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new OnFabClickListener(this));
    }


    class OnFabClickListener implements View.OnClickListener {
        private final AppCompatActivity compatActivity;

        OnFabClickListener(AppCompatActivity compatActivity) {
            this.compatActivity = compatActivity;
        }

        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(compatActivity.getBaseContext(), AddModelActivity.class);
            compatActivity.getBaseContext().startActivity(myIntent);
        }
    }
}
