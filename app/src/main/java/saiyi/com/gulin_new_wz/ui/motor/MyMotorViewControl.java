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

import static saiyi.com.gulin_new_wz.utils.Constants.FALSE;
import static saiyi.com.gulin_new_wz.utils.Constants.TRUE;

/**
 * Created by 陈姣姣 on 2018/8/27.
 */
public class MyMotorViewControl extends AbsBleViewControl implements View.OnClickListener{

    /**
     * 马达展开项布局
     */
    @BindView(R.id.ll_motor_items)
    LinearLayout ll_motor_items;
    @BindView(R.id.iv_motor)
    ImageView iv_motor;
    @BindView(R.id.btn_motor)
    LinearLayout btn_motor;
    @BindView(R.id.tv_motor_left_control_name)
    TextView tvMotorLeftControlName;
    @BindView(R.id.btn_motor_left_control_switch)
    ImageView btn_motor_left_control_switch;
    @BindView(R.id.tv_motor_right_control_name)
    TextView tvMotorRightControlName;
    @BindView(R.id.btn_motor_right_control_switch)
    ImageView btn_motor_right_control_switch;
    @BindView(R.id.ll_motor)
    LinearLayout llMotor;
    @BindView(R.id.btn_motor_lr_control)
    ImageView btn_motor_lr_control;




    //马达状态
    /**
     * 马达左右群控状态，1左右群控，2左右单控
     */
    private int is_motor_lr_control = TRUE;
    /**
     * 马达左右群控开关状态,1开，2关
     */
//    private int motor_lr_control_switch_state = TRUE;
    /**
     * 马达左单控开关状态,1开，2关
     */
    private int motor_left_control_switch_state = TRUE;
    /**
     * 马达右单控开关状态,1开，2关
     */
    private int motor_right_control_switch_state = TRUE;


    public MyMotorViewControl(AppCompatActivity appCompatActivity, int viewId) {
        super(appCompatActivity, viewId);

        //设置马达左右群控或单控是的开关状态和文字颜色
        setMotorSwitchStateAndTextColor(is_motor_lr_control,btn_motor_lr_control,
                btn_motor_left_control_switch, btn_motor_right_control_switch);


    }



    @Override
    public boolean registReciver(LocalBroadcastManager localBroadcastManager, BroadcastReceiver receiver) {

//        IntentFilter intentFilter =new IntentFilter("AAA");
//        localBroadcastManager.registerReceiver();

        IntentFilter intentFilter =new IntentFilter(BleConstant.SUCCED);
        intentFilter.addAction(BleConstant.DUANKAI);
        localBroadcastManager.registerReceiver(receiver,intentFilter);




        return false;
    }

    @Override
    public void reciver(Intent intent) {
        super.reciver(intent);
        String action = intent.getAction();
        if (action.contains(BleConstant.SUCCED)){

            iv_motor.setImageResource(R.mipmap.motor);
        }else if (action.contains(BleConstant.DUANKAI)){

            iv_motor.setImageResource(R.mipmap.motor_pre);
        }


    }



    @Override
    public void onResume() {


    }


    @OnClick({R.id.btn_motor_lr_control,R.id.btn_motor,R.id.btn_motor_left_control_switch,R.id.btn_motor_right_control_switch})
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_motor_lr_control:

                if (is_motor_lr_control == TRUE) { //如果是左右群控,设置为单控
                    is_motor_lr_control = FALSE;
                } else {//如果是单控，设置为群控
                    is_motor_lr_control = TRUE;
                }
                if (is_motor_lr_control == FALSE) {
                    motor_left_control_switch_state = FALSE;
                    motor_right_control_switch_state = FALSE;
                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.MOTOR_SAME_CLOSE));
                } else {
                    motor_left_control_switch_state = TRUE;
                    motor_right_control_switch_state = TRUE;
                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.MOTOR_SAME_OPEN));
                }
                //设置马达左右群控或单控是的开关状态和文字颜色
                setMotorSwitchStateAndTextColor(is_motor_lr_control,btn_motor_lr_control,
                        btn_motor_left_control_switch, btn_motor_right_control_switch);


                break;
            case R.id.btn_motor://马达展开或收缩按钮


                if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {

                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.connect_device_please), Toast.LENGTH_SHORT).show();

                } else {
//
                    if (btn_motor.isSelected()) {
                        btn_motor.setSelected(false);
                        ll_motor_items.setVisibility(View.GONE);
                    } else {//如果是收缩状态
                        btn_motor.setSelected(true);
                        ll_motor_items.setVisibility(View.VISIBLE);
                    }

                }
                break;
            case R.id.btn_motor_left_control_switch://马达左单控开关
                //如果是开,则设置为关
                if (motor_left_control_switch_state == TRUE) {
                    motor_left_control_switch_state = FALSE;
                    is_motor_lr_control = FALSE;
                    btn_motor_lr_control.setSelected(false);
                    btn_motor_left_control_switch.setBackgroundResource(R.mipmap.off);
                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.MOTOR_LEFT_CLOSE));
                } else {//如果是关,则设置为开
                    motor_left_control_switch_state = TRUE;
                    btn_motor_left_control_switch.setBackgroundResource(R.mipmap.on);
                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.MOTOR_LEFT_OPEN));
                }
                break;

            case R.id.btn_motor_right_control_switch://马达右单控开关
                //如果是开,则设置为关
                if (motor_right_control_switch_state == TRUE) {
                    motor_right_control_switch_state = FALSE;
                    is_motor_lr_control = FALSE;
                    btn_motor_lr_control.setSelected(false);
                    btn_motor_right_control_switch.setBackgroundResource(R.mipmap.off);
                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.MOTOR_RIGHT_CLOSE));
                }
                //如果是关,则设置为开
                else {
                    motor_right_control_switch_state = TRUE;
                    btn_motor_right_control_switch.setBackgroundResource(R.mipmap.on);
                    sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.MOTOR_RIGHT_OPEN));
                }

                break;


        }
    }
}
