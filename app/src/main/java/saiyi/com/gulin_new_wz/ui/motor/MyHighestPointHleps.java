package saiyi.com.gulin_new_wz.ui.motor;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.ble.BleConstant;
import saiyi.com.gulin_new_wz.ble.MacIdEntity;
import saiyi.com.gulin_new_wz.entity.DeviceInstructionsEntity;
import saiyi.com.gulin_new_wz.view.SignSeekBar;

import static saiyi.com.gulin_new_wz.utils.Constants.FALSE;
import static saiyi.com.gulin_new_wz.utils.Constants.TRUE;
import static saiyi.com.gulin_new_wz.utils.Constants.VALUE;

/**
 * Created by 陈姣姣 on 2018/8/30.
 */
public class MyHighestPointHleps extends AbsBleViewControl implements View.OnClickListener, SignSeekBar.OnProgressChangedListener{


    @BindView(R.id.iv_highest_point_helps_time)
    ImageView iv_highest_point_helps_time;
    @BindView(R.id.btn_highest_point_helps_time)
    LinearLayout btn_highest_point_helps_time;
    @BindView(R.id.btn_hph_time_lr_control)
    ImageView btn_hph_time_lr_control;
    @BindView(R.id.tv_hph_time_lr_name)
    TextView tv_hph_time_lr_name;
    @BindView(R.id.tv_hph_time_lr_def_name)
    TextView tv_hph_time_lr_def_name;
    @BindView(R.id.tv_hph_time_lr_def_value)
    TextView tv_hph_time_lr_def_value;
    @BindView(R.id.ssb_hph_time_lr_control)
    SignSeekBar ssb_hph_time_lr_control;
    @BindView(R.id.top_tv_highest_helps)
    TextView top_tv_highest_helps;
    @BindView(R.id.tv_hph_time_left_name)
    TextView tv_hph_time_left_name;
    @BindView(R.id.tv_hph_time_left_def_name)
    TextView tv_hph_time_left_def_name;
    @BindView(R.id.tv_hph_time_left_def_value)
    TextView tv_hph_time_left_def_value;
    @BindView(R.id.center_tv_highest_helps)
    TextView center_tv_highest_helps;
    @BindView(R.id.tv_hph_time_right_name)
    TextView tv_hph_time_right_name;
    @BindView(R.id.tv_hph_time_right_def_name)
    TextView tv_hph_time_right_def_name;
    @BindView(R.id.tv_hph_time_right_def_value)
    TextView tv_hph_time_right_def_value;
    @BindView(R.id.ssb_hph_time_right_control)
    SignSeekBar ssb_hph_time_right_control;
    @BindView(R.id.bottom_tv_highest_helps)
    TextView bottom_tv_highest_helps;
    @BindView(R.id.ll_hph_time_items)
    LinearLayout ll_hph_time_items;
    @BindView(R.id.ll_highest_point)
    LinearLayout ll_highest_point;
    @BindView(R.id.ssb_hph_time_left_control)
    SignSeekBar ssb_hph_time_left_control;

    /**
     * 最高助力时间左右群控状态，1左右群控，2单控
     */
    private int hph_time_lr_state = TRUE;

    /**
     *  判断是用于那一个按钮的输入框进入的
     *
     *
     * */

    private int showDiaglogType = 0;


    public MyHighestPointHleps(AppCompatActivity appCompatActivity, int viewId) {
        super(appCompatActivity, viewId);

        setLinsetener();
        //设置进度条颜色和一些相关文字的颜色
        leftAndRightControlSelectStateNewWz(btn_hph_time_lr_control, tv_hph_time_lr_name, tv_hph_time_lr_def_name, tv_hph_time_lr_def_value, ssb_hph_time_lr_control,
                tv_hph_time_left_name, tv_hph_time_left_def_name, tv_hph_time_left_def_value, ssb_hph_time_left_control,
                tv_hph_time_right_name, tv_hph_time_right_def_name, tv_hph_time_right_def_value, ssb_hph_time_right_control, hph_time_lr_state,top_tv_highest_helps,center_tv_highest_helps,bottom_tv_highest_helps);

    }

    @Override
    public boolean registReciver(LocalBroadcastManager localBroadcastManager, BroadcastReceiver receiver) {
        IntentFilter intentFilter =new IntentFilter(VALUE);
        intentFilter.addAction(BleConstant.SUCCED);
        intentFilter.addAction(BleConstant.DUANKAI);
        localBroadcastManager.registerReceiver(receiver,intentFilter);

        return false;
    }

    int MsgProgress ;
    @Override
    public void reciver(Intent intent) {
        super.reciver(intent);
        String action =intent.getAction();
        if (action.contains(VALUE)){
            /**
             *  获取用户输入的值，现在要刷新UI了
             * */
            MsgProgress =  Integer.parseInt(intent.getStringExtra(VALUE)) ;

            if (showDiaglogType==0){

                tv_hph_time_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_hph_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_hph_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));

                top_tv_highest_helps.setText(setText(MsgProgress));
                center_tv_highest_helps.setText(setText(MsgProgress));
                bottom_tv_highest_helps.setText(setText(MsgProgress));

                ssb_hph_time_lr_control.setProgress(MsgProgress);
                ssb_hph_time_left_control.setProgress(MsgProgress);
                ssb_hph_time_right_control.setProgress(MsgProgress);

                sendValueInstructions(DeviceInstructionsEntity.BEST_DELAY_SAME, MsgProgress, DeviceInstructionsEntity.MAX_HIGHEST_HELPS);

            }else if (showDiaglogType==1){
                tv_hph_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                center_tv_highest_helps.setText(setText(MsgProgress));
                ssb_hph_time_left_control.setProgress(MsgProgress);
                sendValueInstructions(DeviceInstructionsEntity.BEST_DELAY_LEFT_NEW, MsgProgress, DeviceInstructionsEntity.MAX_HIGHEST_HELPS);


            }else {
                tv_hph_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                bottom_tv_highest_helps.setText(setText(MsgProgress));
                ssb_hph_time_right_control.setProgress(MsgProgress);
                sendValueInstructions(DeviceInstructionsEntity.BEST_DELAY_RIGHT_NEW, MsgProgress, DeviceInstructionsEntity.MAX_HIGHEST_HELPS);

            }
        }else if (action.contains(BleConstant.SUCCED)){

            iv_highest_point_helps_time.setImageResource(R.mipmap.power_of_time);
        }else if (action.contains(BleConstant.DUANKAI)){

            iv_highest_point_helps_time.setImageResource(R.mipmap.power_of_time_d);
        }

    }


    @OnClick({R.id.tv_hph_time_lr_def_name,R.id.tv_hph_time_left_def_name,R.id.tv_hph_time_right_def_name,R.id.btn_highest_point_helps_time,R.id.btn_hph_time_lr_control,R.id. top_tv_highest_helps,  R.id. center_tv_highest_helps,R.id. bottom_tv_highest_helps})
    @Override
    public void onClick(View view) {

        switch (view.getId()){


            case R.id.tv_hph_time_lr_def_name:
                tv_hph_time_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"0°");
                tv_hph_time_left_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"0°");
                tv_hph_time_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"0°");
                top_tv_highest_helps.setText(setText(0));
                center_tv_highest_helps.setText(setText(0));
                bottom_tv_highest_helps.setText(setText(0));
                ssb_hph_time_lr_control.setProgress(0);
                ssb_hph_time_left_control.setProgress(0);
                ssb_hph_time_right_control.setProgress(0);
                sendValueInstructions(DeviceInstructionsEntity.BEST_DELAY_SAME, 0, DeviceInstructionsEntity.MAX_HIGHEST_HELPS);

                break;
            case R.id.tv_hph_time_left_def_name:
                tv_hph_time_left_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"0°");
                ssb_hph_time_left_control.setProgress(0);
                center_tv_highest_helps.setText(setText(0));
                sendValueInstructions(DeviceInstructionsEntity.BEST_DELAY_LEFT_NEW, 0, DeviceInstructionsEntity.MAX_HIGHEST_HELPS);
                break;
            case R.id.tv_hph_time_right_def_name:

                tv_hph_time_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"0°");
                bottom_tv_highest_helps.setText(setText(0));
                ssb_hph_time_right_control.setProgress(0);
                sendValueInstructions(DeviceInstructionsEntity.BEST_DELAY_RIGHT_NEW, 0, DeviceInstructionsEntity.MAX_HIGHEST_HELPS);

                break;

            case R.id.btn_highest_point_helps_time://最高助力时间展开收缩按钮


                if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {

                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.connect_device_please), Toast.LENGTH_SHORT).show();

                } else {
                    //如果是展开状态
                    if (btn_highest_point_helps_time.isSelected()) {
                        btn_highest_point_helps_time.setSelected(false);
                        ll_hph_time_items.setVisibility(View.GONE);
                    }
                    //如果是收缩状态
                    else {
                        /**
                         *
                         *     A 版本注释
                         *
                         * */
//                    queryState(DeviceQueryEntity.BEST_DELAY_LEFT, DeviceQueryEntity.BEST_DELAY_RIGHT);
                        btn_highest_point_helps_time.setSelected(true);
                        ll_hph_time_items.setVisibility(View.VISIBLE);
                    }
                }
                break;

            case R.id.btn_hph_time_lr_control://最高助力时间左右群控选择按钮
                //如果左右群控选中
                if (hph_time_lr_state == TRUE) {
                    hph_time_lr_state = FALSE;
                } else {
                    hph_time_lr_state = TRUE;
                }
                //设置进度条颜色和一些相关文字的颜色
                 leftAndRightControlSelectStateNewWz(btn_hph_time_lr_control, tv_hph_time_lr_name, tv_hph_time_lr_def_name, tv_hph_time_lr_def_value, ssb_hph_time_lr_control,
                        tv_hph_time_left_name, tv_hph_time_left_def_name, tv_hph_time_left_def_value, ssb_hph_time_left_control,
                        tv_hph_time_right_name, tv_hph_time_right_def_name, tv_hph_time_right_def_value, ssb_hph_time_right_control, hph_time_lr_state,top_tv_highest_helps,center_tv_highest_helps,bottom_tv_highest_helps);
                break;


            case R.id. top_tv_highest_helps:

                showDiaglogType = 0;

                if (hph_time_lr_state==1){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~200)");

                }else {

                }


                break;
            case R.id. center_tv_highest_helps:


                showDiaglogType = 1;

                if (hph_time_lr_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~200)");

                }else {

                }




                break;
            case R.id. bottom_tv_highest_helps:


                showDiaglogType = 2;

                if (hph_time_lr_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~200)");

                }else {

                }

                break;




        }


    }

    private  void setLinsetener(){

        ssb_hph_time_lr_control.setOnProgressChangedListener(this);
        ssb_hph_time_left_control.setOnProgressChangedListener(this);
        ssb_hph_time_right_control.setOnProgressChangedListener(this);

        top_tv_highest_helps.setText(setText(0));
        center_tv_highest_helps.setText(setText(0));
        bottom_tv_highest_helps.setText(setText(0));

        tv_hph_time_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"0°");
        tv_hph_time_left_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"0°");
        tv_hph_time_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"0°");





    }

    @Override
    public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
        switch (signSeekBar.getId()) {

            case R.id.ssb_hph_time_lr_control://最高点_群控


                top_tv_highest_helps.setText(setText(progress));
                center_tv_highest_helps.setText(setText(progress));
                bottom_tv_highest_helps.setText(setText(progress));
                break;

            case R.id.ssb_hph_time_left_control://左单控__最高点

                center_tv_highest_helps.setText(setText(progress));



                break;

            case R.id.ssb_hph_time_right_control://右单控__最高点

                bottom_tv_highest_helps.setText(setText(progress));

                break;




        }
    }

    @Override
    public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {
        switch (signSeekBar.getId()) {

            case R.id.ssb_hph_time_lr_control://最高点_群控

                /**
                 *  之前的协议需要调整
                 * */
//                if (progress <= 25) {
//                    ssb_hph_time_left_control.setProgress(0);
//                    ssb_hph_time_right_control.setProgress(0);
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_SAME_0));
//                } else if (progress <= 75) {
//                    ssb_hph_time_left_control.setProgress(50);
//                    ssb_hph_time_right_control.setProgress(50);
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_SAME_50));
//                } else if (progress <= 125) {
//                    ssb_hph_time_left_control.setProgress(100);
//                    ssb_hph_time_right_control.setProgress(100);
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_SAME_100));
//                } else if (progress <= 175) {
//                    ssb_hph_time_left_control.setProgress(150);
//                    ssb_hph_time_right_control.setProgress(150);
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_SAME_150));
//                } else if (progress <= 200) {
//                    ssb_hph_time_left_control.setProgress(200);
//                    ssb_hph_time_right_control.setProgress(200);
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_SAME_200));
//                }
//

                tv_hph_time_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_hph_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_hph_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));


                    ssb_hph_time_left_control.setProgress(progress);
                    ssb_hph_time_right_control.setProgress(progress);
                    top_tv_highest_helps.setText(setText(progress));
                    center_tv_highest_helps.setText(setText(progress));
                    bottom_tv_highest_helps.setText(setText(progress));
                    sendValueInstructions(DeviceInstructionsEntity.BEST_DELAY_SAME, progress, DeviceInstructionsEntity.MAX_HIGHEST_HELPS);
                break;

            case R.id.ssb_hph_time_left_control://左单控__最高点

                /**
                 *  之前的协议需要调整
                 * */
//                if (progress <= 25) {
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_LEFT_0));
//                } else if (progress <= 75) {
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_LEFT_50));
//                } else if (progress <= 125) {
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_LEFT_100));
//                } else if (progress <= 175) {
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_LEFT_150));
//                } else if (progress <= 200) {
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_LEFT_200));
//                }
                tv_hph_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                center_tv_highest_helps.setText(setText(progress));
                ssb_hph_time_left_control.setProgress(progress);
                sendValueInstructions(DeviceInstructionsEntity.BEST_DELAY_LEFT_NEW, progress, DeviceInstructionsEntity.MAX_HIGHEST_HELPS);



                break;

            case R.id.ssb_hph_time_right_control://右单控__最高点

                /**
                 *  之前的协议需要调整
                 * */
//                if (progress <= 25) {
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_RIGHT_0));
//                } else if (progress <= 75) {
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_RIGHT_50));
//                } else if (progress <= 125) {
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_RIGHT_100));
//                } else if (progress <= 175) {
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_RIGHT_150));
//                } else if (progress <= 200) {
//                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.BEST_DELAY_RIGHT_200));
//                }
                tv_hph_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                bottom_tv_highest_helps.setText(setText(progress));
                ssb_hph_time_right_control.setProgress(progress);
                sendValueInstructions(DeviceInstructionsEntity.BEST_DELAY_RIGHT_NEW, progress, DeviceInstructionsEntity.MAX_HIGHEST_HELPS);

                break;




        }
    }

    @Override
    public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {

    }
}
