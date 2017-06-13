package com.zl.fiight.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zl.fiight.EventMessage.Event_updatePerson;
import com.zl.fiight.R;
import com.zl.fiight.adapter.Adapter_Personal;
import com.zl.fiight.entity.Entity_Personal;
import com.zl.fiight.entity.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class PersonalInformationActivity extends BaseActivity {

    @Bind(R.id.rv_personal)
    RecyclerView rv_personal;
    Entity_Personal entity_personal1;
    Entity_Personal entity_personal2;
    Entity_Personal entity_personal3;
    private List<Entity_Personal> mPersonalList;
    User user;
    private Adapter_Personal mAdapter_personal;

    @Override
    public int setContent() {
        return R.layout.activity_personal_information;
    }


    @Override
    public void init() {
        EventBus.getDefault().register(this);

//        Log.i("bmob", "一开始默认...user.getUsername()" + user.getUsername());
//        Log.i("bmob", "一开始默认...user.getGender()" + user.getGender());
//        Log.i("bmob", "一开始默认...user.getEmail()" + user.getEmail());
        initData();
        setRv_personal();
        setRvOnClick();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void a(Event_updatePerson messageEvent) {
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("mobilePhoneNumber", user.getMobilePhoneNumber());
        Log.i("bmob", "mobilePhoneNumber:" + user.getMobilePhoneNumber());
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object, BmobException e) {
                if (e == null) {
                    Log.i("bmob", "查询用户成功:" + object.size());
                    List<Entity_Personal> userList = new ArrayList<>();
                    User user1 = object.get(0);
//                    User user1 = BmobUser.getCurrentUser(User.class);
                    if (user1 != null) {
                        Log.i("bmob", "跟新..user.getUsername()" + user1.getUsername());
                        Log.i("bmob", "跟新..user.getGender()" + user1.getGender());
                        Log.i("bmob", "跟新..user.getEmail()" + user1.getEmail());

                        entity_personal1 = new Entity_Personal("昵称", user1);
                        entity_personal2 = new Entity_Personal("性别", user1);
                        entity_personal3 = new Entity_Personal("邮箱", user1);

                        userList = new ArrayList<>();
                        userList.add(entity_personal1);
                        userList.add(entity_personal2);
                        userList.add(entity_personal3);
                    }
                    //创建默认的线性布局
                    rv_personal.setLayoutManager(new LinearLayoutManager(PersonalInformationActivity.this));
                    //实例化适配器
                    mAdapter_personal = new Adapter_Personal(PersonalInformationActivity.this, userList);
                    rv_personal.setAdapter(mAdapter_personal);
                    setRvOnClick();

                } else {
                    Log.i("bmob", "更新用户信息失败:" + e.getMessage());
                }
            }
        });


    }

    public void initData() {
        user = BmobUser.getCurrentUser(User.class);
        if (user != null) {
            entity_personal1 = new Entity_Personal("昵称", user);
            entity_personal2 = new Entity_Personal("性别", user);
            entity_personal3 = new Entity_Personal("邮箱", user);
            Log.i("bmob", "user.getUsername()" + user.getUsername());
            Log.i("bmob", "user.getGender()" + user.getGender());
            Log.i("bmob", "user.getEmail()" + user.getEmail());
            mPersonalList = new ArrayList<>();
            mPersonalList.add(entity_personal1);
            mPersonalList.add(entity_personal2);
            mPersonalList.add(entity_personal3);
        }
    }

    public void setRv_personal() {
        //创建默认的线性布局
        rv_personal.setLayoutManager(new LinearLayoutManager(this));
        //实例化适配器
        mAdapter_personal = new Adapter_Personal(this, mPersonalList);
        rv_personal.setAdapter(mAdapter_personal);
    }

    public void setRvOnClick() {
        //设置自定义的监听
        mAdapter_personal.setMyOnItemClickListener(new Adapter_Personal.MyOnItemClickListener() {
            @Override
            public void MyonItemClick(View view, int poition) {
                Intent intent;
                intent = new Intent(PersonalInformationActivity.this, UpdateUserInformationActivity.class);
                Bundle bundle = new Bundle();
                switch (poition) {
                    case 0:
                        //昵称
                        bundle.putString("update", entity_personal1.getMessage());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 1:
                        //性别
                        bundle.putString("update", entity_personal2.getMessage());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 2:
                        //年龄
                        bundle.putString("update", entity_personal3.getMessage());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 3:
                        //邮箱
                        bundle.putString("update", entity_personal3.getMessage());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
