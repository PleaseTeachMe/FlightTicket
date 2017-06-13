package com.zl.fiight.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zl.fiight.MainActivity;
import com.zl.fiight.R;
import com.zl.fiight.entity.User;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.et_phoneNumber)
    EditText et_phoneNumber;

    @Bind(R.id.et_password)
    EditText et_password;

    @Bind(R.id.bt_login)
    Button bt_login;

    @Bind(R.id.bt_register)
    Button bt_register;

    @Bind(R.id.bt_forget)
    Button bt_forget;

    @Bind(R.id.rg_main)
    RadioGroup rg_main;

    @Bind(R.id.rb_qq)
    RadioButton rb_qq;
    @Bind(R.id.rb_wechat)
    RadioButton rb_wechat;
    @Bind(R.id.rb_weibo)
    RadioButton rb_weibo;


    private User mUser;
    private String PhoneNumber;
    private String password;

    @Override
    public int setContent() {
        return R.layout.activity_login;
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

    @OnClick({R.id.bt_login, R.id.bt_register, R.id.bt_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                login();
                finish();
                break;
            case R.id.bt_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.bt_forget:
                break;
        }
    }

    /**
     * 登录方法
     */
    public void login() {
        PhoneNumber = et_phoneNumber.getText().toString();
        password = et_password.getText().toString();
        mUser = new User();
        mUser.loginByAccount(PhoneNumber, password, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    if (user != null) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this,"账号不存在请先注册", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
