package saiyi.com.gulin_new_wz.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import saiyi.com.gulin_new_wz.MyApplication;
import saiyi.com.gulin_new_wz.utils.Functions;


/**
 * Created by 陈姣姣 on 2018/6/19.
 */
public class BaseFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);


    }

    /**
     * 判断当前的网络环境
     */
    public boolean checkNet() {
        return Functions.getNetworkState(getActivity().getApplicationContext()) != Functions.NETWORN_NONE;
    }

    /**
     *  方法三，用于弹框提示
     * */
//***********************************************************************
    public void toast(int resId) {
        Toast.makeText(getActivity().getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public void toast(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //***********************************************************************



    /**
     *
     * 方法四，activity跳转
     * */
    //***********************************************************************
    /**
     * @param cls        现在activity
     * @param activity   现在activity
     * @param intent     有参数和无参数intent
     * @param isFinished 是否销毁当前activity
     */
    public void change(Class<? extends Activity> cls, Activity activity, Intent intent, boolean isFinished) {
        intent.setClass(activity, cls);
        startActivity(intent);
        if (isFinished) {
           finish(activity);
        }
    }

    public void changeForResult(Class<? extends Activity> cls, Activity activity, Intent intent, int i, boolean isFinished) {
        intent.setClass(activity,cls);
        activity.startActivityForResult(intent, i);
        if (isFinished) {
           finish(activity);
        }
    }
    /**
     *
     *   finsh  当前页面
     * */
    public void finish(Activity activity) {
        super.getActivity().finish();
        MyApplication.getInstance().removeActyFromList(activity);
    }


}
