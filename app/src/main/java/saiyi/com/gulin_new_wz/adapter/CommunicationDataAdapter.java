package saiyi.com.gulin_new_wz.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.LinkedList;

import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.ui.communication_data.CommunicationBean;

/**
 *〈通信数据adapter〉
 *
 * @version [版本号, 2017/12/28]
 *
 * @author Huyongqing
 *
 * @since [产品/模块版本]
 */

public class CommunicationDataAdapter extends BaseQuickAdapter<CommunicationBean, BaseViewHolder> {


    public CommunicationDataAdapter(LinkedList<CommunicationBean> data) {
        super(R.layout.item_communication_data, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunicationBean item) {
        helper.setText(R.id.tv_communication_data,item.getContent());
        helper.setText(R.id.tv_communication_time,item.getTime());
        if (item.getIsSent()==1){
            helper.setText(R.id.tv_communication_sent_flag,"Send");
            helper.setBackgroundColor(R.id.fl_sent, Color.parseColor("#FF6A6A"));
        }else{
            helper.setText(R.id.tv_communication_sent_flag,"Receive");
            helper.setBackgroundColor(R.id.fl_sent, Color.parseColor("#C0FF3E"));
        }
    }
}
