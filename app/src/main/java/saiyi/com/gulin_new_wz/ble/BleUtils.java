package saiyi.com.gulin_new_wz.ble;

import java.util.List;

/**
 * Created by 陈姣姣 on 2018/7/31.
 */
public class BleUtils {

    private   static  BleUtils bleUtils ;

    public  static   BleUtils getIntent(){

        if (bleUtils==null){
            bleUtils= new  BleUtils();

        }

        return  bleUtils;
    }


    /**
     * 判断是否已经有了
     */
    public boolean isContains(List<BleBean> beanList, BleBean bleBean) {
        /*boolean isContains = false;
        for (int i = 0; i < beanList.size(); i++) {
            if (beanList.get(i).equals(bleBean)) {
                isContains = true;
                break;
            }
        }
        beanList.contains()*/
        if(beanList == null || beanList.isEmpty() || bleBean == null)return false;
        return beanList.contains(bleBean);
    }

}
