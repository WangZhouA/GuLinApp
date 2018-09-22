package saiyi.com.gulin_new_wz.ui.motor;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 陈姣姣 on 2018/8/27.
 */
public abstract class AbsIViewControlImpl implements IViewControl {

    private AppCompatActivity appCompatActivity;
    private LocalBroadcastManager localBroadcastManager;
    View rootView;
    Unbinder unbinder ;

    public AbsIViewControlImpl(AppCompatActivity appCompatActivity, int viewId){
        this.appCompatActivity = appCompatActivity;
        appCompatActivity.getLifecycle().addObserver(this);
        rootView= appCompatActivity.findViewById(viewId);
        initView(rootView);
    }


    public View getRootView() {
        return rootView;
    }


    protected AppCompatActivity getActivity() {
        return appCompatActivity;
    }

    @Override
    public void initView(View rootView) {
        unbinder = ButterKnife.bind(this,rootView);

        localBroadcastManager = LocalBroadcastManager.getInstance(appCompatActivity);
        registReciver(localBroadcastManager, broadcastReceiver);
    }


    @Override
    public boolean isInterceptReciver(Intent intent) {
        return false;
    }

    @Override
    public boolean isInterceptSend(Intent intent) {
        return false;
    }

    @Override
    public void send(Intent intent) {

        if (!isInterceptSend(intent)){

            localBroadcastManager.sendBroadcast(intent);
        }
    }

    public abstract boolean registReciver(LocalBroadcastManager localBroadcastManager, BroadcastReceiver receiver);


    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            reciver(intent);
        }
    };


    @Override
    public void reciver(Intent intent) {
        if (isInterceptReciver(intent)){
           return;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void onCreate() {
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public void onResume() {

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    @Override
    public void onPause() {

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    @Override
    public void onStop() {

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    @Override
    public void onStart() {

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void onDestory() {
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
        appCompatActivity.getLifecycle().removeObserver(this);
        unbinder.unbind();
    }
}
