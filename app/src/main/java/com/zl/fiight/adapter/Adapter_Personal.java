package com.zl.fiight.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zl.fiight.R;
import com.zl.fiight.entity.Entity_Personal;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/28.
 */
public class Adapter_Personal extends RecyclerView.Adapter<Adapter_Personal.PersonalViewHolder> implements View.OnClickListener{

    private List<Entity_Personal> mPersonalList;
    private Context mContext;
    //申明这个接口
    private MyOnItemClickListener mOnItemClickListener = null;

    public Adapter_Personal(Context context, List<Entity_Personal> personalList) {
        mContext = context;
        mPersonalList = personalList;
    }

    @Override
    public PersonalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //定义一个视图
        View childView = null;

        //布局加载器
        LayoutInflater inflater = LayoutInflater.from(mContext);

        //找到子视图
        childView = inflater.inflate(R.layout.item_personal, parent, false);

        /**
         * 设置系统的点击监听
         */
        childView.setOnClickListener(this);

        //实例化mViewHolder
        PersonalViewHolder mViewHolder = new PersonalViewHolder(childView);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonalViewHolder holder, int position) {
        //itemView设置位置
        holder.itemView.setTag(position);

        Entity_Personal entity_personal = mPersonalList.get(position);

        holder.tv_personala.setText(entity_personal.getMessage());

        switch (position){
            case 0:
                holder.tv_personalb.setText(entity_personal.getUser().getUsername());
                break;
            case 1:
                holder.tv_personalb.setText(entity_personal.getUser().getGender());
                break;
            case 2:
                holder.tv_personalb.setText(entity_personal.getUser().getEmail());
                break;
        }

    }

    @Override
    public int getItemCount() {
        if (mPersonalList != null){
            return mPersonalList.size();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.MyonItemClick(v, (Integer) v.getTag());
        }
    }

    class PersonalViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_personala)
        TextView tv_personala;
        @Bind(R.id.tv_personalb)
        TextView tv_personalb;

        public PersonalViewHolder(View itemView) {
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
