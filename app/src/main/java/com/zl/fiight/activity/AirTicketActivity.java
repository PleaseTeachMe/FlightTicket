package com.zl.fiight.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zl.fiight.R;
import com.zl.fiight.adapter.Adapter_Airtacket;
import com.zl.fiight.entity.AirTicket;

import java.util.List;

import butterknife.Bind;

public class AirTicketActivity extends BaseActivity {

    @Bind(R.id.rv_airTicket)
    RecyclerView rv_airTicket;
//    @Bind(R.id.bt_order)
//    Button bt_order;
    private AirTicket airTicket;
    private Adapter_Airtacket mAdapter_airtacket;
    private List<AirTicket> mTicketList;

    @Override
    public int setContent() {
        return R.layout.activity_air_ticket;
    }

    @Override
    public void init() {
        Intent intent = this.getIntent();
        mTicketList = (List<AirTicket>) intent.getSerializableExtra("airTicket");
        Log.i("bmob","mTicketList.size"+mTicketList.size());
//        if (airTicket != null) {
//            mTicketList = new ArrayList<>();
//            mTicketList.add(airTicket);
//            Log.i("bmob",mTicketList.size()+"");
//        }
        initData();
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

    public void initData() {
        rv_airTicket.setLayoutManager(new LinearLayoutManager(this));
        mAdapter_airtacket = new Adapter_Airtacket(getApplicationContext(), mTicketList);
        rv_airTicket.setAdapter(mAdapter_airtacket);
    }
//    @OnClick(R.id.bt_order)
//    public void onClick(View v){
//        switch (v.getId()){
//            case R.id.bt_order:
//                if (BmobUser.getCurrentUser(User.class) != null){
//                    BmobUser.getCurrentUser(User.class).getMobilePhoneNumber();
//                }else {
//                    Toast.makeText(this,"请先登录",Toast.LENGTH_LONG).show();
//                }
//
//                break;
//        }
//    }
}
