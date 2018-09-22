package saiyi.com.gulin_new_wz.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.MainActivity;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.ble.MacIdEntity;
import saiyi.com.gulin_new_wz.service.MyService;
import saiyi.com.gulin_new_wz.ui.Evaluation.EvaluationActivity;
import saiyi.com.gulin_new_wz.ui.communication_data.DataHomeActivity;
import saiyi.com.gulin_new_wz.ui.communication_data.DialogListener;
import saiyi.com.gulin_new_wz.ui.register.EgistrationActivity;
import saiyi.com.gulin_new_wz.ui.set.SettingActivity;
import saiyi.com.gulin_new_wz.ui.study.StudyActivity;
import saiyi.com.gulin_new_wz.utils.AppManager;
import saiyi.com.gulin_new_wz.utils.DialogUtils;
import saiyi.com.gulin_new_wz.utils.SPUtils;

import static saiyi.com.gulin_new_wz.utils.Constants.APPMODE;
import static saiyi.com.gulin_new_wz.utils.Constants.LEGS_MODLE;

/**
 * Created by 陈姣姣 on 2018/8/16.
 */
public class FirstActivity extends BaseActivity {
    @BindView(R.id.tv_mode_name)
    TextView tvModeName;
    @BindView(R.id.btn_toggle)
    TextView btnToggle;
    @BindView(R.id.tv_electricity_percent)
    TextView tvElectricityPercent;
    @BindView(R.id.iv_electricity)
    ImageView ivElectricity;
    @BindView(R.id.iv_charging)
    ImageView ivCharging;
    @BindView(R.id.rl_egistration)
    RelativeLayout rlEgistration;
    @BindView(R.id.rl_learning)
    RelativeLayout rlLearning;
    @BindView(R.id.rl_training)
    RelativeLayout rlTraining;
    @BindView(R.id.rl_assessment)
    RelativeLayout rlAssessment;
    @BindView(R.id.rl_data)
    RelativeLayout rlData;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;
    Intent intentService;

    @BindView(R.id.lin_bar)
    RelativeLayout linBar;
    @Override
    protected void initView() {

        setContentView(R.layout.activity_first);
    }

   @Override
    protected void initData() {


        linBar.setVisibility(View.GONE);
        SPUtils.putInt(this,APPMODE,LEGS_MODLE);
        intentService =new Intent(this, MyService.class);
        startService(intentService);

    }


    @Override
    protected void onResume() {
        super.onResume();

   }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intentService);


    }


    @OnClick({R.id.btn_toggle, R.id.rl_egistration, R.id.rl_learning, R.id.rl_training, R.id.rl_assessment, R.id.rl_data, R.id.rl_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.rl_egistration:



                    change(EgistrationActivity.class,FirstActivity.this,new Intent(),false);



                break;
            case R.id.rl_learning:

                if (!TextUtils.isEmpty(MacIdEntity.getMacId())){
                    change(StudyActivity.class,FirstActivity.this,new Intent(),false);

                }else {

                    Toast.makeText(FirstActivity.this,getString(R.string.connect_device_please),Toast.LENGTH_SHORT).show();

                }


                break;
            case R.id.rl_training:
                change(MainActivity.class,FirstActivity.this,new Intent(),false);
                break;
            case R.id.rl_assessment:

                if (!TextUtils.isEmpty(MacIdEntity.getMacId())){
                    change(EvaluationActivity.class,FirstActivity.this,new Intent(),false);
                }else {

                    Toast.makeText(FirstActivity.this,getString(R.string.connect_device_please),Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.rl_data:

                if (!TextUtils.isEmpty(MacIdEntity.getMacId())){


                    change(DataHomeActivity.class,FirstActivity.this,new Intent(),false);
                }else {

                    Toast.makeText(FirstActivity.this,getString(R.string.connect_device_please),Toast.LENGTH_SHORT).show();

                }



                break;
            case R.id.rl_setting:
                if (!TextUtils.isEmpty(MacIdEntity.getMacId())){

                    change(SettingActivity.class,FirstActivity.this,new Intent(),false);
                }else {

                    Toast.makeText(FirstActivity.this,getString(R.string.connect_device_please),Toast.LENGTH_SHORT).show();

                }
//              change(SettingActivity.class,FirstActivity.this,new Intent(),false);
                break;
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        //按下返回键
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            //退出
//            exit();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    /**
     * 退出app
     */
    public void exit() {

        if (MacIdEntity.getMacId() == null || MacIdEntity.getMacId().isEmpty()) {
            AppManager.getInstance().closeAllActivity();
            return;
        }

        DialogUtils.showIsOkDialog(this, getString(R.string.save_text),
                getString(R.string.cancel_text), getString(R.string.whether_to_save_user_data),
                "", new DialogListener() {
                    @Override
                    public void onComplete() {
//                        mSmartCotrolFragment.clickSaveInfo();
                        //保存
//                        AppManager.getInstance().closeAllActivity();

                        System.exit(0);

                    }

                    @Override
                    public void onFail() {
                        //取消
                        AppManager.getInstance().closeAllActivity();
                    }
                });
    }

}
