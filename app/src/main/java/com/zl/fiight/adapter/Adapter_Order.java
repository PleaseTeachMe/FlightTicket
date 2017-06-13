package com.zl.fiight.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zl.fiight.EventMessage.Event_updateMine;
import com.zl.fiight.R;
import com.zl.fiight.entity.Order;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/6/12.
 */
public class Adapter_Order extends RecyclerView.Adapter<Adapter_Order.MineViewHolder> {
    private Context mContext;
    private List<Order> mMineList;

    public Adapter_Order(Context context, List<Order> mineList) {
        mContext = context;
        mMineList = mineList;
    }

    @Override
    public MineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //定义一个视图
        View childView = null;

        //布局加载器
        LayoutInflater inflater = LayoutInflater.from(mContext);

        //找到子视图
        childView = inflater.inflate(R.layout.item_order, parent, false);


        //实例化mViewHolder
        MineViewHolder mViewHolder = new MineViewHolder(childView);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MineViewHolder holder, int position) {
        //itemView设置位置
        holder.itemView.setTag(position);
        final Order order = mMineList.get(position);
        Log.i("bmob", mMineList.size() + "*******");
        holder.tv_time.setText(order.getStartTime1());

        holder.tv_start.setText(order.getStartLocation());

        holder.tv_end.setText(order.getEndLocation());

        holder.tv_price.setText(order.getPrice() + "¥");

        holder.tv_startTime.setText(order.getStartTime2());

        holder.tv_endTime.setText(order.getEndTime2());

        holder.tv_category.setText(order.getCategory());

        holder.tv_startAirport.setText(order.getStartairport());

        holder.tv_endAirport.setText(order.getEndairport());

        holder.tv_FlightName.setText(order.getFlightName());
        holder.bt_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Order morder = new Order();
                morder.setObjectId(order.getObjectId());
                morder.delete(new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            EventBus.getDefault().post(new Event_updateMine("update"));
                            Log.i("bmob", "成功");
                        } else {
                            Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mMineList != null) {
            return mMineList.size();
        }
        return 0;
    }

    class MineViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.tv_start)
        TextView tv_start;
        @Bind(R.id.tv_end)
        TextView tv_end;
        @Bind(R.id.tv_price)
        TextView tv_price;

        @Bind(R.id.tv_startTime)
        TextView tv_startTime;
        @Bind(R.id.tv_endTime)
        TextView tv_endTime;
        @Bind(R.id.tv_category)
        TextView tv_category;

        @Bind(R.id.tv_startAirport)
        TextView tv_startAirport;
        @Bind(R.id.tv_endAirport)
        TextView tv_endAirport;
        @Bind(R.id.tv_FlightName)
        TextView tv_FlightName;

        @Bind(R.id.bt_order)
        Button bt_order;

        public MineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
