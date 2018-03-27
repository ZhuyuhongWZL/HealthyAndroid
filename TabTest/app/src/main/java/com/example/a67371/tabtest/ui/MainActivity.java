package com.example.a67371.tabtest.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.UserData;
import com.example.a67371.tabtest.controller.dy.UserResponseBean;
import com.example.a67371.tabtest.ui.refresh.RefreshableView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title, item_zhuye, item_wode,item_xiaoxi;
    private ViewPager vp;
    private FirstFragment oneFragment;
    private SecondFragment twoFragment;
    private ThirdFragment thirdFragment;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;


    String[] titles = new String[]{"主页", "我的","消息"};
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除工具栏

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.user_bar);
        toolbar.setTitle("每日健康");
        final Bundle bundle = this.getIntent().getExtras();
        UserResponseBean userResponseBean = new UserResponseBean();


        initViews();
        userResponseBean = (UserResponseBean) bundle.getSerializable("userdata");

        UserData.setName(userResponseBean.getUser().getName());
        UserData.setMail(userResponseBean.getUser().getMail());
        UserData.setSex(userResponseBean.getUser().getSex());
        UserData.setBirthday(userResponseBean.getUser().getBirthday());
        UserData.setPhone(userResponseBean.getUser().getPhone());
        UserData.setPassword(userResponseBean.getUser().getPassword());

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        vp.setOffscreenPageLimit(3);//ViewPager的缓存为4帧
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);//初始设置ViewPager选中第一帧
        item_zhuye.setTextColor(Color.parseColor("#66CDAA"));

        //ViewPager的监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*此方法在页面被选中时调用*/

                changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
                arg0 ==1的时辰默示正在滑动，
                arg0==2的时辰默示滑动完毕了，
                arg0==0的时辰默示什么都没做。*/
            }
        });




    }

    /**
     * 初始化布局View
     */
    private void initViews() {
        title = (TextView) findViewById(R.id.title);
        item_zhuye = (TextView) findViewById(R.id.item_zhuye);
        item_wode = (TextView) findViewById(R.id.item_wode);
        item_xiaoxi = (TextView) findViewById(R.id.item_xiaoxi);


        item_zhuye.setOnClickListener(this);
        item_wode.setOnClickListener(this);
        item_xiaoxi.setOnClickListener(this);


        vp = (ViewPager) findViewById(R.id.mainViewPager);
        oneFragment = new FirstFragment();
        twoFragment = new SecondFragment();
        thirdFragment=new ThirdFragment();

        //给FragmentList添加数据
        mFragmentList.add(oneFragment);
        mFragmentList.add(twoFragment);
        mFragmentList.add(thirdFragment);

    }

    /**
     * 点击底部Text 动态修改ViewPager的内容
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_zhuye:
                vp.setCurrentItem(0, true);
                break;
            case R.id.item_wode:
                vp.setCurrentItem(1, true);
                break;
            case R.id.item_xiaoxi:
                vp.setCurrentItem(2, true);
                break;

        }
    }


    public class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    /*
     *由ViewPager的滑动修改底部导航Text的颜色
     */
    private void changeTextColor(int position) {
        if (position == 0) {
            item_zhuye.setTextColor(Color.parseColor("#66CDAA"));
            item_wode.setTextColor(Color.parseColor("#000000"));
            item_xiaoxi.setTextColor(Color.parseColor("#000000"));

        } else if (position == 1) {
            item_wode.setTextColor(Color.parseColor("#66CDAA"));
            item_zhuye.setTextColor(Color.parseColor("#000000"));
            item_xiaoxi.setTextColor(Color.parseColor("#000000"));

        }else if (position==2){
            item_xiaoxi.setTextColor(Color.parseColor("#66CDAA"));
            item_zhuye.setTextColor(Color.parseColor("#000000"));
            item_wode.setTextColor(Color.parseColor("#000000"));
        }
    }
}