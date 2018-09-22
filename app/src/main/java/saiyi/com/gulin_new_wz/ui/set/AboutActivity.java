package saiyi.com.gulin_new_wz.ui.set;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import butterknife.BindView;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;

/**
 *〈关于我们页面〉
 *
 * @version [版本号, 2017/12/29]
 *
 * @author Huyongqing
 *
 * @since [产品/模块版本]
 */

public class AboutActivity extends BaseActivity{

    /**
     * <进入关于我们页面>
     * @param context
     */

    @BindView(R.id.imageView)
    ImageView imageView;
    public static void startAboutActivity(Context context){
        Intent intent = new Intent(context,AboutActivity.class);
        context.startActivity(intent);
    }



    @Override
    public void initView() {
        //设置标题和返回



        setContentView(R.layout.activity_about);
    }

    @Override
    protected void initData() {

        setTile(getResources().getString(R.string.about_us_text));
        imageView.setImageResource(R.mipmap.logo);
    }


}
