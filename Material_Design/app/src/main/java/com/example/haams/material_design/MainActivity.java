package com.example.haams.material_design;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haams.material_design.adapters.MainViewPagerAdapter;
import com.example.haams.material_design.events.FabListener;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton ttFab, spotFab, chatFab;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private TextView name, address;
    private TabLayout mTabs;
    MainViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFabs();
        initViews();
    }

    private void initFabs() {
        ttFab = (FloatingActionButton) findViewById(R.id.time_fab);
        spotFab = (FloatingActionButton) findViewById(R.id.spot_fab);
        chatFab = (FloatingActionButton) findViewById(R.id.chat_fab);

        ttFab.setOnClickListener(new FabListener(this));
        spotFab.setOnClickListener(new FabListener(this));
        chatFab.setOnClickListener(new FabListener(this));
    }


    private void initViews() {

        initToolBar();

        Glide.with(this).load(R.drawable.seongho)
                .into((ImageView) findViewById(R.id.main_image));

        mTabs = (TabLayout) findViewById(R.id.main_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        mTabs.addTab(mTabs.newTab().setIcon(android.R.drawable.ic_menu_my_calendar), true);
        mTabs.addTab(mTabs.newTab().setIcon(android.R.drawable.ic_dialog_map), true);
        mTabs.addTab(mTabs.newTab().setIcon(android.R.drawable.sym_action_chat), true);

        mPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), mTabs.getTabCount());
        mTabs.setupWithViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setBackgroundColor(Color.WHITE);
        mViewPager.setAdapter(mPagerAdapter);


        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        ttFab.setVisibility(View.VISIBLE);
                        spotFab.setVisibility(View.GONE);
                        chatFab.setVisibility(View.GONE);
                        break;
                    case 1:
                        ttFab.setVisibility(View.GONE);
                        spotFab.setVisibility(View.VISIBLE);
                        chatFab.setVisibility(View.GONE);
                        break;
                    case 2:
                        ttFab.setVisibility(View.GONE);
                        spotFab.setVisibility(View.GONE);
                        chatFab.setVisibility(View.VISIBLE);
                        break;

                }
                // Toast.makeText(MainActivity.this,mTabs.getSelectedTabPosition()+"/"+mTabs.getTabCount(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mToolbar.setTitleMarginStart(60);
        mToolbar.setTitleMarginEnd(40);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }


}
