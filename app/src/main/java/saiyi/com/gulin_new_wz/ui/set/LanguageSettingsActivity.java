package saiyi.com.gulin_new_wz.ui.set;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.finddreams.languagelib.LanguageType;
import com.finddreams.languagelib.MultiLanguageUtil;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.ui.FirstActivity;

/**
 *〈语言设置页面〉
 *
 * @version [版本号, 2017/12/28]
 *
 * @author Huyongqing
 *
 * @since [产品/模块版本]
 */

public class LanguageSettingsActivity extends BaseActivity {
    /**简体中文选中图标*/
    @BindView(R.id.iv_simplified_chinese)
    ImageView iv_simplified_chinese;
    /**英文选中图标*/
    @BindView(R.id.iv_english)
    ImageView iv_english;
    /**日语选中图标*/
    @BindView(R.id.iv_japanese)
    ImageView iv_japanese;

    /**
     * <进入语言选择页面>
     * @param context
     */

    private int savedLanguageType;
    public static void startLanguageSettingsActivity(Context context) {
        Intent intent = new Intent(context, LanguageSettingsActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void initView() {

        setContentView(R.layout.activity_language_settings);

    }

    @Override
    protected void initData() {
        //设置标题和返回
        setTile(getResources().getString(R.string.language_settings_text));
        //获取语言code


        savedLanguageType = MultiLanguageUtil.getInstance().getLanguageType();
       if (savedLanguageType == LanguageType.LANGUAGE_EN) {
            //设置简体勾选框隐藏
            iv_simplified_chinese.setVisibility(View.GONE);
            //日文勾选框隐藏
            iv_japanese.setVisibility(View.GONE);
            //英文勾选框显示
            iv_english.setVisibility(View.VISIBLE);
        } else if (savedLanguageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
            //设置简体勾选框显示
            iv_simplified_chinese.setVisibility(View.VISIBLE);
            //日文勾选框隐藏
            iv_japanese.setVisibility(View.GONE);
            //英文勾选框隐藏
            iv_english.setVisibility(View.GONE);
        } else{
           iv_simplified_chinese.setVisibility(View.GONE);
           //日文勾选框显示
           iv_japanese.setVisibility(View.VISIBLE);
           //英文勾选框隐藏
           iv_english.setVisibility(View.GONE);
        }
    }

    int selectedLanguage = 0;
    @OnClick({R.id.btn_simplified_chinese, R.id.btn_english, R.id.btn_japanese})
    public void onClick(View view) {
        //获取语言code
        switch (view.getId()) {
            case R.id.btn_simplified_chinese://简体中文

                //设置简体勾选框显示
                iv_simplified_chinese.setVisibility(View.VISIBLE);
                //日文勾选框隐藏
                iv_japanese.setVisibility(View.GONE);
                //英文勾选框隐藏
                iv_english.setVisibility(View.GONE);
                selectedLanguage= LanguageType.LANGUAGE_CHINESE_SIMPLIFIED;
                break;

            case R.id.btn_english://英文

                iv_simplified_chinese.setVisibility(View.GONE);
                //日文勾选框隐藏
                iv_japanese.setVisibility(View.GONE);
                //英文勾选框显示
                iv_english.setVisibility(View.VISIBLE);
                //记住选择
                selectedLanguage= LanguageType.LANGUAGE_EN;
                break;

            case R.id.btn_japanese://日语
                //设置简体勾选框隐藏
                iv_simplified_chinese.setVisibility(View.GONE);
                //日文勾选框显示
                iv_japanese.setVisibility(View.VISIBLE);
                //英文勾选框隐藏
                iv_english.setVisibility(View.GONE);
                //记住选择
                selectedLanguage= LanguageType.LANGUAGE_JAPAN;
                break;


        }

        MultiLanguageUtil.getInstance().updateLanguage(selectedLanguage);
        Intent intent = new Intent(LanguageSettingsActivity.this, FirstActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }



}
