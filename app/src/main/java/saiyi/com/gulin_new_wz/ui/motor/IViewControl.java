package saiyi.com.gulin_new_wz.ui.motor;

import android.arch.lifecycle.LifecycleObserver;
import android.content.Intent;
import android.view.View;

/**
 * Created by 陈姣姣 on 2018/8/27.
 */
public interface IViewControl extends LifecycleObserver ,IViewLifecycle {

     void  initView(View rootView);
     /**
      *  接收消息
      * */
     void  reciver(Intent intent);

     /**
     *  发送消息
     ***/

     void  send(Intent intent);

     /**
      *  是否拦截接收
      * */
     boolean isInterceptReciver(Intent intent);


      /**
       *  是否拦截发送
       * */
     boolean isInterceptSend(Intent intent);




















}


