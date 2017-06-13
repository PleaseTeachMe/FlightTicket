package com.zl.fiight.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zl.fiight.EventMessage.Event_updateMine;
import com.zl.fiight.R;
import com.zl.fiight.activity.LoginActivity;
import com.zl.fiight.activity.MineOrderActivity;
import com.zl.fiight.activity.PersonalInformationActivity;
import com.zl.fiight.activity.RegisterActivity;
import com.zl.fiight.activity.SetActivity;
import com.zl.fiight.activity.UpdatePassActivity;
import com.zl.fiight.adapter.Adapter_Mine;
import com.zl.fiight.entity.Entity_Mine;
import com.zl.fiight.entity.User;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mine_Fragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.rv_mine)
    RecyclerView rv_mine;

    @Bind(R.id.Circular_head)
    ImageView Circular_head;

    @Bind(R.id.bt_login)
    Button bt_login;

    @Bind(R.id.bt_register)
    Button bt_register;
    //适配器
    private Adapter_Mine mAdapter_mine;


    private List<Entity_Mine> mMineList = new ArrayList<>();

    private BmobUser bmobUser;

    @Override
    protected int setContentView() {

        return R.layout.fragment_mine_;
    }

    @Override
    protected void lazyLoad() {
        //加载数据
        initData();
        //设置Recyclerview & adapter
        initRv();

        isLogin();
    }

    @Override
    protected void stopLoad() {
        super.stopLoad();
        mMineList.clear();
    }

    /**
     * 初始化数据
     */
    public void initData() {

        Entity_Mine entity_mine = new Entity_Mine(R.drawable.a, "个人信息");
        Entity_Mine entity_mine1 = new Entity_Mine(R.drawable.b, "修改密码");
        Entity_Mine entity_mine2 = new Entity_Mine(R.drawable.c, "我的关注");
        Entity_Mine entity_mine3 = new Entity_Mine(R.drawable.d, "我的订单");
        Entity_Mine entity_mine4 = new Entity_Mine(R.drawable.e, "应用设置");

        mMineList.add(entity_mine);
        mMineList.add(entity_mine1);
        mMineList.add(entity_mine2);
        mMineList.add(entity_mine3);
        mMineList.add(entity_mine4);

    }

    /**
     * 设置RecyclerView 和 adapter
     */
    public void initRv() {
        //创建默认的线性布局
        rv_mine.setLayoutManager(new LinearLayoutManager(getContext()));
        //实例化适配器
        mAdapter_mine = new Adapter_Mine(getContext(), mMineList);
        //设置自定义的监听
        mAdapter_mine.setMyOnItemClickListener(new Adapter_Mine.MyOnItemClickListener() {
            @Override
            public void MyonItemClick(View view, int poition) {
                switch (poition) {
                    case 0:
                        if (BmobUser.getCurrentUser(User.class) != null) {
                            startActivity(new Intent(getActivity(), PersonalInformationActivity.class));
                        } else {
                            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1:
                        if (BmobUser.getCurrentUser(User.class) != null) {
                            startActivity(new Intent(getActivity(), UpdatePassActivity.class));
                        } else {
                            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        if (BmobUser.getCurrentUser(User.class) != null) {
                            startActivity(new Intent(getActivity(), MineOrderActivity.class));

                        } else {
                            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 4:
                        if (BmobUser.getCurrentUser(User.class) != null) {
                            startActivity(new Intent(getActivity(), SetActivity.class));
                        } else {
                            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
                        }
                        break;
                }

            }
        });
        rv_mine.setAdapter(mAdapter_mine);
    }

    /**
     * 判断用户是否已登陆
     */
    public void isLogin() {
        //获得登陆的对象
        bmobUser = BmobUser.getCurrentUser();
        if (bmobUser != null) {
            bt_login.setVisibility(View.INVISIBLE);
            bt_register.setVisibility(View.INVISIBLE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void a(Event_updateMine messageEvent) {
        bt_register.setVisibility(View.VISIBLE);
        bt_login.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.Circular_head, R.id.bt_login, R.id.bt_register})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
            case R.id.Circular_head:

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                break;
            case R.id.bt_register:
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                break;
        }
    }

}
