package saiyi.com.gulin_new_wz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.adapter.DerviceAdapter;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.ble.BleBean;
import saiyi.com.gulin_new_wz.ble.BleConstant;
import saiyi.com.gulin_new_wz.ble.BleManger;
import saiyi.com.gulin_new_wz.ble.BleUtils;
import saiyi.com.gulin_new_wz.ble.IBleStateListener;
import saiyi.com.gulin_new_wz.ble.MacIdEntity;
import saiyi.com.gulin_new_wz.entity.DeviceInstructionsEntity;
import saiyi.com.gulin_new_wz.ui.FirstActivity;
import saiyi.com.gulin_new_wz.ui.communication_data.DialogListener;
import saiyi.com.gulin_new_wz.ui.motor.BrakingTimeViewControl;
import saiyi.com.gulin_new_wz.ui.motor.MaxBrakeFoceViewControl;
import saiyi.com.gulin_new_wz.ui.motor.MyAssistAngleAngeViewContorl;
import saiyi.com.gulin_new_wz.ui.motor.MyHighestPointHleps;
import saiyi.com.gulin_new_wz.ui.motor.MyInitialBrakeForceViewControl;
import saiyi.com.gulin_new_wz.ui.motor.MyMotorViewControl;
import saiyi.com.gulin_new_wz.ui.motor.MyPowerGear;
import saiyi.com.gulin_new_wz.ui.motor.MySensitivityViewControl;
import saiyi.com.gulin_new_wz.ui.trigger_time.TriggerTimeViewControl;
import saiyi.com.gulin_new_wz.utils.AppManager;
import saiyi.com.gulin_new_wz.utils.DialogUtils;
import saiyi.com.gulin_new_wz.utils.IonMacLinsteners;
import saiyi.com.gulin_new_wz.utils.LogUtils;
import saiyi.com.gulin_new_wz.utils.MyDialog;
import saiyi.com.gulin_new_wz.utils.SPUtils;

import static android.media.MediaCodec.MetricsConstants.MODE;
import static saiyi.com.gulin_new_wz.utils.Constants.APPMODE;
import static saiyi.com.gulin_new_wz.utils.Constants.LEGS_MODLE;
import static saiyi.com.gulin_new_wz.utils.Constants.ONE_LEG_MODLE;

public class MainActivity extends BaseActivity implements View.OnClickListener, IonMacLinsteners, IBleStateListener {


    /**
     * 切换模式的按钮
     ***/
    @BindView(R.id.btn_toggle)
    TextView btn_toggle;
    /**
     * 模式名字 是什么模式
     */
    @BindView(R.id.tv_mode_name)
    TextView tv_mode_name;

    @BindView(R.id.iv_bluetooth)
    ImageView iv_bluetooth;
    /**
     * 蓝牙模块的方面
     */

    @BindView(R.id.ll_bluetooth)
    LinearLayout ll_bluetooth;
    // 保存按钮
    @BindView(R.id.btn_save_param)
    TextView btn_save_param;
    // 紧急制动按钮
    @BindView(R.id.btn_emergency_brake)
    TextView btn_emergency_brake;


    @BindView(R.id.btn_connect_bluetooth)
    TextView btn_connect_bluetooth;


    private boolean mFinishIntent = false;


    @BindView(R.id.header_left)
    ImageButton headerLeft;

    /**
     * 模式，1双腿模式STANDARD_MODLE，2单腿模式APOPLEXY_MODLE
     */
    private int mode = LEGS_MODLE;
    List<BleBean> dataBeanList = new ArrayList<>();
    LocalBroadcastManager mLocalBroadcastManager;


    @BindView(R.id.iv_charging)
    ImageView iv_charging;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void initData() {

        setTile(getResources().getString(R.string.training));

        IntentFilter intentFilter = new IntentFilter(BleConstant.SUCCED);
        intentFilter.addAction(BleConstant.DUANKAI);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);//获得实例
        mLocalBroadcastManager.registerReceiver(receiver, intentFilter);//注册监听

        iv_charging.setVisibility(View.GONE);

        TriggerTimeViewControl triggerTimeViewControl = new TriggerTimeViewControl(this, R.id.ll_trigger_time);
        MyMotorViewControl myMotorViewControl = new MyMotorViewControl(this, R.id.ll_motor);
        MySensitivityViewControl mySensitivityViewControl = new MySensitivityViewControl(this, R.id.ll_sensitivity);
        BrakingTimeViewControl brakingTimeViewControl = new BrakingTimeViewControl(this, R.id.ll_braking);
        MyHighestPointHleps myHighestPointHleps = new MyHighestPointHleps(this, R.id.ll_highest_point);
        MyInitialBrakeForceViewControl myInitialBrakeForceViewControl = new MyInitialBrakeForceViewControl(this, R.id.ll_brake_force);
        MaxBrakeFoceViewControl maxBrakeFoceViewControl = new MaxBrakeFoceViewControl(this, R.id.ll_max_braking_time);
        MyAssistAngleAngeViewContorl myAssistAngleAngeViewContorl = new MyAssistAngleAngeViewContorl(this, R.id.ll_assist_angle_range);
        MyPowerGear myPowerGear = new MyPowerGear(this, R.id.ll_power_gear_new);

        btn_save_param.setClickable(false);
        btn_emergency_brake.setClickable(false);
        btn_toggle.setClickable(false);

    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if (action.contains(BleConstant.SUCCED)) {

                iv_bluetooth.setImageResource(R.mipmap.bluetooth);
                btn_save_param.setClickable(true);
                btn_save_param.setBackgroundResource(R.drawable.btn_green_rectangle_normal);
                btn_save_param.setTextColor(getResources().getColor(R.color.white_color));
                //紧急制动按钮
                btn_emergency_brake.setClickable(true);
                btn_emergency_brake.setBackgroundResource(R.drawable.btn_orange_rectangle);
                btn_emergency_brake.setTextColor(getResources().getColor(R.color.white_color));
                //切换按钮
                btn_toggle.setClickable(true);
                btn_connect_bluetooth.setText(getResources().getString(R.string.connect_already_text));
                btn_connect_bluetooth.setTextColor(Color.parseColor("#00DAB0"));
                iv_charging.setVisibility(View.VISIBLE);


            } else if (action.contains(BleConstant.DUANKAI)) {

                iv_bluetooth.setImageResource(R.mipmap.bluetooth_d);
                btn_save_param.setClickable(false);
                btn_save_param.setBackgroundResource(R.drawable.btn_thirty_percent_green_rectangle_normal);
                btn_save_param.setTextColor(getResources().getColor(R.color.white_thirty_percent_color));
                //紧急制动按钮
                btn_emergency_brake.setClickable(false);
                btn_emergency_brake.setBackgroundResource(R.drawable.btn_thirty_percent_orange_rectangle);
                btn_emergency_brake.setTextColor(getResources().getColor(R.color.white_thirty_percent_color));
                //切换按钮
                btn_toggle.setClickable(false);
                btn_connect_bluetooth.setText(getResources().getString(R.string.un_connect_text));
                btn_connect_bluetooth.setTextColor(Color.parseColor("#CDCDCD"));
                iv_charging.setVisibility(View.GONE);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        if (SPUtils.getInt(this, APPMODE, -1) == -1) {
            setStandard();
        } else if (SPUtils.getInt(this, APPMODE, -1) == 1) {

            setStandard();
        } else {
            setApoplexy();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(receiver);

    }

    @OnClick({R.id.ll_bluetooth, R.id.btn_toggle, R.id.btn_save_param, R.id.btn_emergency_brake, R.id.header_left})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_left:
                change(FirstActivity.class, MainActivity.this, new Intent(), false);
                break;

            case R.id.btn_save_param:  // 保存
                //保存用户数据弹窗
                DialogUtils.showIsOkDialog(MainActivity.this, getString(R.string.save_text), getString(R.string.cancel_text),
                        getString(R.string.whether_to_save_param_text), "", new DialogListener() {
                            @Override
                            public void onComplete() {
                                //保存成功弹窗
                                mFinishIntent = false;
                                sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.R_SAVE_USER_INFO));

                            }

                            @Override
                            public void onFail() {
                            }
                        });
                break;

            case R.id.btn_emergency_brake: // 紧急制动

                //紧急制动弹窗
                DialogUtils.showIsOkDialog(MainActivity.this, getString(R.string.sure_text), getString(R.string.cancel_text),
                        getString(R.string.is_sure_emergency_brake_title_text), getString(R.string.emergency_brake_tips), new DialogListener() {
                            @Override
                            public void onComplete() {
                                //确定
                                sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.EMERGENCY_BRAKE));
                            }

                            @Override
                            public void onFail() {
                            }
                        });


                break;

            case R.id.btn_toggle://切换按钮


                if (!TextUtils.isEmpty(MacIdEntity.getMacId())) {

                    //如果是双腿模式
                    if (mode == LEGS_MODLE) {
                        setApoplexy();
                    } else {
                        setStandard();

                    }

                } else {

                    Toast.makeText(MainActivity.this, getString(R.string.connect_device_please), Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.ll_bluetooth:


//                    checkBle();

                if (TextUtils.isEmpty(MacIdEntity.getMacId())) {
                    BleManger.getInstance().openBle(MainActivity.this);
                    showDialog();
                } else {
                    DialogUtils.showIsOkDialog(this, getString(R.string.sure_text), getString(R.string.cancel_text), getString(R.string.are_you_disconnect_text), new DialogListener() {
                        @Override
                        public void onComplete() {
                            //确定
                            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
                            localBroadcastManager.sendBroadcast(new Intent(BleConstant.DUANKAI));
                            MacIdEntity.setMacId(null);
                            MacIdEntity.mIsSign = false;

                        }

                        @Override
                        public void onFail() {
                            //取消

                        }
                    });


                }

                break;


        }
    }

    Intent intentbroadcastReceiver;

    private void setApoplexy() {
        toast(getResources().getString(R.string.switched_to_stroke_mode_text));
//        iv_select_left_stroke.setVisibility(View.GONE);
//        iv_select_right_stroke.setVisibility(View.GONE);
        mode = ONE_LEG_MODLE;
        tv_mode_name.setText(getString(R.string.stroke_mode_text));
        intentbroadcastReceiver = new Intent(MODE);
        intentbroadcastReceiver.putExtra(MODE, ONE_LEG_MODLE);
        sendLocalBroadcast(intentbroadcastReceiver);


    }


    private void setStandard() {
        /**
         *  切换模式是否要发指令 未知
         * */
//        sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.SWITCH_NORMAL));
        toast(getResources().getString(R.string.switched_to_normal_mode_text));
        mode = LEGS_MODLE;
        //设置模式文字
        tv_mode_name.setText(getString(R.string.Legs_mode_text));
        intentbroadcastReceiver = new Intent(MODE);
        intentbroadcastReceiver.putExtra(MODE, LEGS_MODLE);
        sendLocalBroadcast(intentbroadcastReceiver);


    }


    MyDialog myDialog;
    DerviceAdapter adapter;
    LinearLayout linLoading;
    LinearLayout derviceItem;

    private void showDialog() {
        searchBle();
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_dervice_list, null);
        myDialog = new MyDialog(MainActivity.this, 0, 0, view, R.style.MyDialog, 0);
        Button btn_Yes = view.findViewById(R.id.btn_Yes);
        RecyclerView listDialog = view.findViewById(R.id.listview_dialog);
        listDialog.setHasFixedSize(true);
        listDialog.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DerviceAdapter(this);
        listDialog.setAdapter(adapter);
        adapter.setIonMacLinsteners(this);
        adapter.setiBleStateListener(this);
        linLoading = view.findViewById(R.id.lin_loadering);
        derviceItem = view.findViewById(R.id.lin_dervice_item);

        linLoading.setVisibility(View.VISIBLE);
        derviceItem.setVisibility(View.GONE);


        btn_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDialog.dismiss();
            }
        });

        myDialog.show();


    }

    boolean isSearch = false;

    void searchBle() {
        BleManger.getInstance().startSearch(new SearchResponse() {
            @Override
            public void onSearchStarted() {
                isSearch = true;
            }

            @Override
            public void onDeviceFounded(SearchResult device) {
                BleBean bleBean;
                boolean isEmptyName = (TextUtils.isEmpty(device.getName()) || device.getName().equals("NULL"));
                bleBean = new BleBean(isEmptyName ? device.getAddress() : device.getName(), device.getAddress());
                if (!BleUtils.getIntent().isContains(dataBeanList, bleBean)) {
                    LogUtils.e("===>add new ble:" + bleBean == null ? "null" : bleBean.toString());

                    if (device.getName().contains("BLE")) {
                        dataBeanList.add(bleBean);
                    }
                }
                adapter.updataAdapter(dataBeanList);


            }

            @Override
            public void onSearchStopped() {

                if (dataBeanList.size() == 0) {

                    toast(getResources().getString(R.string.ble_scan_null));
                    myDialog.dismiss();
                } else {

                    linLoading.setVisibility(View.GONE);
                    derviceItem.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onSearchCanceled() {

//
//                if (dataBeanList.size()==0){
//
//                    toast(getResources().getString(R.string.ble_scan_null));
//
//                }

            }
        });
    }


    /**
     * 退出app
     */
    public void exit() {

        if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {
            AppManager.getInstance().closeAllActivity();
            return;
        }

        DialogUtils.showIsOkDialog(this, getString(R.string.save_text),
                getString(R.string.cancel_text), getString(R.string.whether_to_save_user_data),
                "", new DialogListener() {
                    @Override
                    public void onComplete() {
                        clickSaveInfo();
                        //保存
//                        AppManager.getInstance().closeAllActivity();
                    }

                    @Override
                    public void onFail() {
                        //取消
                        AppManager.getInstance().closeAllActivity();
                    }
                });
    }

    Intent intent;

    @Override
    public void macLinstener(String name, String mac, int postion) {
        intent = new Intent(BleConstant.CONNTION);
        intent.putExtra(BleConstant.CONNTION, mac);
        intent.putExtra(BleConstant.NAME, name);
        sendBroadcast(intent);

    }


    @Override
    public void setSuccess(int position, String mac) {

        /**
         *
         *  成功了想要去修改状态
         *
         * */

        MacIdEntity.setMacId(mac);
        /**
         *  连接成功后不断读取电量信息,
         * */
        iv_charging.setVisibility(View.VISIBLE);
        btn_connect_bluetooth.setText(getResources().getString(R.string.connect_already_text));
        btn_connect_bluetooth.setTextColor(Color.parseColor("#00DAB0"));
        intentbroadcastReceiver = new Intent(BleConstant.SUCCED);
        sendLocalBroadcast(intentbroadcastReceiver);


    }

    @Override
    public void setFail(int position) {

        /**
         *
         *  失败了想要去修改状态
         *
         * */
        MacIdEntity.setMacId(null);
        iv_charging.setVisibility(View.GONE);
        btn_connect_bluetooth.setText(getResources().getString(R.string.un_connect_text));
        btn_connect_bluetooth.setTextColor(Color.parseColor("#CDCDCD"));
        intentbroadcastReceiver = new Intent(BleConstant.LOSE);
        sendLocalBroadcast(intentbroadcastReceiver);

    }

    public void clickSaveInfo() {
        if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {
            Toast.makeText(this, getString(R.string.connect_device_please), Toast.LENGTH_SHORT).show();
        } else {
            mFinishIntent = true;
            sendInstructions(DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.R_SAVE_USER_INFO));
        }


    }

}
