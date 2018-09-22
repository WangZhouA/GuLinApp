package saiyi.com.gulin_new_wz.http;


/**
 * Created by Administrator on 2017/6/23.
 */

public interface HttpRequestCallback {
    /**
     * 獲取數據成功
     *
     * @param sequest
     * @param type
     */
    void onResponse(String sequest, int type);
    /**
     * 獲取數據失敗
     *
     * @param exp
     */
    void onFailure(String exp);
}
