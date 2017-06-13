package com.zl.fiight.activity;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zl.fiight.R;
import com.zl.fiight.entity.User;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.et_phoneNumber)
    EditText et_phoneNumber;

    @Bind(R.id.et_password)
    EditText et_password;
    @Bind(R.id.et_passagain)
    EditText et_passagain;

    @Bind(R.id.et_message)
    EditText et_message;
    @Bind(R.id.bt_message)
    Button bt_message;

    @Bind(R.id.bt_register)
    Button bt_register;

    private String PhoneNumber;
    private String password;
    private String passagain;
    private String message;

    private User mUser;

    public static final int UPDATE_ONE = 1;

    private int time = 60;

    @Override
    public int setContent() {
        return R.layout.activity_register;
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

    @OnClick({R.id.bt_message, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_message:
                //短息验证
                mHandler.postDelayed(mRunnable, 0);
                PhoneNumber = et_phoneNumber.getText().toString();
                BmobSMS.requestSMSCode(PhoneNumber, "航班管家", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer smsId, BmobException ex) {
                        if (ex == null) {//验证码发送成功
                            Log.i("bmob", "短信id：" + smsId);//用于查询本次短信发送详情
                        }
                    }
                });
                break;
            case R.id.bt_register:

                register();
                finish();
                break;
        }
    }

    /**
     * 注册方法
     */
    public void register() {
        PhoneNumber = et_phoneNumber.getText().toString();
        password = et_password.getText().toString();
        passagain = et_passagain.getText().toString();
        message = et_message.getText().toString();
        mUser = new User();
        mUser.setUsername(" ");

        mUser.setMobilePhoneNumber(PhoneNumber);
        mUser.setPassword(password);
        mUser.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    bt_register.setClickable(true);
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private Handler mHandler = new Handler();
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            time--;
            if (time > 0) {
                bt_message.setBackgroundColor(000000000);
                bt_message.setText(time + "s后重新获取");
                mHandler.postDelayed(this, 1000);
            }
            if (time == 0) {
                bt_message.setText("获取验证码");
                time = 60;
            }

        }
    };
//                if (PhoneNumber != null) {
//                    if (password != null) {
//                        if (passagain != null) {
//                            if (passagain == password) {
//                                if (e == null && user != null) {
//    bt_register.setClickable(true);
//    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                                }else {
//                                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(RegisterActivity.this, "2次输入的密码不一致", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
//                }

}
