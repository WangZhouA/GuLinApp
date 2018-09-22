package saiyi.com.gulin_new_wz.ui.register;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.saiyi.library.photo.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.MainActivity;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.db.LoveDao;
import saiyi.com.gulin_new_wz.db.User;

/**
 * Created by 陈姣姣 on 2018/8/20.
 */
public class UserMsgXiangQing extends BaseActivity  implements View.OnClickListener{

    @BindView(R.id.im_photo)
    CircleImageView circleImageView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_hight)
    TextView tvHight;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_remarks)
    TextView tvRemarks;
    @BindView(R.id.tv_entering_person)
    TextView tvEnteringPerson;
    @BindView(R.id.tv_doctors_name)
    TextView tvDoctorsName;
    @BindView(R.id.tv_btn_save)
    TextView tvBtnSave;

    long id;

    @Override
    protected void initView() {

        setContentView(R.layout.activity_user_xiangqing);
    }


    User user;

    @Override
    protected void initData() {
        showNormeBar();
        setTile(getResources().getString(R.string.egistration_text));
        Intent intent = getIntent();
         id = intent.getLongExtra("ID", -1L);
        /**
         *  因为id是唯一的，所有呢，直接去取第0个
         * */
        user = LoveDao.queryUserLove(id).get(0);
        tvName.setText(user.getName());
        tvAge.setText(user.getAge());
        tvHight.setText(user.getHight());
        tvGender.setText(user.getGreader());
        tvWeight.setText(user.getWeight());
        tvRemarks.setText(user.getRemarks());
        tvEnteringPerson.setText(user.getEntering_person());
        tvDoctorsName.setText(user.getDoctors_name());


        if (!TextUtils.isEmpty(user.getUserHeader())) {
            Uri uri = Uri.parse(user.getUserHeader());
            circleImageView.setImageURI(uri);
        }else{

            circleImageView.setImageResource(R.mipmap.head_portrait);
        }
    }

    @OnClick({R.id.tv_btn_save})
    @Override
    public void onClick(View view) {


        change(MainActivity.class,UserMsgXiangQing.this,new Intent().putExtra("ID",id),true);
    }
}
