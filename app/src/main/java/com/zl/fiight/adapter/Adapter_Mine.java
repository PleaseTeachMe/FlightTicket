package com.zl.fiight.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zl.fiight.R;
import com.zl.fiight.entity.Entity_Mine;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Adapter_Mine extends RecyclerView.Adapter<Adapter_Mine.MineViewHolder> implements View.OnClickListener {

    private List<Entity_Mine> mMineList;
    private Context mContext;
    //申明这个接口
    private MyOnItemClickListener mOnItemClickListener = null;

    public Adapter_Mine(Context context, List<Entity_Mine> mineList) {
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
        childView = inflater.inflate(R.layout.item_mine, parent, false);

        /**
         * 设置系统的点击监听
         */
        childView.setOnClickListener(this);

        //实例化mViewHolder
        MineViewHolder mViewHolder = new MineViewHolder(childView);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MineViewHolder holder, int position) {
        //itemView设置位置
        holder.itemView.setTag(position);

        Entity_Mine entity_mine = mMineList.get(position);

        holder.iv_mine.setBackgroundResource(entity_mine.getId());

        holder.tv_mine.setText(entity_mine.getMessage());

    }

    @Override
    public int getItemCount() {
        if (mMineList != null) {
            return mMineList.size();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.MyonItemClick(v, (Integer) v.getTag());
        }
    }

    class MineViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_mine)
        ImageView iv_mine;
        @Bind(R.id.tv_mine)
        TextView tv_mine;

        public MineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 定义一个公共的item点击监听的接口
     */
    public static interface MyOnItemClickListener {

        void MyonItemClick(View view, int poition);

    }

    /**
     * 定义一个设置点击监听的方法
     * @param listener
     */
    public void setMyOnItemClickListener(MyOnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


}
