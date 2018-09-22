package saiyi.com.gulin_new_wz.ui.set;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.ui.constant.MyConstants;

/**
 *〈wifi设置页面〉
 *
 * @version [版本号, 2017/12/29]
 *
 * @author Huyongqing
 *
 * @since [产品/模块版本]
 */

public class WifiSettingActivity extends BaseActivity {
    /**wifi开关图标*/
    @BindView(R.id.iv_wifi_switch)
    ImageView iv_wifi_switch;
    /**密码显示控件*/
    @BindView(R.id.tv_password)
    TextView tv_password;

    /**wifi状态,0开，1关*/
    private int wifi_state = 0;

    /**
     * <进入wifi设置页面>
     * @param context
     */
    public static void startWifiSettingActivity(Context context) {
        Intent intent = new Intent(context, WifiSettingActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void initView() {
        //设置标题和返回


        setContentView( R.layout.activity_wifi_setting);
    }

    @Override
    protected void initData() {

        setTile(getResources().getString(R.string.wifi_setting_text));

    }


    @OnClick({R.id.btn_wifi_switch, R.id.btn_change_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_wifi_switch://wifi开关按钮
                if (wifi_state == 0) {
                    wifi_state = 1;
                    iv_wifi_switch.setBackgroundResource(R.mipmap.on);
                    sendBroadcast(new Intent(MyConstants.ACTION_WIFI_OPEN));
                } else if (wifi_state == 1) {
                    wifi_state = 0;
                    iv_wifi_switch.setBackgroundResource(R.mipmap.off);
                    sendBroadcast(new Intent(MyConstants.ACTION_WIFI_CLOSE));
                }

                break;

            case R.id.btn_change_password://修改wifi密码按钮
                //显示进入修改wifi密码弹窗
                if (wifi_state ==0){
                    Toast.makeText(this,getString(R.string.please_open_wifi),Toast.LENGTH_SHORT).show();
                }else{
                    showChangePwdDialog();
                }
                break;
        }

    }

    /**
     * <显示修改wifi弹窗>
     */
    private void showChangePwdDialog() {
        final Dialog mDialog = new Dialog(WifiSettingActivity.this, R.style.dialog_style_in_middle);
        mDialog.show();
        Window window = mDialog.getWindow();
        window.setContentView(R.layout.dialog_change_wifi_pwd);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        //密码输入框
        final EditText et_password = (EditText) window.findViewById(R.id.et_password);
        //取消按钮
        TextView btn_cancel = (TextView) window.findViewById(R.id.btn_cancel);
        //确认按钮
        TextView btn_confirm = (TextView) window.findViewById(R.id.btn_confirm);

        //设置取消按钮的点击事件
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        //设置确认按钮的点击事件
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入的密码
                String password = et_password.getText().toString().trim();
                //如果为输入密码
                if (TextUtils.isEmpty(password)) {
//                    ToastUtil.showShort(mContext, R.string.input_password_text);
                    toast(getResources().getString(R.string.input_password_text));
                    return;
                }

                if ("PAM01".equals(password)) {
                    //进入修改密码页面
                    WifiPasswordChangeActivity.startWifiPasswordChangeActivity(WifiSettingActivity.this);
                } else {
                    toast(getResources().getString(R.string.password_error_text));
                }

                mDialog.dismiss();

            }
        });

    }


}
