package saiyi.com.gulin_new_wz.ui.communication_data;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.adapter.CommunicationDataAdapter;
import saiyi.com.gulin_new_wz.service.MyService;
import saiyi.com.gulin_new_wz.utils.DialogUtils;

/**
 * Created by 陈姣姣 on 2018/9/5.
 */
public class CommunicationDataActivity extends Fragment implements IFragmentListener{

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    Unbinder unbinder;

    /**
     * adapter
     */
    private CommunicationDataAdapter mAdapter;
    /**
     * 数据集合
     */
    private LinkedList<CommunicationBean> mList;
    SimpleDateFormat mDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String ACTION_COMMUNICATION_DATA = "saiyi.communication.data.fragment";
    public static final String SP_LIST = "SmartList";



    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.activity_communication_data,null);
        unbinder = ButterKnife.bind(this,view);
        initData();

        ((DataHomeActivity)getActivity()).setiFragmentListener(this);

        if (MyService.getListDate()!=null){

            mList = MyService.getListDate();
            mAdapter.notifyDataSetChanged();
        }

        return   view;

    }


    protected void initData() {

        //初始化RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        LinkedList<CommunicationBean> list = null;
        try {
            list = readSPLsit(SP_LIST);
        } catch (Exception e) {
        }
        if (list != null) {
            mList = list;
            if (!mList.isEmpty()) {
                if (mList.getFirst().getIsSent() != 1 && mList.getFirst().getIsSent() != 0) {
                    mList.clear();
                }
            }
        } else {
            mList = new LinkedList<>();
        }
        //初始化adapter
        mAdapter = new CommunicationDataAdapter(mList);
        //设置adapter
        mRecyclerView.setAdapter(mAdapter);
       getActivity().registerReceiver(mReceiver, new IntentFilter(ACTION_COMMUNICATION_DATA));

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();

        getActivity(). unregisterReceiver(mReceiver);
        writeListIntoSP(SP_LIST, mList);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String instructions = intent.getStringExtra("instructions");
            if (!instructions.matches("\\*P\\d+") && !instructions.matches("\\*K\\d+")) {
                {
                    if (mList.size() > 200) {
                        mList.remove();
                    }
                    CommunicationBean communicationBean = new CommunicationBean();
                    communicationBean.setIsSent(intent.getBooleanExtra("isSent", false) ? 1 : 0);
                    communicationBean.setContent(instructions);
                    communicationBean.setTime(mDf.format(System.currentTimeMillis()));
                    mList.add(communicationBean);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    };


    public void writeListIntoSP(String spName, LinkedList<CommunicationBean> list) {
        SharedPreferences sp = getActivity().getSharedPreferences(spName, Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list); //将List转换成Json
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("KEY_PEOPLE_LIST_DATA", jsonStr); //存入json串
        editor.commit();  //提交
    }

    public LinkedList<CommunicationBean> readSPLsit(String fileName) {
        SharedPreferences sp =  getActivity().getSharedPreferences(fileName, Activity.MODE_PRIVATE);//创建sp对象,如果有key为"SP_PEOPLE"的sp就取出
        String listJson = sp.getString("KEY_PEOPLE_LIST_DATA", "");  //取出key为"KEY_PEOPLE_DATA"的值，如果值为空，则将第二个参数作为默认值赋值
        Log.d("zc", listJson);
        if (listJson != "")  //防空判断
        {
            Gson gson = new Gson();
            LinkedList<CommunicationBean> o = gson.fromJson(listJson, new TypeToken<LinkedList<CommunicationBean>>() {
            }.getType());
            return o; //将json字符串转换成List集合
        }
        return null;
    }




    @Override
    public void setLeftFragment() {



    }

    @Override
    public void setRightFragment() {

    }

    @Override
    public void setdeleteButton() {

        DialogUtils.showIsOkDialog(getActivity(), getString(R.string.sure_text), getString(R.string.cancel_text),
                getString(R.string.determine_empty), new DialogListener() {
                    @Override
                    public void onComplete() {
                        MyService.getListDate().clear();
                        mList.clear();
                        //初始化adapter
                        mAdapter = new CommunicationDataAdapter(mList);
                        //设置adapter
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFail() {

                    }
                });


    }
}
