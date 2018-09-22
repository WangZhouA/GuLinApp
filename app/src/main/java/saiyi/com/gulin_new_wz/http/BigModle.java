package saiyi.com.gulin_new_wz.http;

import android.content.Context;

import java.util.Map;


/**
 * @author Nicholas.Huang
 * @Declaration:
 * @Email: kurode@sina.cn
 * <p>
 * 17-7-13 上午9:44
 **/
public class BigModle extends Httpabstract {
    HttpRequestCallback call;
    private static BigModle instance;
    private Context context;


    public BigModle(Context context){
        this.context = context;
    }


    public static synchronized BigModle getInstance(Context context){
        if (instance == null){
            instance = new BigModle(context);
        }
        return instance;
    }


    /**
     * 获取数据
     */
    public void getData(Context ext, Map<String, String> data, int type, HttpRequestCallback call, String url) {
        Map<String, String> datas = data;
//        String token = SPUtils.getString(ext, MyConstaints.TOKEN, "");
//        if (!TextUtils.isEmpty(token)) {
//            datas.put("token", token);
//        }
//        String userId = SPUtils.getString(ext, MyConstaints.USER_ID, "");
//        if (!TextUtils.isEmpty(userId)){
//            datas.put("userId",userId);
//        }
        this.call = call;
        HttpUtils.getInstance(ext).postAsynHttp(url, data, type, callback);
    }



    /**
     * 提交文件
     */
    public void putData_File(Context ext,String path, int type, HttpRequestCallback call, String url) {
        this.call = call;
        HttpUtils.getInstance(ext).postAsynHttp_File(path, url, callback, type);
    }


    @Override
    public void onResponses(String sequest, int type) {
        call.onResponse(sequest, type);
    }

    @Override
    public void onFailures(String exp) {
        call.onFailure(exp);
    }
}
