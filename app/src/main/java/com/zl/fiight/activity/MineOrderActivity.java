package com.zl.fiight.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.zl.fiight.EventMessage.Event_updateMine;
import com.zl.fiight.R;
import com.zl.fiight.adapter.Adapter_Order;
import com.zl.fiight.entity.Order;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MineOrderActivity extends BaseActivity {
    @Bind(R.id.rv_order)
    RecyclerView rv_order;

    private Adapter_Order mAdapterOrder;

    private List<Order> mOrderList;

    @Override
    public int setContent() {

        return R.layout.activity_mine_order;
    }

    @Override
    public void init() {
        //注册EventBus
        EventBus.getDefault().register(this);
        selectData();

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
    public void a(Event_updateMine messageEvent) {
        selectData();
    }

    public void selectData() {
        BmobQuery<Order> query = new BmobQuery<Order>();
        BmobUser user = BmobUser.getCurrentUser();
        if (user != null) {
            query.addWhereEqualTo("Number", user.getMobilePhoneNumber());
            query.setLimit(20);
            query.findObjects(new FindListener<Order>() {
                @Override
                public void done(List<Order> list, BmobException e) {
                    if (e == null) {
//                        mOrderList = new ArrayList<Order>();
//                        mOrderList = list;
                        rv_order.setLayoutManager(new LinearLayoutManager(MineOrderActivity.this));
                        mAdapterOrder = new Adapter_Order(MineOrderActivity.this, list);
                        rv_order.setAdapter(mAdapterOrder);
                        Log.i("bmob", list.size() + ".......");
                    } else {
                        Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    }
                }
            });
        } else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_LONG);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
