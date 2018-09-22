package saiyi.com.gulin_new_wz.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.db.User;
import saiyi.com.gulin_new_wz.utils.INotifyLinstener;

/**
 * Created by 陈姣姣 on 2018/8/20.
 */
public   class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<User> mDatas;


    private INotifyLinstener iNotifyLinstener;

    public void setiNotifyLinstener(INotifyLinstener iNotifyLinstener) {
        this.iNotifyLinstener = iNotifyLinstener;
    }

    public UserAdapter(Activity context, List<User> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false));


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        final MyViewHolder holder = (MyViewHolder) viewHolder;
        final User dataBean = mDatas.get(i);
        holder.tvValue.setText(dataBean.getName());
        holder.linTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iNotifyLinstener.setNotify(i);

            }
        });

    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvValue;
        RelativeLayout linTitle;


        public MyViewHolder(View view) {
            super(view);
            tvValue = view.findViewById(R.id.tv_name);
            linTitle = view.findViewById(R.id.rl_user);

        }
    }
}