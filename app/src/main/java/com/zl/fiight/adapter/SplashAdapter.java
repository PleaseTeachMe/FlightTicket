package com.zl.fiight.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public class SplashAdapter extends PagerAdapter {
    private List<View> mViewList;

    public SplashAdapter(List<View> viewList) {

        mViewList = viewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public int getCount() {
        if (mViewList != null) {
            return mViewList.size();
        }
        return 0;
    }

    //判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //初始化position位置的界面
    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewGroup) container).addView(mViewList.get(position), 0);
        return mViewList.get(position);
    }

}
