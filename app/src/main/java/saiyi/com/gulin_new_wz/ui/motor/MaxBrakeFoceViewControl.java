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
import saiyi.com.gulin_new_wz.view.SignSeekBar;

import static saiyi.com.gulin_new_wz.utils.Constants.FALSE;
import static saiyi.com.gulin_new_wz.utils.Constants.LEGS_MODLE;
import static saiyi.com.gulin_new_wz.utils.Constants.MODE;
import static saiyi.com.gulin_new_wz.utils.Constants.TRUE;
import static saiyi.com.gulin_new_wz.utils.Constants.VALUE;

/**
 * Created by 陈姣姣 on 2018/9/1.
 */
public class MaxBrakeFoceViewControl extends AbsBleViewControl implements View.OnClickListener, SignSeekBar.OnProgressChangedListener{

    @BindView(R.id.iv_max_braking_time)
    ImageView iv_max_braking_time;
    @BindView(R.id.btn_max_braking_time)
    LinearLayout btn_max_braking_time;
    @BindView(R.id.btn_max_braking_time_lr_control)
    ImageView btn_max_braking_time_lr_control;
    @BindView(R.id.tv_max_braking_time_lr_name)
    TextView tv_max_braking_time_lr_name;
    @BindView(R.id.tv_max_braking_time_lr_def_name)
    TextView tv_max_braking_time_lr_def_name;
    @BindView(R.id.tv_max_braking_time_lr_def_value)
    TextView tv_max_braking_time_lr_def_value;
    @BindView(R.id.ssb_max_braking_time_lr_control)
    SignSeekBar ssb_max_braking_time_lr_control;
    @BindView(R.id.top_max_braking_time)
    TextView top_max_braking_time;
    @BindView(R.id.tv_max_braking_time_left_name)
    TextView tv_max_braking_time_left_name;
    @BindView(R.id.tv_max_braking_time_left_def_name)
    TextView tv_max_braking_time_left_def_name;
    @BindView(R.id.tv_max_braking_time_left_def_value)
    TextView tv_max_braking_time_left_def_value;
    @BindView(R.id.ssb_max_braking_time_left_control)
    SignSeekBar ssb_max_braking_time_left_control;
    @BindView(R.id.center_max_braking_time)
    TextView center_max_braking_time;
    @BindView(R.id.tv_max_braking_time_right_name)
    TextView tv_max_braking_time_right_name;
    @BindView(R.id.tv_max_braking_time_right_def_name)
    TextView tv_max_braking_time_right_def_name;
    @BindView(R.id.tv_max_braking_time_right_def_value)
    TextView tv_max_braking_time_right_def_value;
    @BindView(R.id.ssb_max_braking_time_right_control)
    SignSeekBar ssb_max_braking_time_right_control;
    @BindView(R.id.bottom_max_braking_time)
    TextView bottom_max_braking_time;
    @BindView(R.id.ll_max_braking_time_items)
    LinearLayout ll_max_braking_time_items;
    @BindView(R.id.ll_max_braking_time)
    LinearLayout ll_max_braking_time;


    /**
     * 初始刹车力左右群控状态，1左右群控，2单控
     */
    private int max_brake_force_lr_control_state = TRUE;


    public MaxBrakeFoceViewControl(AppCompatActivity appCompatActivity, int viewId) {
        super(appCompatActivity, viewId);
        setLinstener();
        leftAndRightControlSelectStateNewWz(btn_max_braking_time_lr_control, tv_max_braking_time_lr_name, tv_max_braking_time_lr_def_name, tv_max_braking_time_lr_def_value, ssb_max_braking_time_lr_control,
                tv_max_braking_time_left_name, tv_max_braking_time_left_def_name, tv_max_braking_time_left_def_value, ssb_max_braking_time_left_control,
                tv_max_braking_time_right_name, tv_max_braking_time_right_def_name, tv_max_braking_time_right_def_value, ssb_max_braking_time_right_control, max_brake_force_lr_control_state,top_max_braking_time,center_max_braking_time,bottom_max_braking_time);


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

        }else if (action.contains(BleConstant.SUCCED)){

            iv_max_braking_time.setImageResource(R.mipmap.time_wz);
        }else if (action.contains(BleConstant.DUANKAI)){

            iv_max_braking_time.setImageResource(R.mipmap.time_wzo_wz);


        }else if (action.contains(VALUE)){

            /**
             *  获取用户输入的值，现在要刷新UI了
             * */
            MsgProgress =  Integer.parseInt(intent.getStringExtra(VALUE)) ;

            if (showDiaglogType==0){


                tv_max_braking_time_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_max_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_max_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));



                top_max_braking_time.setText(setText(MsgProgress));
                center_max_braking_time.setText(setText(MsgProgress));
                bottom_max_braking_time.setText(setText(MsgProgress));

                ssb_max_braking_time_lr_control.setProgress(MsgProgress);
                ssb_max_braking_time_left_control.setProgress(MsgProgress);
                ssb_max_braking_time_right_control.setProgress(MsgProgress);
                if (mode == LEGS_MODLE) { /**
                 双腿模式
                 */
                } else {

                }

            }else if (showDiaglogType==1){
                tv_max_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                center_max_braking_time.setText(setText(MsgProgress));
                ssb_max_braking_time_left_control.setProgress(MsgProgress);
                if (mode == LEGS_MODLE) {
                } else {
                }
            }else if (showDiaglogType==2){

                tv_max_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                bottom_max_braking_time.setText(setText(MsgProgress));
                ssb_max_braking_time_right_control.setProgress(MsgProgress);
                if (mode == LEGS_MODLE) {
                } else {
                }
            }
        }

    }

    @OnClick({R.id.tv_max_braking_time_lr_def_name,R.id.tv_max_braking_time_left_def_name ,R.id.tv_max_braking_time_right_def_name ,R.id.top_max_braking_time,R.id.center_max_braking_time,R.id.bottom_max_braking_time,R.id.btn_max_braking_time ,R.id.btn_max_braking_time_lr_control})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_max_braking_time_lr_def_name:

                initializationSenVelocitySensitivity(1,0,true,10);

                break;
            case R.id.tv_max_braking_time_left_def_name:
                initializationSenVelocitySensitivity(1,1,true,10);
                break;
            case R.id.tv_max_braking_time_right_def_name:

                initializationSenVelocitySensitivity(1,2,true,10);
                break;




            case R.id.btn_max_braking_time://初始刹车力展开收缩按钮

                if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {

                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.connect_device_please), Toast.LENGTH_SHORT).show();

                } else {

                    //如果是展开
                    if (btn_max_braking_time.isSelected()) {
                        btn_max_braking_time.setSelected(false);
                        ll_max_braking_time_items.setVisibility(View.GONE);
                    }
                    //如果是收缩
                    else {
                        /**
                         *
                         *     A 版本注释
                         *
                         * */
//                    queryState(DeviceQueryEntity.BRAKE_FORCE_LEFT, DeviceQueryEntity.BRAKE_FORCE_RIGHT);
                        btn_max_braking_time.setSelected(true);
                        ll_max_braking_time_items.setVisibility(View.VISIBLE);
                    }
                }
                break;

            case R.id.btn_max_braking_time_lr_control://初始刹车力左右群控选择按钮
                //如果左右群控选中
                if (max_brake_force_lr_control_state == TRUE) {
                    max_brake_force_lr_control_state = FALSE;
                } else {
                    max_brake_force_lr_control_state = TRUE;
                }
                //设置进度条颜色和一些相关文字的颜色
                leftAndRightControlSelectStateNewWz(btn_max_braking_time_lr_control, tv_max_braking_time_lr_name, tv_max_braking_time_lr_def_name, tv_max_braking_time_lr_def_value, ssb_max_braking_time_lr_control,
                        tv_max_braking_time_left_name, tv_max_braking_time_left_def_name, tv_max_braking_time_left_def_value, ssb_max_braking_time_left_control,
                        tv_max_braking_time_right_name, tv_max_braking_time_right_def_name, tv_max_braking_time_right_def_value, ssb_max_braking_time_right_control, max_brake_force_lr_control_state,top_max_braking_time,center_max_braking_time,bottom_max_braking_time);

                break;

            case R.id. top_max_braking_time:

                showDiaglogType = 0;

                if (max_brake_force_lr_control_state==1){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }


                break;
            case R.id. center_max_braking_time:


                showDiaglogType = 1;

                if (max_brake_force_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }




                break;
            case R.id. bottom_max_braking_time:


                showDiaglogType = 2;

                if (max_brake_force_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }

                break;



        }


    }


    // Number 是点击的那一个  Type 类别  vis 是否显示发送命令    progress 当前进度
    private  void   initializationSenVelocitySensitivity(int Type,int Number,boolean vis,int progress) {

        if (Type == 1) {
            if (Number == 0) {
                tv_max_braking_time_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "10°");
                tv_max_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "10°");
                tv_max_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "10°");

                ssb_max_braking_time_lr_control.setProgress(progress);
                ssb_max_braking_time_left_control.setProgress(progress);
                ssb_max_braking_time_right_control.setProgress(progress);
                top_max_braking_time.setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                center_max_braking_time.setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                bottom_max_braking_time.setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));


                if (vis){

                    if (mode == LEGS_MODLE) { /**
                     双腿模式
                     */
                    } else {
                    }
                }

            }else  if (Number == 1){
                center_max_braking_time.setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                tv_max_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "10°");
                ssb_max_braking_time_left_control.setProgress(progress);
                if (vis){

                    if (mode == LEGS_MODLE) { /**
                     双腿模式
                     */
                    } else {
                    }
                }

            }else if (Number == 2){
                bottom_max_braking_time .setText(getActivity().getResources().getString(R.string.current)+"10"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                ssb_max_braking_time_right_control.setProgress(progress);
                tv_max_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "10°");
                if (vis){

                    if (mode == LEGS_MODLE) { /**
                     双腿模式
                     */
                    } else {
                    }
                }


            }
        }else {
            if (Number==3) {


                tv_max_braking_time_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_max_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_max_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
            }else if (Number==4){

                tv_max_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
            }else if (Number==5){

                tv_max_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));

            }
        }

    }


    private  void  setLinstener(){

        ssb_max_braking_time_lr_control.setOnProgressChangedListener(this);
        ssb_max_braking_time_left_control.setOnProgressChangedListener(this);
        ssb_max_braking_time_right_control.setOnProgressChangedListener(this);

        ssb_max_braking_time_lr_control.setProgress(10);
        ssb_max_braking_time_left_control.setProgress(10);
        ssb_max_braking_time_right_control.setProgress(10);

        top_max_braking_time.setText(setText(10));
        center_max_braking_time.setText(setText(10));
        bottom_max_braking_time.setText(setText(10));

        tv_max_braking_time_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"10°");
        tv_max_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"10°");
        tv_max_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"10°");

    }






    @Override
    public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
        switch (signSeekBar.getId()){

            case R.id.ssb_max_braking_time_lr_control://刹车力_群控


                center_max_braking_time.setText(setText(progress));
                top_max_braking_time.setText(setText(progress));
                bottom_max_braking_time.setText(setText(progress));
/**
 *  发指令
 * */

                break;

            case R.id.ssb_max_braking_time_left_control ://左单控__刹车力
                center_max_braking_time.setText(setText(progress));

                /**
                 *  发指令
                 * */

                break;

            case R.id.ssb_max_braking_time_right_control ://右单控__刹车力

                bottom_max_braking_time.setText(setText(progress));
                break;


        }
    }

    @Override
    public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {
        switch (signSeekBar.getId()){

            case R.id.ssb_max_braking_time_lr_control://刹车力_群控


                initializationSenVelocitySensitivity(2,3,true,100);
                center_max_braking_time.setText(setText(progress));
                top_max_braking_time.setText(setText(progress));
                bottom_max_braking_time.setText(setText(progress));
                ssb_max_braking_time_left_control.setProgress(progress);
                ssb_max_braking_time_right_control.setProgress(progress);
/**
 *  发指令
 * */

                break;

            case R.id.ssb_max_braking_time_left_control ://左单控__刹车力
                initializationSenVelocitySensitivity(2,4,true,progress);
                center_max_braking_time.setText(setText(progress));
                ssb_max_braking_time_left_control.setProgress(progress);

                /**
                 *  发指令
                 * */

                break;

            case R.id.ssb_max_braking_time_right_control ://右单控__刹车力

                ssb_max_braking_time_right_control.setProgress(progress);
                bottom_max_braking_time.setText(setText(progress));
                initializationSenVelocitySensitivity(2,5,true,progress);



                break;


        }
    }

    @Override
    public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {

    }
}
