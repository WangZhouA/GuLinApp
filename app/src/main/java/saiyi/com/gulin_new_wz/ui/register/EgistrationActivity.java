package saiyi.com.gulin_new_wz.ui.register;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.db.LoveDao;
import saiyi.com.gulin_new_wz.db.User;

/**
 * Created by 陈姣姣 on 2018/8/17.
 */
public class EgistrationActivity extends BaseActivity {

    @BindView(R.id.header_right_msg)
    TextView headerRightMsg;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.rl_age)
    RelativeLayout rlAge;
    @BindView(R.id.rl_hight)
    RelativeLayout rlHight;
    @BindView(R.id.rl_gender)
    RelativeLayout rlGender;
    @BindView(R.id.rl_weight)
    RelativeLayout rlWeight;
    @BindView(R.id.rl_remarks)
    RelativeLayout rlRemarks;
    @BindView(R.id.rl_entering_person)
    RelativeLayout rlEnteringPerson;
    @BindView(R.id.rl_Doctors_name)
    RelativeLayout rlDoctorsName;
    @BindView(R.id.tv_btn_save)
    TextView tvBtnSave;
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


    TextView [] listsDate;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_egistration);
    }

    @Override
    protected void initData() {

        showNormeBar();
        setTile(getResources().getString(R.string.egistration_text));
        headerRightMsg.setVisibility(View.VISIBLE);
        headerRightMsg.setText(getResources().getString(R.string.history_record));

        listsDate=new TextView[]{tvName,tvAge,tvHight,tvGender,tvWeight,tvRemarks,tvEnteringPerson,tvDoctorsName};

    }

  boolean  PanDuanTiaoJian =true;
    @OnClick({R.id.header_right_msg, R.id.rl_name, R.id.rl_age, R.id.rl_hight, R.id.rl_gender, R.id.rl_weight, R.id.rl_remarks, R.id.rl_entering_person, R.id.rl_Doctors_name, R.id.tv_btn_save})
    public void onViewClicked(View view) {

        Intent intentMsg = new Intent();

        switch (view.getId()) {
            case R.id.header_right_msg:
                change(UserListActivity.class, EgistrationActivity.this, intentMsg, false);
                break;
            case R.id.rl_name:

                changeForResult(UserInfoActivity.class, EgistrationActivity.this, intentMsg.putExtra("FLAG", 0), 0, false);

                break;
            case R.id.rl_age:

                changeForResult(UserInfoActivity.class, EgistrationActivity.this, intentMsg.putExtra("FLAG", 1), 1, false);

                break;
            case R.id.rl_hight:

                changeForResult(UserInfoActivity.class, EgistrationActivity.this, intentMsg.putExtra("FLAG", 2), 2, false);

                break;
            case R.id.rl_gender:

                changeForResult(UserInfoActivity.class, EgistrationActivity.this, intentMsg.putExtra("FLAG", 3), 3, false);

                break;
            case R.id.rl_weight:

                changeForResult(UserInfoActivity.class, EgistrationActivity.this, intentMsg.putExtra("FLAG", 4), 4, false);

                break;
            case R.id.rl_remarks:

                changeForResult(UserInfoActivity.class, EgistrationActivity.this, intentMsg.putExtra("FLAG", 5), 5, false);

                break;
            case R.id.rl_entering_person:

                changeForResult(UserInfoActivity.class, EgistrationActivity.this, intentMsg.putExtra("FLAG", 6), 6, false);

                break;
            case R.id.rl_Doctors_name:

                changeForResult(UserInfoActivity.class, EgistrationActivity.this, intentMsg.putExtra("FLAG", 7), 7, false);

                break;
            case R.id.tv_btn_save:

                for (int i = 0; i < listsDate.length; i++) {

                    if (TextUtils.isEmpty(listsDate[i].getText().toString())) {
                        toast(getResources().getString(R.string.edit_not_null));
                        PanDuanTiaoJian =false;
                        break ;

                    }else {

                        PanDuanTiaoJian =true;
                    }
                }
                if (PanDuanTiaoJian==true) {
                    tvBtnSave.setBackgroundColor(Color.parseColor("#00DAB0"));
                    User user = new User();
                    user.setName(tvName.getText().toString());
                    user.setAge(tvAge.getText().toString());
                    user.setHight(tvHight.getText().toString());
                    user.setGreader(tvGender.getText().toString());
                    user.setWeight(tvWeight.getText().toString());
                    user.setRemarks(tvRemarks.getText().toString());
                    user.setEntering_person(tvEnteringPerson.getText().toString());
                    user.setDoctors_name(tvDoctorsName.getText().toString());
                    user.setUserHeader("");
                    LoveDao.insertLove(user);
                    List<User> id = LoveDao.queryUserLove(user.getId());
                    Log.e("---->", "刚创建的ID" + id.get(id.size() - 1).getId());
                    change(UserSetSuccessActivity.class, EgistrationActivity.this, new Intent().putExtra("ID", id.get(id.size() - 1).getId()), true);
                }

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result ="";
        if (data!=null) {
            if (!TextUtils.isEmpty(data.getStringExtra("text"))) {
                result = data.getStringExtra("text");
            }
        }
        switch (requestCode) {
            case 0:
                tvName.setText(result);
                break;
            case 1:
                tvAge.setText(result);
                break;
            case 2:
                tvHight.setText(result);
                break;
            case 3:
                tvGender.setText(result);
                break;
            case 4:
                tvWeight.setText(result);
                break;
            case 5:
                tvRemarks.setText(result);
                break;
            case 6:
                tvEnteringPerson.setText(result);
                break;
            case 7:
                tvDoctorsName.setText(result);
                break;


        }
    }



}

