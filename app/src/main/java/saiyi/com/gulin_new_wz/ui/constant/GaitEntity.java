package saiyi.com.gulin_new_wz.ui.constant;

/**
 * Created by Ligs on 2018-01-29.
 */

public class GaitEntity {
    public static final String ANGLE_LEFT = "01 40 11";
    public static final String ANGLE_RIGHT = "01 30 11";
    public static final String VELOCITY_LEFT = "01 40 12";
    public static final String VELOCITY_RIGHT = "01 30 12";
    public static final String CURRENT_RIGHT = "01 30 13";
    public static final String CURRENT_LEFT = "01 40 13";
    public static final String BAROMETRIC_SENSOR = "01 40 14";
    public static final String SENSOR = "01 40 15";

    public static final String ANGLE_LEFT_FLAGE = "*AL";
    public static final String ANGLE_RIGHT_FLAGE = "*AR";
    public static final String VELOCITY_LEFT_FLAGE = "*GL";
    public static final String VELOCITY_RIGHT_FLAGE = "*GR";
    public static final String CURRENT_RIGHT_FLAGE = "*CCR";
    public static final String CURRENT_LEFT_FLAGE = "*CCL";
    public static final String BAROMETRIC_SENSOR_FLAGE = "*UIA";
    public static final String SENSOR_FLAGE = "*PR";

    //    灵敏度（角度）
    public final static int MAX_ANGLE = 90;//    灵敏度（角度）
    public final static int LEAST_ANGLE = -90;
    //    灵敏度（角速度）
    public final static int MAX_SPEED = 500;
    public final static int LEAST_SPEED = -500;

}
