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
 * Created by 陈姣姣 on 2018/8/28.
 */
public class MySensitivityViewControl extends AbsBleViewControl implements View.OnClickListener, SignSeekBar.OnProgressChangedListener{



    @BindView(R.id.btn_speed)
    ImageView btn_speed;
    @BindView(R.id.iv_angular_velocity_sensitivity)
    ImageView iv_angular_velocity_sensitivity;
    @BindView(R.id.btn_angular_velocity_sensitivity)
    LinearLayout btn_angular_velocity_sensitivity;
    @BindView(R.id.btn_angular_velocity_sensitivity_lr_control)
    ImageView btn_angular_velocity_sensitivity_lr_control;
    @BindView(R.id.tv_angular_velocity_sensitivity_lr_name)
    TextView tv_angular_velocity_sensitivity_lr_name;
    @BindView(R.id.tv_angular_velocity_sensitivity_lr_def_name)
    TextView tv_angular_velocity_sensitivity_lr_def_name;
    @BindView(R.id.tv_angular_velocity_sensitivity_lr_def_value)
    TextView tv_angular_velocity_sensitivity_lr_def_value;
    @BindView(R.id.ssb_angular_velocity_sensitivity_lr_control)
    SignSeekBar ssb_angular_velocity_sensitivity_lr_control;
    @BindView(R.id.tv_angular_velocity_sensitivity_left_name)
    TextView tv_angular_velocity_sensitivity_left_name;
    @BindView(R.id.tv_angular_velocity_sensitivity_left_def_name)
    TextView tv_angular_velocity_sensitivity_left_def_name;
    @BindView(R.id.tv_angular_velocity_sensitivity_left_def_value)
    TextView tv_angular_velocity_sensitivity_left_def_value;
    @BindView(R.id.ssb_angular_velocity_sensitivity_left_control)
    SignSeekBar ssb_angular_velocity_sensitivity_left_control;
    @BindView(R.id.tv_angular_velocity_sensitivity_right_name)
    TextView tv_angular_velocity_sensitivity_right_name;
    @BindView(R.id.tv_angular_velocity_sensitivity_right_def_name)
    TextView tv_angular_velocity_sensitivity_right_def_name;
    @BindView(R.id.tv_angular_velocity_sensitivity_right_def_value)
    TextView tv_angular_velocity_sensitivity_right_def_value;
    @BindView(R.id.ssb_angular_velocity_sensitivity_right_control)
    SignSeekBar ssb_angular_velocity_sensitivity_right_control;
    @BindView(R.id.ll_angular_velocity_sensitivity_items)
    LinearLayout ll_angular_velocity_sensitivity_items;

    @BindView(R.id.lin_velocity_sensitivity)
    LinearLayout lin_velocity_sensitivity;

    @BindView(R.id.lin_tab_velocity_sensitivity)
    LinearLayout lin_tab_velocity_sensitivity;

    @BindView(R.id.top_tv_angular_velocity_sensitivity)
    TextView top_tv_angular_velocity_sensitivity;

    @BindView(R.id.center_tv_angular_velocity_sensitivity)
    TextView  center_tv_angular_velocity_sensitivity;

    @BindView(R.id.bottom_tv_angular_velocity_sensitivity)
    TextView bottom_tv_angular_velocity_sensitivity;





    private int mode ;





    @BindView(R.id.ll_sensitivity_angle_items)
    LinearLayout ll_sensitivity_angle_items;

    @BindView(R.id.btn_angle)
    ImageView btn_angle;

    @BindView(R.id.btn_sensitivity_angle_lr_control)
    ImageView btn_sensitivity_angle_lr_control;

    @BindView(R.id.tv_sensitivity_angle_lr_name)
    TextView tv_sensitivity_angle_lr_name;

    @BindView(R.id.tv_sensitivity_angle_lr_def_name)
    TextView tv_sensitivity_angle_lr_def_name;

    @BindView(R.id.tv_sensitivity_angle_lr_def_value)
    TextView tv_sensitivity_angle_lr_def_value;

    @BindView(R.id.ssb_sensitivity_angle_lr_control)
    SignSeekBar ssb_sensitivity_angle_lr_control;

    @BindView(R.id.tv_sensitivity_angle_left_name)
    TextView tv_sensitivity_angle_left_name;

    @BindView(R.id.tv_sensitivity_angle_left_def_name)
    TextView tv_sensitivity_angle_left_def_name;

    @BindView(R.id.tv_sensitivity_angle_left_def_value)
    TextView  tv_sensitivity_angle_left_def_value;

    @BindView(R.id.ssb_sensitivity_angle_left_control)
    SignSeekBar ssb_sensitivity_angle_left_control;

    @BindView(R.id.tv_sensitivity_angle_right_name)
    TextView tv_sensitivity_angle_right_name;

    @BindView(R.id.tv_sensitivity_angle_right_def_name)
    TextView tv_sensitivity_angle_right_def_name;


    @BindView(R.id.tv_sensitivity_angle_right_def_value)
    TextView  tv_sensitivity_angle_right_def_value;

    @BindView(R.id.ssb_sensitivity_angle_right_control)
    SignSeekBar ssb_sensitivity_angle_right_control;

    @BindView(R.id.lin_tab_sensitivity_angle)
    LinearLayout lin_tab_sensitivity_angle;

    @BindView(R.id.lin_gray_sensitivity)
    LinearLayout lin_gray_sensitivity;

    @BindView(R.id.top_tv_sensitivity_angle)
    TextView top_tv_sensitivity_angle;

    @BindView(R.id.center_tv_sensitivity_angle)
    TextView center_tv_sensitivity_angle;

    @BindView(R.id.bottom_tv_sensitivity_angle )
    TextView bottom_tv_sensitivity_angle;


    /**
     * 灵敏度角速度左右群控状态，1左右群控，2单控
     */
    private int angular_velocity_sensitivity_lr_control_state = TRUE;


    private int sensitivity_angle_lr_control_state =TRUE;


    /**
     *  判断是用于那一个按钮的输入框进入的
     *
     *
     * */

    private int showDiaglogType = 0;


    public MySensitivityViewControl(AppCompatActivity appCompatActivity, int viewId) {
        super(appCompatActivity, viewId);

        initSeekBar();

        /**
         *  初始化所以的颜色
         * */
        leftAndRightControlSelectStateNewWz(btn_angular_velocity_sensitivity_lr_control, tv_angular_velocity_sensitivity_lr_name, tv_angular_velocity_sensitivity_lr_def_name, tv_angular_velocity_sensitivity_lr_def_value, ssb_angular_velocity_sensitivity_lr_control,
                tv_angular_velocity_sensitivity_left_name, tv_angular_velocity_sensitivity_left_def_name, tv_angular_velocity_sensitivity_left_def_value, ssb_angular_velocity_sensitivity_left_control,
                tv_angular_velocity_sensitivity_right_name, tv_angular_velocity_sensitivity_right_def_name, tv_angular_velocity_sensitivity_right_def_value, ssb_angular_velocity_sensitivity_right_control, angular_velocity_sensitivity_lr_control_state,top_tv_angular_velocity_sensitivity,center_tv_angular_velocity_sensitivity,bottom_tv_angular_velocity_sensitivity);

        leftAndRightControlSelectStateNewWz(btn_sensitivity_angle_lr_control, tv_sensitivity_angle_lr_name, tv_sensitivity_angle_lr_def_name, tv_sensitivity_angle_lr_def_value, ssb_sensitivity_angle_lr_control,
                tv_sensitivity_angle_left_name, tv_sensitivity_angle_left_def_name, tv_sensitivity_angle_left_def_value, ssb_sensitivity_angle_left_control,
                tv_sensitivity_angle_right_name, tv_sensitivity_angle_right_def_name, tv_sensitivity_angle_right_def_value, ssb_sensitivity_angle_right_control, sensitivity_angle_lr_control_state
        ,top_tv_sensitivity_angle,center_tv_sensitivity_angle,bottom_tv_sensitivity_angle
        );


    }

    @Override
    public boolean registReciver(LocalBroadcastManager localBroadcastManager, BroadcastReceiver receiver) {
        IntentFilter intentFilter =new IntentFilter(MODE);
        intentFilter.addAction(VALUE);
        intentFilter.addAction(BleConstant.SUCCED);
        intentFilter.addAction(BleConstant.DUANKAI);

        localBroadcastManager.registerReceiver(receiver,intentFilter);
        return true;
    }
    int MsgProgress ;
    @Override
    public void reciver(Intent intent) {
        super.reciver(intent);
        String action =intent.getAction();
        if ( action.contains(MODE)){
            mode = intent.getIntExtra(MODE,0);
            Toast.makeText(getActivity(), ""+intent.getIntExtra(MODE,0), Toast.LENGTH_SHORT).show();
        }else if (action.contains(BleConstant.SUCCED)){

            iv_angular_velocity_sensitivity.setImageResource(R.mipmap.angular_velocity);
            }else if (action.contains(BleConstant.DUANKAI)){

            iv_angular_velocity_sensitivity.setImageResource(R.mipmap.angular_velocity_d);


        }else if (action.contains(VALUE)){

            /**
             *  获取用户输入的值，现在要刷新UI了
             * */
            MsgProgress =  Integer.parseInt(intent.getStringExtra(VALUE)) ;

            if (showDiaglogType==0){

                top_tv_angular_velocity_sensitivity.setText(setText(MsgProgress));
                center_tv_angular_velocity_sensitivity.setText(setText(MsgProgress));
                bottom_tv_angular_velocity_sensitivity.setText(setText(MsgProgress));





                tv_angular_velocity_sensitivity_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_angular_velocity_sensitivity_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_angular_velocity_sensitivity_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));





                ssb_angular_velocity_sensitivity_lr_control.setProgress(MsgProgress);
                ssb_angular_velocity_sensitivity_left_control.setProgress(MsgProgress);
                ssb_angular_velocity_sensitivity_right_control.setProgress(MsgProgress);
                if (mode == LEGS_MODLE) { /**
                 双腿模式
                 */
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.SPEED_SAME, MsgProgress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_SPEED_SAME, MsgProgress + ""));
                }

            }else if (showDiaglogType==1){
                tv_angular_velocity_sensitivity_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                center_tv_angular_velocity_sensitivity.setText(setText(MsgProgress));
                ssb_angular_velocity_sensitivity_left_control.setProgress(MsgProgress);
                if (mode == LEGS_MODLE) {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.SPEED_LEFT, MsgProgress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_SPEED_LEFT, MsgProgress + ""));
                }
            }else if (showDiaglogType==2){
                tv_angular_velocity_sensitivity_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                bottom_tv_angular_velocity_sensitivity.setText(setText(MsgProgress));
                ssb_angular_velocity_sensitivity_right_control.setProgress(MsgProgress);
                if (mode == LEGS_MODLE) {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.SPEED_RIGHT, MsgProgress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_SPEED_RIGHT, MsgProgress + ""));
                }

            }else if (showDiaglogType==3){

                tv_sensitivity_angle_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_sensitivity_angle_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_sensitivity_angle_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));


                top_tv_sensitivity_angle.setText(setText(MsgProgress));
                center_tv_sensitivity_angle.setText(setText(MsgProgress));
                bottom_tv_sensitivity_angle.setText(setText(MsgProgress));

                ssb_sensitivity_angle_lr_control.setProgress(MsgProgress);
                ssb_sensitivity_angle_left_control.setProgress(MsgProgress);
                ssb_sensitivity_angle_right_control.setProgress(MsgProgress);

                if (mode == LEGS_MODLE) {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.ANGLE_SAME, MsgProgress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_ANGLE_SAME, MsgProgress + ""));
                }

            }else if (showDiaglogType==4){
                tv_sensitivity_angle_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                center_tv_sensitivity_angle.setText(setText(MsgProgress));
                ssb_sensitivity_angle_left_control.setProgress(MsgProgress);

                if (mode == LEGS_MODLE) {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.ANGLE_LEFT, MsgProgress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_ANGLE_LEFT, MsgProgress + ""));
                }

            }else {
                tv_sensitivity_angle_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                bottom_tv_sensitivity_angle.setText(setText(MsgProgress));
                if (mode == LEGS_MODLE) {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.ANGLE_RIGHT, MsgProgress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_ANGLE_RIGHT, MsgProgress + ""));
                }
            }
        }
    }


    @Override
    public boolean isInterceptSend(Intent intent) {
        return super.isInterceptSend(intent);

    }

    boolean tab_velocity_sensitivity;
    boolean tab_sensitivity_angle;


    @OnClick({R.id.tv_sensitivity_angle_lr_def_name,R.id.tv_sensitivity_angle_left_def_name,R.id.tv_sensitivity_angle_right_def_name,R.id.tv_angular_velocity_sensitivity_lr_def_name,R.id.tv_angular_velocity_sensitivity_left_def_name,R.id.tv_angular_velocity_sensitivity_right_def_name,R.id.btn_angular_velocity_sensitivity,R.id.btn_angular_velocity_sensitivity_lr_control,R.id.lin_tab_velocity_sensitivity,R.id.lin_tab_sensitivity_angle,R.id.btn_sensitivity_angle_lr_control,R.id.top_tv_angular_velocity_sensitivity,R.id.center_tv_angular_velocity_sensitivity,R.id.bottom_tv_angular_velocity_sensitivity,R.id.top_tv_sensitivity_angle,R.id.center_tv_sensitivity_angle,R.id.bottom_tv_sensitivity_angle})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case  R.id.tv_sensitivity_angle_lr_def_name:

                initializationSenVelocitySensitivityAngular(1,0,true,15);

                break;
            case  R.id.tv_sensitivity_angle_left_def_name:

                initializationSenVelocitySensitivityAngular(1,1,true,15);

                break;
            case  R.id.tv_sensitivity_angle_right_def_name:

                initializationSenVelocitySensitivityAngular(1,2,true,15);

                break;
            case  R.id.tv_angular_velocity_sensitivity_lr_def_name:

                initializationSenVelocitySensitivity(1,0,true,10);

                break;
            case  R.id.tv_angular_velocity_sensitivity_left_def_name:

                initializationSenVelocitySensitivity(1,1,true,10);

                break;
            case  R.id.tv_angular_velocity_sensitivity_right_def_name:

                initializationSenVelocitySensitivity(1,2,true,10);

                break;



            case R.id.lin_tab_sensitivity_angle:

                if (!tab_sensitivity_angle){

                    btn_angle.setImageResource(R.mipmap.check2);
                    lin_gray_sensitivity.setVisibility(View.VISIBLE);
                    tab_sensitivity_angle=!tab_sensitivity_angle;
                }else {
                    btn_angle.setImageResource(R.mipmap.check1);
                    lin_gray_sensitivity.setVisibility(View.GONE);
                    tab_sensitivity_angle=!tab_sensitivity_angle;
                }

                break;
            case R.id.lin_tab_velocity_sensitivity:

                if (!tab_velocity_sensitivity){
                    btn_speed.setImageResource(R.mipmap.check2);
                    lin_velocity_sensitivity.setVisibility(View.VISIBLE);
                    tab_velocity_sensitivity=!tab_velocity_sensitivity;
                }else {
                    btn_speed.setImageResource(R.mipmap.check1);
                    lin_velocity_sensitivity.setVisibility(View.GONE);
                    tab_velocity_sensitivity=!tab_velocity_sensitivity;
                }

                break;

            case R.id.btn_angular_velocity_sensitivity://灵敏度角速度展开收缩按钮


                if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {

                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.connect_device_please), Toast.LENGTH_SHORT).show();

                } else {

                    //如果收缩
                    if (btn_angular_velocity_sensitivity.isSelected()) {
                        btn_angular_velocity_sensitivity.setSelected(false);
                        lin_tab_velocity_sensitivity.setVisibility(View.GONE);
                        ll_angular_velocity_sensitivity_items.setVisibility(View.GONE);
                        lin_tab_sensitivity_angle.setVisibility(View.GONE);
                        ll_sensitivity_angle_items.setVisibility(View.GONE);

                    }
                    //如果是展开
                    else {
                        /**
                         *
                         *     A 版本注释
                         *
                         * */

//                    if (mode == STANDARD_MODLE) {
//
////                        queryState(DeviceQueryEntity.SPEED_ANGLE_LEFT, DeviceQueryEntity.SPEED_ANGLE_RIGHT);
//                    } else {
////                        queryState(DeviceQueryEntity.TRIGGER_SPEED_ANGLE_LEFT, DeviceQueryEntity.TRIGGER_SPEED_ANGLE_RIGHT);
//                    }
                        btn_angular_velocity_sensitivity.setSelected(true);
                        lin_tab_velocity_sensitivity.setVisibility(View.VISIBLE);
                        ll_angular_velocity_sensitivity_items.setVisibility(View.VISIBLE);
                        lin_tab_sensitivity_angle.setVisibility(View.VISIBLE);
                        ll_sensitivity_angle_items.setVisibility(View.VISIBLE);
                        lin_tab_velocity_sensitivity.postInvalidate();

                    }
                }

                break;
            case R.id.btn_angular_velocity_sensitivity_lr_control://灵敏度角速度左右群控选择按钮
                //如果左右群控选中
                if (angular_velocity_sensitivity_lr_control_state == TRUE) {
                    angular_velocity_sensitivity_lr_control_state = FALSE;
                } else {
                    angular_velocity_sensitivity_lr_control_state = TRUE;
                }
                //设置进度条颜色和一些相关文字的颜色
                leftAndRightControlSelectStateNewWz(btn_angular_velocity_sensitivity_lr_control, tv_angular_velocity_sensitivity_lr_name, tv_angular_velocity_sensitivity_lr_def_name, tv_angular_velocity_sensitivity_lr_def_value, ssb_angular_velocity_sensitivity_lr_control,
                        tv_angular_velocity_sensitivity_left_name, tv_angular_velocity_sensitivity_left_def_name, tv_angular_velocity_sensitivity_left_def_value, ssb_angular_velocity_sensitivity_left_control,
                        tv_angular_velocity_sensitivity_right_name, tv_angular_velocity_sensitivity_right_def_name, tv_angular_velocity_sensitivity_right_def_value, ssb_angular_velocity_sensitivity_right_control, angular_velocity_sensitivity_lr_control_state
                ,top_tv_angular_velocity_sensitivity,center_tv_angular_velocity_sensitivity,bottom_tv_angular_velocity_sensitivity
                );
                break;


            case R.id.btn_sensitivity_angle_lr_control://灵敏度角度左右群控选择按钮
                //如果左右群控选中
                if (sensitivity_angle_lr_control_state == TRUE) {
                    sensitivity_angle_lr_control_state = FALSE;
                } else {
                    sensitivity_angle_lr_control_state = TRUE;
                }
                //设置进度条颜色和一些相关文字的颜色
                leftAndRightControlSelectStateNewWz(btn_sensitivity_angle_lr_control, tv_sensitivity_angle_lr_name, tv_sensitivity_angle_lr_def_name, tv_sensitivity_angle_lr_def_value, ssb_sensitivity_angle_lr_control,
                        tv_sensitivity_angle_left_name, tv_sensitivity_angle_left_def_name, tv_sensitivity_angle_left_def_value, ssb_sensitivity_angle_left_control,
                        tv_sensitivity_angle_right_name, tv_sensitivity_angle_right_def_name, tv_sensitivity_angle_right_def_value, ssb_sensitivity_angle_right_control, sensitivity_angle_lr_control_state
                 ,top_tv_sensitivity_angle,center_tv_sensitivity_angle,bottom_tv_sensitivity_angle
                );

                break;

            case R.id. top_tv_angular_velocity_sensitivity:

                showDiaglogType = 0;

                if (angular_velocity_sensitivity_lr_control_state==1){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }


                break;
            case R.id. center_tv_angular_velocity_sensitivity:


                showDiaglogType = 1;

                if (angular_velocity_sensitivity_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }




                break;
            case R.id. bottom_tv_angular_velocity_sensitivity:


                showDiaglogType = 2;

                if (angular_velocity_sensitivity_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }

                break;
            case R.id. top_tv_sensitivity_angle:

                showDiaglogType = 3;

                if (sensitivity_angle_lr_control_state==1){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }


                break;
            case R.id. center_tv_sensitivity_angle:


                showDiaglogType = 4;

                if (sensitivity_angle_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }




                break;
            case R.id. bottom_tv_sensitivity_angle:


                showDiaglogType = 5;

                if (sensitivity_angle_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }

                break;







        }


    }

    private void initSeekBar(){

        ssb_angular_velocity_sensitivity_lr_control.setOnProgressChangedListener(this);
        ssb_angular_velocity_sensitivity_left_control.setOnProgressChangedListener(this);
        ssb_angular_velocity_sensitivity_right_control.setOnProgressChangedListener(this);

        ssb_sensitivity_angle_lr_control.setOnProgressChangedListener(this);
        ssb_sensitivity_angle_left_control.setOnProgressChangedListener(this);
        ssb_sensitivity_angle_right_control.setOnProgressChangedListener(this);

        ssb_angular_velocity_sensitivity_lr_control.setProgress(10);
        ssb_angular_velocity_sensitivity_left_control.setProgress(10);
        ssb_angular_velocity_sensitivity_right_control.setProgress(10);

        ssb_sensitivity_angle_lr_control.setProgress(15);
        ssb_sensitivity_angle_left_control.setProgress(15);
        ssb_sensitivity_angle_right_control.setProgress(15);

        tv_sensitivity_angle_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"15°");
        tv_sensitivity_angle_left_def_name .setText(getActivity().getResources().getString(R.string.default_text)+"15°");
        tv_sensitivity_angle_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"15°");

        tv_angular_velocity_sensitivity_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"10°/s");
        tv_angular_velocity_sensitivity_left_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"10°/s");
        tv_angular_velocity_sensitivity_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"10°/s");


        top_tv_sensitivity_angle.setText(getActivity().getResources().getString(R.string.current)+"15"+" "+ getActivity().getResources().getString(R.string.Manual_input));
        center_tv_sensitivity_angle .setText(getActivity().getResources().getString(R.string.current)+"15"+" "+ getActivity().getResources().getString(R.string.Manual_input));
        bottom_tv_sensitivity_angle.setText(getActivity().getResources().getString(R.string.current)+"15"+" "+ getActivity().getResources().getString(R.string.Manual_input));

        top_tv_angular_velocity_sensitivity.setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));
        center_tv_angular_velocity_sensitivity.setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));
        bottom_tv_angular_velocity_sensitivity.setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));



    }

    @Override
    public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
//        LogUtils.d("请求", (signSeekBar == ssb_power_gear_left_control) + " " + progress);

        switch (signSeekBar.getId()) {

            case R.id.ssb_angular_velocity_sensitivity_lr_control://群控__灵敏度角度速度


                top_tv_angular_velocity_sensitivity.setText(setText(progress));
                center_tv_angular_velocity_sensitivity.setText(setText(progress));
                bottom_tv_angular_velocity_sensitivity .setText(setText(progress));

                break;
            case R.id.ssb_angular_velocity_sensitivity_left_control://左单控__灵敏度角度速度
                center_tv_angular_velocity_sensitivity.setText(setText(progress));
                break;
            case R.id.ssb_angular_velocity_sensitivity_right_control://右单控__灵敏度角度速度
                bottom_tv_angular_velocity_sensitivity .setText(setText(progress));
                break;

            case R.id.ssb_sensitivity_angle_lr_control://群控__灵敏度角度

                top_tv_sensitivity_angle.setText(setText(progress));
                center_tv_sensitivity_angle.setText(setText(progress));
                bottom_tv_sensitivity_angle.setText(setText(progress));

                break;
            case R.id.ssb_sensitivity_angle_left_control://左单控__灵敏度角度

                center_tv_sensitivity_angle.setText(setText(progress));
                break;

            case R.id.ssb_sensitivity_angle_right_control://右单控__灵敏度角度

                bottom_tv_sensitivity_angle.setText(setText(progress));
                break;

        }

    }

    @Override
    public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {

        switch (signSeekBar.getId()) {

            case R.id.ssb_angular_velocity_sensitivity_lr_control://群控__灵敏度角度速度

                initializationSenVelocitySensitivity(2,3,true,0);

                ssb_angular_velocity_sensitivity_left_control.setProgress(progress);
                ssb_angular_velocity_sensitivity_right_control.setProgress(progress);

                top_tv_angular_velocity_sensitivity.setText(setText(progress));
                center_tv_angular_velocity_sensitivity.setText(setText(progress));
                bottom_tv_angular_velocity_sensitivity .setText(setText(progress));

                if (mode == LEGS_MODLE) { /**
                 双腿模式
                 */
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.SPEED_SAME, progress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_SPEED_SAME, progress + ""));
                }
                break;
            case R.id.ssb_angular_velocity_sensitivity_left_control://左单控__灵敏度角度速度
                center_tv_angular_velocity_sensitivity.setText(setText(progress));
                initializationSenVelocitySensitivity(2,4,true,0);
                if (mode == LEGS_MODLE) {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.SPEED_LEFT, progress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_SPEED_LEFT, progress + ""));
                }
                break;
            case R.id.ssb_angular_velocity_sensitivity_right_control://右单控__灵敏度角度速度
                bottom_tv_angular_velocity_sensitivity .setText(setText(progress));
                initializationSenVelocitySensitivity(2,5,true,0);
                if (mode == LEGS_MODLE) {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.SPEED_RIGHT, progress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_SPEED_RIGHT, progress + ""));
                }
                break;

            case R.id.ssb_sensitivity_angle_lr_control://群控__灵敏度角度


                if (mode == LEGS_MODLE) {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.ANGLE_SAME, progress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_ANGLE_SAME, progress + ""));
                }

                initializationSenVelocitySensitivityAngular(2,3,true,0);

                ssb_sensitivity_angle_left_control.setProgress(progress);
                ssb_sensitivity_angle_right_control.setProgress(progress);

                top_tv_sensitivity_angle.setText(setText(progress));
                center_tv_sensitivity_angle.setText(setText(progress));
                bottom_tv_sensitivity_angle.setText(setText(progress));

                break;
            case R.id.ssb_sensitivity_angle_left_control://左单控__灵敏度角度

                initializationSenVelocitySensitivityAngular(2,4,true,0);
                center_tv_sensitivity_angle.setText(setText(progress));
                if (mode == LEGS_MODLE) {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.ANGLE_LEFT, progress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_ANGLE_LEFT, progress + ""));
                }
                break;

            case R.id.ssb_sensitivity_angle_right_control://右单控__灵敏度角度

                initializationSenVelocitySensitivityAngular(2,5,true,0);
                bottom_tv_sensitivity_angle.setText(setText(progress));
                if (mode == LEGS_MODLE) {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.ANGLE_RIGHT, progress + ""));
                } else {
                    sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_ANGLE_RIGHT, progress + ""));
                }
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
                  tv_angular_velocity_sensitivity_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "10°/s");
                  tv_angular_velocity_sensitivity_left_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "10°/s");
                  tv_angular_velocity_sensitivity_right_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "10°/s");

                  ssb_angular_velocity_sensitivity_lr_control.setProgress(progress);
                  ssb_angular_velocity_sensitivity_left_control.setProgress(progress);
                  ssb_angular_velocity_sensitivity_right_control.setProgress(progress);
                  top_tv_angular_velocity_sensitivity.setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                  center_tv_angular_velocity_sensitivity.setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                  bottom_tv_angular_velocity_sensitivity.setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));


                  if (vis){

                      if (mode == LEGS_MODLE) { /**
                       双腿模式
                       */
                          sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.SPEED_SAME, progress + ""));
                      } else {
                          sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_SPEED_SAME, progress + ""));
                      }
                  }

              }else  if (Number == 1){
                  center_tv_angular_velocity_sensitivity.setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                  tv_angular_velocity_sensitivity_left_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "10°/s");
                  ssb_angular_velocity_sensitivity_left_control.setProgress(progress);
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
                  bottom_tv_angular_velocity_sensitivity .setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                  ssb_angular_velocity_sensitivity_right_control.setProgress(progress);
                  tv_angular_velocity_sensitivity_right_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "10°/s");
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
                   tv_angular_velocity_sensitivity_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                   tv_angular_velocity_sensitivity_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                   tv_angular_velocity_sensitivity_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
               }else if (Number==4){

                   tv_angular_velocity_sensitivity_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
               }else if (Number==5){

                   tv_angular_velocity_sensitivity_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));

               }
          }

      }


    // Number 是点击的那一个  Type 类别  vis 是否显示发送命令    progress 当前进度
    private  void   initializationSenVelocitySensitivityAngular(int Type,int Number,boolean vis,int progress) {


//          ssb_sensitivity_angle_lr_control.setProgress(15);
//          ssb_sensitivity_angle_left_control.setProgress(15);
//          ssb_sensitivity_angle_right_control.setProgress(15);
//
//          tv_sensitivity_angle_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"15°");
//          tv_sensitivity_angle_left_def_name .setText(getActivity().getResources().getString(R.string.default_text)+"15°");
//          tv_sensitivity_angle_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"15°");
//




        if (Type == 1) {
            if (Number == 0) {
                tv_sensitivity_angle_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "15°");
                tv_sensitivity_angle_left_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "15°");
                tv_sensitivity_angle_right_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "15°");

                ssb_sensitivity_angle_lr_control.setProgress(progress);
                ssb_sensitivity_angle_left_control.setProgress(progress);
                ssb_sensitivity_angle_right_control.setProgress(progress);


                top_tv_sensitivity_angle.setText(getActivity().getResources().getString(R.string.current)+"15"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                center_tv_sensitivity_angle .setText(getActivity().getResources().getString(R.string.current)+"15"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                bottom_tv_sensitivity_angle.setText(getActivity().getResources().getString(R.string.current)+"15"+" "+ getActivity().getResources().getString(R.string.Manual_input));


                if (vis){

                    if (mode == LEGS_MODLE) {
                        sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.ANGLE_SAME, progress + ""));
                    } else {
                        sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_ANGLE_SAME, progress + ""));
                    }
                }

            }else  if (Number == 1){

                tv_sensitivity_angle_left_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "15°");
                ssb_sensitivity_angle_left_control.setProgress(progress);
                center_tv_sensitivity_angle .setText(getActivity().getResources().getString(R.string.current)+"15"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                if (vis){

                    if (mode == LEGS_MODLE) {
                        sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.ANGLE_LEFT, progress + ""));
                    } else {
                        sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_ANGLE_LEFT, progress + ""));
                    }
                }

            }else if (Number == 2){
                ssb_sensitivity_angle_right_control.setProgress(progress);
                bottom_tv_sensitivity_angle .setText(getActivity().getResources().getString(R.string.current)+"15"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                tv_sensitivity_angle_right_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "15°");
                if (vis){

                    if (mode == LEGS_MODLE) {
                        sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.ANGLE_RIGHT, progress + ""));
                    } else {
                        sendInstructions(DeviceInstructionsEntity.getStringToByte(DeviceInstructionsEntity.TRIGGER_ANGLE_RIGHT, progress + ""));
                    }
                }


            }
        }else {
            if (Number==3) {

                tv_sensitivity_angle_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_sensitivity_angle_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_sensitivity_angle_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
            }else if (Number==4){

                tv_sensitivity_angle_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
            }else if (Number==5){

                tv_sensitivity_angle_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));

            }
        }

    }

}
