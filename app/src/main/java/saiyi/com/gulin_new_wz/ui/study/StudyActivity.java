package saiyi.com.gulin_new_wz.ui.study;

import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.view.MyShowDialog;

/**
 * Created by 陈姣姣 on 2018/8/24.
 */
public class StudyActivity extends BaseActivity {


    @BindView(R.id.im_switch)
    ImageView imSwitch;

    private boolean isSwitch;


     MyShowDialog MyShowDialog;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_study);
    }

    @Override
    protected void initData() {

        showNormeBar();
        setTile(getResources().getString(R.string.initial_learning));

    }



    @OnClick(R.id.im_switch)
    public void onViewClicked() {

            myShowDialog();

    }

    private void myShowDialog() {

        MyShowDialog = new MyShowDialog(StudyActivity.this);
        MyShowDialog.setNoOnclickListener(getResources().getString(R.string.no), new MyShowDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {

                MyShowDialog.dismiss();

            }
        });

        MyShowDialog.setYesOnclickListener(getResources().getString(R.string.yes), new MyShowDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {



                if (!isSwitch) {
                    imSwitch.setImageResource(R.mipmap.on);
                    MyShowDialog.setTitle(getResources().getString(R.string.onstudying));
                    isSwitch=!isSwitch;
                    MyShowDialog.dismiss();
                }else {
                    imSwitch.setImageResource(R.mipmap.off);
                    MyShowDialog.setTitle(getResources().getString(R.string.nostudying));
                    isSwitch=!isSwitch;
                    MyShowDialog.dismiss();

                }
            }
        });

        MyShowDialog.show();

    }
}
