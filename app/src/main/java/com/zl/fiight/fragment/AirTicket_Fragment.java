package com.zl.fiight.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zl.fiight.EventMessage.Event_Date;
import com.zl.fiight.R;
import com.zl.fiight.activity.AirTicketActivity;
import com.zl.fiight.activity.CalendarActivity;
import com.zl.fiight.entity.AirTicket;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class AirTicket_Fragment extends Fragment {
    @Bind(R.id.banner)
    Banner mBanner;
    View view;
    @Bind(R.id.et_start)
    EditText et_start;
    @Bind(R.id.et_end)
    EditText et_end;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.bt_query)
    Button bt_query;
    private String startL;
    private String endL;
    private String startTime1;

    private List<Uri> mList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public AirTicket_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_air_ticket_, container, false);
        ButterKnife.bind(this, view);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setBanner();
        return view;
    }

    /**
     * 设置Banner
     */
    public void setBanner() {
        mList.add(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.drawable.label4));
        mList.add(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.drawable.label2));
        mList.add(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.drawable.label3));
        mList.add(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.drawable.label1));
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getActivity()).load(path).into(imageView);
            }
        });
        mBanner.setImages(mList);

        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置标题集合（当banner样式有显示title时）
//        mBanner.setBannerTitles(titles);
//        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void a(Event_Date messageEvent) {
        tv_time.setText(messageEvent.getYear() + "/"
                +messageEvent.getMonth() + "/"
                + messageEvent.getDay());
    }

    @OnClick({R.id.tv_time, R.id.bt_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                startActivity(new Intent(getActivity(), CalendarActivity.class));
                break;
            case R.id.bt_query:
                startL = et_start.getText().toString().trim();
                endL = et_end.getText().toString().trim();
                startTime1 = tv_time.getText().toString().trim();
//                字符串转换为日期
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                try {
//                    startTime = format.parse("2015-05-01 00:00:00");
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                query();
                break;
        }
    }

    public void query() {
        BmobQuery<AirTicket> b1 = new BmobQuery<AirTicket>();
        b1.addWhereEqualTo("startLocation", startL);

        BmobQuery<AirTicket> b2 = new BmobQuery<AirTicket>();
        b2.addWhereEqualTo("endLocation", endL);

        BmobQuery<AirTicket> b3 = new BmobQuery<>();
        b3.addWhereEqualTo("startTime1",startTime1);

        List<BmobQuery<AirTicket>> queries = new ArrayList<BmobQuery<AirTicket>>();
        queries.add(b1);
        queries.add(b2);
        queries.add(b3);

        BmobQuery<AirTicket> query = new BmobQuery<AirTicket>();

        query.and(queries);
        query.findObjects(new FindListener<AirTicket>() {
            @Override
            public void done(List<AirTicket> list, BmobException e) {
                if (e == null) {
                    if (list.size() != 0) {
                        if (list.get(0) != null) {
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), AirTicketActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("airTicket", (Serializable)list);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            Log.i("bmob", "对象" + list.get(0));
                        }
                    } else {
                        Log.i("bmob", "对象集合的size" + list.size());
                    }
                } else {
                    Log.i("bmob", e.getMessage());
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
