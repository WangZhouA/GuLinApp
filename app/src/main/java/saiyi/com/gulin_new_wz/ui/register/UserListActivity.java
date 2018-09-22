package saiyi.com.gulin_new_wz.ui.register;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.adapter.UserAdapter;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.db.LoveDao;
import saiyi.com.gulin_new_wz.db.User;
import saiyi.com.gulin_new_wz.utils.INotifyLinstener;
import saiyi.com.gulin_new_wz.utils.RecycleViewDivider;

/**
 * Created by 陈姣姣 on 2018/8/20.
 */
public class UserListActivity extends BaseActivity implements INotifyLinstener{
    @BindView(R.id.recycle)
    RecyclerView recycle;

    private UserAdapter userAdapter;


    List<User>mDateUser =new ArrayList<>();

    @Override
    protected void initView() {


        setContentView(R.layout.activity_user_list);
    }

    @Override
    protected void initData() {


        showNormeBar();
        setTile(getResources().getString(R.string.egistration_text));

        mDateUser.clear();

        mDateUser = LoveDao.queryUserALL();
        for (int i =0;i<mDateUser.size();i++){

            Log.e("---数据",mDateUser.get(i).getId()+"==="+mDateUser.get(i).getName() );
        }

        //设置布局管理器
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this, mDateUser);
        userAdapter.setiNotifyLinstener(this);
        recycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        recycle.setAdapter(userAdapter);


    }

     public void  add (View view){

        change(EgistrationActivity.class,UserListActivity.this,new Intent(),true);

     }

    @Override
    public void setNotify(int postion) {

        change(UserMsgXiangQing.class,this,new Intent().putExtra("ID",mDateUser.get(postion).getId()),true);

    }
}
