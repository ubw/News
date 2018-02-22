package com.example.android.news;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager)findViewById(R.id.news_viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        CategoryFragmentPagerAdapter adapter = new CategoryFragmentPagerAdapter(MainActivity.this, getSupportFragmentManager());
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.news_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
