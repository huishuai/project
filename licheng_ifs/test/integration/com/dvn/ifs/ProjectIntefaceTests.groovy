package com.dvn.ifs

import com.dvn.miniboss.acct.AcctPaymentMethod
import com.dvn.miniboss.jc.ftp.JcPublishAssetService
import com.dvn.miniboss.jc.ftp.JcUmsUserInfoService
import com.dvn.miniboss.jc.process.AuthServiceProcessService
import com.dvn.miniboss.jc.process.UmsProcessService
import com.dvn.sys.dass.sms.po.baseaccount.request.SMSBaseAccoBankAPIRequest
import com.dvn.sys.dass.sms.po.baseaccount.response.SMSBaseAccoBankAPIResponse
import com.dvn.sys.dass.sms.po.basequery.request.SMSBaseQueryAPIRequest
import com.dvn.sys.dass.sms.po.basequery.response.SMSBaseQueryAPIResponse
import com.dvn.sys.dass.sms.po.pwdmod.request.SMSPwdModAPIRequest
import com.dvn.sys.dass.sms.po.pwdmod.response.SMSPwdModAPIResponse
import com.dvn.sys.dass.sms.po.pwdval.request.SMSPwdValAPIRequest
import com.dvn.sys.dass.sms.po.pwdval.response.SMSPwdValAPIResponse
import com.miniboss.bean.ums.BaseServiceDataBean
import org.apache.log4j.Logger
import org.hibernate.SessionFactory
import com.miniboss.acct.utils.JdbcUtils
import com.miniboss.bean.BasePage
import java.sql.Connection
import javax.sql.DataSource
import com.dvn.miniboss.acct.CmngUser
import com.dvn.miniboss.oldsms.AuthService
import com.miniboss.acct.utils.FileUtil
import com.dvn.miniboss.serv.Empowerment
import com.dvn.miniboss.oldsms.CmngCustom
import com.miniboss.acct.utils.DateUtil

/**
 * Created by IntelliJ IDEA.
 * User: tb
 * Date: 2009-11-20
 * Time: 11:33:32
 * To change this template use File | Settings | File Templates.
 */

public class ProjectIntefaceTests extends GroovyTestCase {
    private static final Logger log = Logger.getLogger(ProjectIntefaceTests.class)
    def AuthServiceProcessService AuthServiceProcessService
    def JcPublishAssetService jcPublishAssetService
    def JcUmsUserInfoService jcUmsUserInfoService
    def UmsProcessService umsProcessService
    def SessionFactory sessionFactory
    def DataSource dataSource



    void testAuthServiceProcess() {
        jcPublishAssetService.run()
//        jcUmsUserInfoService.run()
    }

    void testUmsProcess() {

        List <String>list = new ArrayList<String>()
        list.add(BaseServiceDataBean.UMS_MONTH_BILLINGCYCLECOUNT + "")
        list.add(BaseServiceDataBean.UMS_ONEYEAR_BILLINGCYCLECOUNT + "")
        list.add(BaseServiceDataBean.UMS_THREEYEAR_BILLINGCYCLECOUNT + "")
        for (String billingCycleCount: list) {
            umsOrderBaseService(billingCycleCount);
            sessionFactory.getCurrentSession().clear()
        }
    }
    /**
     * 基本包订购查询
     * @param billingCycleCount
     */
    private def umsOrderBaseService(String billingCycleCount) {
        SMSBaseQueryAPIRequest request = new SMSBaseQueryAPIRequest()
        request.setTransactionID("T002")
        request.setUUID("000043")
        request.setNetId(null)
        SMSBaseQueryAPIResponse response = umsProcessService.baseQuery(request)
        System.out.println("Code:" + response.getReturnCode());
        System.out.println("Msg:" + response.getReturnMsg());

        //count=1,paying=2300;count=12,paying=27600;count=36,paying=80000;
        Map map = new HashMap()
        String[] split = response.getPaying().split(BaseServiceDataBean.sign_semicolon)
        for (String onePrice: split) {
            String[] priceStr = onePrice.split(BaseServiceDataBean.sign_comma)
            String key = priceStr[0].substring(priceStr[0].indexOf(BaseServiceDataBean.sign_equal) + 1)
            String value = priceStr[1].substring(priceStr[1].indexOf(BaseServiceDataBean.sign_equal) + 1)
            map.put(key, value)
        }

        SMSBaseAccoBankAPIRequest requestBaseAccoBank = new SMSBaseAccoBankAPIRequest()
        requestBaseAccoBank.setTransactionID("T002")
        requestBaseAccoBank.setUUID("000043")
        requestBaseAccoBank.setBillingCount(billingCycleCount)
        requestBaseAccoBank.setChargeAccount((String) map.get(billingCycleCount))
        requestBaseAccoBank.setModelID(AcctPaymentMethod.PAYMETHOD_CARD)
        requestBaseAccoBank.setNetId(null)
        SMSBaseAccoBankAPIResponse responseBaseAccoBank = umsProcessService.baseAccoBank(requestBaseAccoBank)
        System.out.println("Code:" + responseBaseAccoBank.getReturnCode());
        System.out.println("Msg:" + responseBaseAccoBank.getReturnMsg())
    }



    void testUmsPasswordModifyAndValid() {

        SMSPwdValAPIRequest request = new SMSPwdValAPIRequest()
        request.setTransactionID("T001")
        request.setUUID("000043")
        request.setPassword("123456")
        SMSPwdValAPIResponse response = umsProcessService.passwordValid(request)
        System.out.println("Password Valid Code:" + response.getReturnCode());

        SMSPwdModAPIRequest requestModify = new SMSPwdModAPIRequest()
        requestModify.setTransactionID("T002")
        requestModify.setUUID("000043")
        requestModify.setOldPassword("123456")
        requestModify.setNewPassword("654321")
        SMSPwdModAPIResponse responseModify = umsProcessService.passwordModify(requestModify)
        System.out.println("Password Modify Code:" + responseModify.getReturnCode());
    }


    void testUmsUserInfoSync(){
        System.out.println("-----------------------------------------------------");
        System.out.println("------testUmsUserInfoSync-------StartTime:"+new Date());
        System.out.println("-----------------------------------------------------");

        Set validUserSet = authServiceProcessService.getValidUser()
        Set freeUserSet = authServiceProcessService.getFreeUser()

        String localPath = "E:\\home"
        String fileName = "test.txt"

		String sql = "SELECT cc.DVBCUSTOMID, "
		sql += '       cu.ID, '
		sql += '       cc.CUSTOMTYPE, '
		sql += '       cs.STBID,'
		sql += '       cs.ICID,'
		sql += '       cs.SMCODE,'
        sql += '       ci.SEX, '
        sql += '       ci.CUSTOMNM, '
        sql += '       ci.ADDRESS, '
        sql += '       ci.IDCARD, '
        sql += '       ci.MAINTELE '
		sql += '  FROM (((CMNG_CUSTOM cc LEFT JOIN CMNG_CUSTOMINFO ci ON'
		sql += '        cc.CUSTOMID = ci.CUSTOMID) LEFT JOIN CMNG_USER cu ON'
		sql += '        cc.DVBCUSTOMID = cu.DVB_COSTOM_ID) LEFT JOIN CMNG_STBCUSTOMVDO cs ON'
		sql += '        cu.ID = cs.USERID) '
        sql += ' WHERE  cu.GROUP_TYPE = \'' + CmngUser.GROUPTYPE_PERSON + '\' '

        System.out.println("sql:"+sql);
		Connection conn1 = dataSource.getConnection()
        int page = 1;
        int pageNum = 1000;
        BasePage basePage = new BasePage();
        basePage.setPageNo(page);
        basePage.setPageSize(pageNum);
        JdbcUtils.calcCount(basePage,sql,conn1)
        conn1.close()

        int count = basePage.getPageCount()
        System.out.println("count:"+count);
        for (int i = 0; i < count; i++) {
            Connection conn = dataSource.getConnection()
            basePage.setPageNo(i+1);
            basePage.setFetchTotal(false)   
            List <Map>userInfoList = JdbcUtils.getResultList(sql, basePage, conn);
            System.out.println("userInfoList" + userInfoList.size());
            authServiceProcessService.createCmngUserSyncData(userInfoList, validUserSet, freeUserSet, localPath, fileName)

        }
        System.out.println("-----------------------------------------------------");
        System.out.println("------testUmsUserInfoSync-------StartTime:"+new Date());
        System.out.println("-----------------------------------------------------");

    }


    void testAllAuthServiceSync(){
        
        System.out.println("-----------------------------------------------------");
        System.out.println("------getAllCmngUserRef-------StartTime:"+new Date());
        System.out.println("-----------------------------------------------------");

        String localPath = "E:\\home"
        String fileName = "test.txt"

        StringBuffer allAuthServiceSyncData = new StringBuffer()

        HashMap userRefMap = authServiceProcessService.getAllCmngUserRef()
        HashMap uuidUserIdRefMap = authServiceProcessService.getUUidUserIdRef()

         def c = AuthService.createCriteria()
        List<AuthService> results = c {
            eq("status", AuthService.STATUS_RUNNING)
            order("createdate", "desc")
        }
        //获取全量订购关系同步数据
        for (com.dvn.miniboss.oldsms.AuthService authService: results) {

            String currAuthServiceStr = authServiceProcessService.makeAuthServiceSyncData(authService, userRefMap, uuidUserIdRefMap)
            allAuthServiceSyncData.append(currAuthServiceStr)
        }

        FileUtil fileUtil = new FileUtil()
        fileUtil.writeSyncDataToFileFoot(localPath, fileName, allAuthServiceSyncData.toString())
        allAuthServiceSyncData.delete(0, allAuthServiceSyncData.length());

//        HashMap uuIdUserIdRef = authServiceProcessService.getUUidUserIdRef()

//        String localPath = "E:\\home"
//        String fileName = "test.txt"
//        Connection conn = dataSource.getConnection()
//        String sql = "select * from AUTH_SERVICE a where a.status = '" + AuthService.STATUS_RUNNING + "' ";
//        authServiceProcessService.batchWriteAuthServiceToLocalFile(sql, conn, localPath, fileName,userRefMap,uuIdUserIdRef)

        System.out.println("-----------------------------------------------------");
        System.out.println("------getAllCmngUserRef-------StartTime:"+new Date());
        System.out.println("-----------------------------------------------------");

    }

    void testSqlProcess(){

        System.out.println("-----------------------------------------------------");
        System.out.println("------TestSqlProcess-------StartTime:"+new Date());
        System.out.println("-----------------------------------------------------");

        long customID = 1882203
        CmngCustom custom = CmngCustom.findById(customID)

        //设置订购用户类型(个人用户，用户组)
        String userGroupType = CmngUser.GROUPTYPE_PERSON
        if (!CmngCustom.CUSTOMTYPE_PERSONAL.equals(custom.getCustomType())) {
            userGroupType = CmngUser.GROUPTYPE_COMPANY_USERTEAM
        }
        String getEmpListHql = "from Empowerment e where e.customId = '" + custom.id + "' and e.status = '" + Empowerment.STATUS_VALID + "'" +
                " and groupType = '" + userGroupType + "' and e.maxEndDate Is Not Null and " +
                " e.maxEndDate >= to_date('" + DateUtil.getDateFullStr(new Date()) + "','yyyy-mm-dd hh24:mi:ss') order by e.maxEndDate desc"
        System.out.println("SQL:" + getEmpListHql);
        List<Empowerment> empowermentList = Empowerment.executeQuery(getEmpListHql)

        for (com.dvn.miniboss.serv.Empowerment empowerment: empowermentList) {
            System.out.print("ID:"+empowerment.id);
            System.out.print("--status:"+empowerment.status);
            System.out.print("--endDate:"+empowerment.endDate);
            System.out.println("--maxEndDate:"+empowerment.maxEndDate);
        }



        System.out.println("-----------------------------------------------------");
        System.out.println("------TestSqlProcess-------StartTime:"+new Date());
        System.out.println("-----------------------------------------------------");

    }

}