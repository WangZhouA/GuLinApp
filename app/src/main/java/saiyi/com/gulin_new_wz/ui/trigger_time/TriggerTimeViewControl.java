package saiyi.com.gulin_new_wz.ui.trigger_time;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import saiyi.com.gulin_new_wz.ui.motor.AbsBleViewControl;
import saiyi.com.gulin_new_wz.view.SignSeekBar;

import static saiyi.com.gulin_new_wz.utils.Constants.FALSE;
import static saiyi.com.gulin_new_wz.utils.Constants.LEGS_MODLE;
import static saiyi.com.gulin_new_wz.utils.Constants.MODE;
import static saiyi.com.gulin_new_wz.utils.Constants.TRUE;
import static saiyi.com.gulin_new_wz.utils.Constants.VALUE;

/**
 * Created by 陈姣姣 on 2018/9/22.
 */
public class TriggerTimeViewControl extends AbsBleViewControl implements View.OnClickListener,SignSeekBar.OnProgressChangedListener{

    @BindView(R.id.iv_trigger_time)
    ImageView iv_trigger_time;
    @BindView(R.id.btn_trigger_time)
    LinearLayout btn_trigger_time;
    @BindView(R.id.btn_trigger_time_lr_control)
    ImageView btn_trigger_time_lr_control;
    @BindView(R.id.tv_trigger_time_lr_name)
    TextView tv_trigger_time_lr_name;
    @BindView(R.id.tv_trigger_time_lr_def_name)
    TextView tv_trigger_time_lr_def_name;
    @BindView(R.id.tv_trigger_time_lr_def_value)
    TextView tv_trigger_time_lr_def_value;
    @BindView(R.id.ssb_trigger_time_lr_control)
    SignSeekBar ssb_trigger_time_lr_control;
    @BindView(R.id.top_tv_trigger_time)
    TextView top_tv_trigger_time;
    @BindView(R.id.tv_trigger_time_left_name)
    TextView tv_trigger_time_left_name;
    @BindView(R.id.tv_trigger_time_left_def_name)
    TextView tv_trigger_time_left_def_name;
    @BindView(R.id.tv_trigger_time_left_def_value)
    TextView tv_trigger_time_left_def_value;
    @BindView(R.id.ssb_trigger_time_left_control)
    SignSeekBar ssb_trigger_time_left_control;
    @BindView(R.id.center_trigger_time)
    TextView center_trigger_time;
    @BindView(R.id.tv_trigger_time_right_name)
    TextView tv_trigger_time_right_name;
    @BindView(R.id.tv_trigger_time_right_def_name)
    TextView tv_trigger_time_right_def_name;
    @BindView(R.id.tv_trigger_time_right_def_value)
    TextView tv_trigger_time_right_def_value;
    @BindView(R.id.ssb_trigger_time_right_control)
    SignSeekBar ssb_trigger_time_right_control;
    @BindView(R.id.bottom_trigger_time)
    TextView bottom_trigger_time;
    @BindView(R.id.ll_trigger_time_items)
    LinearLayout ll_trigger_time_items;
    @BindView(R.id.ll_trigger_time)
    LinearLayout ll_trigger_time;


    /**
     *   触发时间左右群控状态，1左右群控，2单控
     */
    private int trigger_time_lr_control_state = TRUE;



    public TriggerTimeViewControl(AppCompatActivity appCompatActivity, int viewId) {
        super(appCompatActivity, viewId);

        setLinstener();
        leftAndRightControlSelectStateNewWz(btn_trigger_time_lr_control,
                tv_trigger_time_lr_name, tv_trigger_time_lr_def_name, tv_trigger_time_lr_def_value,
                ssb_trigger_time_lr_control,
                tv_trigger_time_left_name, tv_trigger_time_left_def_name,
                tv_trigger_time_left_def_value, ssb_trigger_time_left_control,
                tv_trigger_time_right_name, tv_trigger_time_right_def_name,
                tv_trigger_time_right_def_value, ssb_trigger_time_right_control,
                trigger_time_lr_control_state,top_tv_trigger_time,center_trigger_time,bottom_trigger_time);

    }

    private void setLinstener() {

        ssb_trigger_time_lr_control.setOnProgressChangedListener(this);
        ssb_trigger_time_left_control.setOnProgressChangedListener(this);
        ssb_trigger_time_right_control.setOnProgressChangedListener(this);

        ssb_trigger_time_lr_control.setProgress(1);
        ssb_trigger_time_left_control.setProgress(1);
        ssb_trigger_time_right_control.setProgress(1);

        top_tv_trigger_time.setText(setText(1));
        center_trigger_time.setText(setText(1));
        bottom_trigger_time.setText(setText(1));

        tv_trigger_time_lr_def_name.setText(getActivity().getResources().getString
                (R.string.default_text)+"1s");
        tv_trigger_time_left_def_name.setText(getActivity().getResources().getString
                (R.string.default_text)+"1s");
        tv_trigger_time_right_def_name.setText(getActivity().getResources().getString
                (R.string.default_text)+"1s");

    }


    @Override
    public boolean registReciver(LocalBroadcastManager localBroadcastManager, BroadcastReceiver receiver) {
        IntentFilter intentFilter =new IntentFilter(MODE);
        intentFilter.addAction(VALUE);
        intentFilter.addAction(BleConstant.SUCCED);
        intentFilter.addAction(BleConstant.DUANKAI);


        localBroadcastManager.registerReceiver(receiver,intentFilter);

        return false;
    }

    private  int mode;
    private  int  MsgProgress;
    private  int  showDiaglogType;
    @Override
    public void reciver(Intent intent) {
        super.reciver(intent);

        String action =intent.getAction();
        if ( action.contains(MODE)){
            mode = intent.getIntExtra(MODE,0);


        }else if (action.contains(BleConstant.SUCCED)){

            iv_trigger_time.setImageResource(R.mipmap.trigger_ed);
        }else if (action.contains(BleConstant.DUANKAI)){

            iv_trigger_time.setImageResource(R.mipmap.trigger);



        }else if (action.contains(VALUE)){

            /**
             *  获取用户输入的值，现在要刷新UI了
             * */
            MsgProgress =  Integer.parseInt(intent.getStringExtra(VALUE)) ;

            if (showDiaglogType==0){

                tv_trigger_time_lr_def_name.setText(getActivity().getResources().getString
                        (R.string.restore_defaults));
                tv_trigger_time_left_def_name.setText(getActivity().getResources().getString
                        (R.string.restore_defaults));
                tv_trigger_time_right_def_name.setText(getActivity().getResources().getString
                        (R.string.restore_defaults));


                top_tv_trigger_time.setText(setText(MsgProgress));
                center_trigger_time.setText(setText(MsgProgress));
                bottom_trigger_time.setText(setText(MsgProgress));

                ssb_trigger_time_lr_control.setProgress(MsgProgress);
                ssb_trigger_time_left_control.setProgress(MsgProgress);
                ssb_trigger_time_right_control.setProgress(MsgProgress);
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_SAME, MsgProgress,
                        DeviceInstructionsEntity.MAX_BRAKE_DURATION);


            }else if (showDiaglogType==1){
                tv_trigger_time_left_def_name.setText(getActivity().getResources().getString
                        (R.string.restore_defaults));

                center_trigger_time.setText(setText(MsgProgress));
                ssb_trigger_time_left_control.setProgress(MsgProgress);

                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_LEFT, MsgProgress,
                        DeviceInstructionsEntity.MAX_BRAKE_DURATION);

            }else if (showDiaglogType==2){
                tv_trigger_time_right_def_name.setText(getActivity().getResources().getString
                        (R.string.restore_defaults));
                bottom_trigger_time.setText(setText(MsgProgress));
                ssb_trigger_time_right_control.setProgress(MsgProgress);
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_RIGHT, MsgProgress,
                        DeviceInstructionsEntity.MAX_BRAKE_DURATION);

            }
        }

    }



    @OnClick({R.id.tv_trigger_time_lr_def_name,R.id.tv_trigger_time_left_def_name,R.id.tv_trigger_time_right_def_name
            ,R.id.top_tv_trigger_time,R.id.center_trigger_time,R.id.bottom_trigger_time
                    ,R.id.btn_trigger_time,R.id.btn_trigger_time_lr_control})
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tv_trigger_time_lr_def_name:

                initializationSenVelocitySensitivity(1,0,true,15);

                break;
            case R.id.tv_trigger_time_left_def_name:
                initializationSenVelocitySensitivity(1,1,true,15);
                break;
            case R.id.tv_trigger_time_right_def_name:

                initializationSenVelocitySensitivity(1,2,true,15);
                break;




            case R.id.btn_trigger_time://初始刹车力展开收缩按钮

                if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {

                    Toast.makeText(getActivity(), getActivity().getResources().getString
                            (R.string.connect_device_please), Toast.LENGTH_SHORT).show();

                } else {

                    //如果是展开
                    if (btn_trigger_time.isSelected()) {
                        btn_trigger_time.setSelected(false);
                        ll_trigger_time_items.setVisibility(View.GONE);
                    }
                    //如果是收缩
                    else {
                        /**
                         *
                         *     A 版本注释
                         *
                         * */
//                      queryState(DeviceQueryEntity.BRAKE_FORCE_LEFT, DeviceQueryEntity.BRAKE_FORCE_RIGHT);
                        btn_trigger_time.setSelected(true);
                        ll_trigger_time_items.setVisibility(View.VISIBLE);
                    }
                }

                break;

            case R.id.btn_trigger_time_lr_control://初始刹车力左右群控选择按钮
                //如果左右群控选中
                if (trigger_time_lr_control_state == TRUE) {
                    trigger_time_lr_control_state = FALSE;
                } else {
                    trigger_time_lr_control_state = TRUE;
                }
                //设置进度条颜色和一些相关文字的颜色
                leftAndRightControlSelectStateNewWz(btn_trigger_time_lr_control,
                        tv_trigger_time_lr_name, tv_trigger_time_lr_def_name, tv_trigger_time_lr_def_value,
                        ssb_trigger_time_lr_control,
                        tv_trigger_time_left_name, tv_trigger_time_left_def_name,
                        tv_trigger_time_left_def_value, ssb_trigger_time_left_control,
                        tv_trigger_time_right_name, tv_trigger_time_right_def_name,
                        tv_trigger_time_right_def_value, ssb_trigger_time_right_control,
                        trigger_time_lr_control_state,top_tv_trigger_time,center_trigger_time,bottom_trigger_time);

                break;

            case R.id. top_tv_trigger_time:

                showDiaglogType = 0;

                if (trigger_time_lr_control_state==1){

                    showDialog(getActivity().getResources().getString
                            (R.string.Manually_enter_values)+"(0~5)");

                }else {

                }


                break;
            case R.id. center_trigger_time:

                showDiaglogType = 1;

                if (trigger_time_lr_control_state==2){

                    showDialog(getActivity().getResources().getString
                            (R.string.Manually_enter_values)+"(0~5)");

                }else {

                }




                break;
            case R.id. bottom_trigger_time:

                showDiaglogType = 2;

                if (trigger_time_lr_control_state==2){

                    showDialog(getActivity().getResources().getString
                            (R.string.Manually_enter_values)+"(0~5)");

                }else {

                }

                break;

        }




    }

    @Override
    public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {




//        ssb_trigger_time_lr_control.setProgress(1);
//        ssb_trigger_time_left_control.setProgress(1);
//        ssb_trigger_time_right_control.setProgress(1);
//
//        top_tv_trigger_time.setText(setText(1));
//        center_trigger_time.setText(setText(1));
//        bottom_trigger_time.setText(setText(1));
//
//        tv_trigger_time_lr_def_name.setText(getActivity().getResources().getString
//                (R.string.default_text)+"1s");
//        tv_trigger_time_left_def_name.setText(getActivity().getResources().getString
//                (R.string.default_text)+"1s");
//        tv_trigger_time_right_def_name.setText(getActivity().getResources().getString
//                (R.string.default_text)+"1s");
//


        Log.e("=====2====",progress+"");


        switch (signSeekBar.getId()){

            case  R.id.ssb_trigger_time_lr_control:

                top_tv_trigger_time.setText(setText(progress));
                center_trigger_time.setText(setText(progress));
                bottom_trigger_time.setText(setText(progress));

                break;
            case  R.id.ssb_assist_angle_range_left_control:
                center_trigger_time.setText(setText(progress));

                break;
            case  R.id.ssb_assist_angle_range_right_control:
                bottom_trigger_time.setText(setText(progress));

                break;

        }

    }

    @Override
    public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {

        switch (signSeekBar.getId()){



//        ssb_trigger_time_lr_control.setProgress(1);
//        ssb_trigger_time_left_control.setProgress(1);
//        ssb_trigger_time_right_control.setProgress(1);
//
//        top_tv_trigger_time.setText(setText(1));
//        center_trigger_time.setText(setText(1));
//        bottom_trigger_time.setText(setText(1));
//
//        tv_trigger_time_lr_def_name.setText(getActivity().getResources().getString
//                (R.string.default_text)+"1s");
//        tv_trigger_time_left_def_name.setText(getActivity().getResources().getString
//                (R.string.default_text)+"1s");
//        tv_trigger_time_right_def_name.setText(getActivity().getResources().getString
//                (R.string.default_text)+"1s");
//


            case R.id.ssb_trigger_time_lr_control:


                Log.e("=====1====",progress+"");

                initializationSenVelocitySensitivity(2,3,true,100);
                top_tv_trigger_time.setText(setText(progress));
                center_trigger_time.setText(setText(progress));
                bottom_trigger_time.setText(setText(progress));

                ssb_trigger_time_left_control.setProgress(progress);
                ssb_trigger_time_right_control.setProgress(progress);

                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_SAME, MsgProgress,
                        DeviceInstructionsEntity.MAX_BRAKE_DURATION);


                break;

            case R.id.ssb_trigger_time_left_control://
                initializationSenVelocitySensitivity(2,4,true,progress);
                center_trigger_time.setText(setText(progress));
                ssb_trigger_time_left_control.setProgress(progress);

                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_LEFT, MsgProgress,
                        DeviceInstructionsEntity.MAX_BRAKE_DURATION);


                break;

            case R.id.ssb_trigger_time_right_control://

                ssb_trigger_time_right_control.setProgress(progress);
                bottom_trigger_time.setText(setText(progress));
                initializationSenVelocitySensitivity(2,5,true,progress);

                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_RIGHT, MsgProgress,
                        DeviceInstructionsEntity.MAX_BRAKE_DURATION);

                break;


        }



    }

    @Override
    public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {

    }

    // Number 是点击的那一个  Type 类别  vis 是否显示发送命令    progress 当前进度
    private  void   initializationSenVelocitySensitivity(int Type,int Number,boolean vis,int progress) {


//        ssb_trigger_time_lr_control.setProgress(1);
//        ssb_trigger_time_left_control.setProgress(1);
//        ssb_trigger_time_right_control.setProgress(1);
//
//        top_tv_trigger_time.setText(setText(1));
//        center_trigger_time.setText(setText(1));
//        bottom_trigger_time.setText(setText(1));
//
//        tv_trigger_time_lr_def_name.setText(getActivity().getResources().getString
//                (R.string.default_text)+"1s");
//        tv_trigger_time_left_def_name.setText(getActivity().getResources().getString
//                (R.string.default_text)+"1s");
//        tv_trigger_time_right_def_name.setText(getActivity().getResources().getString
//                (R.string.default_text)+"1s");
//

        if (Type == 1) {
            if (Number == 0) {
                tv_trigger_time_lr_def_name.setText(getActivity().getResources().getString
                        (R.string.default_text) + "1s");
                tv_trigger_time_left_def_name.setText(getActivity().getResources().getString
                        (R.string.default_text) + "1s");
                tv_trigger_time_right_def_name.setText(getActivity().getResources().getString
                        (R.string.default_text) + "1s");

                ssb_trigger_time_lr_control.setProgress(progress);
                ssb_trigger_time_left_control.setProgress(progress);
                ssb_trigger_time_right_control.setProgress(progress);
                top_tv_trigger_time.setText(getActivity().getResources().getString
                        (R.string.current)+"1"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                center_trigger_time.setText(getActivity().getResources().getString
                        (R.string.current)+"1"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                bottom_trigger_time.setText(getActivity().getResources().getString
                        (R.string.current)+"1"+" "+ getActivity().getResources().getString(R.string.Manual_input));


                if (vis){

                    sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_SAME, 15,
                            DeviceInstructionsEntity.MAX_BRAKE_DURATION);

                }

            }else  if (Number == 1){
                center_trigger_time.setText(getActivity().getResources().getString
                        (R.string.current)+"1"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                tv_trigger_time_left_def_name.setText(getActivity().getResources().getString
                        (R.string.default_text) + "1s");
                ssb_trigger_time_left_control.setProgress(progress);
                if (vis){

                    if (mode == LEGS_MODLE) { /**
                     双腿模式
                     */
                        sendInstructions(DeviceInstructionsEntity.getStringToByte
                                (DeviceInstructionsEntity.SPEED_LEFT, progress + ""));
                    } else {
                        sendInstructions(DeviceInstructionsEntity.getStringToByte
                                (DeviceInstructionsEntity.TRIGGER_SPEED_LEFT , progress + ""));
                    }
                }

            }else if (Number == 2){
                bottom_trigger_time .setText(getActivity().getResources().getString
                        (R.string.current)+"1"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                ssb_trigger_time_right_control.setProgress(progress);
                tv_trigger_time_right_def_name.setText(getActivity().getResources().getString
                        (R.string.default_text) + "1s");
                if (vis){

                    if (mode == LEGS_MODLE) { /**
                     双腿模式
                     */
                        sendInstructions(DeviceInstructionsEntity.getStringToByte
                                (DeviceInstructionsEntity.SPEED_RIGHT, progress + ""));
                    } else {
                        sendInstructions(DeviceInstructionsEntity.getStringToByte
                                (DeviceInstructionsEntity.TRIGGER_SPEED_RIGHT , progress + ""));
                    }
                }

            }
        }else {
            if (Number==3) {
                tv_trigger_time_lr_def_name.setText(getActivity().getResources().getString
                        (R.string.restore_defaults));
                tv_trigger_time_left_def_name.setText(getActivity().getResources().getString
                        (R.string.restore_defaults));
                tv_trigger_time_right_def_name.setText(getActivity().getResources().getString
                        (R.string.restore_defaults));
            }else if (Number==4){

                tv_trigger_time_left_def_name.setText(getActivity().getResources().getString
                        (R.string.restore_defaults));
            }else if (Number==5){

                tv_trigger_time_right_def_name.setText(getActivity().getResources().getString
                        (R.string.restore_defaults));

            }
        }

    }
}
