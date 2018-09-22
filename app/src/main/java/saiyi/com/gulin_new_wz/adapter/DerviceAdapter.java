package saiyi.com.gulin_new_wz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.inuker.bluetooth.library.Constants;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.model.BleGattProfile;

import java.util.ArrayList;
import java.util.List;

import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.ble.BleBean;
import saiyi.com.gulin_new_wz.ble.BleConstant;
import saiyi.com.gulin_new_wz.ble.BleManger;
import saiyi.com.gulin_new_wz.ble.IBleStateListener;
import saiyi.com.gulin_new_wz.entity.DeviceInstructionsEntity;
import saiyi.com.gulin_new_wz.utils.DeviceQueryEntity;
import saiyi.com.gulin_new_wz.utils.IonMacLinsteners;
import saiyi.com.gulin_new_wz.utils.LogUtils;
import saiyi.com.gulin_new_wz.utils.SPUtils;

import static saiyi.com.gulin_new_wz.constaint.MyConstaints.MAC;
import static saiyi.com.gulin_new_wz.utils.Constants.UUID_SERVICE;
import static saiyi.com.gulin_new_wz.utils.Constants.UUID_WRITE_READ;

/**
 * Created by 陈姣姣 on 2018/7/31.

 /**
 * Created by 陈姣姣 on 2018/6/28.
 */

public   class DerviceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<BleBean> mDatas;


    IBleStateListener iBleStateListener;

    public void setiBleStateListener(IBleStateListener iBleStateListener) {
        this.iBleStateListener = iBleStateListener;
    }

    IonMacLinsteners ionMacLinsteners;

    public void setIonMacLinsteners(IonMacLinsteners ionMacLinsteners) {
        this.ionMacLinsteners = ionMacLinsteners;
    }

    public DerviceAdapter(Context context) {
        this.context = context;
        setDatas(mDatas);
    }



    public void setDatas(List<BleBean> datas) {
        if (datas != null)
            this.mDatas = datas;
        else
            this.mDatas = new ArrayList<BleBean>();
    }

    //数据更新的方法，用于数据改变时刷新数据
    public void updataAdapter(List<BleBean> datas) {
        setDatas(datas);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_dervice, viewGroup,false));

        return  holder;
    }

    //0 是去连接  1 是去断开
    int  isConntion=0;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        final MyViewHolder holder = (MyViewHolder) viewHolder;
        final BleBean dataBean = mDatas.get(i);
        holder.tvName.setText(dataBean.getName());

        holder.tvState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isConntion==0){
                /**
                 *     去连接操作
                 **/
                    holder.progressBar.setVisibility(View.VISIBLE);
                holder.tvState.setVisibility(View.GONE);
//                ionMacLinsteners.macLinstener(dataBean.getName(),dataBean.getAddress(),i);
//                SPUtils.putInt(context,"POSID",i);
                 BleManger.getInstance().connectBle(dataBean.getAddress(), new BleConnectResponse() {
                    @Override
                    public void onResponse(int code, BleGattProfile data) {
                        LogUtils.e("code======" + code);

                        //成功
                        if (code == Constants.REQUEST_SUCCESS) {
                            for (int i = 0; i < data.getServices().size(); i++) {
                                LogUtils.e("uuuid=====" + data.getServices().get(i).getUUID());
                            }

                            iBleStateListener.setSuccess(i,dataBean.getAddress());
                            SPUtils.putString(context, "mac", dataBean.getAddress());
                            SPUtils.putString(context, "name", dataBean.getName());
                            BleManger.getInstance().openNotify(dataBean.getAddress(), BleConstant.seviceUID, BleConstant.NotifyUID);
                            BleManger.getInstance().sendCmd(saiyi.com.gulin_new_wz.utils.SPUtils.getString(context,MAC,""),UUID_SERVICE, UUID_WRITE_READ,DeviceInstructionsEntity.getHaveEndByte(DeviceQueryEntity.BLE_CONNECT_INIT));


                            holder.progressBar.setVisibility(View.GONE);
                            holder.tvState.setVisibility(View.VISIBLE);
                            holder.tvState.setTextColor(Color.parseColor("#00DAB0"));
                            holder.tvState.setText(context.getResources().getString(R.string.close));
                            isConntion=1;

                        }
                        //失败
                        else if (code == Constants.REQUEST_FAILED) {
                            LogUtils.e("连接失败======");
//                            holder.tvState.setTextColor(Color.parseColor("#00DAB0"));


                        }
                    }
                });

            }else {
                    BleManger.getInstance().disConnectBle(dataBean.getAddress());
                    holder.progressBar.setVisibility(View.GONE);
                    holder.tvState.setVisibility(View.VISIBLE);
                    holder.tvState.setTextColor(Color.parseColor("#704E5969"));
                    holder.tvState.setText(context.getResources().getString(R.string.connetction));
                    isConntion=0;
                    iBleStateListener.setFail(i);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvState;
        ProgressBar progressBar;
        public MyViewHolder(View view){
            super(view);
            tvName =view.findViewById(R.id.tv_name);
            tvState =view.findViewById(R.id.tv_state);
            progressBar =view.findViewById(R.id.bar);


        }
    }


}