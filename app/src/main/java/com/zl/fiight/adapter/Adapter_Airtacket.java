package com.zl.fiight.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.fiight.R;
import com.zl.fiight.activity.LoginActivity;
import com.zl.fiight.entity.AirTicket;
import com.zl.fiight.entity.Order;
import com.zl.fiight.entity.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/6/4.
 */
public class Adapter_Airtacket extends RecyclerView.Adapter<Adapter_Airtacket.MineViewHolder> {
    private Context mContext;
    private List<AirTicket> mMineList;

    public Adapter_Airtacket(Context context, List<AirTicket> mineList) {
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
        childView = inflater.inflate(R.layout.item_airticket, parent, false);


        //实例化mViewHolder
        MineViewHolder mViewHolder = new MineViewHolder(childView);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MineViewHolder holder, int position) {
        //itemView设置位置
        holder.itemView.setTag(position);
        final AirTicket airTicket = mMineList.get(position);

        holder.tv_start.setText(airTicket.getStartLocation());
        holder.tv_end.setText(airTicket.getEndLocation());
        holder.tv_price.setText(airTicket.getPrice() + "¥");
        holder.tv_startTime.setText(airTicket.getStartTime2());
        holder.tv_endTime.setText(airTicket.getEndTime2());
        holder.tv_category.setText(airTicket.getCategory());
        holder.tv_startAirport.setText(airTicket.getStartairport());
        holder.tv_endAirport.setText(airTicket.getEndairport());
        holder.tv_FlightNumber.setText(airTicket.getFlightNumber());
        holder.tv_FlightName.setText(airTicket.getFlightName());
        holder.bt_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BmobUser.getCurrentUser(User.class) != null) {
                    String Number = BmobUser.getCurrentUser(User.class).getMobilePhoneNumber();
                    String startLocation = airTicket.getStartLocation();
                    String endLocation = airTicket.getEndLocation();
                    String Category = airTicket.getCategory();
                    String Startairport = airTicket.getStartairport();
                    String Endairport = airTicket.getEndairport();
                    String FlightName = airTicket.getFlightName();
                    int Price = airTicket.getPrice();
                    String startTime1 = airTicket.getStartTime1();
                    String startTime2 = airTicket.getStartTime2();
                    String endTime1 = airTicket.getEndTime1();
                    String endTime2 = airTicket.getEndTime2();
                    Order order = new Order();
                    order.setNumber(Number);
                    order.setCategory(Category);
                    order.setPrice(Price);
                    order.setStartairport(Startairport);
                    order.setEndairport(Endairport);
                    order.setStartLocation(startLocation);
                    order.setEndLocation(endLocation);
                    order.setFlightName(FlightName);
                    order.setStartTime1(startTime1);
                    order.setStartTime2(startTime2);
                    order.setEndTime1(endTime1);
                    order.setEndTime2(endTime2);
                    order.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Toast.makeText(mContext, "预定成功", Toast.LENGTH_LONG).show();
                            } else {
                                Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                            }
                        }
                    });

//                     Order order = new Order(Category,Endairport,endLocation,
//                            endTime1,endTime2,FlightName,Number,Price,Startairport,
//                            startLocation,startTime1,startTime2);


//                    String tableName, String category,
//                            String endairport, String endLocation,
//                            String endTime1, String endTime2,
//                            String flightName, String number,
//                    int price, String startairport,
//                            String startLocation, String startTime1,
//                            String startTime2) {


                } else {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_LONG).show();
                    mContext.startActivity(new Intent(mContext, LoginActivity.class));
                }
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
        @Bind(R.id.tv_FlightNumber)
        TextView tv_FlightNumber;
        @Bind(R.id.bt_order)
        Button bt_order;


        public MineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
