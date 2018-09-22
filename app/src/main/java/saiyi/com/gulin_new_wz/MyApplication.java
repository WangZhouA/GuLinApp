package saiyi.com.gulin_new_wz;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.finddreams.languagelib.MultiLanguageUtil;

import java.util.ArrayList;
import java.util.List;

import saiyi.com.gulin_new_wz.ble.BleManger;
import saiyi.com.gulin_new_wz.db.gen.DaoMaster;
import saiyi.com.gulin_new_wz.db.gen.DaoSession;

/**
 * Created by 覃微
 * Data:2017/3/23.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    public  static List<Activity> activitiesList = new ArrayList<Activity>(); // 活动管理集合


    private static DaoSession daoSession;
    DaoMaster daoMaster;




    private void setupDataValuebase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "User.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }


    public static DaoSession getDaoInstant() {
        return daoSession;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        BleManger.getInstance().intiBle(this);
        setupDataValuebase();
        MultiLanguageUtil.init(this);

    }

    /**
     * 获取单例
     *
     * @return
     *
     */
    public MyApplication() {
    }

    //单例模式中获取唯一的MyApplication实例
    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }



    /**
     * 把活动添加到活动管理集合
     *
     * @param acty
     */
    public void addActyToList(Activity acty) {
        if (!activitiesList.contains(acty))
            activitiesList.add(acty);
    }

    /**
     * 把活动从活动管理集合移除
     *
     * @param acty
     */
    public void removeActyFromList(Activity acty) {
        if (activitiesList.contains(acty))
            activitiesList.remove(acty);
    }

    /**
     * 程序退出
     */
    public void clearAllActies() {
        for (Activity acty : activitiesList) {
            if (acty != null)
                acty.finish();
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public static void exit() {
        for (Activity activity : activitiesList) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }
}
