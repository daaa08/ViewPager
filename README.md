# ViewPager
ViewPager + TabLayout


## FragmentPagerAdapter

- ViewPager 에서 Fragment 를 활용할 수 있도록 해 줌 

```java

class PagerAdapter extends FragmentPagerAdapter{
        List<Fragment> datas;  // Fragment 데이터를 담음

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

```

## addOnPageChangeListener

- 페이저가 변경되었을때 탭을 변경해주는 리스너

```java
tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("1"));
        tabLayout.addTab(tabLayout.newTab().setText("2"));
        tabLayout.addTab(tabLayout.newTab().setText("3"));
        tabLayout.addTab(tabLayout.newTab().setText("4"));
        
 viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
```

## addOnTabSelectedListener

-  탭이 변경되었을때 페이저를 변경해주는 리스너

```java
viewPager = (ViewPager) findViewById(R.id.viewPager);

tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
```

## getSupportFragmentManager
- FragmentManager에 접근하기위해 사용 

```java
PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), datas);
```


[전체소스보기](https://github.com/daaa08/ViewPager/blob/master/app/src/main/java/com/example/da08/viewpager/MainActivity.java)
