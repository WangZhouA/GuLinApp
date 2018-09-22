package saiyi.com.gulin_new_wz.ble;

/**
 * Created by 陈姣姣 on 2018/9/15.
 */
public interface IBleStateListener {

     void setSuccess(int position,String mac);  // 连接成功
     void setFail(int position);     //连接失败

}
