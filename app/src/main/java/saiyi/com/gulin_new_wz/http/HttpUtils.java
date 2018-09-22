package saiyi.com.gulin_new_wz.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import saiyi.com.gulin_new_wz.constaint.MyConstaints;
import saiyi.com.gulin_new_wz.utils.MyLog;
import saiyi.com.gulin_new_wz.utils.SPUtils;

/**
 * Created by Administrator on 2017/6/23.
 */

public class HttpUtils {


    public static String URL1 = StringUtils.HTTP_SERVICE;//阿里云服务器
    private static OkHttpClient mOkHttpClient;

    private static HttpUtils instance;
    private Context context;

    public HttpUtils(Context context){
        mOkHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(5000, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(5000, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(5000, TimeUnit.SECONDS)//设置连接超时时间
                .build();

        this.context = context;

    }

    public static synchronized HttpUtils getInstance(Context context){
        if (instance == null){
            instance = new HttpUtils(context);
        }
        return instance;
    }


    public  void postJsonHttp(final Context context, String action, String json, final int HttpType, final HttpRequestCallback requestCallback) {
        if (CheckConnect()) {
            requestCallback.onFailure("暂无网络连接，请连接网络!");
            return;
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);

        mOkHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(20000, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(20000, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(20000, TimeUnit.SECONDS)//设置连接超时时间
                .build();
        final Request request = new Request.Builder()
                .url(URL1 + action)
                .post(body)
                .build();
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String exceptionCode = e.getLocalizedMessage();
                MyLog.e(exceptionCode);
                if ("java.net.SocketTimeoutException".equals(exceptionCode)) {
                    requestCallback.onFailure("数据获取超时");

                } else if (exceptionCode.contains("http.conn.ConnectTimeoutException")) {
                    requestCallback.onFailure("网络连接超时");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                MyLog.e(str);
                try {
                    JSONObject object = new JSONObject(str);
                    if (object.getBoolean("success")){
                        requestCallback.onResponse(str, HttpType);
                    }else{
                        requestCallback.onFailure(object.getString("message"));
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    requestCallback.onFailure("数据解析异常!");
                } catch (JSONException e) {
                    requestCallback.onFailure("数据异常!");
                    e.printStackTrace();
                }
            }
        });
    }



    /**
     * 获取数据接口
     *
     * @param action          服务器方法
     * @param HttpType        接口类型
     * @param requestCallback 数据回调接口
     *                        1
     */
    public void postAsynHttp(String action, Map<String, String> data, final int HttpType, final HttpRequestCallback requestCallback) {
        if (CheckConnect()) {
            requestCallback.onFailure("暂无网络连接，请连接网络!");
            return;
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(data));
        MyLog.e(new Gson().toJson(data).toString());

        final Request request = new Request.Builder()
                .url(URL1 + action)
                .post(body)
                .addHeader("token", saiyi.com.gulin_new_wz.utils.SPUtils.getString(context, MyConstaints.TOKEN, ""))
                .build();
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String exceptionCode = e.getLocalizedMessage();
                MyLog.e(exceptionCode);
                if ("java.net.SocketTimeoutException".equals(exceptionCode)) {
                    requestCallback.onFailure("数据获取超时");

                } else if (exceptionCode.contains("http.conn.ConnectTimeoutException")) {
                    requestCallback.onFailure("网络连接超时");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                MyLog.e(str);
                try {
                    JSONObject object = new JSONObject(str);
                    if (object.getString("success").equals("true")) {
                        requestCallback.onResponse(str, HttpType);
                    } else {
                        requestCallback.onFailure(object.getString("message"));
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    requestCallback.onFailure("数据解析异常!");
                } catch (JSONException e) {
                    requestCallback.onFailure("数据异常!");
                    e.printStackTrace();
                }
            }
        });
    }
// HttpUtils.getInstance(getActivity()).upDateByStr(NETWORK_STEP, "Sportwtach/app/data/addSportData", parameter, this);
    public void upDateByStr(final int type,String url,String parms,final HttpRequestCallback requestCallback){
        if (CheckConnect()) {
            requestCallback.onFailure("暂无网络连接，请连接网络!");
            return;
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, parms);
        Log.e("url","url:" + URL1 + url);
        Log.e("url","参数:" +parms);
        final Request request = new Request.Builder()
                .url(URL1 + url)
                .post(requestBody)
                .addHeader("token",SPUtils.getString(context, MyConstaints.TOKEN, ""))
                .build();
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String exceptionCode = e.getLocalizedMessage();
                MyLog.e(exceptionCode);
                if ("java.net.SocketTimeoutException".equals(exceptionCode)) {
                    requestCallback.onFailure("数据获取超时");

                } else if (exceptionCode.contains("http.conn.ConnectTimeoutException")) {
                    requestCallback.onFailure("网络连接超时");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                MyLog.e(str);
                try {
                    JSONObject object = new JSONObject(str);
                    if (object.getString("result").equals("1")) {
                        requestCallback.onResponse(str, type);
                    } else {
                        requestCallback.onFailure(object.getString("message"));
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    requestCallback.onFailure("数据解析异常!");
                } catch (JSONException e) {
                    requestCallback.onFailure("数据异常!");
                    e.printStackTrace();
                }
            }
        });
    }

// HttpUtils.getInstance(getActivity()).getSyncHttp(NETWORK_PM, "http://www.pm25.in/api/querys/pm2_5.json?city=" + cityCode + "&token=5j1znBVAsnSf5xQyNQyq", this);
    public void getSyncHttp(final int HttpType, String url, final HttpRequestCallback requestCallback) {
        if (CheckConnect()) {
            requestCallback.onFailure("暂无网络连接，请连接网络!");
            return;
        }

        Request request = new Request
                .Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String exceptionCode = e.getLocalizedMessage();
                MyLog.e(exceptionCode);
                if ("java.net.SocketTimeoutException".equals(exceptionCode)) {
                    requestCallback.onFailure("数据获取超时");

                } else if (exceptionCode.contains("http.conn.ConnectTimeoutException")) {
                    requestCallback.onFailure("网络连接超时");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                MyLog.e(str);
                try {
                    JSONObject object = new JSONObject(str);
                    if (object.getString("code").equals("1000")) {
                        requestCallback.onResponse(str, HttpType);
                    } else {
                        requestCallback.onFailure(object.getString("message"));
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    requestCallback.onFailure("数据解析异常!");
                } catch (JSONException e) {
                    requestCallback.onFailure("数据异常!");
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @param action          服務器方法
     * @param HttpType        接口類型
     * @param requestCallback 回調接口
     */
    public void postAsynHttp_File(String path, String action, final HttpRequestCallback requestCallback, final int HttpType) {
        CheckConnect();
        mOkHttpClient = new OkHttpClient();
        // mImgUrls为存放图片的url集合
        File file = new File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .build();
        Request request = new Request.Builder()
                .url(URL1 + action)
                .post(requestBody)
                    .addHeader("token", SPUtils.getString(context, MyConstaints.TOKEN, ""))
                .build();
        Log.e("url", "url:" + URL1 + action);
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String exceptionCode = e.getLocalizedMessage();
                if ("java.net.SocetTimeoutException".equals(exceptionCode)) {
                    Toast.makeText(context, "数据获取超时", Toast.LENGTH_SHORT).show();
                } else if (exceptionCode
                        .contains("http.conn.ConnectTimeoutException")) {
                    Toast.makeText(context, "网络连接超时", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                MyLog.e(str);
                try {
                    JSONObject object = new JSONObject(str);
                    if (object.getString("result").equals("1")) {
                        requestCallback.onResponse(str, HttpType);
                    } else {
                        requestCallback.onFailure(object.getString("msg"));
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    requestCallback.onFailure("数据解析异常!");
                } catch (JSONException e) {
                    requestCallback.onFailure("数据异常!");
                    e.printStackTrace();
                }
            }
        });
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    private boolean CheckConnect() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info == null;
    }
}
