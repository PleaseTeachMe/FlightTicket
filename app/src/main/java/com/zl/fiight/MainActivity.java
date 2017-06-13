package com.zl.fiight;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zl.fiight.activity.BaseActivity;
import com.zl.fiight.adapter.FragmentVpAdapter;
import com.zl.fiight.fragment.AirTicket_Fragment;
import com.zl.fiight.fragment.Map_Fragment;
import com.zl.fiight.fragment.Mine_Fragment;
import com.zl.fiight.fragment.Weather_Fragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * @author: ZL
 * @created at  2017/5/1 May 1st,2017
 * @description: MainActiviy 实现碎片的管理
 */
public class MainActivity extends BaseActivity
        implements RadioGroup.OnCheckedChangeListener {
    //fragment集合
    private ArrayList<Fragment> mFragments;

    //fragment的适配器  ViewpagerAdapter
    private FragmentVpAdapter mPagerAdapter;

    //机票碎片 AriTicketFragment
    AirTicket_Fragment fragment_ticket;

    //天气碎片 WeatherFragment
    Weather_Fragment fragment_wea;

    //关注碎片
    Map_Fragment fragment_map;

    //我的碎片 MineFragment
    Mine_Fragment fragment_mine;

    //是否进入双击退出计时状态
    boolean is_exit = false;
    //第一次点击退出的时间戳
    long l_firstClickTime;
    //第二次点击退出的时间戳
    long l_secondClickTime;
    /**
     * 绑定控件
     */
    //viewPager
    @Bind(R.id.vp_main)
    ViewPager mViewPager;
    @Bind(R.id.rg_main)
    RadioGroup mRadioGroup;
    @Bind(R.id.rb_ticket)
    RadioButton rb_ticket;
    @Bind(R.id.rb_weather)
    RadioButton rb_weather;
    @Bind(R.id.rb_map)
    RadioButton rb_map;
    @Bind(R.id.rb_mine)
    RadioButton rb_mine;

    @Override
    public int setContent() {

        return R.layout.activity_main;
    }

    @Override
    public void init() {
    /**
     * 加载单选组，使用单选组不必给单选组内的每个RadioButton 设置监听
    */
        /**
         * 给单选按钮设置监听
         */
        mRadioGroup.setOnCheckedChangeListener(this);
        /**
         * 实例化fragment
         */
        fragment_ticket = new AirTicket_Fragment();
        fragment_map = new Map_Fragment();
        fragment_wea = new Weather_Fragment();
        fragment_mine = new Mine_Fragment();

        //实例化fragmentlist
        mFragments = new ArrayList<>();

        //fragment集合中添加fragment
        mFragments.add(fragment_ticket);
        mFragments.add(fragment_map);
        mFragments.add(fragment_wea);
        mFragments.add(fragment_mine);

        //适配器
        mPagerAdapter = new FragmentVpAdapter(getSupportFragmentManager(), mFragments);

        //viewPager设置适配器
        mViewPager.setAdapter(mPagerAdapter);

        //当前为第一个页面
//        mViewPager.setCurrentItem(0);


        mRadioGroup.check(R.id.rb_ticket);

        //ViewPager的页面改变监听
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());

    }

    @Override
    public void setMonitor() {

    }

    @Override
    public boolean setFullScreen() {
        return false;
    }

    @Override
    public void setTitle() {

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //双击退出
        doubleColickExit(keyCode, event);

        return true;
    }
    /**
     * @description 双击退出函数
     */
    private void doubleColickExit(int keyCode, KeyEvent event) {
        //当用户第一次点击返回钮时
        if (keyCode == KeyEvent.KEYCODE_BACK && is_exit == false) {
            is_exit = true;//设置记录标志为true
            l_firstClickTime = System.currentTimeMillis();//获得第一次点击的时间戳
            //显示再次点击退出提示
            Toast.makeText(this,"再次点击退出", Toast.LENGTH_SHORT).show();
        }
        //用户第二次点击返回钮
        else if (keyCode == KeyEvent.KEYCODE_BACK && is_exit == true) {
            l_secondClickTime = System.currentTimeMillis();//记录下第二次点击退出的时间
            //时间差在两秒之内，退出程序
            if (l_secondClickTime - l_firstClickTime < 2000) {
                finish();
            } else {
                is_exit = false;//重置记录退出时间标志
                doubleColickExit(keyCode, event);//超出2000毫秒时，重新开始本函数逻辑
            }
        }

    }
    /**
     *
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            //获取当前页面用于改变对应RadioButton的状态
            int current = mViewPager.getCurrentItem();

            switch (current) {
                case 0:
                    mRadioGroup.check(R.id.rb_ticket);
                    break;
                case 1:
                    mRadioGroup.check(R.id.rb_weather);
                    break;
                case 2:
                    mRadioGroup.check(R.id.rb_map);
                    break;
                case 3:
                    mRadioGroup.check(R.id.rb_mine);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {


        }
    }

    //    @OnCheckedChanged(R.id.rg_main)
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_ticket:
                    mViewPager.setCurrentItem(0);
                break;
            case R.id.rb_weather:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rb_map:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rb_mine:
                mViewPager.setCurrentItem(3);
                break;
        }
    }

}
