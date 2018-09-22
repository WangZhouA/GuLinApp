package saiyi.com.gulin_new_wz.entity;

/**
 * Created by Ligs on 2018-01-25.
 * 控制设备命令的实体类.
 * R_开头的为有设备数据返回
 */

public class DeviceInstructionsEntity {

    //触发时间
    public final static int MAX_TRIGGER_TIME = 500;
    //刹车时间
    public final static int MAX_BRAKE_DURATION = 100;

    //最高助力(王洲自己加的)
    public final static int MAX_HIGHEST_HELPS = 200;


    //    灵敏度（角度）
    public final static int MAX_ANGLE = 800;
    //    灵敏度（角速度）
    public final static int MAX_SPEED = 300;
    //电流
    public final static int MAX_CURRENT = 6;


    //读取电量信息
    public final static String read_Electricity= "01 30 56";

    //保存用户数据
    public final static String R_SAVE_USER_INFO = "01 50 22";
    //切换成正常模式
    public final static String SWITCH_NORMAL = "01 50 101";
    //紧急制动
    public final static String EMERGENCY_BRAKE = "01 50 84";
    //WIFI控制
    public final static String WIFI_OPEN = "01 50 83";
    public final static String WIFI_CLOSE = "01 50 82";

    //左力量控制  1档 2档 3档
    // 指令:01 40 200
    public final static String POWER_LEFT_1 = "01 40 200 1";
    public final static String POWER_LEFT_2 = "01 40 200 2";
    public final static String POWER_LEFT_3 = "01 40 200 3";
    public final static String POWER_LEFT_4 = "01 40 200 4";
    public final static String POWER_LEFT_5 = "01 40 200 5";
    public final static String POWER_LEFT_6 = "01 40 200 6";
    public final static String POWER_LEFT_7 = "01 40 200 7";
    public final static String POWER_LEFT_8 = "01 40 200 8";
    public final static String POWER_LEFT_9 = "01 40 200 9";
    //右力量控制  1档 2档 3档
    // 指令:01 30 200
    public final static String POWER_RIGHT_1 = "01 30 200 1";
    public final static String POWER_RIGHT_2 = "01 30 200 2";
    public final static String POWER_RIGHT_3 = "01 30 200 3";
    public final static String POWER_RIGHT_4 = "01 30 200 4";
    public final static String POWER_RIGHT_5 = "01 30 200 5";
    public final static String POWER_RIGHT_6 = "01 30 200 6";
    public final static String POWER_RIGHT_7 = "01 30 200 7";
    public final static String POWER_RIGHT_8 = "01 30 200 8";
    public final static String POWER_RIGHT_9 = "01 30 200 9";
    //一起力量控制
    // 指令:01 50 200
    public final static String POWER_SAME_1 = "01 50 200 1";
    public final static String POWER_SAME_2 = "01 50 200 2";
    public final static String POWER_SAME_3 = "01 50 200 3";
    public final static String POWER_SAME_4 = "01 50 200 4";
    public final static String POWER_SAME_5 = "01 50 200 5";
    public final static String POWER_SAME_6 = "01 50 200 6";
    public final static String POWER_SAME_7 = "01 50 200 7";
    public final static String POWER_SAME_8 = "01 50 200 8";
    public final static String POWER_SAME_9 = "01 50 200 9";



















    //最高点时间控制
    //left:01 40 87
    public final static String BEST_DELAY_LEFT_0 = "01 40 87 0";
    public final static String BEST_DELAY_LEFT_50 = "01 40 87 50";
    public final static String BEST_DELAY_LEFT_100 = "01 40 87 100";
    public final static String BEST_DELAY_LEFT_150 = "01 40 87 150";
    public final static String BEST_DELAY_LEFT_200 = "01 40 87 200";
    // (王洲新增的)
    public final static String BEST_DELAY_LEFT_NEW = "01 40 87";


    //最高点时间控制
    //right:01 30 87
    public final static String BEST_DELAY_RIGHT_0 = "01 30 87 0";
    public final static String BEST_DELAY_RIGHT_50 = "01 30 87 50";
    public final static String BEST_DELAY_RIGHT_100 = "01 30 87 100";
    public final static String BEST_DELAY_RIGHT_150 = "01 30 87 150";
    public final static String BEST_DELAY_RIGHT_200 = "01 30 87 200";
    // (王洲新增的)
    public final static String BEST_DELAY_RIGHT_NEW = "01 30 87";


    //最高点时间控制
    //same:01 50 87
    public final static String BEST_DELAY_SAME_0 = "01 50 87 0";
    public final static String BEST_DELAY_SAME_50 = "01 50 87 50";
    public final static String BEST_DELAY_SAME_100 = "01 50 87 100";
    public final static String BEST_DELAY_SAME_150 = "01 50 87 150";
    public final static String BEST_DELAY_SAME_200 = "01 50 87 200";

    // (王洲新增的)
    public final static String BEST_DELAY_SAME = "01 50 87";


    //刹车力
    //left:01 40 86
    public final static String BRAKE_FORCE_LEFT_100 = "01 40 86 100";
    public final static String BRAKE_FORCE_LEFT_80 = "01 40 86 80";
    public final static String BRAKE_FORCE_LEFT_60 = "01 40 86 60";
    public final static String BRAKE_FORCE_LEFT = "01 40 86";

    //刹车力
    //right:01 30 86
    public final static String BRAKE_FORCE_RIGHT_100 = "01 30 86 100";
    public final static String BRAKE_FORCE_RIGHT_80 = "01 30 86 80";
    public final static String BRAKE_FORCE_RIGHT_60 = "01 30 86 60";
    public final static String BRAKE_FORCE_RIGHT = "01 30 86";

    //刹车力
    //same:01 50 86
    public final static String BRAKE_FORCE_SAME_100 = "01 50 86 100";
    public final static String BRAKE_FORCE_SAME_80 = "01 50 86 80";
    public final static String BRAKE_FORCE_SAME_60 = "01 50 86 60";
    // (王洲新增的)
    public final static String BRAKE_FORCE_SAME = "01 50 86";


    //刹车时间
    //left:01 40 88
    public final static String BRAKE_DURATION_LEFT = "01 40 88";

    //刹车时间
    //right:01 30 88
    public final static String BRAKE_DURATION_RIGHT = "01 30 88";

    //刹车时间
    //same:01 50 88
    public final static String BRAKE_DURATION_SAME = "01 50 88";

    //马达控制
    //left open:01 40 99
    //left close:01 40 100
    public final static String MOTOR_LEFT_OPEN = "01 40 99";
    public final static String MOTOR_LEFT_CLOSE = "01 40 100";

    //马达控制
    //right open:01 30 99
    //right close:01 30 100
    public final static String MOTOR_RIGHT_OPEN = "01 30 99";
    public final static String MOTOR_RIGHT_CLOSE = "01 30 100";

    //马达控制
    //same open:01 50 99
    //same close:01 50 100
    public final static String MOTOR_SAME_OPEN = "01 50 99";
    public final static String MOTOR_SAME_CLOSE = "01 50 100";

    //灵敏度（角度）
//left：01 40 90 数值
//right:01 30 90 数值
//same：01 50 90 数值
    public final static String ANGLE_LEFT = "01 40 90";
    public final static String ANGLE_RIGHT = "01 30 90";
    public final static String ANGLE_SAME = "01 50 90";
    //马达
//left：01 40 90 数值
//right:01 30 90 数值
//same：01 50 90 数值
    public final static String MADA_LEFT = "01 40 34";
    public final static String MADA_RIGHT = "01 30 34";
    public final static String MADA_SAME = "01 50 34";






    //    灵敏度（角速度）
//    left：01 40 89 数值
//    right:01 30 89 数值
//    同时：01 50 89 数值
    public final static String SPEED_LEFT = "01 40 89";
    public final static String SPEED_RIGHT = "01 30 89";
    public final static String SPEED_SAME = "01 50 89";


    //中风模式特有--------------------------------

    //    触发灵敏度（角度）
//    left:  01 40 16 数值
//    right：01 30 16 数值
//    同时： 01 50 16 数值
    public final static String TRIGGER_ANGLE_LEFT = "01 40 16";
    public final static String TRIGGER_ANGLE_RIGHT = "01 30 16";
    public final static String TRIGGER_ANGLE_SAME = "01 50 16";

    //    触发灵敏度（角速度）
//    left: 01 40 17 数值（-500,500）
//    right:01 30 17 数值（-500,500）
//    同时：01 50 17 数值（-500,500）
    public final static String TRIGGER_SPEED_LEFT = "01 40 17";
    public final static String TRIGGER_SPEED_RIGHT = "01 30 17";
    public final static String TRIGGER_SPEED_SAME = "01 50 17";

    //    触发时间
//    left: 01 40 85 time（0，65535）
//    right:01 30 85 time（0，65535）
//    同时：01 50 85 time（0，65535）
    public final static String TRIGGER_TIME_LEFT = "01 40 85";
    public final static String TRIGGER_TIME_RIGHT = "01 30 85";
    public final static String TRIGGER_TIME_SAME = "01 50 85";

    //    左中风切换: 01 40 109
//    右中风切换: 01 30 109
    //2月02号:客户要求左右命令交换下
    public final static String IN_WIND_LEFT = "01 30 109";
    public final static String IN_WIND_RIGHT = "01 40 109";

    //设置传感器超限幅值
//    gyro:
//    Left: 01 40 98 幅值（-300,300）
//    right: 01 30 98 幅值（-300,300）
//    accy:
//    Left: 01 40 97 幅值（-900,900)
//    right: 01 30 97 幅值（-900,900）
    public final static String GYRO_LEFT = "01 40 98";
    public final static String GYRO_RIGHT = "01 30 98";

    public final static String ACCY_LEFT = "01 40 97";
    public final static String ACCY_RIGHT = "01 30 97";

    //设置静态sensor超限幅值
//    gyro:	01 50 94 幅值（-300,300）
//    accy:	01 50 93 幅值（-900,900）
    public final static String GYRO_SENSOR = "01 50 94";
    public final static String ACCY_SENSOR = "01 50 93";

    //中风模式特有--------------------------------
    //恢复出厂设置
    public final static String RESET = "01 50 33";

    //添加结束标记
    public final static byte[] END_BYTE = {0x0d, 0x0a};

    public static byte[] getHaveEndByte(String content) {
        return getGroupByte(content.getBytes(), END_BYTE);
    }

    private static byte[] getGroupByte(byte[] data1, byte[] data2) {
        byte[] result = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, result, 0, data1.length);
        System.arraycopy(data2, 0, result, data1.length, data2.length);
        return result;
    }

    public static byte[] getStringToByte(String data1, String data2) {
        return getHaveEndByte(data1 + " " + data2);
    }
}
