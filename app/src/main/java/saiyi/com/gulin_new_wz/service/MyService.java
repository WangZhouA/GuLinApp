package saiyi.com.gulin_new_wz.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

import saiyi.com.gulin_new_wz.ble.BDWReadI;
import saiyi.com.gulin_new_wz.ble.BleConstant;
import saiyi.com.gulin_new_wz.ble.BleManger;
import saiyi.com.gulin_new_wz.ble.HexDump;
import saiyi.com.gulin_new_wz.ble.MacIdEntity;
import saiyi.com.gulin_new_wz.entity.DeviceInstructionsEntity;
import saiyi.com.gulin_new_wz.ui.communication_data.CommunicationBean;
import saiyi.com.gulin_new_wz.ui.constant.GaitEntity;
import saiyi.com.gulin_new_wz.utils.LogUtils;

import static saiyi.com.gulin_new_wz.utils.Constants.UUID_SERVICE;
import static saiyi.com.gulin_new_wz.utils.Constants.UUID_WRITE_READ;


/**
 * Created by 陈姣姣 on 2018/8/1.
 */
public class MyService extends Service implements BDWReadI{

    private int MEG_DEVICE_STRING = 2210;


    static String ANGLE_LEFT_FLAGE_WZ;
    static String ANGLE_RIGHT_FLAGE_WZ;
    static String VELOCITY_LEFT_FLAGE_WZ;
    static String VELOCITY_RIGHT_FLAGE_WZ;
    static String CURRENT_LEFT_FLAGE_WZ;
    static String CURRENT_RIGHT_FLAGE_WZ;
    static String BAROMETRIC_SENSOR_FLAGE_WZ;
    static String SENSOR_FLAGE_WZ;



    private Handler mHandler =new Handler(){

        @Override
        public void handleMessage(Message msg) {

            if (msg.what==MEG_DEVICE_STRING) {

                /**
                 *  这里是通信数据采集的一块
                 * */
                String indication = (String) msg.obj;
//                Intent intentExtra = new Intent(CommunicationDataActivity.ACTION_COMMUNICATION_DATA);
//                intentExtra.putExtra("instructions", indication);
//                intentExtra.putExtra("isSent", false);
//                sendBroadcast(intentExtra);
                getListDate(indication, false);

                /**
                 *  这里是
                 * */

                if (indication.contains(GaitEntity.ANGLE_LEFT_FLAGE)) {
//                    Intent intent = new Intent(DerviceDateFragment.ACTION_ANGLE_LEFT);
//                    intent.putExtra("content", indication.substring(GaitEntity.ANGLE_LEFT_FLAGE.length(), indication.length()).trim());
//                    sendBroadcast(intent);
                    ANGLE_LEFT_FLAGE_WZ =indication.substring(GaitEntity.ANGLE_LEFT_FLAGE.length(), indication.length()).trim();

                } else if (indication.contains(GaitEntity.ANGLE_RIGHT_FLAGE)) {
//                    Intent intent = new Intent(DerviceDateFragment.ACTION_ANGLE_RIGHT);
//                    intent.putExtra("content", indication.substring(GaitEntity.ANGLE_RIGHT_FLAGE.length(), indication.length()).trim());
//                    sendBroadcast(intent);

                    ANGLE_RIGHT_FLAGE_WZ =indication.substring(GaitEntity.ANGLE_RIGHT_FLAGE.length(), indication.length()).trim();


                } else if (indication.contains(GaitEntity.VELOCITY_LEFT_FLAGE)) {
//                    Intent intent = new Intent(DerviceDateFragment.ACTION_VELOCITY_LEFT);
//                    intent.putExtra("content", indication.substring(GaitEntity.VELOCITY_LEFT_FLAGE.length(), indication.length()).trim());
//                    sendBroadcast(intent);

                    VELOCITY_LEFT_FLAGE_WZ =indication.substring(GaitEntity.VELOCITY_LEFT_FLAGE.length(), indication.length()).trim();



                } else if (indication.contains(GaitEntity.VELOCITY_RIGHT_FLAGE)) {
//                    Intent intent = new Intent(DerviceDateFragment.ACTION_VELOCITY_RIGHT);
//                    intent.putExtra("content", indication.substring(GaitEntity.VELOCITY_RIGHT_FLAGE.length(), indication.length()).trim());
//                    sendBroadcast(intent);

                    VELOCITY_RIGHT_FLAGE_WZ =indication.substring(GaitEntity.VELOCITY_RIGHT_FLAGE.length(), indication.length()).trim();


                } else if (indication.contains(GaitEntity.CURRENT_LEFT_FLAGE)) {
//                    Intent intent = new Intent(DerviceDateFragment.ACTION_CURRENT_LEFT);
//                    intent.putExtra("content", indication.substring(GaitEntity.CURRENT_LEFT_FLAGE.length(), indication.length()).trim());
//                    sendBroadcast(intent);

                    CURRENT_LEFT_FLAGE_WZ =indication.substring(GaitEntity.CURRENT_LEFT_FLAGE.length(), indication.length()).trim();



                } else if (indication.contains(GaitEntity.CURRENT_RIGHT_FLAGE)) {
//                    Intent intent = new Intent(DerviceDateFragment.ACTION_CURRENT_RIGHT);
//                    intent.putExtra("content", indication.substring(GaitEntity.CURRENT_RIGHT_FLAGE.length(), indication.length()).trim());
//                    sendBroadcast(intent);
//
                    CURRENT_RIGHT_FLAGE_WZ =indication.substring(GaitEntity.CURRENT_RIGHT_FLAGE.length(), indication.length()).trim();



                } else if (indication.contains(GaitEntity.BAROMETRIC_SENSOR_FLAGE)) {
//                    Intent intent = new Intent(DerviceDateFragment.ACTION_BAROMETRIC_SENSOR);
//                    intent.putExtra("content", indication.substring(GaitEntity.BAROMETRIC_SENSOR_FLAGE.length(), indication.length()).trim());
//                    sendBroadcast(intent);

                    BAROMETRIC_SENSOR_FLAGE_WZ =indication.substring(GaitEntity.BAROMETRIC_SENSOR_FLAGE.length(), indication.length()).trim();



                } else if (indication.contains(GaitEntity.SENSOR_FLAGE)) {
//                    Intent intent = new Intent(DerviceDateFragment.ACTION_SENSOR);
//                    intent.putExtra("content", indication.substring(GaitEntity.SENSOR_FLAGE.length(), indication.length()).trim());
//                    sendBroadcast(intent);

                    SENSOR_FLAGE_WZ =indication.substring(GaitEntity.SENSOR_FLAGE.length(), indication.length()).trim();


                }
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        BleManger.getInstance().setBdwReadI(this);
//        IntentFilter intentFilter = new IntentFilter(BleConstant.CONNTION);
//        intentFilter.addAction(BleConstant.DUANKAI);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BleConstant.SUCCED);
        registerReceiver(broadcastReceiver,intentFilter);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);

    }
    //    String  mac;
//    String  name;
    private BroadcastReceiver broadcastReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action =intent.getAction();
//            if (action.contains(BleConstant.CONNTION)){
//                /**
//                 *  连接蓝牙
//                 * */
//                mac = intent.getStringExtra(BleConstant.CONNTION);
//                name = intent.getStringExtra(BleConstant.NAME);
//                BleManger.getInstance().connectBle(mac, new BleConnectResponse() {
//                    @Override
//                    public void onResponse(int code, BleGattProfile data) {
//                        LogUtils.e("code======" + code);
//
//                        //成功
//                        if (code == Constants.REQUEST_SUCCESS) {
//                            for (int i = 0; i < data.getServices().size(); i++) {
//                                LogUtils.e("uuuid=====" + data.getServices().get(i).getUUID());
//                            }
//                            SPUtils.putString(MyService.this,"mac",mac);
//                            SPUtils.putString(MyService.this,"name",name);
//                            BleManger.getInstance().openNotify(mac, BleConstant.seviceUID, BleConstant.NotifyUID);
//                            sendBroadcast(new Intent(BleConstant.SUCCED));
//
//                        }
//                        //失败
//                        else if (code == Constants.REQUEST_FAILED) {
//                            LogUtils.e("连接失败======");
//                            sendBroadcast(new Intent(BleConstant.LOSE));
//                        }
//                    }
//                });
//
//            }else if (action.contains(BleConstant.DUANKAI)){
//                BleManger.getInstance().disConnectBle(mac);
//            }
                if (action.contains(BleConstant.SUCCED)){
                    Toast.makeText(context, "回调走到后台", Toast.LENGTH_SHORT).show();
                    BleManger.getInstance().sendCmd(MacIdEntity.getMacId(),UUID_SERVICE, UUID_WRITE_READ, DeviceInstructionsEntity.getHaveEndByte(DeviceInstructionsEntity.read_Electricity));


                }
        }
    };

    @Override
    public void onRCResult(String result) {

    }

    @Override
    public void onRCFail() {

    }

    @Override
    public void onNotifyResult(String result) {

    }

    @Override
    public void onNotifyFail() {

    }
    //处理byte流的Str
    private StringBuffer mNewInstruction = new StringBuffer();
    @Override
    public void onCharacteristicChanged(byte[] data) {


        LogUtils.e("==回调回来的数据=="+ HexDump.BytetohexString(data));

        for (byte b : data) {
            if (b == 0x0a) {
                continue;
            }
            if (b == 0x0d) {
                Message message = mHandler.obtainMessage(MEG_DEVICE_STRING);
                message.obj = mNewInstruction.toString();
                mHandler.sendMessage(message);
                mNewInstruction.delete(0, mNewInstruction.length());
            } else {
                mNewInstruction.append((char) b);
            }
        }

    }



    @Override
    public void onWriteCharSuccess(String instructions) {
        LogUtils.e("==写入成功了=="+ new String(instructions));
//        Intent intentExtra = new Intent(CommunicationDataActivity.ACTION_COMMUNICATION_DATA);
//        intentExtra.putExtra("instructions", instructions);
//        intentExtra.putExtra("isSent", true);
//        sendBroadcast(intentExtra);
        getListDate(instructions,true);
    }

    SimpleDateFormat mDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private   LinkedList <CommunicationBean> mList=new LinkedList<>() ;

    private  static LinkedList <CommunicationBean> mListIntent ;

    private    void   getListDate(String instructions,boolean isSent){
        if (!instructions.matches("\\*P\\d+") && !instructions.matches("\\*K\\d+")) {
            {
                if (mList.size() > 200) {
                    mList.remove();
                }
                CommunicationBean communicationBean = new CommunicationBean();
                communicationBean.setIsSent(isSent==false ? 1 : 0);
                communicationBean.setContent(instructions);
                communicationBean.setTime(mDf.format(System.currentTimeMillis()));
                mList.add(communicationBean);

            }
        }
        mListIntent = mList;

    }


    public   static  LinkedList  getListDate(){


        return  mListIntent;
    }


     public  static  String   getDerviceDateValue(int flag ,String str){

        if (flag==0){

            str = ANGLE_LEFT_FLAGE_WZ;
        }else if (flag==1){

            str = ANGLE_RIGHT_FLAGE_WZ;
        }else if (flag==2){

            str = VELOCITY_LEFT_FLAGE_WZ;
        }else if (flag==3){

            str = VELOCITY_RIGHT_FLAGE_WZ;
        }else if (flag==4){

            str = CURRENT_LEFT_FLAGE_WZ;
        }else if (flag==5){
            str = BAROMETRIC_SENSOR_FLAGE_WZ;
        }else if (flag==6){

            str = SENSOR_FLAGE_WZ;
        }

        return  str;
     }

}
