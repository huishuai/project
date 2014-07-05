package com.miniboss.bean.ums

import com.dvn.miniboss.acct.CmngStbType

import com.miniboss.exception.BaseException
import com.dvn.miniboss.oldsms.AuthService

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-9
 * Time: 16:48:32
 * To change this template use File | Settings | File Templates.
 */

/**
 * 为UMS基本收视费查询接口使用（UmsProcessService.baseQuery）
 * 基本服务数据对象
 */
class BaseServiceDataBean {


//    type=1,status=1,sumnopaid=2300,enddate=20100531;*;

    public static final String sign_comma = ","
    public static final String sign_equal = "="
    public static final String sign_colon = ":"
    public static final String sign_semicolon = ";"

    public static final String attr_type = "type"
    public static final String attr_status = "status"
    public static final String attr_sumnopaid = "sumnopaid"
    public static final String attr_enddate = "enddate"

    public static final String UMS_STATUS_RUNNING = "1"        //正常
    public static final String UMS_STATUS_USER_PAUSE = "2"     //主动暂停-用户报停
    public static final String UMS_STATUS_ARREAR_PAUSE = "3"   //欠费暂停
    public static final String UMS_STATUS_STOPPED = "4"        //终止

    public static final String UMS_CLASSTYPE_MASTER = "1"        //主机
    public static final String UMS_CLASSTYPE_SALVE = "2"         //从机

    public static final String UMS_DEFAUT_ENDDATE = "19000101" //如果服务结束时间不存在的默认值

    //UMS基本收视费查询接口正常访问后的响应代码和消息
    public static final String SUCCESS_RETURN_CODE = "0"
    public static final String SUCCESS_RETURN_MESSAGE = "Operate success!"

    //对于UMS基本收视费查询时对应的不同的基本包产品计费周期常量
    public static final long UMS_MONTH_BILLINGCYCLECOUNT = 1
    public static final long UMS_ONEYEAR_BILLINGCYCLECOUNT = 12
    public static final long UMS_THREEYEAR_BILLINGCYCLECOUNT = 36

    //对于UMS基本收视费查询时对应的不同的基本包产品计费周期常量
    public static final String UMS_MONTH_BILLINGCYCLECOUNT_KEY = "01"
    public static final String UMS_ONEYEAR_BILLINGCYCLECOUNT_KEY = "12"
    public static final String UMS_THREEYEAR_BILLINGCYCLECOUNT_KEY = "36"

    //是否可以使用一卡通支付“UMS基本收视费”
    public static final String ISORNOT_NOT = "0"    //不可以
    public static final String ISORNOT_YES = "1"    //可以

    public static final String PAYING_CONTENT_COUNT = "count"
    public static final String PAYING_CONTENT_PAYING = "paying"

    //低保户限制进行Portal订购的主机基本包批价金额(分)
    public static final long UMS_ALLOW_MIN_PRICE = 2300

    //月末可以进行Portal基本包查询和订购的截止时间
    public static final long ALLOW_BASE_QUERYORDER_HOUR = 18



    String type             //type表示此盒是主机或从机， 1表示主机， 2表示从机
    String status           //Status表示此机的基本服务状态，1-正常，2-主动暂停，3-欠费暂停，4-终止
    String sumNoPaid        //sumnopaid表示未付费帐单的金额，目前没有用到--韩金鹏MSN已确认
    String endDate          //基本包订购关系终止时间

    long userId             //用户ID
    long oneMonthPrice      //包月基本包价格
    long oneYearPrice       //一年基本包价格
    long threeYearPrice     //三年基本包价格

    boolean canIaddUpPrice = false  //是否进行进行批价

    public void setType(String ibossClassType){
        if(ibossClassType.equals(CmngStbType.MASTER)){
            type = UMS_CLASSTYPE_MASTER
        }else if(ibossClassType.equals(CmngStbType.SLAVE)){
            type = UMS_CLASSTYPE_SALVE
        }else{
            throw new BaseException("Current ClassType Not Exist!","Current ClassType Not Exist!")
        }
    }

    public String getType(){
        return this.type
    }

    public String convertBeanToString(){
        StringBuffer retSB = new StringBuffer()
        retSB.append(attr_type).append(sign_equal).append(this.type).append(sign_comma)
        retSB.append(attr_status).append(sign_equal).append(this.status).append(sign_comma)
        retSB.append(attr_sumnopaid).append(sign_equal).append(this.sumNoPaid).append(sign_comma)
        retSB.append(attr_enddate).append(sign_equal).append(this.endDate)
        return retSB.toString()
    }

    //拼装<Paying>count=1,paying=2300;count=12,paying=27600;count=36,paying=80000;</Paying>中数据
    public static String getPayingStr(Map<String,Long> map){
        StringBuffer strBuffer = new StringBuffer()
        strBuffer.append(PAYING_CONTENT_COUNT).append(sign_equal).append(UMS_MONTH_BILLINGCYCLECOUNT).append(sign_comma)
        Long oneMonthPrice = map.get(UMS_MONTH_BILLINGCYCLECOUNT_KEY + "")
        if(oneMonthPrice == null)
            oneMonthPrice = 0
        strBuffer.append(PAYING_CONTENT_PAYING).append(sign_equal).append(oneMonthPrice).append(sign_semicolon)
        strBuffer.append(PAYING_CONTENT_COUNT).append(sign_equal).append(UMS_ONEYEAR_BILLINGCYCLECOUNT).append(sign_comma)
        Long oneYearPrice = map.get(UMS_ONEYEAR_BILLINGCYCLECOUNT_KEY + "")
        if(oneYearPrice == null)
            oneYearPrice = 0
        strBuffer.append(PAYING_CONTENT_PAYING).append(sign_equal).append(oneYearPrice).append(sign_semicolon)
        strBuffer.append(PAYING_CONTENT_COUNT).append(sign_equal).append(UMS_THREEYEAR_BILLINGCYCLECOUNT).append(sign_comma)
        Long threeYearPrice = map.get(UMS_THREEYEAR_BILLINGCYCLECOUNT_KEY + "")
        if(threeYearPrice == null)
            threeYearPrice = 0
        strBuffer.append(PAYING_CONTENT_PAYING).append(sign_equal).append(threeYearPrice).append(sign_semicolon)
        return strBuffer.toString()
    }

    // 获得批价索引中的用户ID信息
    // 索引的后两位是账期（01、12、36），前面是用户ID。
    // 例子:13221601，‘132216’是用户ID，‘01’是账期)
    public static long getUserIdByPriceIndex(long priceIndex){
        String priceIndexStr = priceIndex+""
        int index = priceIndexStr.length() - 2
        String userID = priceIndexStr.substring(0, index)
        return Long.parseLong(userID)
    }

    // 获得批价索引中的账期信息，结构同getUserIdByPriceIndex方法
    public static String getBillingCycleCountByPriceIndex(long priceIndex){
        String priceIndexStr = priceIndex.toString()
        int index = priceIndexStr.length() - 2
        String billingCycleCount = priceIndexStr.substring(index, priceIndexStr.length())
        return billingCycleCount
    }


    public void makeBaseServiceDataBean(AuthService auth){
        long price = auth.getPriceResult().total
        //有残月问题
        if(auth.billingCyclecount<=2)
            this.oneMonthPrice = price
        else if(auth.billingCyclecount<=13)
            this.oneYearPrice = price
        else(auth.billingCyclecount<=37)
            this.threeYearPrice = price

    }

}
