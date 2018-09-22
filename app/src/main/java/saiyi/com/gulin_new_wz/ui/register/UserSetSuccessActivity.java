package saiyi.com.gulin_new_wz.ui.register;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.db.LoveDao;
import saiyi.com.gulin_new_wz.db.User;
import saiyi.com.gulin_new_wz.photo.PhotoClipActivity;
import saiyi.com.gulin_new_wz.photo.PhotoUtil;
import saiyi.com.gulin_new_wz.utils.LogUtils;

/**
 * Created by 陈姣姣 on 2018/8/20.
 */
public class UserSetSuccessActivity extends BaseActivity {
    @BindView(R.id.btn_sure)
    TextView btnSure;
    @BindView(R.id.btn_photo)
    TextView btnPhoto;

    PhotoUtil photoUtil;
    @Override
    protected void initView() {

        setContentView(R.layout.activity_egistration_end);
    }

    User user ;
    @Override
    protected void initData() {

        showNormeBar();
        setTile(getResources().getString(R.string.egistration_text));

        user =new User();

        Intent intent =getIntent();
        if (intent.getLongExtra("ID",-1)!=-1){

            user  = LoveDao.queryUserLove(intent.getLongExtra("ID",-1)).get(0);


        }
    }


    @OnClick({R.id.btn_sure, R.id.btn_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sure:

                change(UserListActivity.class,UserSetSuccessActivity.this,new Intent(),true);

                break;
            case R.id.btn_photo:

                photoUtil =new PhotoUtil(this);
                photoUtil.showDialog(getResources().getString(R.string.xiangji),getResources().getString(R.string.xiangji));

                break;
        }
    }

    /**
     *  相机相册测回调
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        // 相册返回
        if (PhotoUtil.CAMRA_SETRESULT_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                // 相册选中图片路径
                String cameraPath = photoUtil.getCameraPath(data);
                Bitmap bitmap = photoUtil.readBitmapAutoSize(cameraPath);
                LogUtils.d("相相册选中路径  = " + cameraPath);
                startClipActivity(cameraPath);
            }
        }
        // 相机返回
        else if (PhotoUtil.PHOTO_SETRESULT_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                String photoPath = photoUtil.getPhotoPath();
                Bitmap bitmap = photoUtil.readBitmapAutoSize(photoPath);
                LogUtils.d("相机选中路径  = " + photoPath);
                startClipActivity(photoPath);

            }
        }
        // 裁剪返回
        else if (PhotoUtil.PHOTO_CORPRESULT_CODE == requestCode) {
            if (resultCode == RESULT_OK) {

                String path = data.getStringExtra("path");
                LogUtils.d("裁剪返回  = "+path);
                if (path!=null) {
                    user.setUserHeader(path);
                    LoveDao.updateLove(user);
                    change(UserListActivity.class,UserSetSuccessActivity.this,new Intent(),true);


                }

            }
        }

    }


    //点击跳转到图片处理的界面
    public void startClipActivity(String path) {
        Intent intent = new Intent(this, PhotoClipActivity.class);
        intent.putExtra("path", path);
        startActivityForResult(intent, PhotoUtil.PHOTO_CORPRESULT_CODE);
    }


}
