package saiyi.com.gulin_new_wz.http;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by 陈姣姣 on 2017/11/30.
 */

public class StringUtils {


    // 服务器地址
    public static  String HTTP_SERVICE ="http://58.250.30.13:8086/";

    // 登录
    public static  String LOGIN="peritoneal/app/user/login";
    // 公告栏查询标题指定查询
    public static  String NOTICE_TITLE="peritoneal/app/noticeInfo/getAppNoticeInfoTitle?hospitalId=";
    // 公告栏查询标题
    public static  String NOTICE_TITLE_ALL="peritoneal/app/noticeInfo/getAppNoticeInfoTitle?hospitalId=";
    // 查询 公告栏标题内容
    public static  String NOTICE_MSG= "peritoneal/app/noticeInfo/getAppNoticeInfoTitleClassification?id=";
    //查询答题分类
//    public static  String QUERY_FENLEI_TITLE= "peritoneal/app/question/getAppQuestionGroup";
    public static  String QUERY_FENLEI_TITLE= "peritoneal/app/question/getAppQuestionGroup?hospitalId=";
    //明细
    public static  String ORDER_DETAILS= "peritoneal/app/user/getBill?uid=";
    //账单详情
    public static  String ZHANGDAN_XIANGQING= "peritoneal/app/user/getBillDetails?serialNumber=";
    //查询用户信息
    public static  String USER= "peritoneal/app/user/getUserId?id=";
    //查询公告栏分类详情
    public static  String QUERY_GONGGAOLAN_FENLEI_XIANGQING="peritoneal/app/noticeInfo/getAppNoticeInfoTitleDetails?id=";
    //试题小结
    public static String TEST_SUMMARY="peritoneal/app/question/getAppQuestionList?id=";
    //查询公告栏分类详情
    public static String DETAILS = "peritoneal/app/noticeInfo/getAppNoticeInfoClassifyDetails?id=";
    //上传头像
    public static String HEARDER = "peritoneal/app/user/addHeadPicture";
    //试题小结
    public static String ANSWERSLIST="peritoneal/app/question/saveAnswer";
    //视频列表
    public static String VIDEOITEM ="peritoneal/app/videoInfo/getAppVideoInfoList?condition=";
    //添加参加人数
    public static String ADDPEOPLE= "peritoneal/app/videoInfo/addVideoInfoVisit";
    //设备列表
    public static String DERVICEITEM ="peritoneal/app/equipment/getAppEquipment?userId=";
    //重命名
    public static String RENAME = "peritoneal/app/equipment/updateRqname";
    //支付宝订单
    public static String ZHIFUBAOZHIFU="peritoneal/app/alipay/appPay";
    //查询用户余额
    public static String MONERY="peritoneal/app/user/getBalanceByid?id=";
    //详情
    public static String XIANGQING = "peritoneal/app/equData/getEqudataDetail";
    //每天的数据
    public static String DAY_DATE= "peritoneal/app/equData/getEquDataList";
    //医生处方
    public static String DOCTOR_PRESCRIBED= "peritoneal/app/equData/getPrescription";
    //查询某天数据详情
    public static String FIND_OUT_THE_DATE_DETAILS="peritoneal/app/equData/getEqudata";
    //修改密码
    public static String REPASSWORD="peritoneal/app/user/updateUserPassword";










    public  static  void showImage(Context context, String url, int erro , int loadpic, ImageView imageView){
        Glide.with(context).load(url).asBitmap().fitCenter().placeholder(loadpic).error(erro).dontAnimate().into(imageView);
    }
}
