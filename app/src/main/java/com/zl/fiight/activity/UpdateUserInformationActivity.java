package com.zl.fiight.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zl.fiight.EventMessage.Event_updatePerson;
import com.zl.fiight.R;
import com.zl.fiight.entity.User;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UpdateUserInformationActivity extends BaseActivity {

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.bt)
    Button bt;
    @Bind(R.id.et)
    EditText et;
    String s;

    @Override
    public int setContent() {
        return R.layout.activity_update_user_name_activityn;
    }

    @Override
    public void init() {
        s = getIntent().getStringExtra("update");
        tv.setText("输入" + s);
        switch (s) {
            case "昵称":
                et.setHint("昵称长度不超过10");
                break;
            case "性别":
                et.setHint("男/女");
                break;
            case "邮箱":
                et.setHint("123456789@163/qq.com");
                break;
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

    @OnClick(R.id.bt)
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.bt:
                if (s.equals("昵称")) {
                    updateUsername();
                    EventBus.getDefault().post(new Event_updatePerson("update"));
                    finish();
                } else if (s.equals("性别")) {
                    updateGander();
                    EventBus.getDefault().post(new Event_updatePerson("update"));
                    finish();
                } else if (s.equals("邮箱")) {
                    updateEmail();
                    EventBus.getDefault().post(new Event_updatePerson("update"));
                    finish();
                }
                break;
        }
    }

    public void updateUsername() {

        User newUser = new User();
        newUser.setUsername(et.getText().toString());
        Log.i("bmob", "用户名" + et.getText().toString() + ".......");
        User bmobUser = BmobUser.getCurrentUser(User.class);
        newUser.update(bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("bmob", "用户名更新用户信息成功");
                } else {
                    Log.i("bmob", " 邮箱更新用户信息失败:" + e.getMessage());
                }
            }
        });
    }

    public void updateGander() {

        User newUser = new User();
        newUser.setGender(et.getText().toString());
        Log.i("bmob", "性别" + et.getText().toString() + ".......");
        BmobUser bmobUser = BmobUser.getCurrentUser(User.class);
        newUser.update(bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("bmob", "性别更新用户信息成功.........");
                } else {
                    Log.i("bmob", " 邮箱更新用户信息失败:" + e.getMessage());
                }
            }
        });
    }


    public void updateEmail() {


        User newUser = new User();
        newUser.setEmail(et.getText().toString());

        Log.i("bmob", "邮箱" + et.getText().toString() + ".......");

        BmobUser bmobUser = BmobUser.getCurrentUser(User.class);

        newUser.update(bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("bmob", "邮箱更新用户信息成功.....");
                } else {
                    Log.i("bmob", " 邮箱更新用户信息失败:" + e.getMessage());
                }
            }
        });
    }
}
