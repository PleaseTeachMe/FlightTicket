package com.zl.fiight.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Window;

import com.zl.fiight.R;

public class Main2Activity extends BaseActivity {

    @Override
    public int setContent() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_main2;
    }

    @Override
    public void init() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getString("weather", null) != null) {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        }
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
}
