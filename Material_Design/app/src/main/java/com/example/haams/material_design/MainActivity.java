package com.example.haams.material_design;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.haams.material_design.adapters.MainViewPagerAdapter;
import com.example.haams.material_design.events.FabListener;
import com.example.haams.material_design.main_tabs.TimeTable;

import java.util.function.ToLongBiFunction;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton ttFab, spotFab, chatFab;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private TextView name, address;
    private TabLayout tabLayout;
    private MainViewPagerAdapter mPagerAdapter;
    private DrawerLayout mainDrawer;
    private NavigationView navView;
    private ActionBarDrawerToggle mainDrawerToggle;
    Menu menu;
    private int menuIndex = 0;
    private int subMenuIndex = 0;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initViewPagingAndTabLayout();
        initToolBars();
        initDrawer();
    }


    private void initViews() {
        tabLayout = (TabLayout) findViewById(R.id.main_tablayout);
    }

    private void initViewPagingAndTabLayout() {
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
        tabLayout.setSelectedTabIndicatorHeight(6);
        tabLayout.addTab(tabLayout.newTab().setIcon(android.R.drawable.ic_menu_my_calendar).setText("일정정리"));
        tabLayout.addTab(tabLayout.newTab().setIcon(android.R.drawable.ic_dialog_map).setText("모임장소"));
        tabLayout.addTab(tabLayout.newTab().setIcon(android.R.drawable.sym_action_chat).setText("채팅하기"));



        mPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                menuIndex = position;
                // 각 페이지 마다 메뉴를 다르게 하기 위해서
                super.onPageSelected(position);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (menuIndex) {
            case 0:
                TimeTableMenu(menu);
                break;
            case 1:
                SpotMenu(menu);
                break;
            case 2:
                ChatMenu(menu);
                break;

        }
        return super.onCreateOptionsMenu(menu);
    }


    private void TimeTableMenu(Menu menu) {
        Log.i(TAG, menuIndex + "일정 정리");
        getMenuInflater().inflate(R.menu.time_alarm_menu, menu);
    }

    private void SpotMenu(Menu menu) {
        Log.i(TAG, menuIndex + "장소 정하기");
        getMenuInflater().inflate(R.menu.spot_menu, menu);
    }

    private void ChatMenu(Menu menu) {
        Log.i(TAG, menuIndex + "채팅 알림");
        getMenuInflater().inflate(R.menu.chat_menu, menu);
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        Log.i(TAG + "mainMenu", menuIndex + "옵션 만들기");
        menu.clear(); // 전체 초기화
        switch (menuIndex) {
            case 0:
                TimeTableMenu(menu);
                break;
            case 1:
                SpotMenu(menu);
                break;
            case 2:
                ChatMenu(menu);
                break;
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (menuIndex) {
            case 0:
                switch (item.getItemId()) {
                    case R.id.alarm_item:
                        Toast.makeText(MainActivity.this, "일정 알람 선택", Toast.LENGTH_LONG).show();
                        subMenuIndex = 1;
                        break;
                }
                break;
            case 1:
                switch (item.getItemId()) {
                    case R.id.spot_item:
                        Toast.makeText(MainActivity.this, "장소 알람 선택", Toast.LENGTH_LONG).show();
                        subMenuIndex = 2;
                        break;

                }
                break;

            case 2:
                switch (item.getItemId()) {
                    case R.id.chat_item:
                        Toast.makeText(MainActivity.this, "채팅 알람 선택", Toast.LENGTH_LONG).show();
                        subMenuIndex = 3;
                        break;
                }
                break;
        }
        if (mainDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private void initToolBars() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_apps_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initCollapsingToolBar();
    }

    private void initCollapsingToolBar() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.col_toolbar);
        collapsingToolbarLayout.setTitle("나만의 관리표");
    }

    private void initDrawer() {
        mainDrawer = (DrawerLayout) findViewById(R.id.drawer);
        navView = (NavigationView) findViewById(R.id.main_nav);
        mainDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                mainDrawer, R.string.drawer_open, R.string.drawer_close);

        mainDrawer.addDrawerListener(mainDrawerToggle);


        // DrawerToggle에 따라서 DrawerLayout이 열리고 닫히고 정리
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mainDrawer.closeDrawers();
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.nav_item1:
                        Log.i(TAG, "Nav_menu1");
                        break;
                    case R.id.nav_item2:
                        Log.i(TAG, "Nav_menu2");
                        break;
                    case R.id.nav_item3:
                        Log.i(TAG, "Nav_menu3");
                        break;
                    case R.id.nav_item4:
                        Log.i(TAG, "Nav_menu4");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mainDrawerToggle.syncState();
    }
}
