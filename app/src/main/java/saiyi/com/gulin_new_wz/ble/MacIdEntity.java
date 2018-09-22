package saiyi.com.gulin_new_wz.ble;

/**
 * Created by Ligs on 2018-01-27.
 */

public class MacIdEntity {

    private static String mMacId;

    public static String getMacId() {
        return mMacId;
    }

    public static void setMacId(String macId) {
        mMacId = macId;
    }

    public static boolean mIsSign = false;

    public static String mSwitchLanguage = null;
}
