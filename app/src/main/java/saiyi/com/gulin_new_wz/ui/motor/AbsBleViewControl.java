package saiyi.com.gulin_new_wz.ui.motor;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.ble.BleManger;
import saiyi.com.gulin_new_wz.ble.MacIdEntity;
import saiyi.com.gulin_new_wz.entity.DeviceInstructionsEntity;
import saiyi.com.gulin_new_wz.view.MyDialogForText;
import saiyi.com.gulin_new_wz.view.SignSeekBar;

import static saiyi.com.gulin_new_wz.utils.Constants.FALSE;
import static saiyi.com.gulin_new_wz.utils.Constants.TRUE;
import static saiyi.com.gulin_new_wz.utils.Constants.UUID_SERVICE;
import static saiyi.com.gulin_new_wz.utils.Constants.UUID_WRITE_READ;
import static saiyi.com.gulin_new_wz.utils.Constants.VALUE;

/**
 * Created by 陈姣姣 on 2018/8/27.
 */
public  abstract class AbsBleViewControl extends AbsIViewControlImpl {

    MyDialogForText myDialogForText;

    public AbsBleViewControl(AppCompatActivity appCompatActivity, int viewId) {
        super(appCompatActivity, viewId);

        myDialogForText =new MyDialogForText(getActivity());

    }




    /**
     * 左右群控是否选中设置相应的文字颜色
     *
     * @param btn_lr_control     左右群控按钮
     * @param tv_lr_name         左右群控名称
     * @param tv_lr_def_name     左右群控默认名称
     * @param tv_lr_def_value    左右群控默认值控件
     * @param seekBar_lr         左右群控滚动条
     * @param tv_left_name       左单控名称
     * @param tv_left_def_name   左单控默认名称
     * @param tv_left_def_value  左单控默认值控件
     * @param seekBar_left       左单控滚动条
     * @param tv_right_name      右单控名称
     * @param tv_right_def_name  右单控默认名称
     * @param tv_right_def_value 右单控默认值控件
     * @param seekBar_right      右单控滚动条
     * @param state              是否选中状态，1选中，2未选中
     */
    public void leftAndRightControlSelectState(ImageView btn_lr_control,
                                               TextView tv_lr_name, TextView tv_lr_def_name, TextView tv_lr_def_value, SignSeekBar seekBar_lr,
                                               TextView tv_left_name, TextView tv_left_def_name, TextView tv_left_def_value, SignSeekBar seekBar_left,
                                               TextView tv_right_name, TextView tv_right_def_name, TextView tv_right_def_value, SignSeekBar seekBar_right,
                                               int state) {
        //如果左右群控选中
        if (state == TRUE) {
            //设置左右群控选中
            btn_lr_control.setSelected(true);
            //设置左右群控文字的颜色
            tv_lr_name.setTextColor(Color.parseColor("#4E5969"));
            tv_lr_def_name.setTextColor(Color.parseColor("#9FACBD"));
            tv_lr_def_value.setTextColor(Color.parseColor("#9FACBD"));
            //设置左右群控进度条可拖动
            seekBar_lr.setEnabled(true);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_lr, Color.parseColor("#D5DEE7"), Color.parseColor("#00DAB0"),
                    Color.parseColor("#9FACBD"));

            //设置左单控文字颜色
            tv_left_name.setTextColor(Color.parseColor("#4D4E5969"));
            tv_left_def_name.setTextColor(Color.parseColor("#4D9FACBD"));
            tv_left_def_value.setTextColor(Color.parseColor("#4D9FACBD"));
            //设置左单控进度条不可拖动
            seekBar_left.setEnabled(false);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_left, Color.parseColor("#EEF5F6"), Color.parseColor("#D1F8F2"),
                    Color.parseColor("#4D9FACBD"));

            //设置右单控文字颜色
            tv_right_name.setTextColor(Color.parseColor("#4D4E5969"));
            tv_right_def_name.setTextColor(Color.parseColor("#4D9FACBD"));
            tv_right_def_value.setTextColor(Color.parseColor("#4D9FACBD"));
            //设置右单控进度条不可拖动
            seekBar_right.setEnabled(false);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_right, Color.parseColor("#EEF5F6"), Color.parseColor("#D1F8F2"),
                    Color.parseColor("#4D9FACBD"));
        }
        //如果左右群控未选中
        else if (state == FALSE) {

            //设置左右群控选中
            btn_lr_control.setSelected(false);
            //设置左右群控文字的颜色
            tv_lr_name.setTextColor(Color.parseColor("#4D4E5969"));
            tv_lr_def_name.setTextColor(Color.parseColor("#4D9FACBD"));
            tv_lr_def_value.setTextColor(Color.parseColor("#4D9FACBD"));
            //设置左右群控进度条不可拖动
            seekBar_lr.setEnabled(false);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_lr, Color.parseColor("#EEF5F6"), Color.parseColor("#D1F8F2"), Color.parseColor("#4D9FACBD"));

            //设置左单控文字颜色
            tv_left_name.setTextColor(Color.parseColor("#4E5969"));
            tv_left_def_name.setTextColor(Color.parseColor("#9FACBD"));
            tv_left_def_value.setTextColor(Color.parseColor("#9FACBD"));
            //设置左单控进度条可拖动
            seekBar_left.setEnabled(true);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_left, Color.parseColor("#D5DEE7"), Color.parseColor("#00DAB0"), Color.parseColor("#9FACBD"));

            //设置右单控文字颜色
            tv_right_name.setTextColor(Color.parseColor("#4E5969"));
            tv_right_def_name.setTextColor(Color.parseColor("#9FACBD"));
            tv_right_def_value.setTextColor(Color.parseColor("#9FACBD"));
            //设置右单控进度条可拖动
            seekBar_right.setEnabled(true);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_right, Color.parseColor("#D5DEE7"), Color.parseColor("#00DAB0"), Color.parseColor("#9FACBD"));
        }
    }
    public void leftAndRightControlSelectStateNewWz(ImageView btn_lr_control,
                                               TextView tv_lr_name, TextView tv_lr_def_name, TextView tv_lr_def_value, SignSeekBar seekBar_lr,
                                               TextView tv_left_name, TextView tv_left_def_name, TextView tv_left_def_value, SignSeekBar seekBar_left,
                                               TextView tv_right_name, TextView tv_right_def_name, TextView tv_right_def_value, SignSeekBar seekBar_right,
                                               int state,TextView top,TextView center,TextView bottom) {
        //如果左右群控选中
        if (state == TRUE) {
            //设置左右群控选中
            btn_lr_control.setSelected(true);
            //设置左右群控文字的颜色
            tv_lr_name.setTextColor(Color.parseColor("#4E5969"));
            tv_lr_def_name.setTextColor(Color.parseColor("#9FACBD"));
            top.setTextColor(Color.parseColor("#9FACBD"));
            tv_lr_def_value.setTextColor(Color.parseColor("#9FACBD"));
            //设置左右群控进度条可拖动
            seekBar_lr.setEnabled(true);
            top.setEnabled(true);
            tv_lr_def_name.setEnabled(true);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_lr, Color.parseColor("#D5DEE7"), Color.parseColor("#00DAB0"),
                    Color.parseColor("#9FACBD"));

            //设置左单控文字颜色
            tv_left_name.setTextColor(Color.parseColor("#4D4E5969"));
            tv_left_def_name.setTextColor(Color.parseColor("#4D9FACBD"));
            center.setTextColor(Color.parseColor("#4D9FACBD"));
            tv_left_def_value.setTextColor(Color.parseColor("#4D9FACBD"));
            //设置左单控进度条不可拖动
            seekBar_left.setEnabled(false);
            center.setEnabled(false);
            tv_left_def_name.setEnabled(false);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_left, Color.parseColor("#EEF5F6"), Color.parseColor("#D1F8F2"),
                    Color.parseColor("#4D9FACBD"));

            //设置右单控文字颜色
            tv_right_name.setTextColor(Color.parseColor("#4D4E5969"));
            tv_right_def_name.setTextColor(Color.parseColor("#4D9FACBD"));
            bottom.setTextColor(Color.parseColor("#4D9FACBD"));
            tv_right_def_value.setTextColor(Color.parseColor("#4D9FACBD"));
            //设置右单控进度条不可拖动
            seekBar_right.setEnabled(false);
            bottom.setEnabled(false);
            tv_right_def_name.setEnabled(false);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_right, Color.parseColor("#EEF5F6"), Color.parseColor("#D1F8F2"),
                    Color.parseColor("#4D9FACBD"));
        }
        //如果左右群控未选中
        else if (state == FALSE) {

            //设置左右群控选中
            btn_lr_control.setSelected(false);
            //设置左右群控文字的颜色
            tv_lr_name.setTextColor(Color.parseColor("#4D4E5969"));
            tv_lr_def_name.setTextColor(Color.parseColor("#4D9FACBD"));
            top.setTextColor(Color.parseColor("#4D9FACBD"));
            tv_lr_def_value.setTextColor(Color.parseColor("#4D9FACBD"));
            //设置左右群控进度条不可拖动
            seekBar_lr.setEnabled(false);
            top.setEnabled(false);
            tv_lr_def_name.setEnabled(false);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_lr, Color.parseColor("#EEF5F6"), Color.parseColor("#D1F8F2"), Color.parseColor("#4D9FACBD"));

            //设置左单控文字颜色
            tv_left_name.setTextColor(Color.parseColor("#4E5969"));
            tv_left_def_name.setTextColor(Color.parseColor("#9FACBD"));
            center.setTextColor(Color.parseColor("#9FACBD"));
            tv_left_def_value.setTextColor(Color.parseColor("#9FACBD"));
            //设置左单控进度条可拖动
            seekBar_left.setEnabled(true);
            center.setEnabled(true);
            tv_left_def_name.setEnabled(true);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_left, Color.parseColor("#D5DEE7"), Color.parseColor("#00DAB0"), Color.parseColor("#9FACBD"));

            //设置右单控文字颜色
            tv_right_name.setTextColor(Color.parseColor("#4E5969"));
            tv_right_def_name.setTextColor(Color.parseColor("#9FACBD"));
            bottom.setTextColor(Color.parseColor("#9FACBD"));
            tv_right_def_value.setTextColor(Color.parseColor("#9FACBD"));
            //设置右单控进度条可拖动
            seekBar_right.setEnabled(true);
            bottom.setEnabled(true);
            tv_right_def_name.setEnabled(true);
            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_right, Color.parseColor("#D5DEE7"), Color.parseColor("#00DAB0"), Color.parseColor("#9FACBD"));
        }
    }
    public void leftAndRightControlSelectStateNewWzPowerGear(ImageView btn_lr_control,
                                               TextView tv_lr_name, TextView tv_lr_def_name, TextView tv_lr_def_value, SignSeekBar seekBar_lr,
                                               TextView tv_left_name, TextView tv_left_def_name, TextView tv_left_def_value, SignSeekBar seekBar_left,
                                               TextView tv_right_name, TextView tv_right_def_name, TextView tv_right_def_value, SignSeekBar seekBar_right,
                                               int state,TextView top,TextView center,TextView bottom,TextView top_top_circle,TextView top_center_circle,TextView top_bottom_circle,TextView center_top_circle,TextView center_center_circle ,TextView center_bottom_circle,TextView bottom_top_circle,TextView bottom_center_circle,TextView bottom_bottom_circle) {
        //如果左右群控选中
        if (state == TRUE) {
            //设置左右群控选中
            btn_lr_control.setSelected(true);
            //设置左右群控文字的颜色
            tv_lr_name.setTextColor(Color.parseColor("#4E5969"));
            tv_lr_def_name.setTextColor(Color.parseColor("#9FACBD"));
            top.setTextColor(Color.parseColor("#9FACBD"));
            tv_lr_def_value.setTextColor(Color.parseColor("#9FACBD"));
            //设置左右群控进度条可拖动
            seekBar_lr.setEnabled(true);
            top.setEnabled(true);
            tv_lr_def_name.setEnabled(true);
            top_top_circle.setEnabled(true);
            top_center_circle.setEnabled(true);
            top_bottom_circle.setEnabled(true);


            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_lr, Color.parseColor("#D5DEE7"), Color.parseColor("#00DAB0"),
                    Color.parseColor("#9FACBD"));

            //设置左单控文字颜色
            tv_left_name.setTextColor(Color.parseColor("#4D4E5969"));
            tv_left_def_name.setTextColor(Color.parseColor("#4D9FACBD"));
            center.setTextColor(Color.parseColor("#4D9FACBD"));
            tv_left_def_value.setTextColor(Color.parseColor("#4D9FACBD"));
            //设置左单控进度条不可拖动
            seekBar_left.setEnabled(false);
            center.setEnabled(false);
            tv_left_def_name.setEnabled(false);
            center_top_circle.setEnabled(false);
            center_center_circle.setEnabled(false);
            center_bottom_circle.setEnabled(false);



            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_left, Color.parseColor("#EEF5F6"), Color.parseColor("#D1F8F2"),
                    Color.parseColor("#4D9FACBD"));

            //设置右单控文字颜色
            tv_right_name.setTextColor(Color.parseColor("#4D4E5969"));
            tv_right_def_name.setTextColor(Color.parseColor("#4D9FACBD"));
            bottom.setTextColor(Color.parseColor("#4D9FACBD"));
            tv_right_def_value.setTextColor(Color.parseColor("#4D9FACBD"));
            //设置右单控进度条不可拖动
            seekBar_right.setEnabled(false);
            bottom.setEnabled(false);
            tv_right_def_name.setEnabled(false);

            bottom_top_circle.setEnabled(false);
            bottom_center_circle.setEnabled(false);
            bottom_bottom_circle.setEnabled(false);

            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_right, Color.parseColor("#EEF5F6"), Color.parseColor("#D1F8F2"),
                    Color.parseColor("#4D9FACBD"));
        }
        //如果左右群控未选中
        else if (state == FALSE) {

            //设置左右群控选中
            btn_lr_control.setSelected(false);
            //设置左右群控文字的颜色
            tv_lr_name.setTextColor(Color.parseColor("#4D4E5969"));
            tv_lr_def_name.setTextColor(Color.parseColor("#4D9FACBD"));
            top.setTextColor(Color.parseColor("#4D9FACBD"));
            tv_lr_def_value.setTextColor(Color.parseColor("#4D9FACBD"));
            //设置左右群控进度条不可拖动
            seekBar_lr.setEnabled(false);
            top.setEnabled(false);
            tv_lr_def_name.setEnabled(false);
            top_top_circle.setEnabled(false);
            top_center_circle.setEnabled(false);
            top_bottom_circle.setEnabled(false);



            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_lr, Color.parseColor("#EEF5F6"), Color.parseColor("#D1F8F2"), Color.parseColor("#4D9FACBD"));

            //设置左单控文字颜色
            tv_left_name.setTextColor(Color.parseColor("#4E5969"));
            tv_left_def_name.setTextColor(Color.parseColor("#9FACBD"));
            center.setTextColor(Color.parseColor("#9FACBD"));
            tv_left_def_value.setTextColor(Color.parseColor("#9FACBD"));
            //设置左单控进度条可拖动
            seekBar_left.setEnabled(true);
            center.setEnabled(true);
            tv_left_def_name.setEnabled(true);
            center_top_circle.setEnabled(true);
            center_center_circle.setEnabled(true);
            center_bottom_circle.setEnabled(true);


            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_left, Color.parseColor("#D5DEE7"), Color.parseColor("#00DAB0"), Color.parseColor("#9FACBD"));

            //设置右单控文字颜色
            tv_right_name.setTextColor(Color.parseColor("#4E5969"));
            tv_right_def_name.setTextColor(Color.parseColor("#9FACBD"));
            bottom.setTextColor(Color.parseColor("#9FACBD"));
            tv_right_def_value.setTextColor(Color.parseColor("#9FACBD"));
            //设置右单控进度条可拖动
            seekBar_right.setEnabled(true);
            bottom.setEnabled(true);
            tv_right_def_name.setEnabled(true);

            bottom_top_circle.setEnabled(true);
            bottom_center_circle.setEnabled(true);
            bottom_bottom_circle.setEnabled(true);

            //设置滚动条颜色和文字颜色
            setSeekBarProgressColor(seekBar_right, Color.parseColor("#D5DEE7"), Color.parseColor("#00DAB0"), Color.parseColor("#9FACBD"));
        }
    }


    /**
     * 设置滚动条颜色和文字颜色
     *
     * @param seekBar                滚动条
     * @param ssb_track_color        进度条背景
     * @param ssb_second_track_color 进度颜色
     * @param ssb_section_text_color 文字颜色
     */
    private void setSeekBarProgressColor(SignSeekBar seekBar, int ssb_track_color,
                                         int ssb_second_track_color, int ssb_section_text_color) {
        seekBar.getConfigBuilder()
//                .min(0)
//                .max(4)
//                .progress(3)
//                .sectionCount(4)
                .trackColor(ssb_track_color)
                .secondTrackColor(ssb_second_track_color)
                .sectionTextColor(ssb_section_text_color)
                .thumbColor(Color.parseColor("#FFFFFF"))
                .build();
    }



    Intent intent;
    public  void  showDialog(String title){
        if (myDialogForText!=null) {
            myDialogForText.setTitle(title);
            myDialogForText.setMessageToNull();
            myDialogForText.setYesOnclickListener(getActivity().getResources().getString(R.string.sure), new MyDialogForText.onYesOnclickListener() {
                @Override
                public void onYesClick() {

                    myDialogForText.dismiss();
                    String value = myDialogForText.getMessage();
                    intent = new Intent(VALUE);
                    intent.putExtra(VALUE, value);
                    send(intent);

                }
            });
            myDialogForText.setNoOnclickListener(getActivity().getResources().getString(R.string.cancel), new MyDialogForText.onNoOnclickListener() {
                @Override
                public void onNoClick() {

                    myDialogForText.dismiss();

                }
            });

            myDialogForText.show();
        }
     }


    /**
     *  用户封装值，显示ui
     * */
    public String  setText(int progeress){

        return  getActivity().getResources().getString(R.string.current)+progeress+" "+getActivity().getResources().getString(R.string.Manual_input);

    }


    /**
     * 设置马达左右群控或单控是的开关状态和文字颜色
     *
     * @param state                          群控状态，1是群控，2单控
     * @param btn_motor_lr_control
     * @param
     * @param btn_motor_left_control_switch
     * @param btn_motor_right_control_switch
     */
    public void setMotorSwitchStateAndTextColor(int state, ImageView btn_motor_lr_control,
                                                ImageView btn_motor_left_control_switch,
                                                ImageView btn_motor_right_control_switch) {
        //如果是群控
        if (state == FALSE) {
            btn_motor_lr_control.setSelected(false);
            btn_motor_left_control_switch.setBackgroundResource(R.mipmap.off);
            btn_motor_right_control_switch.setBackgroundResource(R.mipmap.off);
        }else{
            btn_motor_lr_control.setSelected(true);
            btn_motor_left_control_switch.setBackgroundResource(R.mipmap.on);
            btn_motor_right_control_switch.setBackgroundResource(R.mipmap.on);
        }
    }




    //发送指令
    public void sendInstructions(byte[] instructions) {
        BleManger.getInstance().sendCmd(MacIdEntity.getMacId(),UUID_SERVICE, UUID_WRITE_READ, instructions);
    }

    //发送带进度条数值的指令
    public void sendValueInstructions(String data1, int progress, int maxProgress) {
        sendInstructions(DeviceInstructionsEntity.getStringToByte(data1, (int) (progress / 100f * maxProgress) + ""));
    }

}
