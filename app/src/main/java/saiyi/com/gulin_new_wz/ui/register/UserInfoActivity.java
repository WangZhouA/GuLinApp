package saiyi.com.gulin_new_wz.ui.register;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;

/**
 * Created by 陈姣姣 on 2018/8/17.
 */
public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.header_right_msg)
    TextView headerRightMsg;
    @BindView(R.id.user_input)
    EditText userInput;
    @BindView(R.id.delet)
    ImageView delet;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_setusermessage);



    }

    int  flag;
    @Override
    protected void initData() {

        showNormeBar();
        headerRightMsg.setText(getResources().getString(R.string.save));
        headerRightMsg.setVisibility(View.VISIBLE);


        Intent intent =getIntent();
        if (intent.getIntExtra("FLAG",-1)!=-1){
            flag = intent.getIntExtra("FLAG",-1);

            if (flag==0){
                setTile(getResources().getString(R.string.set_name));
            } else if (flag==1){
                userInput.setInputType( InputType.TYPE_CLASS_NUMBER);
                setTile(getResources().getString(R.string.set_age));
            }else if (flag==2){
                setTile(getResources().getString(R.string.set_hight));
                userInput.setInputType( InputType.TYPE_CLASS_NUMBER);
            }else if (flag==3){
                setTile(getResources().getString(R.string.set_gender));
            }else if (flag==4){
                setTile(getResources().getString(R.string.set_weight));
                userInput.setInputType( InputType.TYPE_CLASS_NUMBER);
            }else if (flag==5){
                setTile(getResources().getString(R.string.set_remarks));
            }else if (flag==6){
                setTile(getResources().getString(R.string.set_entering_person));
            }else if (flag==7){
                setTile(getResources().getString(R.string.set_doctors_name));
            }

        }

    }

    Intent intent = new Intent();
    @OnClick({R.id.header_right_msg, R.id.delet})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.header_right_msg:

                if (!TextUtils.isEmpty(userInput.getText().toString().trim())) {
                    intent.putExtra("text", userInput.getText().toString().trim());
                    setResult(flag, intent);
                    finish();
                }else {

                    toast(getResources().getString(R.string.edit_not_null));
                }
                break;
            case R.id.delet:

                userInput.setText("");

                break;
        }
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            intent.putExtra("text", "");
//            setResult(flag, intent);
//
//            return super.onKeyDown(keyCode, event);
//        }else {
//            return super.onKeyDown(keyCode, event);
//        }
//
//    }

}
