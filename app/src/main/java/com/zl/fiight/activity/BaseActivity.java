package com.zl.fiight.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {

    /**
     * 定义一个成员变量
     * Define a  member arivable that is isFullScreen.
     * it's default value is false,which means not full-screen.
     */
    private boolean isFullScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 必须设置在加载布局前
         * Must be set before loading the Layout
         */
        isFullScreen = setFullScreen();

        if (isFullScreen){
            /**
             * 设置为无标题，全屏
             *
             * setting to Untitled,Full Screen
             */
            requestWindowFeature(Window.FEATURE_NO_TITLE);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );

        }else {
            /**
             * 设置为无标题
             * set to Untitled
             */
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        setContentView(setContent());
        //绑定注解
        ButterKnife.bind(this);

        init();

        setMonitor();

        setTitle();
    }

    /**
     * @description 加载当前Activity的布局文件
     *              Loading the layout of current Activity
     */
    public abstract int setContent();

    /**
     * @description 加载当前Activity的控件
     *              Loading Controls of current Activity
     */
    public abstract void init();

    /**
     * @description 设置当前Activity的监听事件
     *                Setting listener event of current Activity
     */
    public abstract void setMonitor();

    /**
     *@description 是否设置当前Activity为全屏
     *               Weather to set the current Activity to full screen
     */
    public abstract boolean setFullScreen();

    public abstract void setTitle();
}
