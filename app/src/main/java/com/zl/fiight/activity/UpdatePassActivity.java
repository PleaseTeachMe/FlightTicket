package com.zl.fiight.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zl.fiight.EventMessage.Event_updateMine;
import com.zl.fiight.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UpdatePassActivity extends BaseActivity {
    @Bind(R.id.et_originalpass)
    EditText et_originalpass;
    @Bind(R.id.et_newpassa)
    EditText et_newpassa;
    @Bind(R.id.et_newpassb)
    EditText et_newpassb;
    @Bind(R.id.bt_update)
    Button bt_update;
    private String originalpass;
    private String newpassa;
    private String newpassb;
    @Override
    public int setContent() {
        return R.layout.activity_update_pass;
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
    @OnClick(R.id.bt_update)
    public void updatePass(View view){
        switch (view.getId()){
            case R.id.bt_update:
                UpdatePass();
                break;
        }
    }

    /**
     *修改密码
     */
    public void UpdatePass(){
        originalpass = et_originalpass.getText().toString();
        newpassa = et_newpassa.getText().toString();
        newpassb = et_newpassb.getText().toString();
        BmobUser.updateCurrentUserPassword(originalpass, newpassa, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    startActivity(new Intent(UpdatePassActivity.this,LoginActivity.class));
                    Toast.makeText(UpdatePassActivity.this, "密码已修改，重新登录", Toast.LENGTH_SHORT).show();
                    finish();
                    BmobUser.logOut();
                    EventBus.getDefault().post(new Event_updateMine("update Main"));
                }else{
                    Toast.makeText(UpdatePassActivity.this, "原始密码错误", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
