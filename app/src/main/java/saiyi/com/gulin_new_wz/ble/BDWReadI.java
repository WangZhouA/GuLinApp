package saiyi.com.gulin_new_wz.ble;

/**
 * Bluetooth Write or Read callback.
 */

public interface BDWReadI {

    /**
     * 读取特性成功回调。
     *
     * @param result result content
     */
    void onRCResult(String result);

    /**
     * 读取特性失败回调。
     */
    void onRCFail();

    /**
     * 读描述符的回调。
     *
     * @param result result content
     */
    void onNotifyResult(String result);

    /**
     * 读描述符失败回调。
     */
    void onNotifyFail();

    void onCharacteristicChanged(byte[] data);

    void onWriteCharSuccess(String instructions);

}
