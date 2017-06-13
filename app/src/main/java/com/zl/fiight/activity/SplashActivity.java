package com.zl.fiight.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zl.fiight.R;
import com.zl.fiight.adapter.SplashAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author: ZL
 * @created at 2017/5/1 May 1st,2017
 * @description: 引导界面 Splash Acitivity
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.vp_welcome)
    ViewPager vp_welcome;
    @Bind(R.id.bt_enter)
    Button bt_enter;

    //图片id数组
    private int[] image_id = new int[]{R.drawable.guidepage1, R.drawable.guidepage2,
            R.drawable.guidepage3, R.drawable.guidepage4, R.drawable.guidepage5};


    //定义存储ImageView的集合
    private List<View> images_list;

    //引导页ViewPager适配器
    private SplashAdapter mSplashAdapter;




    @Override
    public int setContent() {
        return R.layout.activity_splash;
    }
    @Override
    public void init() {

        setWelcome();


    }

    @Override
    public void setMonitor() {

    }

    @Override
    public boolean setFullScreen() {
        return true;
    }

    @Override
    public void setTitle() {

    }

    //设置欢迎界面
    public void setWelcome() {
        //初始化存放图片集合
        images_list = new ArrayList<>();
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //初始化引导列表
        for (int i = 0; i < image_id.length; i++) {

            ImageView imageView = new ImageView(this);

            imageView.setImageResource(image_id[i]);

            imageView.setLayoutParams(mParams);

            images_list.add(imageView);
        }
        //初始化适配器
        mSplashAdapter = new SplashAdapter(images_list);
        //viewpager设置适配器
        vp_welcome.setAdapter(mSplashAdapter);
        //viewpager设置监听
        vp_welcome.addOnPageChangeListener(new MyOnPageChangeListener());

    }



    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int current = vp_welcome.getCurrentItem();
                switch (current){
                    case 4:
                        bt_enter.setVisibility(View.VISIBLE);
                        bt_enter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //
                                startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));

                            }
                        });
                        break;
                }
        }

        @Override
        public void onPageScrollStateChanged(int state) {


        }
    }
}
