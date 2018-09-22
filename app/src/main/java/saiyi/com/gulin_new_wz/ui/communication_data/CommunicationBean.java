package saiyi.com.gulin_new_wz.ui.communication_data;

/**
 * Created by Ligs on 2018-01-27.
 */

public class CommunicationBean{

    private String mContent;
    private String mTime;
    private int mIsSent;

    public int getIsSent() {
        return mIsSent;
    }

    //0:false 1:true
    public void setIsSent(int isSent) {
        mIsSent = isSent;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }
}
