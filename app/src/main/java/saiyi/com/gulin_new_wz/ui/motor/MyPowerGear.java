package saiyi.com.gulin_new_wz.ui.motor;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import saiyi.com.gulin_new_wz.utils.MyDialog;
import saiyi.com.gulin_new_wz.view.SignSeekBar;

import static saiyi.com.gulin_new_wz.utils.Constants.FALSE;
import static saiyi.com.gulin_new_wz.utils.Constants.LEGS_MODLE;
import static saiyi.com.gulin_new_wz.utils.Constants.MODE;
import static saiyi.com.gulin_new_wz.utils.Constants.TRUE;
import static saiyi.com.gulin_new_wz.utils.Constants.VALUE;

/**
 * Created by 陈姣姣 on 2018/9/3.
 */
public class MyPowerGear extends AbsBleViewControl implements View.OnClickListener,SignSeekBar.OnProgressChangedListener{


    @BindView(R.id.iv_power_gear)
    ImageView iv_power_gear;
    @BindView(R.id.btn_power_gear)
    LinearLayout btn_power_gear;
    @BindView(R.id.btn_power_gear_lr_control)
    ImageView btn_power_gear_lr_control;
    @BindView(R.id.tv_power_gear_lr_name)
    TextView tv_power_gear_lr_name;
    @BindView(R.id.tv_power_gear_lr_def_name)
    TextView tv_power_gear_lr_def_name;
    @BindView(R.id.tv_power_gear_lr_def_value)
    TextView tv_power_gear_lr_def_value;
    @BindView(R.id.top_top_circle)
    TextView top_top_circle;

    @BindView(R.id.ssb_power_gear_lr_control)
    SignSeekBar ssb_power_gear_lr_control;
    @BindView(R.id.ssb_power_gear_left_control)
    SignSeekBar ssb_power_gear_left_control;
    @BindView(R.id.ssb_power_gear_right_control)
    SignSeekBar ssb_power_gear_right_control;

    //    @BindView(R.id.top_top_line)
//    TextView top_top_line;
    @BindView(R.id.top_center_circle)
    TextView top_center_circle;
    //    @BindView(R.id.top_center_line)
//    TextView top_center_line;
    @BindView(R.id.top_bottom_circle)
    TextView top_bottom_cicle;
    @BindView(R.id.top_tv_power_gear)
    TextView top_tv_power_gear;
    @BindView(R.id.tv_power_gear_left_name)
    TextView tv_power_gear_left_name;
    @BindView(R.id.tv_power_gear_left_def_name)
    TextView tv_power_gear_left_def_name;
    @BindView(R.id.tv_power_gear_left_def_value)
    TextView tv_power_gear_left_def_value;
    @BindView(R.id.center_top_circle)
    TextView center_top_circle;
    //    @BindView(R.id.center_top_line)
//    TextView center_top_line;
    @BindView(R.id.center_center_circle)
    TextView center_center_circle;
    //    @BindView(R.id.center_center_line)
//    TextView center_center_line;
    @BindView(R.id.center_bottom_circle)
    TextView center_bottom_cicle;
    @BindView(R.id.center_tv_power_gear)
    TextView center_tv_power_gear;
    @BindView(R.id.tv_power_gear_right_name)
    TextView tv_power_gear_right_name;
    @BindView(R.id.tv_power_gear_right_def_name)
    TextView tv_power_gear_right_def_name;
    @BindView(R.id.tv_power_gear_right_def_value)
    TextView tv_power_gear_right_def_value;
    @BindView(R.id.bottom_top_circle)
    TextView bottom_top_circle;
    //    @BindView(R.id.bottom_top_line)
//    TextView bottom_top_line;
    @BindView(R.id.bottom_center_circle)
    TextView bottom_center_circle;
    //    @BindView(R.id.bottom_center_line)
//    TextView bottom_center_line;
    @BindView(R.id.bottom_bottom_circle)
    TextView bottom_bottom_cicle;
    @BindView(R.id.bottom_power_gear)
    TextView bottom_power_gear;
    @BindView(R.id.ll_power_gear_items)
    LinearLayout ll_power_gear_items;


    /**
     * 力量档位左右群控状态，1左右群控，2单控
     */
    private int power_gear_lr_control_state = TRUE;





    public MyPowerGear(AppCompatActivity appCompatActivity, int viewId) {
        super(appCompatActivity, viewId);

        setLinsteners();
        //设置进度条颜色和一些相关文字的颜色
        leftAndRightControlSelectStateNewWzPowerGear(btn_power_gear_lr_control, tv_power_gear_lr_name, tv_power_gear_lr_def_name, tv_power_gear_lr_def_value, ssb_power_gear_lr_control,
                tv_power_gear_left_name, tv_power_gear_left_def_name, tv_power_gear_left_def_value, ssb_power_gear_left_control,
                tv_power_gear_right_name, tv_power_gear_right_def_name, tv_power_gear_right_def_value, ssb_power_gear_right_control, power_gear_lr_control_state,top_tv_power_gear,center_tv_power_gear,bottom_power_gear,top_top_circle,top_center_circle,top_bottom_cicle,center_top_circle,center_center_circle,center_bottom_cicle,bottom_top_circle,bottom_center_circle,bottom_bottom_cicle);




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
        if ( action.contains(MODE)){
            mode = intent.getIntExtra(MODE,0);

        }else if (action.contains(BleConstant.SUCCED)){

            iv_power_gear.setImageResource(R.mipmap.power_gear);
        }else if (action.contains(BleConstant.DUANKAI)){

            iv_power_gear.setImageResource(R.mipmap.power_gear_d);




        }else if (action.contains(VALUE)){

            /**
             *  获取用户输入的值，现在要刷新UI了
             * */
            MsgProgress =  Integer.parseInt(intent.getStringExtra(VALUE)) ;

            if (showDiaglogType==0){

                tv_power_gear_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));

                top_tv_power_gear.setText(setText(MsgProgress));
                center_tv_power_gear.setText(setText(MsgProgress));
                bottom_power_gear.setText(setText(MsgProgress));

                ssb_power_gear_lr_control.setProgress(MsgProgress);
                ssb_power_gear_left_control.setProgress(MsgProgress);
                ssb_power_gear_right_control.setProgress(MsgProgress);
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_SAME, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);


            }else if (showDiaglogType==1){
                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                center_tv_power_gear.setText(setText(MsgProgress));
                ssb_power_gear_left_control.setProgress(MsgProgress);

                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_LEFT, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);

            }else if (showDiaglogType==2){
                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                bottom_power_gear.setText(setText(MsgProgress));
                ssb_power_gear_right_control.setProgress(MsgProgress);
                sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_RIGHT, MsgProgress, DeviceInstructionsEntity.MAX_BRAKE_DURATION);

            }
        }

    }


    /**
     *
     *  int  flag  判断那个
     * */

    int  flag =0 ;

    @OnClick({R.id.bottom_top_circle,R.id.bottom_center_circle,R.id.bottom_bottom_circle,R.id.center_top_circle,R.id.center_center_circle,R.id.center_bottom_circle,R.id.top_tv_power_gear,R.id. center_tv_power_gear,R.id.bottom_power_gear,R.id.btn_power_gear_lr_control,R.id.btn_power_gear,R.id.tv_power_gear_lr_def_name,R.id.tv_power_gear_left_def_name,R.id.tv_power_gear_right_def_name ,R.id.top_top_circle,R.id.top_center_circle,R.id.top_bottom_circle})
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id. top_tv_power_gear:

                showDiaglogType = 0;

                if (power_gear_lr_control_state==1){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }

                break;
            case R.id. center_tv_power_gear:


                showDiaglogType = 1;

                if (power_gear_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }

                break;
            case R.id. bottom_power_gear:


                showDiaglogType = 2;

                if (power_gear_lr_control_state==2){

                    showDialog(getActivity().getResources().getString(R.string.Manually_enter_values)+"(0~100)");

                }else {

                }

                break;

            case R.id.tv_power_gear_lr_def_name:

                initializationSenVelocitySensitivity(1,0,true,50);

                break;
            case R.id.tv_power_gear_left_def_name:
                initializationSenVelocitySensitivity(1,1,true,50);
                break;
            case R.id.tv_power_gear_right_def_name:

                initializationSenVelocitySensitivity(1,2,true,50);
                break;


            case R.id.btn_power_gear://力量档位展开收缩按钮

                if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {

                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.connect_device_please), Toast.LENGTH_SHORT).show();

                } else {

                    //如果是展开
                    if (btn_power_gear.isSelected()) {
                        btn_power_gear.setSelected(false);
                        ll_power_gear_items.setVisibility(View.GONE);
                    }
                    //如果是收缩
                    else {
                        /**
                         *
                         *     A 版本注释
                         *
                         * */

//                    queryState(DeviceQueryEntity.POWER_LEFT, DeviceQueryEntity.POWER_RIGHT);
                        btn_power_gear.setSelected(true);
                        ll_power_gear_items.setVisibility(View.VISIBLE);
                    }
                }

                break;
            case R.id.btn_power_gear_lr_control://力量档位左右群控选择按钮
                //如果左右群控选中
                if (power_gear_lr_control_state == TRUE) {
                    power_gear_lr_control_state = FALSE;
                } else {
                    power_gear_lr_control_state = TRUE;
                }
                //设置进度条颜色和一些相关文字的颜色
                //设置进度条颜色和一些相关文字的颜色
                leftAndRightControlSelectStateNewWzPowerGear(btn_power_gear_lr_control, tv_power_gear_lr_name, tv_power_gear_lr_def_name, tv_power_gear_lr_def_value, ssb_power_gear_lr_control,
                        tv_power_gear_left_name, tv_power_gear_left_def_name, tv_power_gear_left_def_value, ssb_power_gear_left_control,
                        tv_power_gear_right_name, tv_power_gear_right_def_name, tv_power_gear_right_def_value, ssb_power_gear_right_control, power_gear_lr_control_state,top_tv_power_gear,center_tv_power_gear,bottom_power_gear,top_top_circle,top_center_circle,top_bottom_cicle,center_top_circle,center_center_circle,center_bottom_cicle,bottom_top_circle,bottom_center_circle,bottom_bottom_cicle);


                break;


            case R.id.top_top_circle:
                flag=1;

                showSeekBarDialog(flag,0);


                tv_power_gear_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));

                ssb_power_gear_lr_control.setProgress(0);
                ssb_power_gear_left_control.setProgress(0);
                ssb_power_gear_right_control.setProgress(0);
                break;

            case R.id.top_center_circle:
                flag=1;
                showSeekBarDialog(flag,1);


                tv_power_gear_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));

                ssb_power_gear_lr_control.setProgress(50);
                ssb_power_gear_left_control.setProgress(50);
                ssb_power_gear_right_control.setProgress(50);

                break;

            case R.id.top_bottom_circle:
                flag=1;

                showSeekBarDialog(flag,2);


                tv_power_gear_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                ssb_power_gear_lr_control.setProgress(100);
                ssb_power_gear_left_control.setProgress(100);
                ssb_power_gear_right_control.setProgress(100);

                break;



            case R.id.center_top_circle:

                flag=2;

                showSeekBarDialog(flag,0);


                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));


                ssb_power_gear_left_control.setProgress(0);

                break;

            case R.id.center_center_circle:

                flag=2;

                showSeekBarDialog(flag,1);
                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                ssb_power_gear_left_control.setProgress(50);


                break;

            case R.id.center_bottom_circle:
                flag =2 ;

                showSeekBarDialog(flag,2);
                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                ssb_power_gear_left_control.setProgress(100);

                break;



            case  R.id.bottom_top_circle:

                flag=3;

                showSeekBarDialog(flag,0);
                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                ssb_power_gear_right_control .setProgress(0);


                break;
            case  R.id.bottom_center_circle:
                flag=3;

                showSeekBarDialog(flag,1);
                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                ssb_power_gear_right_control.setProgress(50);

                break;
            case  R.id.bottom_bottom_circle:

                flag=4;
                showSeekBarDialog(flag,2);
                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                ssb_power_gear_right_control.setProgress(100);

                break;

        }

    }


    private  void  setLinsteners(){



        ssb_power_gear_lr_control.setProgress(50);
        ssb_power_gear_left_control.setProgress(50);
        ssb_power_gear_right_control.setProgress(50);

        top_tv_power_gear.setText(setText(50));
        center_tv_power_gear.setText(setText(50));
        bottom_power_gear.setText(setText(50));

        tv_power_gear_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"50°");
        tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"50°");
        tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.default_text)+"50°");



    }



    // Number 是点击的那一个  Type 类别  vis 是否显示发送命令    progress 当前进度
    private  void   initializationSenVelocitySensitivity(int Type,int Number,boolean vis,int progress) {

        if (Type == 1) {
            if (Number == 0) {
                tv_power_gear_lr_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "50°");
                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "50°");
                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "50°");

                ssb_power_gear_lr_control.setProgress(progress);
                ssb_power_gear_left_control.setProgress(progress);
                ssb_power_gear_right_control.setProgress(progress);
                top_tv_power_gear.setText(getActivity().getResources().getString(R.string.current)+"50"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                center_tv_power_gear.setText(getActivity().getResources().getString(R.string.current)+"50"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                bottom_power_gear.setText(getActivity().getResources().getString(R.string.current)+"50"+" "+ getActivity().getResources().getString(R.string.Manual_input));


                if (vis){

                    sendValueInstructions(DeviceInstructionsEntity.BRAKE_FORCE_SAME, 50, DeviceInstructionsEntity.MAX_BRAKE_DURATION);

                }

            }else  if (Number == 1){
                center_tv_power_gear.setText(getActivity().getResources().getString(R.string.current)+"50"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "50°");
                ssb_power_gear_left_control.setProgress(progress);
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
                bottom_power_gear .setText(getActivity().getResources().getString(R.string.current)+"50"+" "+ getActivity().getResources().getString(R.string.Manual_input));
                ssb_power_gear_right_control.setProgress(progress);
                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.default_text) + "50°");
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
                tv_power_gear_lr_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
            }else if (Number==4){

                tv_power_gear_left_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));
            }else if (Number==5){

                tv_power_gear_right_def_name.setText(getActivity().getResources().getString(R.string.restore_defaults));

            }
        }

    }

    MyDialog myDialog ;
    SignSeekBar signSeekBarTOP;
    SignSeekBar signSeekBarCenter;
    SignSeekBar signSeekBarBottom;
    TextView tv_dialog_range_lr_def_value;

    SignSeekBar [] arraySeekBar ;
    Button btn_yes_o;
    Button btn_no_o;


    /***
     * Flag 上中下那一个seekbar进来的 （Activity  里的上中下）
     * type 是低中高的那个一个进来的
     *
     * */

    private  void  showSeekBarDialog(int Flag,int type){

        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_seekbar,null);
        if (myDialog==null) {
            myDialog = new MyDialog(getActivity(), 0, 0, view, R.style.MyDialog);
            signSeekBarTOP = view.findViewById(R.id.ssb_dialog_range_lr_control_TOP);
            signSeekBarCenter = view.findViewById(R.id.ssb_dialog_range_lr_control_CENTER);
            signSeekBarBottom = view.findViewById(R.id.ssb_dialog_range_lr_control_BOTTOM);
            tv_dialog_range_lr_def_value = view.findViewById(R.id.tv_dialog_range_lr_def_value);
            btn_yes_o = view.findViewById(R.id.btn_yes_o);
            btn_no_o = view.findViewById(R.id.btn_no_o);

            signSeekBarTOP.setOnProgressChangedListener(this);
            signSeekBarCenter.setOnProgressChangedListener(this);
            signSeekBarBottom.setOnProgressChangedListener(this);

            arraySeekBar = new SignSeekBar[]{signSeekBarTOP, signSeekBarCenter, signSeekBarBottom};
        }
        if (type==0){

            tv_dialog_range_lr_def_value.setText(" "+0);
            vis(type);

        } else if (type==1){

            tv_dialog_range_lr_def_value.setText(" "+41);
            vis(type);
        }else {
            tv_dialog_range_lr_def_value.setText(" "+71);
            vis(type);
        }


        btn_yes_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDialog.dismiss();

            }
        });
        btn_no_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDialog.dismiss();


            }
        });


        myDialog.show();


    }


    private void vis(int flag){

        for ( int i =0;i< arraySeekBar.length;i++){

            if (i==flag) {

                arraySeekBar[i].setVisibility(View.VISIBLE);

            }else {

                arraySeekBar[i].setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {

        switch (signSeekBar.getId()){

            case R.id.ssb_dialog_range_lr_control_TOP:

                tv_dialog_range_lr_def_value.setText(" "+progress);

                if (flag==1){
                    top_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else if (flag==2){
                    center_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else {

                    bottom_power_gear .setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }
                break;
            case R.id.ssb_dialog_range_lr_control_CENTER:

                tv_dialog_range_lr_def_value.setText(" "+progress);

                if (flag==1){
                    top_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else if (flag==2){
                    center_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else {

                    bottom_power_gear .setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }

                break;
            case R.id.ssb_dialog_range_lr_control_BOTTOM:

                tv_dialog_range_lr_def_value.setText(" "+progress);

                if (flag==1){
                    top_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else if (flag==2){
                    center_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else {

                    bottom_power_gear .setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }

                break;




        }
    }

    @Override
    public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {

        switch (signSeekBar.getId()){

            case R.id.ssb_dialog_range_lr_control_TOP:

                tv_dialog_range_lr_def_value.setText(" "+progress);

                if (flag==1){
                    top_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else if (flag==2){
                    center_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else {

                    bottom_power_gear .setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }
                    break;
            case R.id.ssb_dialog_range_lr_control_CENTER:

                tv_dialog_range_lr_def_value.setText(" "+progress);

                if (flag==1){
                    top_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else if (flag==2){
                    center_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else {

                    bottom_power_gear .setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }

                break;
            case R.id.ssb_dialog_range_lr_control_BOTTOM:

                tv_dialog_range_lr_def_value.setText(" "+progress);

                if (flag==1){
                    top_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else if (flag==2){
                    center_tv_power_gear.setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }else {

                    bottom_power_gear .setText(getActivity().getResources().getString(R.string.current )+" "+progress );
                }

                break;




        }


    }

    @Override
    public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {

    }
}
