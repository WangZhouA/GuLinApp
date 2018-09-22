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
import saiyi.com.gulin_new_wz.view.MyDialogForText;
import saiyi.com.gulin_new_wz.view.SignSeekBar;

import static saiyi.com.gulin_new_wz.utils.Constants.TRUE;
import static saiyi.com.gulin_new_wz.utils.Constants.VALUE;

/**
 * Created by 陈姣姣 on 2018/8/29.
 */
public class BrakingTimeViewControl extends AbsBleViewControl implements View.OnClickListener, SignSeekBar.OnProgressChangedListener{


    @BindView(R.id.iv_braking_time)
    ImageView iv_braking_time;
    @BindView(R.id.btn_braking_time)
    LinearLayout btn_braking_time;
    @BindView(R.id.btn_braking_time_lr_control)
    ImageView btn_braking_time_lr_control;
    @BindView(R.id.tv_braking_time_lr_name)
    TextView tv_braking_time_lr_name;
    @BindView(R.id.tv_braking_time_lr_def_name)
    TextView tv_braking_time_lr_def_name;
    @BindView(R.id.tv_braking_time_lr_def_value)
    TextView tv_braking_time_lr_def_value;
    @BindView(R.id.ssb_braking_time_lr_control)
    SignSeekBar ssb_braking_time_lr_control;
    @BindView(R.id.tv_braking_time_left_name)
    TextView tv_braking_time_left_name;
    @BindView(R.id.tv_braking_time_left_def_name)
    TextView tv_braking_time_left_def_name;
    @BindView(R.id.tv_braking_time_left_def_value)
    TextView tv_braking_time_left_def_value;
    @BindView(R.id.ssb_braking_time_left_control)
    SignSeekBar ssb_braking_time_left_control;
    @BindView(R.id.tv_braking_time_right_name)
    TextView tv_braking_time_right_name;
    @BindView(R.id.tv_braking_time_right_def_name)
    TextView tv_braking_time_right_def_name;
    @BindView(R.id.tv_braking_time_right_def_value)
    TextView tv_braking_time_right_def_value;
    @BindView(R.id.ssb_braking_time_right_control)
    SignSeekBar ssb_braking_time_right_control;
    @BindView(R.id.ll_braking_time_items)
    LinearLayout ll_braking_time_items;
    @BindView(R.id.tv_top_editext_progerss)
    TextView tv_top_editext_progerss;
    @BindView(R.id.tv_center_editext_progerss)
    TextView tv_center_editext_progerss;
    @BindView(R.id.tv_bottom_editext_progerss)
    TextView tv_bottom_editext_progerss;


    MyDialogForText myDialogForText;


    /**
     * 刹车时间左右群控状态,1左右群控，2单控
     */
    private int braking_time_lr_control_state = TRUE;

    /**
     *  判断是用于那一个按钮的输入框进入的
     *
     *
     * */

    private int showDiaglogType = 0;


    public BrakingTimeViewControl(AppCompatActivity appCompatActivity, int viewId) {
        super(appCompatActivity, viewId);

        myDialogForText =new MyDialogForText(getActivity());
        setLinstener();
        leftAndRightControlSelectStateNewWz(btn_braking_time_lr_control, tv_braking_time_lr_name, tv_braking_time_lr_def_name, tv_braking_time_lr_def_value, ssb_braking_time_lr_control,
                tv_braking_time_left_name, tv_braking_time_left_def_name, tv_braking_time_left_def_value, ssb_braking_time_left_control,
                tv_braking_time_right_name, tv_braking_time_right_def_name, tv_braking_time_right_def_value, ssb_braking_time_right_control, braking_time_lr_control_state,tv_top_editext_progerss,tv_center_editext_progerss,tv_bottom_editext_progerss);


    }

    @Override
    public boolean registReciver(LocalBroadcastManager localBroadcastManager, BroadcastReceiver receiver) {

        IntentFilter intentFilter =new IntentFilter(VALUE);
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
        if (action.contains(VALUE)){
            /**
             *  获取用户输入的值，现在要刷新UI了
             * */
            MsgProgress =  Integer.parseInt(intent.getStringExtra(VALUE)) ;

            if (showDiaglogType==0){

                tv_braking_time_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));


                tv_top_editext_progerss.setText(setText(MsgProgress));
                tv_center_editext_progerss.setText(setText(MsgProgress));
                tv_bottom_editext_progerss.setText(setText(MsgProgress));

                ssb_braking_time_lr_control.setProgress(MsgProgress);
                ssb_braking_time_left_control.setProgress(MsgProgress);
                ssb_braking_time_right_control.setProgress(MsgProgress);

                sendValueInstructions(DeviceInstructionsEntity.BRAKE_DURATION_SAME, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);


            }else if (showDiaglogType==1){
                tv_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_center_editext_progerss.setText(setText(MsgProgress));
                ssb_braking_time_left_control.setProgress(MsgProgress);
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_DURATION_LEFT, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);

            }else {
                tv_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_bottom_editext_progerss.setText(setText(MsgProgress));
                ssb_braking_time_right_control.setProgress(MsgProgress);
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_DURATION_RIGHT, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);
            }

        }else if (action.contains(BleConstant.SUCCED)){

            iv_braking_time.setImageResource(R.mipmap.braking_time);
        }else if (action.contains(BleConstant.DUANKAI)) {

            iv_braking_time.setImageResource(R.mipmap.braking_time_d);

        }

    }





    @OnClick({R.id.tv_braking_time_lr_def_name,R.id.tv_braking_time_left_def_name,R.id.tv_braking_time_right_def_name,R.id.btn_braking_time,R.id.btn_braking_time_lr_control,R.id.tv_top_editext_progerss,R.id.tv_center_editext_progerss,R.id.tv_bottom_editext_progerss})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.tv_braking_time_lr_def_name:
                tv_braking_time_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"40ms");
                tv_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"40ms");
                tv_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"40ms");
                tv_top_editext_progerss.setText(setText(40));
                tv_center_editext_progerss.setText(setText(40));
                tv_bottom_editext_progerss.setText(setText(40));
                ssb_braking_time_lr_control.setProgress(40);
                ssb_braking_time_left_control.setProgress(40);
                ssb_braking_time_right_control.setProgress(40);
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_DURATION_SAME, 40, DeviceInstructionsEntity.MAX_BRAKE_DURATION);
                break;
            case R.id.tv_braking_time_left_def_name:
                tv_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"40ms");
                ssb_braking_time_left_control.setProgress(40);
                tv_center_editext_progerss.setText(setText(40));
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_DURATION_LEFT, 40, DeviceInstructionsEntity.MAX_BRAKE_DURATION);

                break;
            case R.id.tv_braking_time_right_def_name:

                tv_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"40ms");
                tv_bottom_editext_progerss.setText(setText(40));
                ssb_braking_time_right_control.setProgress(40);
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_DURATION_RIGHT, 40, DeviceInstructionsEntity.MAX_BRAKE_DURATION);

                break;









            case R.id.btn_braking_time://刹车时间展开收缩按钮


                if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {

                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.connect_device_please), Toast.LENGTH_SHORT).show();

                } else {

                    //如果是展示状态
                    if (btn_braking_time.isSelected()) {
                        btn_braking_time.setSelected(false);
                        ll_braking_time_items.setVisibility(View.GONE);
                    }
                    //如果是收缩状态
                    else {
                        /**
                         *
                         *     A 版本注释
                         *
                         * */

//                    queryState(DeviceQueryEntity.BRAKE_DURATION_LEFT, DeviceQueryEntity.BRAKE_DURATION_RIGHT);
                        btn_braking_time.setSelected(true);
                        ll_braking_time_items.setVisibility(View.VISIBLE);
                    }
                }
                break;

            case R.id.btn_braking_time_lr_control://刹车时间左右群控选择按钮
                //如果左右群控选中
                if (braking_time_lr_control_state == 1) {
                    braking_time_lr_control_state = 2;
                } else {
                    braking_time_lr_control_state = 1;
                }
                //设置进度条颜色和一些相关文字的颜色
                leftAndRightControlSelectStateNewWz(btn_braking_time_lr_control, tv_braking_time_lr_name, tv_braking_time_lr_def_name, tv_braking_time_lr_def_value, ssb_braking_time_lr_control,
                        tv_braking_time_left_name, tv_braking_time_left_def_name, tv_braking_time_left_def_value, ssb_braking_time_left_control,
                        tv_braking_time_right_name, tv_braking_time_right_def_name, tv_braking_time_right_def_value, ssb_braking_time_right_control, braking_time_lr_control_state,tv_top_editext_progerss,tv_center_editext_progerss,tv_bottom_editext_progerss);
                break;


            case R.id. tv_top_editext_progerss:

                showDiaglogType = 0;

                if (braking_time_lr_control_state==1){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }


                break;
            case R.id. tv_center_editext_progerss:


                showDiaglogType = 1;

                if (braking_time_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }




                break;
            case R.id. tv_bottom_editext_progerss:


                showDiaglogType = 2;

                if (braking_time_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }

                break;


        }
    }


    private  void    setLinstener(){

        ssb_braking_time_lr_control.setOnProgressChangedListener(this);
        ssb_braking_time_left_control.setOnProgressChangedListener(this);
        ssb_braking_time_right_control.setOnProgressChangedListener(this);

        ssb_braking_time_lr_control.setProgress(40);
        ssb_braking_time_left_control.setProgress(40);
        ssb_braking_time_right_control.setProgress(40);

        tv_top_editext_progerss.setText(setText(40));
        tv_center_editext_progerss.setText(setText(40));
        tv_bottom_editext_progerss.setText(setText(40));

        tv_braking_time_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"40ms");
        tv_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"40ms");
        tv_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"40ms");


    }

    @Override
    public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {


        switch (signSeekBar.getId()){

            case  R.id.ssb_braking_time_lr_control:

                tv_top_editext_progerss.setText(setText(progress));
                tv_center_editext_progerss.setText(setText(progress));
                tv_bottom_editext_progerss.setText(setText(progress));

                break;
            case  R.id.ssb_braking_time_left_control:
                tv_center_editext_progerss.setText(setText(progress));

                break;
            case  R.id.ssb_braking_time_right_control:
                tv_bottom_editext_progerss.setText(setText(progress));

                break;

        }

    }

    @Override
    public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {
        switch (signSeekBar.getId()) {
            case R.id.ssb_braking_time_lr_control://群控__刹车时间


                tv_braking_time_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));

                ssb_braking_time_left_control.setProgress(progress);
                ssb_braking_time_right_control.setProgress(progress);


                tv_top_editext_progerss.setText(getActivity().getResources().getString(R.string.current)+progress+" "+getActivity().getResources().getString(R.string.Manual_input));
                tv_center_editext_progerss.setText(getActivity().getResources().getString(R.string.current)+progress+" "+getActivity().getResources().getString(R.string.Manual_input));
                tv_bottom_editext_progerss.setText(getActivity().getResources().getString(R.string.current)+progress+" "+getActivity().getResources().getString(R.string.Manual_input));

                sendValueInstructions(DeviceInstructionsEntity.BRAKE_DURATION_SAME, progress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);
                break;
            case R.id.ssb_braking_time_left_control://左单控__刹车时间
                tv_braking_time_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_center_editext_progerss.setText(getActivity().getResources().getString(R.string.current)+progress+" "+getActivity().getResources().getString(R.string.Manual_input));
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_DURATION_LEFT, progress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);
                break;
            case R.id.ssb_braking_time_right_control://右单控__刹车时间
                tv_braking_time_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_bottom_editext_progerss.setText(getActivity().getResources().getString(R.string.current)+progress+" "+getActivity().getResources().getString(R.string.Manual_input));
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_DURATION_RIGHT, progress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);
                break;


        }
    }

    @Override
    public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {

    }


}
