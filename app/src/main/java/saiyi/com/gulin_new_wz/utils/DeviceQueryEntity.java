package saiyi.com.gulin_new_wz.utils;

/**
 * Created by Ligs on 2018-01-26.
 */

public class DeviceQueryEntity {

    //开发者模式的查询
    //18号通道被干掉了----
    public final static byte[] ACC_L = {0x30, 0x31, 0x20, 0x34, 0x30, 0x20, 0x31, 0x38, 0x0d, 0x0a};
    public final static byte[] ACC_R = {0x30, 0x31, 0x20, 0x33, 0x30, 0x20, 0x31, 0x38, 0x0d, 0x0a};
    //18号通道被干掉了----
    public final static byte[] GYRO_L = {0x30, 0x31, 0x20, 0x34, 0x30, 0x20, 0x31, 0x39, 0x0d, 0x0a};
    public final static byte[] GYRO_R = {0x30, 0x31, 0x20, 0x33, 0x30, 0x20, 0x31, 0x39, 0x0d, 0x0a};

    //电量
    public final static String ELECTRICITY = "*GP";
    //
    public final static String CHARGE = "*K";


    //    gyro:
//    Left: 01 40 96  return: 01 40 96 幅值
//    right: 01 30 96 return: 01 30 96 幅值
    public final static String GYRO_LEFT = "01 40 96";
    public final static String GYRO_RIGHT = "01 30 96";
    //accy:
//    left:	01 40 95  return: 01 40 95 幅值
//    right: 01 30 95 return:01 30 95 幅值
    public final static String ACCY_LEFT = "01 40 95";
    public final static String ACCY_RIGHT = "01 30 95";
    //读取静态Sensor数据超限幅值
//    accy:
//    app send: 01 50 91		return:01 50 91 幅值
//    gyro:
//    app send: 01 50 92 		return: 01 50 92 幅值
    public final static String GYRO_SENSOR = "01 50 92";
    public final static String ACCY_SENSOR = "01 50 91";

    //角度
    public final static String ANGLE_LEFT = "01 40 190";
    public final static String ANGLE_RIGHT = "01 30 190";


    //马达力度
    public final static String WZ_MADA_LEFT = "01 40 34";
    public final static String WZ_MADA_RIGHT = "01 30 34";


    //角速度
    public final static String SPEED_ANGLE_LEFT = "01 40 189";
    public final static String SPEED_ANGLE_RIGHT = "01 30 189";

    //中风角度
    public final static String TRIGGER_ANGLE_LEFT = "01 40 116";
    public final static String TRIGGER_ANGLE_RIGHT = "01 30 116";

    //中风角速度
    public final static String TRIGGER_SPEED_ANGLE_LEFT = "01 40 117";
    public final static String TRIGGER_SPEED_ANGLE_RIGHT = "01 30 117";

    //触发时间
    public final static String TRIGGER_TIME_LEFT = "01 40 185";
    public final static String TRIGGER_TIME_RIGHT = "01 30 185";

    //力量
    public final static String POWER_LEFT = "01 40 191";
    public final static String POWER_RIGHT = "01 30 191";

    //刹车时间
    public final static String BRAKE_DURATION_LEFT = "01 40 188";
    public final static String BRAKE_DURATION_RIGHT = "01 30 188";

    //刹车力
    public final static String BRAKE_FORCE_LEFT= "01 40 186";
    public final static String BRAKE_FORCE_RIGHT = "01 30 186";

    //最高点
    public final static String BEST_DELAY_LEFT = "01 40 187";
    public final static String BEST_DELAY_RIGHT = "01 30 187";

    public final static String BLE_CONNECT_INIT = "01 50 192";


}
