package com.zl.fiight.activity;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.zl.fiight.MainActivity;
import com.zl.fiight.R;
import com.zl.fiight.utils.Constants;

import butterknife.Bind;

public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.iv_welcome)
    ImageView iv_welcome;
    //属性动画
    ValueAnimator alpha;
    //偏好设置文件存储
    private SharedPreferences preferences;

    //用于判断是否第一次进入app
    private boolean isFirstin;

    @Override
    public int setContent() {
        return R.layout.activity_welcome;
    }

    @Override
    public void init() {
        setSp();

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

    /**
     * SharedPrefererence存储
     */
    public void setSp() {

        preferences = getSharedPreferences(Constants.APP_STATE, MODE_PRIVATE);

        //默认存储位true
        isFirstin = preferences.getBoolean(Constants.OPEN_STATE, true);

        Log.i("zl", isFirstin + "1");
        if (isFirstin) {
            Log.i("zl", isFirstin + "2");
            startActivity(new Intent(WelcomeActivity.this, SplashActivity.class));
            //修改值
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(Constants.OPEN_STATE, false);
            editor.commit();
        } else {
            initAnima();
        }

    }
    /**
     * 加载属性动画
     * load property Animation
     */
    protected void initAnima(){
        //淡入动画的设置
        //Fade animation sets
        alpha= ObjectAnimator.ofFloat(iv_welcome,"alpha",0.5f,1.0f);
        //设置动画的持续时间
        //Set the Duration of the Animation
        alpha.setDuration(2000);
        //动画设置监听
        //Animation set upListener
        alpha.addListener(new AnimatorListenerAdapter() {
            //跳转到主页面
            //jump to main page
            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                Log.i("zl","1");
                startActivity(intent);
                Log.i("zl","2");
                finish();
            }
        });
        //启动动画
        //Start Animation
        alpha.start();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //双击退出
        OneColickExit(keyCode, event);

        return true;
    }
    /**
     * @description 单击退出函数，加载开屏动画过程中，点击退出，不会开启新的Activity
     */
    private void OneColickExit(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
        }
    }
    /**
     * @description
     * Whether current page gets focus
     * 当前页是否已获得焦点
     *
//     * @param hasFocus true代表获得焦点，false相反
     *         hasFocus true  representative for focus,false contraty
     */
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            initAnima();
//        }
//    }

    @Override
    protected void onPause() {
        alpha.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        alpha.pause();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        //让当前动画暂停，使其无法触发结束监听错误地开启新Activity
        alpha.pause();
        super.onDestroy();
    }
}
