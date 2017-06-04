package com.example.da08.viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    Fragment one;
    Fragment two;
    Fragment three;
    Fragment four;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1 뷰페이저 위젯 연결
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("1"));
        tabLayout.addTab(tabLayout.newTab().setText("2"));
        tabLayout.addTab(tabLayout.newTab().setText("3"));
        tabLayout.addTab(tabLayout.newTab().setText("4"));

        // 2 프래그먼트들 생성
        one = new OneFragment();
        two = new TwoFragment();
        three = new ThreeFragment();
        four = new FourFragment();

        // 3 프래그먼트를 datas 저장소에 담은 후
        List<Fragment> datas = new ArrayList<>();  // 상속받는게 프래그먼트라서 제네릭이 프래그먼트임
        datas.add(one);
        datas.add(two);
        datas.add(three);
        datas.add(four);

        // 4 프래그먼트 매니저와 함께 아답터 연결
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), datas);  // 연결을 해줘야함
        // 5 아답터를 페이지 위젯에 연결
        viewPager.setAdapter(adapter);

        // 6 페이저가 변경되었을때 탭을 변경해주는 리스너
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // 7 탭이 변경되었을때 페이저를 변경해주는 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    class PagerAdapter extends FragmentPagerAdapter{
        List<Fragment> datas;

        public PagerAdapter(FragmentManager fm, List<Fragment> datas) {
            super(fm);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }
}
