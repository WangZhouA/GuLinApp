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
import static saiyi.com.gulin_new_wz.utils.Constants.LEGS_MODLE;
import static saiyi.com.gulin_new_wz.utils.Constants.MODE;
import static saiyi.com.gulin_new_wz.utils.Constants.TRUE;
import static saiyi.com.gulin_new_wz.utils.Constants.VALUE;

/**
 * Created by 陈姣姣 on 2018/8/31.
 */
public class MyInitialBrakeForceViewControl extends  AbsBleViewControl implements View.OnClickListener,SignSeekBar.OnProgressChangedListener{


    @BindView(R.id.iv_brake_force)
    ImageView iv_brake_force;
    @BindView(R.id.btn_brake_force)
    LinearLayout btn_brake_force;
    @BindView(R.id.btn_brake_force_lr_control)
    ImageView btn_brake_force_lr_control;
    @BindView(R.id.tv_brake_force_lr_name)
    TextView tv_brake_force_lr_name;
    @BindView(R.id.tv_brake_force_lr_def_name)
    TextView tv_brake_force_lr_def_name;
    @BindView(R.id.tv_brake_force_lr_def_value)
    TextView tv_brake_force_lr_def_value;
    @BindView(R.id.ssb_brake_force_lr_control)
    SignSeekBar ssb_brake_force_lr_control;
    @BindView(R.id.top_brake_force)
    TextView top_brake_force;
    @BindView(R.id.tv_brake_force_left_name)
    TextView tv_brake_force_left_name;
    @BindView(R.id.tv_brake_force_left_def_name)
    TextView tv_brake_force_left_def_name;
    @BindView(R.id.tv_brake_force_left_def_value)
    TextView tv_brake_force_left_def_value;
    @BindView(R.id.ssb_brake_force_left_control)
    SignSeekBar ssb_brake_force_left_control;
    @BindView(R.id.center_brake_force)
    TextView center_brake_force;
    @BindView(R.id.tv_brake_force_right_name)
    TextView tv_brake_force_right_name;
    @BindView(R.id.tv_brake_force_right_def_name)
    TextView tv_brake_force_right_def_name;
    @BindView(R.id.tv_brake_force_right_def_value)
    TextView tv_brake_force_right_def_value;
    @BindView(R.id.ssb_brake_force_right_control)
    SignSeekBar ssb_brake_force_right_control;
    @BindView(R.id.bottom_brake_force)
    TextView bottom_brake_force;
    @BindView(R.id.ll_brake_force_items)
    LinearLayout ll_brake_force_items;
    @BindView(R.id.ll_brake_force)
    LinearLayout ll_brake_force;


    /**
     * 初始刹车力左右群控状态，1左右群控，2单控
     */
    private int brake_force_lr_control_state = TRUE;


    public MyInitialBrakeForceViewControl(AppCompatActivity appCompatActivity, int viewId) {
        super(appCompatActivity, viewId);

        setLinstener();

        leftAndRightControlSelectStateNewWz(btn_brake_force_lr_control, tv_brake_force_lr_name, tv_brake_force_lr_def_name, tv_brake_force_lr_def_value, ssb_brake_force_lr_control,
                tv_brake_force_left_name, tv_brake_force_left_def_name, tv_brake_force_left_def_value, ssb_brake_force_left_control,
                tv_brake_force_right_name, tv_brake_force_right_def_name, tv_brake_force_right_def_value, ssb_brake_force_right_control, brake_force_lr_control_state,top_brake_force,center_brake_force,bottom_brake_force);


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


    private  int  mode;
    private  int  MsgProgress;
    private  int  showDiaglogType;
    @Override
    public void reciver(Intent intent) {
        super.reciver(intent);

        String action =intent.getAction();
        if ( action.contains(MODE)) {
            mode = intent.getIntExtra(MODE, 0);
            Toast.makeText(getActivity(), "" + intent.getIntExtra(MODE, 0), Toast.LENGTH_SHORT).show();

        }else if (action.contains(BleConstant.SUCCED)){

            iv_brake_force.setImageResource(R.mipmap.braking_force);
        }else if (action.contains(BleConstant.DUANKAI)){

            iv_brake_force.setImageResource(R.mipmap.braking_force_d);
        }else if (action.contains(VALUE)){

            /**
             *  获取用户输入的值，现在要刷新UI了
             * */
            MsgProgress =  Integer.parseInt(intent.getStringExtra(VALUE)) ;

            if (showDiaglogType==0){

                tv_brake_force_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_brake_force_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_brake_force_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));

                top_brake_force.setText(setText(MsgProgress));
                center_brake_force.setText(setText(MsgProgress));
                bottom_brake_force.setText(setText(MsgProgress));

                ssb_brake_force_lr_control.setProgress(MsgProgress);
                ssb_brake_force_left_control.setProgress(MsgProgress);
                ssb_brake_force_right_control.setProgress(MsgProgress);
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_SAME, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);


            }else if (showDiaglogType==1){
                tv_brake_force_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                center_brake_force.setText(setText(MsgProgress));
                ssb_brake_force_left_control.setProgress(MsgProgress);

                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_LEFT, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);

            }else if (showDiaglogType==2){
                tv_brake_force_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                bottom_brake_force.setText(setText(MsgProgress));
                ssb_brake_force_right_control.setProgress(MsgProgress);
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_RIGHT, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);

            }
        }

    }

    @OnClick({R.id.tv_brake_force_lr_def_name,R.id.tv_brake_force_left_def_name,R.id.tv_brake_force_right_def_name,R.id.top_brake_force,R.id.center_brake_force,R.id.bottom_brake_force,R.id.btn_brake_force,R.id.btn_brake_force_lr_control})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_brake_force_lr_def_name:

                initializationSenVelocitySensitivity(1,0,true,100);

                break;
            case R.id.tv_brake_force_left_def_name:
                initializationSenVelocitySensitivity(1,1,true,100);
                break;
            case R.id.tv_brake_force_right_def_name:

                initializationSenVelocitySensitivity(1,2,true,100);
                break;

            case R.id.btn_brake_force://初始刹车力展开收缩按钮

                if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {

                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.connect_device_please), Toast.LENGTH_SHORT).show();

                } else {

                    //如果是展开
                    if (btn_brake_force.isSelected()) {
                        btn_brake_force.setSelected(false);
                        ll_brake_force_items.setVisibility(View.GONE);
                    }
                    //如果是收缩
                    else {
                        /**
                         *
                         *     A 版本注释
                         *
                         * */
//                    queryState(DeviceQueryEntity.BRAKE_FORCE_LEFT, DeviceQueryEntity.BRAKE_FORCE_RIGHT);
                        btn_brake_force.setSelected(true);
                        ll_brake_force_items.setVisibility(View.VISIBLE);
                    }
                }

                break;

            case R.id.btn_brake_force_lr_control://初始刹车力左右群控选择按钮
                //如果左右群控选中
                if (brake_force_lr_control_state == TRUE) {
                    brake_force_lr_control_state = FALSE;
                } else {
                    brake_force_lr_control_state = TRUE;
                }
                //设置进度条颜色和一些相关文字的颜色
                leftAndRightControlSelectStateNewWz(btn_brake_force_lr_control, tv_brake_force_lr_name, tv_brake_force_lr_def_name, tv_brake_force_lr_def_value, ssb_brake_force_lr_control,
                        tv_brake_force_left_name, tv_brake_force_left_def_name, tv_brake_force_left_def_value, ssb_brake_force_left_control,
                        tv_brake_force_right_name, tv_brake_force_right_def_name, tv_brake_force_right_def_value, ssb_brake_force_right_control, brake_force_lr_control_state,top_brake_force,center_brake_force,bottom_brake_force);
                break;

            case R.id. top_brake_force:

                showDiaglogType = 0;

                if (brake_force_lr_control_state==1){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }
                break;
            case R.id. center_brake_force:


                showDiaglogType = 1;

                if (brake_force_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }

                break;
            case R.id. bottom_brake_force:


                showDiaglogType = 2;

                if (brake_force_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }

                break;

        }
    }

    @Override
    public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
        switch (signSeekBar.getId()){

            case R.id.ssb_brake_force_lr_control://刹车力_群控


                center_brake_force.setText(setText(progress));
                top_brake_force.setText(setText(progress));
                bottom_brake_force.setText(setText(progress));



                break;

            case R.id.ssb_brake_force_left_control://左单控__刹车力
                center_brake_force.setText(setText(progress));

                break;

            case R.id.ssb_brake_force_right_control://右单控__刹车力

                bottom_brake_force.setText(setText(progress));

                break;


        }

    }

    private  void  setLinstener(){

        ssb_brake_force_lr_control.setOnProgressChangedListener(this);
        ssb_brake_force_left_control.setOnProgressChangedListener(this);
        ssb_brake_force_right_control.setOnProgressChangedListener(this);

        ssb_brake_force_lr_control.setProgress(100);
        ssb_brake_force_left_control.setProgress(100);
        ssb_brake_force_right_control.setProgress(100);

        top_brake_force.setText(setText(100));
        center_brake_force.setText(setText(100));
        bottom_brake_force.setText(setText(100));

        tv_brake_force_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"100°");
        tv_brake_force_left_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"100°");
        tv_brake_force_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"100°");

    }



    @Override
    public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {

        switch (signSeekBar.getId()){

            case R.id.ssb_brake_force_lr_control://刹车力_群控


                initializationSenVelocitySensitivity(2,3,true,100);
                center_brake_force.setText(setText(progress));
                top_brake_force.setText(setText(progress));
                bottom_brake_force.setText(setText(progress));

                ssb_brake_force_left_control.setProgress(progress);
                ssb_brake_force_right_control.setProgress(progress);

                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_SAME, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);


                break;

            case R.id.ssb_brake_force_left_control://左单控__刹车力
                initializationSenVelocitySensitivity(2,4,true,progress);
                center_brake_force.setText(setText(progress));
                ssb_brake_force_left_control.setProgress(progress);

                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_LEFT, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);


                break;

            case R.id.ssb_brake_force_right_control://右单控__刹车力

                ssb_brake_force_right_control.setProgress(progress);
                bottom_brake_force.setText(setText(progress));
                initializationSenVelocitySensitivity(2,5,true,progress);

                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_RIGHT, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);

                break;


        }

    }

    @Override
    public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {

    }


    // Number 是点击的那一个  Type 类别  vis 是否显示发送命令    progress 当前进度
    private  void   initializationSenVelocitySensitivity(int Type,int Number,boolean vis,int progress) {

        if (Type == 1) {
            if (Number == 0) {
                tv_brake_force_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "100°");
                tv_brake_force_left_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "100°");
                tv_brake_force_right_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "100°");

                ssb_brake_force_lr_control.setProgress(progress);
                ssb_brake_force_left_control.setProgress(progress);
                ssb_brake_force_right_control.setProgress(progress);
                top_brake_force.setText(getActivity().getResources().getString(R.string.current)+"100"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                center_brake_force.setText(getActivity().getResources().getString(R.string.current)+"100"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                bottom_brake_force.setText(getActivity().getResources().getString(R.string.current)+"100"+" "+ getActivity().getResources().getString(R.string.Manual_input));


                if (vis){

                    sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_SAME, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);

                }

            }else  if (Number == 1){
                center_brake_force.setText(getActivity().getResources().getString(R.string.current)+"100"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                tv_brake_force_left_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "100°");
                ssb_brake_force_left_control.setProgress(progress);
                if (vis){

                    if (mode == LEGS_MODLE) { /**
                     双腿模式
                     */
                        sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.SPEED_LEFT, progress + ""));
                    } else {
                        sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_SPEED_LEFT , progress + ""));
                    }
                }

            }else if (Number == 2){
                bottom_brake_force .setText(getActivity().getResources().getString(R.string.current)+"100"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                ssb_brake_force_right_control.setProgress(progress);
                tv_brake_force_right_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "100°");
                if (vis){

                    if (mode == LEGS_MODLE) { /**
                     双腿模式
                     */
                        sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.SPEED_RIGHT, progress + ""));
                    } else {
                        sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_SPEED_RIGHT , progress + ""));
                    }
                }


            }
        }else {
            if (Number==3) {
                tv_brake_force_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_brake_force_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_brake_force_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
            }else if (Number==4){

                tv_brake_force_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
            }else if (Number==5){

                tv_brake_force_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));

            }
        }

    }



}
