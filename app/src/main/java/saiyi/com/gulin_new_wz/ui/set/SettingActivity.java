package saiyi.com.gulin_new_wz.ui.set;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.ble.MacIdEntity;
import saiyi.com.gulin_new_wz.ui.communication_data.DialogListener;
import saiyi.com.gulin_new_wz.ui.constant.MyConstants;
import saiyi.com.gulin_new_wz.utils.DialogUtils;

/**
 * Created by 陈姣姣 on 2018/9/6.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener{


    @Override
    protected void initView() {

        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initData() {

        showNormeBar();
        setTile(getResources().getString(R.string.setting_text));


    }


    @OnClick({ R.id.btn_developer_mode, R.id.btn_wifi_setting,
            R.id.btn_language_settings, R.id.btn_about, R.id.btn_factory_reset})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_developer_mode://开发者模式
                //进入开发者模式页面
                if (MacIdEntity.getMacId()==null||MacIdEntity.getMacId().isEmpty()){
                    Toast.makeText(SettingActivity.this,getString(R.string.connect_device_please),Toast.LENGTH_SHORT).show();
                    return;
                }
                DeveloperModeActivity.startDeveloperModeActivity(SettingActivity.this);

                break;

            case R.id.btn_wifi_setting://wifi设置
                if (MacIdEntity.getMacId()==null||MacIdEntity.getMacId().isEmpty()){
                    Toast.makeText(SettingActivity.this,getString(R.string.connect_device_please),Toast.LENGTH_SHORT).show();
                    return;
                }
                //进入wifi设置页面
                WifiSettingActivity.startWifiSettingActivity(SettingActivity.this);

                break;

            case R.id.btn_language_settings://语言设置
                //进入语言选择页面
                LanguageSettingsActivity.startLanguageSettingsActivity(SettingActivity.this);
                break;

            case R.id.btn_about://关于我们
                //进入关于我们页面
                AboutActivity.startAboutActivity(SettingActivity.this);
                break;

            case R.id.btn_factory_reset://恢复出厂设置
                if (MacIdEntity.getMacId()==null||MacIdEntity.getMacId().isEmpty()){
                    Toast.makeText(SettingActivity.this,getString(R.string.connect_device_please),Toast.LENGTH_SHORT).show();
                    return;
                }
                //恢复出厂设置弹窗
                DialogUtils.showIsOkDialog(SettingActivity.this, getString(R.string.sure_text), getString(R.string.cancel_text), getString(R.string.factory_reset_tips_text), new DialogListener() {
                    @Override
                    public void onComplete() {
                        //确定
                        if (MacIdEntity.getMacId()==null||MacIdEntity.getMacId().isEmpty()){
                            Toast.makeText(SettingActivity.this,getString(R.string.connect_device_please),Toast.LENGTH_SHORT).show();
                        }else{
                           sendBroadcast(new Intent(MyConstants.ACTION_RESTORE));
                        }
                    }

                    @Override
                    public void onFail() {
                        //取消
                    }
                });

                break;
        }
    }
}
