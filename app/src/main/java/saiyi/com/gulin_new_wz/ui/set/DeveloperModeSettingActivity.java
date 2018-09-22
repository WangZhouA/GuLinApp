package saiyi.com.gulin_new_wz.ui.set;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.ui.constant.MyConstants;

/**
 * 〈开发者模式设置页面〉
 *
 * @author Huyongqing
 * @version [版本号, 2018/1/10]
 * @since [产品/模块版本]
 */

public class DeveloperModeSettingActivity extends BaseActivity {
    /**
     * gyro左输入框
     */
    @BindView(R.id.et_gyro_left)
    EditText et_gyro_left;
    /**
     * gyro左输入范围
     */
    @BindView(R.id.tv_gyro_left_scope)
    TextView tv_gyro_left_scope;
    /**
     * gyro右输入框
     */
    @BindView(R.id.et_gyro_right)
    EditText et_gyro_right;
    /**
     * gyro右输入范围
     */
    @BindView(R.id.tv_gyro_right_scope)
    TextView tv_gyro_right_scope;
    /**
     * accy左输入框
     */
    @BindView(R.id.et_accy_left)
    EditText et_accy_left;
    /**
     * accy左输入范围
     */
    @BindView(R.id.tv_accy_left_scope)
    TextView tv_accy_left_scope;
    /**
     * accy右输入框
     */
    @BindView(R.id.et_accy_right)
    EditText et_accy_right;
    /**
     * accy右输入范围
     */
    @BindView(R.id.tv_accy_right_scope)
    TextView tv_accy_right_scope;
    /**
     * gyro输入框
     */
    @BindView(R.id.et_gyro)
    EditText et_gyro;
    /**
     * gyro输入范围
     */
    @BindView(R.id.tv_gyro_scope)
    TextView tv_gyro_scope;
    /**
     * accy输入框
     */
    @BindView(R.id.et_accy)
    EditText et_accy;
    /**
     * accy输入范围
     */
    @BindView(R.id.et_accy_scope)
    TextView et_accy_scope;
    /**
     * 确认修改按钮
     */
    @BindView(R.id.btn_confirm_change)
    Button btn_confirm_change;



    /**
     * <进入开发者设置页面>
     *
     * @param context 上下文
     */
    public static void startDeveloperModeSettingActivity(Context context) {
        Intent intent = new Intent(context, DeveloperModeSettingActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void initView() {


       setContentView( R.layout.activity_developer_mode_setting);

    }

    @Override
    protected void initData() {
        //设置标题和返回
        setTile(getResources().getString(R.string.developer_mode_setting_text));

    }


    @OnClick(R.id.btn_confirm_change)
    public void onClick() {
        //确认修改按钮
        showProgressDialog();
        sendBroadcast(new Intent(MyConstants.ACTION_GYRO_LEFT_SET).putExtra("content", et_gyro_left.getText().toString()));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(MyConstants.ACTION_GYRO_RIGHT_SET).putExtra("content", et_gyro_right.getText().toString()));
            }
        }, 300);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(MyConstants.ACTION_ACCY_LEFT_SET).putExtra("content", et_accy_left.getText().toString()));
            }
        }, 600);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(MyConstants.ACTION_ACCY_RIGHT_SET).putExtra("content", et_accy_right.getText().toString()));
            }
        }, 900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(MyConstants.ACTION_ACCY_SENSOR_SET).putExtra("content", et_accy_scope.getText().toString()));
            }
        }, 1200);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(MyConstants.ACTION_GYRO_SENSOR_SET).putExtra("content", et_gyro.getText().toString()));

            }
        }, 1500);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                dismissProgressDialog();
                finish();
            }
        }, 1500);

    }


}
