package com.zl.fiight.activity;

import android.view.View;
import android.widget.Button;

import com.zl.fiight.EventMessage.Event_updateMine;
import com.zl.fiight.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

public class SetActivity extends BaseActivity {
    @Bind(R.id.bt_LogOut)
    Button bt_LogOut;

    @Override
    public int setContent() {
        return R.layout.activity_set;
    }

    @Override
    public void init() {

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

    @OnClick(R.id.bt_LogOut)
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_LogOut:
                BmobUser.logOut();  //清除缓存用户对象
                //EventBus传递Event
                EventBus.getDefault().post(new Event_updateMine("update Main"));
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
