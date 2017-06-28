package com.example.haams.fragmentex;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.haams.fragmentex.adapters.MainFragPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager mainPager;
    private TabLayout mainTab;
    private MainFragPagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mainPager = (ViewPager) findViewById(R.id.viewPager);
        mainTab = (TabLayout) findViewById(R.id.main_tab);
        mainTab.setSelectedTabIndicatorHeight(10);
        mainTab.setBackgroundColor(Color.parseColor("#ffffff"));
        mainTab.setSelectedTabIndicatorColor(Color.CYAN);
        mainTab.addTab(mainTab.newTab().setText("첫 페이지"));
        mainTab.addTab(mainTab.newTab().setText("둘 페이지"));
        mainTab.addTab(mainTab.newTab().setText("셋 페이지"));
        mainTab.addTab(mainTab.newTab().setText("넷 페이지"));

        mainPagerAdapter = new MainFragPagerAdapter(getSupportFragmentManager(), mainTab.getTabCount());
        mainPager.setAdapter(mainPagerAdapter);

        mainPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainTab) {
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
                super.onPageSelected(position);
            }
        });

        mainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    /*
    메인 액티비티에서 backPressed() 메소드가 실행될 경우
    걸쳐 있는 것이 FirstMainFragment인지 FirstMainFragment 내의 SubFragment들인지 확인하고자
    구분을 주어야 한다.
    MainFragment일 경우 내부에 백스택을 체크하고 있을 경우에
    pop을 해주면 된다.
     */

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment parentFragment = fragmentManager.findFragmentById(R.id.subFrame); // subFrame에 subFragment들이 들어가기 때문에 FirstMainFragment의 프레임, 즉 부모 프레임이다.
        if (parentFragment != null && parentFragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
            /*
            부모 프레그먼트가 존재하고 , 부모 프레그먼트의 백스택 내의 프레그먼트 수가 1 이상일 경우
             */
            parentFragment.getChildFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
