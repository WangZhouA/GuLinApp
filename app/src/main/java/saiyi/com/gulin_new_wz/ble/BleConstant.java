package saiyi.com.gulin_new_wz.ble;

/**
 * Created by tt on 2017/8/15.
 * 静态变量
 */

public class BleConstant {



    public static String  CONNTION ="CONNTION";         //去连接蓝牙
    public static String  NAME ="NAME";
    public static String  SUCCED ="SUCCED";              //连接成功
    public static String  DUANKAI ="DUANKAI";          // 蓝牙断开
    public static String  LOSE ="LOSE";                // 连接失败
    public static String  WRITESUCCESS ="write_sucess";  //写入成功
    public static String  NOTIFYDATE ="notify_date";   //拿到了读取的数据


    /***********控制app命令*************/
    public static final String seviceUID = "1D5688DE-866D-3AA4-EC46-A1BDDB37ECF6";
    //通知
    public static final String NotifyUID = "AF20FBAC-2518-4998-9AF7-AF42540731B3";
    //写
    public static final String WriteUID =  "AF20FBAC-2518-4998-9AF7-AF42540731B3";


}