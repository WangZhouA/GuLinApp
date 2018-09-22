package saiyi.com.gulin_new_wz.fragment.evaluation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.adapter.UserAdapter;
import saiyi.com.gulin_new_wz.db.LoveDao;
import saiyi.com.gulin_new_wz.db.User;
import saiyi.com.gulin_new_wz.ui.Evaluation.CalendarSelectionActivity;
import saiyi.com.gulin_new_wz.utils.INotifyLinstener;
import saiyi.com.gulin_new_wz.utils.RecycleViewDivider;

/**
 * Created by 陈姣姣 on 2018/8/24.
 */
public class EvaluationHistoryFragment extends Fragment  implements INotifyLinstener{


    View view;


    @BindView(R.id.recycle)
    RecyclerView recycle;
    Unbinder unbinder;

    List<User>mDateUser =new ArrayList<>();
    private UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragmeng_history, null);
        unbinder = ButterKnife.bind(this, view);

        mDateUser.clear();

        mDateUser = LoveDao.queryUserALL();
        for (int i =0;i<mDateUser.size();i++){

            Log.e("---数据",mDateUser.get(i).getId()+"==="+mDateUser.get(i).getName() );
        }

        //设置布局管理器
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        userAdapter = new UserAdapter(getActivity(), mDateUser);
        userAdapter.setiNotifyLinstener(this);
        recycle.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL));
        recycle.setAdapter(userAdapter);


        return view;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void setNotify(int postion) {

        Intent intent =new Intent(getActivity(),CalendarSelectionActivity.class);
        getActivity().startActivity(intent);


    }


}
