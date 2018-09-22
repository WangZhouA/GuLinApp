package saiyi.com.gulin_new_wz.ui.communication_data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.ble.MacIdEntity;
import saiyi.com.gulin_new_wz.entity.DeviceInstructionsEntity;
import saiyi.com.gulin_new_wz.http.HttpRequestCallback;
import saiyi.com.gulin_new_wz.http.HttpUtils;
import saiyi.com.gulin_new_wz.ui.constant.GaitEntity;
import saiyi.com.gulin_new_wz.ui.constant.MyConstants;
import saiyi.com.gulin_new_wz.view.AreaChartView;

/**
 * Created by 陈姣姣 on 2018/9/13.
 */
public class DerviceDateFragment extends Fragment implements CompoundButton.OnCheckedChangeListener,View.OnClickListener, HttpRequestCallback {


    @BindView(R.id.cb_angle_left)
    CheckBox cb_angle_left;
    @BindView(R.id.cb_angle_right)
    CheckBox cb_angle_right;
    @BindView(R.id.cb_velocity_left)
    CheckBox cb_velocity_left;
    @BindView(R.id.cb_velocity_right)
    CheckBox cb_velocity_right;
    @BindView(R.id.cb_current_left)
    CheckBox cb_current_left;
    @BindView(R.id.cb_current_right)
    CheckBox cb_current_right;
    @BindView(R.id.cb_barometric_sensor)
    CheckBox cb_barometric_sensor;
    @BindView(R.id.cb_sensor)
    CheckBox cb_sensor;
    @BindView(R.id.sv_gait)
    ScrollView sv_gait;

    @BindView(R.id.acv_angle_left)
    AreaChartView acv_angle_left;
    @BindView(R.id.acv_angle_right)
    AreaChartView acv_angle_right;
    @BindView(R.id.acv_velocity_left)
    AreaChartView acv_velocity_left;
    @BindView(R.id.acv_velocity_right)
    AreaChartView acv_velocity_right;
    @BindView(R.id.acv_current_left)
    AreaChartView acv_current_left;
    @BindView(R.id.acv_current_right)
    AreaChartView acv_current_right;
    @BindView(R.id.acv_barometric_sensor)
    AreaChartView acv_barometric_sensor;
    @BindView(R.id.acv_sensor)
    AreaChartView acv_sensor;
    @BindView(R.id.btn_select_curve)
    LinearLayout btn_select_curve;
    List<Double> mYALeftList = new ArrayList<>();
    List<Double> mYARightList = new ArrayList<>();
    List<Double> mYVLeftList = new ArrayList<>();
    List<Double> mYVRightList = new ArrayList<>();
    List<Double> mYCLeftList = new ArrayList<>();
    List<Double> mYCRightList = new ArrayList<>();
    List<Double> mYBSList = new ArrayList<>();
    List<Double> mYSList = new ArrayList<>();

    public static final String ACTION_ANGLE_LEFT = "gait.angle.left";
    public static final String ACTION_ANGLE_RIGHT = "gait.angle.right";
    public static final String ACTION_VELOCITY_LEFT = "gait.velocity.left";
    public static final String ACTION_VELOCITY_RIGHT = "gait.velocity.right";
    public static final String ACTION_CURRENT_LEFT = "gait.current.left";
    public static final String ACTION_CURRENT_RIGHT = "gait.current.right";
    public static final String ACTION_BAROMETRIC_SENSOR = "gait.angle.barometric";
    public static final String ACTION_SENSOR = "gait.sensor";
    private List<String> mXtitle;
    private SimpleDateFormat mFormatter;

    private static final int NETWORK_FLAGE_UPLOAD = 101;

    Unbinder unbinder;


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dervice_date,null);
        unbinder = ButterKnife.bind(this,view);
        mXtitle = new ArrayList<>();
        mXtitle.add("");
        mXtitle.add("");
        initCb();
        initACV();
        initBroadcast();
        mFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return view;



    }


    private void initCb() {
        cb_angle_left.setOnCheckedChangeListener(this);
        cb_angle_right.setOnCheckedChangeListener(this);
        cb_velocity_left.setOnCheckedChangeListener(this);
        cb_velocity_right.setOnCheckedChangeListener(this);
        cb_current_left.setOnCheckedChangeListener(this);
        cb_current_right.setOnCheckedChangeListener(this);
        cb_barometric_sensor.setOnCheckedChangeListener(this);
        cb_sensor.setOnCheckedChangeListener(this);
    }

    private void initACV() {
        for (int i = 0; i < 50; i++) {
            mXtitle.add("");
        }
        acv_angle_left.setInitialization(getResources().getColor(R.color.gait_angle_left_color),
                getResources().getColor(R.color.gait_angle_left_line_color), GaitEntity.MAX_ANGLE, GaitEntity.LEAST_ANGLE, "°/d", "0°/d");
        acv_angle_right.setInitialization(getResources().getColor(R.color.gait_angle_right_color),
                getResources().getColor(R.color.gait_angle_right_line_color), GaitEntity.MAX_ANGLE, GaitEntity.LEAST_ANGLE, "°/d", "0°/d");
        acv_velocity_left.setInitialization(getResources().getColor(R.color.gait_velocity_left_color),
                getResources().getColor(R.color.gait_velocity_left_line_color), GaitEntity.MAX_SPEED, GaitEntity.LEAST_SPEED, "°/s", "0°/s");
        acv_velocity_right.setInitialization(getResources().getColor(R.color.gait_velocity_right_color),
                getResources().getColor(R.color.gait_velocity_right_line_color), GaitEntity.MAX_SPEED, GaitEntity.LEAST_SPEED, "°/s", "0°/s");
        acv_current_left.setInitialization(getResources().getColor(R.color.gait_current_left_color),
                getResources().getColor(R.color.gait_current_left_line_color), DeviceInstructionsEntity.MAX_CURRENT, 0, "A", "");
        acv_current_right.setInitialization(getResources().getColor(R.color.gait_current_right_color),
                getResources().getColor(R.color.gait_current_right_line_color), DeviceInstructionsEntity.MAX_CURRENT, 0, "A", "");
        acv_barometric_sensor.setInitialization(getResources().getColor(R.color.gait_barometric_sensor_color),
                getResources().getColor(R.color.gait_barometric_sensor_line_color), 100, 0, "", "");
        acv_sensor.setInitialization(getResources().getColor(R.color.gait_sensor_color), getResources().getColor(R.color.gait_sensor_line_color), 100, 0, "", "");



    }

    private void initBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_ANGLE_LEFT);
        intentFilter.addAction(ACTION_ANGLE_RIGHT);
        intentFilter.addAction(ACTION_VELOCITY_LEFT);
        intentFilter.addAction(ACTION_VELOCITY_RIGHT);
        intentFilter.addAction(ACTION_CURRENT_LEFT);
        intentFilter.addAction(ACTION_CURRENT_RIGHT);
        intentFilter.addAction(ACTION_BAROMETRIC_SENSOR);
        intentFilter.addAction(ACTION_SENSOR);
        getActivity().registerReceiver(mGaitBroadcast, intentFilter);
    }


    private BroadcastReceiver mGaitBroadcast = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            double value;
            String contentStr = intent.getStringExtra("content");
            try {
                value = Double.parseDouble(contentStr);
            } catch (Exception e) {
                return;
            }
            switch (intent.getAction()) {
                case ACTION_ANGLE_LEFT:
//                    Toast.makeText(getActivity(),contentStr,Toast.LENGTH_SHORT).show();
                    uploadData(KEY_ANGLELEFT, contentStr);
                    setYList(mYALeftList, (value + GaitEntity.MAX_ANGLE) * 80 / (GaitEntity.MAX_ANGLE * 2));
                    acv_angle_left.refreshChart(mXtitle, mYALeftList, 100, 20, 20);
                    break;
                case ACTION_ANGLE_RIGHT:
                    uploadData(KEY_ANGLERIGHT, contentStr);
                    setYList(mYARightList, (value + GaitEntity.MAX_ANGLE) * 80 / (GaitEntity.MAX_ANGLE * 2));
                    acv_angle_right.refreshChart(mXtitle, mYARightList, 100, 20, 20);
                    break;
                case ACTION_VELOCITY_LEFT:
                    uploadData(KEY_ANGULARLEFT, contentStr);
                    setYList(mYVLeftList, (value + GaitEntity.MAX_SPEED) * 80 / (GaitEntity.MAX_SPEED * 2));
                    acv_velocity_left.refreshChart(mXtitle, mYVLeftList, 100, 20, 20);
                    break;
                case ACTION_VELOCITY_RIGHT:
                    uploadData(KEY_ANGULARRIGHT, contentStr);
                    setYList(mYVRightList, (value + GaitEntity.MAX_SPEED) * 80 / (GaitEntity.MAX_SPEED * 2));
                    acv_velocity_right.refreshChart(mXtitle, mYVRightList, 100, 20, 20);
                    break;
                case ACTION_CURRENT_LEFT:
                    uploadData(KEY_ELECTRIXCITYLEFT, contentStr);
                    setYList(mYCLeftList, value * 80 / DeviceInstructionsEntity.MAX_CURRENT);
                    acv_current_left.refreshChart(mXtitle, mYCLeftList, 100, 20, 20);
                    break;
                case ACTION_CURRENT_RIGHT:
                    uploadData(KEY_ELECTRICITYRIGHT, contentStr);
                    setYList(mYCRightList, value * 80 / DeviceInstructionsEntity.MAX_CURRENT);
                    acv_current_right.refreshChart(mXtitle, mYCRightList, 100, 20, 20);
                    break;
                case ACTION_BAROMETRIC_SENSOR:
                    uploadData(KEY_ATMOSPHERIC, contentStr);
                    setYList(mYBSList, value);
                    acv_barometric_sensor.refreshChart(mXtitle, mYBSList, 100, 20, 20);
                    break;
                case ACTION_SENSOR:
                    uploadData(KEY_UISTATIC, contentStr);
                    setYList(mYSList, value);
                    acv_sensor.refreshChart(mXtitle, mYSList, 100, 20, 20);
                    break;
            }
        }
    };
    private static final String KEY_ANGLELEFT = "angleLeft";
    private static final String KEY_ANGLERIGHT = "angleRight";
    private static final String KEY_ANGULARLEFT = "angularLeft";
    private static final String KEY_ANGULARRIGHT = "angularRight";
    private static final String KEY_ATMOSPHERIC = "atmospheric";
    private static final String KEY_ELECTRIXCITYLEFT = "electricityLeft";
    private static final String KEY_ELECTRICITYRIGHT = "electricityRight";
    private static final String KEY_UISTATIC = "uiStatic";

    private StringBuffer jsonAngleLeft = new StringBuffer();
    private StringBuffer jsonAngleRight = new StringBuffer();
    private StringBuffer jsonVelocityLeft= new StringBuffer();
    private StringBuffer jsonVelocityRight= new StringBuffer();
    private StringBuffer jsonCurrentLeft= new StringBuffer();
    private StringBuffer jsonCurrentRight= new StringBuffer();
    private StringBuffer jsonBarometricSensor= new StringBuffer();
    private StringBuffer jsonSensor= new StringBuffer();

    private int countAngleLeft = 0;
    private int countAngleRight = 0;
    private int countVelocityLeft = 0;
    private int countVelocityRight = 0;
    private int countCurrentLeft = 0;
    private int countCurrentRight = 0;
    private int countBarometricSensor = 0;
    private int countSensor = 0;


    private void uploadData(String key, String value) {
        switch (key) {
            case KEY_ANGLELEFT:
                appendALJson(jsonAngleLeft, key, value);
                break;
            case KEY_ANGLERIGHT:
                appendARJson(jsonAngleRight, key, value);
                break;
            case KEY_ANGULARLEFT:
                appendVLJson(jsonVelocityLeft, key, value);
                break;
            case KEY_ANGULARRIGHT:
                appendVRJson(jsonVelocityRight, key, value);
                break;
            case KEY_ATMOSPHERIC:
                appendCLJson(jsonCurrentLeft, key, value);
                break;
            case KEY_ELECTRIXCITYLEFT:
                appendCRJson(jsonCurrentRight, key, value);
                break;
            case KEY_ELECTRICITYRIGHT:
                appendBSJson(jsonBarometricSensor, key, value);
                break;
            case KEY_UISTATIC:
                appendSJson(jsonSensor, key, value);
                break;
        }
    }
    private void appendALJson(StringBuffer sb, String key, String value) {
        if (countAngleLeft == 0) {
            countAngleLeft++;
            sb.append("\"").append(key).append("\":[");
        } else if (countAngleLeft < 50) {
            countAngleLeft++;
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"},");
        } else {
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"}]");
            uploadJsonData(sb);
            countAngleLeft = 0;
            sb.setLength(0);
        }
    }
    private void appendARJson(StringBuffer sb, String key, String value) {
        if (countAngleRight == 0) {
            countAngleRight++;
            sb.append("\"").append(key).append("\":[");
        } else if (countAngleRight < 50) {
            countAngleRight++;
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"},");
        } else {
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"}]");
            uploadJsonData(sb);
            countAngleRight = 0;
            sb.setLength(0);
        }
    }
    private void appendVLJson(StringBuffer sb, String key, String value) {
        if (countVelocityLeft == 0) {
            countVelocityLeft++;
            sb.append("\"").append(key).append("\":[");
        } else if (countVelocityLeft < 50) {
            countVelocityLeft++;
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"},");
        } else {
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"}]");
            uploadJsonData(sb);
            countVelocityLeft = 0;
            sb.setLength(0);
        }
    }
    private void appendVRJson(StringBuffer sb, String key, String value) {
        if (countVelocityRight == 0) {
            countVelocityRight++;
            sb.append("\"").append(key).append("\":[");
        } else if (countVelocityRight < 50) {
            countVelocityRight++;
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"},");
        } else {
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"}]");
            uploadJsonData(sb);
            countVelocityRight = 0;
            sb.setLength(0);
        }
    }
    private void appendCLJson(StringBuffer sb, String key, String value) {
        if (countCurrentLeft == 0) {
            countCurrentLeft++;
            sb.append("\"").append(key).append("\":[");
        } else if (countCurrentLeft < 50) {
            countCurrentLeft++;
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"},");
        } else {
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"}]");
            uploadJsonData(sb);
            countCurrentLeft = 0;
            sb.setLength(0);
        }
    }
    private void appendCRJson(StringBuffer sb, String key, String value) {
        if (countCurrentRight == 0) {
            countCurrentRight++;
            sb.append("\"").append(key).append("\":[");
        } else if (countCurrentRight < 50) {
            countCurrentRight++;
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"},");
        } else {
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"}]");
            uploadJsonData(sb);
            countCurrentRight = 0;
            sb.setLength(0);
        }
    }
    private void appendBSJson(StringBuffer sb, String key, String value) {
        if (countBarometricSensor == 0) {
            countBarometricSensor++;
            sb.append("\"").append(key).append("\":[");
        } else if (countBarometricSensor < 50) {
            countBarometricSensor++;
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"},");
        } else {
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"}]");
            uploadJsonData(sb);
            countBarometricSensor = 0;
            sb.setLength(0);
        }
    }
    private void appendSJson(StringBuffer sb, String key, String value) {
        if (countSensor == 0) {
            countSensor++;
            sb.append("\"").append(key).append("\":[");
        } else if (countSensor < 50) {
            countSensor++;
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"},");
        } else {
            sb.append("{\"macId\":\"").append(MacIdEntity.getMacId())
                    .append("\",\"number\":\"").append(value).append("\",\"time\":\"").append(mFormatter.format(new Date())).append("\"}]");
            uploadJsonData(sb);
            countSensor = 0;
            sb.setLength(0);
        }
    }



    private void uploadJsonData(StringBuffer sb) {
        sb.insert(0, "{");
        sb.append("}");
        HttpUtils.getInstance(getActivity()).postJsonHttp(getActivity(),"SmartControl/GaitData/addGaitData",sb.toString(),NETWORK_FLAGE_UPLOAD,this);
        Log.d("zc", sb.toString());
    }



    private void setYList(List<Double> list, Double newValue) {
        if (list.size() > 51) {
            list.remove(0);
            list.add(newValue + 20);
        } else {
            list.add(newValue + 20);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        if (isChecked) {
            if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {
                Toast.makeText(getActivity(), getString(R.string.connect_device_please), Toast.LENGTH_SHORT).show();
                compoundButton.setChecked(false);
                return;
            }
        }
        switch (compoundButton.getId()) {
            case R.id.cb_angle_left:
                if (isChecked) {
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_ANGLE_LEFT));
                } else {
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_ANGLE_LEFT_FALSE));
                }
                break;
            case R.id.cb_angle_right:
                if (isChecked)
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_ANGLE_RIGHT));
                else
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_ANGLE_RIGHT_FALSE));
                break;
            case R.id.cb_velocity_left:
                if (isChecked)
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_VELOCITY_LEFT));
                else
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_VELOCITY_LEFT_FALSE));
                break;
            case R.id.cb_velocity_right:
                if (isChecked)
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_VELOCITY_RIGHT));
                else
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_VELOCITY_RIGHT_FALSE));
                break;
            case R.id.cb_current_left:
                if (isChecked)
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_CURRENT_LEFT));
                else
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_CURRENT_LEFT_FALSE));
                break;
            case R.id.cb_current_right:
                if (isChecked)
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_CURRENT_RIGHT));
                else
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_CURRENT_RIGHT_FALSE));
                break;
            case R.id.cb_barometric_sensor:
                if (isChecked)
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_BAROMETRIC_SENSOR));
                else
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_BAROMETRIC_SENSOR_FALSE));
                break;
            case R.id.cb_sensor:
                if (isChecked)
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_SENSOR));
                else
                    getActivity().sendBroadcast(new Intent(MyConstants.ACTION_GAIT_SENSOR_FALSE));
                break;
        }

    }

    @Override
    public void onResponse(String sequest, int type) {
        if (type == NETWORK_FLAGE_UPLOAD){
//            Toast.makeText(getActivity(),"nice",Toast.LENGTH_SHORT).show();
            Log.d("zc",sequest);
        }
    }

    @Override
    public void onFailure(String exp) {
        Log.d("zc",exp);
    }

    @Override
    public void onDestroyView() {
        uploadAll();
        getActivity().unregisterReceiver(mGaitBroadcast);
        super.onDestroyView();
    }
    //上传所有数据
    private void uploadAll(){
        StringBuffer buffer = new StringBuffer();
        appEndData(buffer,jsonAngleLeft);
        appEndData(buffer,jsonAngleRight);
        appEndData(buffer,jsonVelocityLeft);
        appEndData(buffer,jsonVelocityRight);
        appEndData(buffer,jsonCurrentLeft);
        appEndData(buffer,jsonCurrentRight);
        appEndData(buffer,jsonBarometricSensor);
        appEndData(buffer,jsonSensor);
        uploadJsonData(buffer);
    }
    private void appEndData(StringBuffer buffer,StringBuffer endBuffer){
        if (endBuffer.length()!=0){
            buffer.append(endBuffer);
        }
    }

    @OnClick({R.id.btn_select_curve, R.id.tv_angle_left, R.id.tv_angle_right, R.id.tv_velocity_left
            , R.id.tv_velocity_right, R.id.tv_current_left, R.id.tv_current_right, R.id.tv_barometric_sensor, R.id.tv_sensor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_select_curve://选择曲线下拉按钮
                if (btn_select_curve.isSelected()) {
                    btn_select_curve.setSelected(false);
                    sv_gait.setVisibility(View.GONE);
                } else {
                    btn_select_curve.setSelected(true);
                    sv_gait.setVisibility(View.VISIBLE);
                }
                break;


            case R.id.tv_angle_left:
                cb_angle_left.setChecked(!cb_angle_left.isChecked());
                break;
            case R.id.tv_angle_right:
                cb_angle_right.setChecked(!cb_angle_right.isChecked());
                break;
            case R.id.tv_velocity_left:
                cb_velocity_left.setChecked(!cb_velocity_left.isChecked());
                break;
            case R.id.tv_velocity_right:
                cb_velocity_right.setChecked(!cb_velocity_right.isChecked());
                break;
            case R.id.tv_current_left:
                cb_current_left.setChecked(!cb_current_left.isChecked());
                break;
            case R.id.tv_current_right:
                cb_current_right.setChecked(!cb_current_right.isChecked());
                break;
            case R.id.tv_barometric_sensor:
                cb_barometric_sensor.setChecked(!cb_barometric_sensor.isChecked());
                break;
            case R.id.tv_sensor:
                cb_sensor.setChecked(!cb_sensor.isChecked());
                break;

        }
    }
}
