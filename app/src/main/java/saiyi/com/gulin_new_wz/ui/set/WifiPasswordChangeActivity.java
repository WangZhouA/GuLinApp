package saiyi.com.gulin_new_wz.ui.set;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;

/**
 * 〈功能详细描述〉
 *
 * @author Huyongqing
 * @version [版本号, 2017/12/29]
 * @since [产品/模块版本]
 */

public class WifiPasswordChangeActivity extends BaseActivity {
    /**
     * 旧密码输入框
     */
    @BindView(R.id.et_old_pwd)
    EditText et_old_pwd;
    /**
     * 新密码输入框
     */
    @BindView(R.id.et_new_pwd)
    EditText et_new_pwd;
    /**
     * 确认密码输入框
     */
    @BindView(R.id.et_confirm_pwd)
    EditText et_confirm_pwd;

    /**
     * 旧密码
     */
    private String pwd_old = "";
    /**
     * 新密码
     */
    private String pwd_new = "";
    /**
     * 确认密码
     */
    private String pwd_confirm = "";
    private WifiManager mWifiManager;

    private static final int WIFICIPHER_NOPASS = 0;
    private static final int WIFICIPHER_WEP = 1;
    private static final int WIFICIPHER_WPA = 2;

    private static final int MEG_PROGRESS_SHOW = 0;
    private static final int MEG_PROGRESS_DISMISS = 1;
    private static final int MEG_START_SCAN = 10;
    private static final int MEG_FINISH = 100;
    private static final int MEG_RESULT = 1001;
    private static final int MEG_CONNECT_TCP = 1000;
    private static final int MEG_TCP_EXCEPTION = 1002;

    private boolean isScanOvertime = true;
    private boolean isScan = true;

    private Socket mSocket;
    private PrintStream mOutput;

    /**
     * <进入修改wifi密码页面>
     *
     * @param context
     */
    public static void startWifiPasswordChangeActivity(Context context) {
        Intent intent = new Intent(context, WifiPasswordChangeActivity.class);
        context.startActivity(intent);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MEG_PROGRESS_SHOW:
                    showProgressDialog();
                    break;
                case MEG_PROGRESS_DISMISS:
                    dismissProgressDialog();
                    break;
                case MEG_START_SCAN:
                    startConnectAP();
                    break;
                case MEG_FINISH:
                    finish();
                    break;
                case MEG_CONNECT_TCP:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            initClientReceive();
                        }
                    }).start();
                    break;
                case MEG_RESULT:
                    dismissProgressDialog();
                    String obj = (String) msg.obj;
                    if (obj.contains("PWDOK")){
                        Toast.makeText(WifiPasswordChangeActivity.this, getString(R.string.failure_to_sucess), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(WifiPasswordChangeActivity.this, getString(R.string.failure_to_modify), Toast.LENGTH_LONG).show();
                    }
                    finish();
                    break;
                case MEG_TCP_EXCEPTION:
                    dismissProgressDialog();
                    Toast.makeText(WifiPasswordChangeActivity.this,getString(R.string.failure_to_modify),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void initView() {

        setContentView( R.layout.activity_wifi_password_change);
    }

    @Override
    protected void initData() {
        //设置标题和返回
        setTile(getResources().getString(R.string.change_pwd_text));
        mHandler.sendEmptyMessage(MEG_PROGRESS_SHOW);
        startOpenWifi();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isScanOvertime = false;
            }
        }, 10000);

    }

    private void startOpenWifi() {
        mWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
            mHandler.sendEmptyMessage(MEG_START_SCAN);
        } else {
            mHandler.sendEmptyMessage(MEG_START_SCAN);
        }
    }

    public void startConnectAP() {
        if (!isScanOvertime) {
            Toast.makeText(WifiPasswordChangeActivity.this, getString(R.string.wifi_scan_overtime), Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessage(MEG_PROGRESS_DISMISS);
            mHandler.sendEmptyMessage(MEG_FINISH);
            return;
        }
        if (!isScan) {
            return;
        }
        mWifiManager.startScan();
        List<ScanResult> scanResults = mWifiManager.getScanResults();
        if (scanResults.isEmpty()) {
            mHandler.sendEmptyMessageDelayed(MEG_START_SCAN, 1000);
            return;
        }
        for (ScanResult result: scanResults) {
            if (result.SSID .contains("PAM_")){
                int netId = mWifiManager.addNetwork(createWifiConfig(result.SSID, "12345678", WIFICIPHER_WPA));
                if(mWifiManager.enableNetwork(netId, true)){
                    Toast.makeText(this,getString(R.string.wifi_connect_success),Toast.LENGTH_SHORT).show();
                    mHandler.sendEmptyMessage(MEG_PROGRESS_DISMISS);
                    isScan = false;
                }else{
                    Toast.makeText(this,getString(R.string.wifi_connect_fail),Toast.LENGTH_SHORT).show();
                    mHandler.sendEmptyMessage(MEG_PROGRESS_DISMISS);
                    mHandler.sendEmptyMessage(MEG_FINISH);
                    isScan = false;
                }
                return;
            }
            mHandler.sendEmptyMessage(MEG_START_SCAN);
        }
    }

    private WifiConfiguration createWifiConfig(String ssid, String password, int type) {
        //初始化WifiConfiguration
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        //指定对应的SSID
        config.SSID = "\"" + ssid + "\"";
        //如果之前有类似的配置
        WifiConfiguration tempConfig = isExist(ssid);
        if (tempConfig != null) {
            //则清除旧有配置
            mWifiManager.removeNetwork(tempConfig.networkId);
        }
        //不需要密码的场景
        if (type == WIFICIPHER_NOPASS) {
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            //以WEP加密的场景
        } else if (type == WIFICIPHER_WEP) {
            config.hiddenSSID = true;
            config.wepKeys[0] = "\"" + password + "\"";
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
            //以WPA加密的场景，自己测试时，发现热点以WPA2建立时，同样可以用这种配置连接
        } else if (type == WIFICIPHER_WPA) {
            config.preSharedKey = "\"" + password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
        }
        return config;
    }

    private WifiConfiguration isExist(String ssid) {
        List<WifiConfiguration> configs = mWifiManager.getConfiguredNetworks();
        for (WifiConfiguration config : configs) {
            if (config.SSID.equals("\"" + ssid + "\"")) {
                return config;
            }
        }
        return null;
    }


    @OnClick(R.id.btn_confirm_change)
    public void onClick() {
        //获取输入的旧密码
        pwd_old = et_old_pwd.getText().toString().trim();
        //如果未输入旧密码
        if (TextUtils.isEmpty(pwd_old)) {
            toast(getResources().getString(R.string.input_old_pwd_text));
            return;
        }

        //获取新密码
        pwd_new = et_new_pwd.getText().toString().trim();
        //如果未输入新密码
        if (TextUtils.isEmpty(pwd_new)) {
            toast(getResources().getString(R.string.input_new_pwd_text));
            return;
        }

        //获取确认密码
        pwd_confirm = et_confirm_pwd.getText().toString().trim();
        //如果未输入确认密码
        if (TextUtils.isEmpty(pwd_confirm)) {
            toast(getResources().getString(R.string.input_confirm_pwd_text));
            return;
        }

        //两次输入的新密码不一致
        if (!pwd_new.equals(pwd_confirm)) {
            toast(getResources().getString(R.string.input_is_not_consistent_text));
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                initClientSocket();
            }
        }).start();
        showProgressDialog();
    }

    private void initClientReceive() {

        InputStream inputStream = null;
        try {
            inputStream = mSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                Message message = new Message();
                message.what = MEG_RESULT;
                message.obj = s;
                mHandler.sendMessage(message);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void initClientSocket() {
        try {
            mSocket = new Socket("192.168.0.108", 10086);
            mOutput = new PrintStream(mSocket.getOutputStream(), true, "gbk");
            String passwordChange = "PWD " + et_old_pwd.getText().toString() + "," + et_new_pwd.getText().toString();
            mOutput.write(passwordChange.getBytes());
            mOutput.flush();
            mHandler.sendEmptyMessage(MEG_CONNECT_TCP);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            mHandler.sendEmptyMessage(MEG_TCP_EXCEPTION);
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            mHandler.sendEmptyMessage(MEG_TCP_EXCEPTION);
            e.printStackTrace();
        }
    }
}
