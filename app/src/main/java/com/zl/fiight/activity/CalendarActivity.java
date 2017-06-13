package com.zl.fiight.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.codbking.calendar.CaledarAdapter;
import com.codbking.calendar.CalendarBean;
import com.codbking.calendar.CalendarDateView;
import com.codbking.calendar.CalendarUtil;
import com.codbking.calendar.CalendarView;
import com.zl.fiight.EventMessage.Event_Date;
import com.zl.fiight.R;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CalendarActivity extends Activity {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.calendarDateView)
    CalendarDateView mCalendarDateView;
    @Bind(R.id.list)
    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
        initView();
        initList();
    }

    private void initList() {
        mList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(CalendarActivity.this).inflate(android.R.layout.simple_list_item_1, null);
                }
                TextView textView = (TextView) convertView;
                textView.setText("position:" + position);

                return convertView;
            }
        });
    }

    private void initView() {

        mCalendarDateView.setAdapter(new CaledarAdapter() {
            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarBean bean) {

                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_xiaomi, null);
                }

                TextView chinaText = (TextView) convertView.findViewById(R.id.chinaText);
                TextView text = (TextView) convertView.findViewById(R.id.text);

                text.setText("" + bean.day);
                if (bean.mothFlag != 0) {
                    text.setTextColor(0xff9299a1);
                } else {
                    text.setTextColor(0xff444444);
                }
                chinaText.setText(bean.chinaDay);

                return convertView;
            }
        });

        mCalendarDateView.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion, CalendarBean bean) {

                mTitle.setText(bean.year + "/" + bean.moth + "/" + bean.day);
                EventBus.getDefault().post(new Event_Date(bean.year,bean.moth,bean.day));
            }
        });

        int[] data = CalendarUtil.getYMD(new Date());
        mTitle.setText(data[0] + "/" + data[1] + "/" + data[2]);
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
