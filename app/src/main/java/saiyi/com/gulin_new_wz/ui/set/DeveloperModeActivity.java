package saiyi.com.gulin_new_wz.ui.set;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.ble.MacIdEntity;
import saiyi.com.gulin_new_wz.ui.constant.MyConstants;

/**
 * 〈开发者模式页面〉
 *
 * @author Huyongqing
 * @version [版本号, 2017/12/28]
 * @since [产品/模块版本]
 */

public class DeveloperModeActivity extends BaseActivity implements View.OnClickListener{


   @BindView(R.id.header_right_msg)
   TextView tvRight;

    /**
     * gyro左值
     */
    @BindView(R.id.tv_gyro_left)
    TextView tv_gyro_left;
    /**
     * gyro右值
     */
    @BindView(R.id.tv_gyro_right)
    TextView tv_gyro_right;
    /**
     * accy左值
     */
    @BindView(R.id.tv_accy_left)
    TextView tv_accy_left;
    /**
     * accy右值
     */
    @BindView(R.id.tv_accy_right)
    TextView tv_accy_right;
    /**
     * gyro值
     */
    @BindView(R.id.tv_gyro)
    TextView tv_gyro;
    /**
     * accy值
     */
    @BindView(R.id.tv_accy)
    TextView tv_accy;


    @Override
    protected void onStart() {
        super.onStart();
        sendBroadcast(new Intent(MyConstants.ACTION_GYRO_LEFT));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(MyConstants.ACTION_GYRO_RIGHT));
            }
        }, 300);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(MyConstants.ACTION_ACCY_LEFT));
            }
        }, 600);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(MyConstants.ACTION_ACCY_RIGHT));
            }
        }, 900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(MyConstants.ACTION_GYRO_SENSOR));
            }
        }, 1200);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(MyConstants.ACTION_ACCY_SENSOR));
            }
        }, 1500);

    }



    public static final String ACTION_GYRO_LEFT = "saiyi.developer.gyro.left";
    public static final String ACTION_GYRO_RIGHT = "saiyi.developer.gyro.right";
    public static final String ACTION_ACCY_LEFT = "saiyi.developer.accy.left";
    public static final String ACTION_ACCY_RIGHT = "saiyi.developer.accy.right";
    public static final String ACTION_GYRO_SENSOR = "saiyi.developer.gyro.sensor";
    public static final String ACTION_ACCY_SENSOR = "saiyi.developer.accy.sensor";

    public static final String INTENT_FLAGE = "Instructions";
    public static final String ACTION_INSTRUCTIONS = "saiyi.developer.instructions";

    /**
     * <进入开发者模式页面>
     *
     * @param context
     */
    public static void startDeveloperModeActivity(Context context) {
        Intent intent = new Intent(context, DeveloperModeActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_developer_mode);
    }

    @Override
    protected void initData() {

        showNormeBar();
        setTile(getResources().getString(R.string.developer_mode_text));
        tvRight.setText(getResources().getString(R.string.setting_text));
        tvRight.setVisibility(View.VISIBLE);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_GYRO_LEFT);
        intentFilter.addAction(ACTION_GYRO_RIGHT);
        intentFilter.addAction(ACTION_ACCY_LEFT);
        intentFilter.addAction(ACTION_ACCY_RIGHT);
        intentFilter.addAction(ACTION_GYRO_SENSOR);
        intentFilter.addAction(ACTION_ACCY_SENSOR);
        intentFilter.addAction(ACTION_INSTRUCTIONS);
        registerReceiver(mReceiver, intentFilter);



    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String content = intent.getStringExtra("content");
            switch (intent.getAction()) {
                case ACTION_ACCY_LEFT:
                    tv_accy_left.setText(content.substring(9, content.length()));
                    break;
                case ACTION_ACCY_RIGHT:
                    tv_accy_right.setText(content.substring(9, content.length()));
                    break;
                case ACTION_ACCY_SENSOR:
                    tv_accy.setText(content.substring(9, content.length()));
                    break;
                case ACTION_GYRO_LEFT:
                    tv_gyro_left.setText(content.substring(9, content.length()));
                    break;
                case ACTION_GYRO_RIGHT:
                    tv_gyro_right.setText(content.substring(9, content.length()));
                    break;
                case ACTION_GYRO_SENSOR:
                    tv_gyro.setText(content.substring(9, content.length()));
                    break;

            }
        }
    };

    @OnClick({R.id.header_right_msg})
    @Override
    public void onClick(View view) {

       switch (view.getId()){

           case R.id.header_right_msg:

               if (MacIdEntity.mIsSign) {
                   DeveloperModeSettingActivity.startDeveloperModeSettingActivity(DeveloperModeActivity.this);
                   return;
               }
               showSettingDialog();

               break;

       }

    }

    /**
     * <显示设置弹窗>
     */
    private void showSettingDialog() {
        final Dialog mDialog = new Dialog(DeveloperModeActivity.this, R.style.dialog_style_in_middle);
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
                    toast(getResources().getString(R.string.input_password_text));
                    return;
                }
                //如果密码正确
                if ("PAM01".equals(password)) {
                    //进入开发者设置页面
                    MacIdEntity.mIsSign = true;
                    DeveloperModeSettingActivity.startDeveloperModeSettingActivity(DeveloperModeActivity.this);
                } else {

                    toast(getResources().getString(R.string.password_error_text));
                }
                mDialog.dismiss();
            }
        });
    }
}
