package com.locatetasks;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.locatetasks.ui.main.AddNewSectionPagesAdapter;

public class AddModelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_model);
        AddNewSectionPagesAdapter addNewSectionPagesAdapter= new AddNewSectionPagesAdapter(this, getSupportFragmentManager());
        ViewPager addNewPager = findViewById(R.id.add_model_pager);
        TabLayout addTabs = findViewById(R.id.add_tabs);
        addNewPager.setAdapter(addNewSectionPagesAdapter);
        addTabs.setupWithViewPager(addNewPager);
    }


    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        getBaseContext().startActivity(myIntent);
        startActivity(myIntent);
    }


}
