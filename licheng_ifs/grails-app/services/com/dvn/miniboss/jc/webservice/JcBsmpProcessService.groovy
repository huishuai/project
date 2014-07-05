package com.dvn.miniboss.jc.webservice

import com.bsmp.syncboss.webservice.jincheng.model.Affirm
import com.bsmp.syncboss.webservice.jincheng.model.Cooperater
import com.bsmp.syncboss.webservice.jincheng.model.PorductPackage
import com.bsmp.syncboss.webservice.jincheng.service.BossService
import com.dvn.miniboss.oldsms.Company
import com.dvn.miniboss.product.BillingCycle
import com.dvn.miniboss.product.Commodity
import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * 需要外部crm来调用或者用定时任务
 */
class JcBsmpProcessService {
    def BossService bsmpService

    boolean transactional = false

    public void syncAllCooperater() {
        List<Company> list = Company.list()
        log.info("---start sync all Cooperater info!--")
        for (Company company: list) {
            try {
                Cooperater cooperater = new Cooperater()
                cooperater.spId = company.id
                cooperater.spName = company.companyName
                cooperater.spStatus = "Y"
                cooperater.effDate = new Date()
                cooperater.expDate = new Date()
                Affirm affirm = bsmpService.addCooperater(cooperater)
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
                log.info gson.toJson(affirm)
            } catch (Exception e) {
                log.error(e.getMessage(), e)
            };
        }
        log.info("---end sync all Cooperater info!--")
    }

    public void addCooperater(String companyId) {
        Cooperater cooperater = new Cooperater();
        Company company = Company.findById(companyId)
        cooperater.spId = company.id
        cooperater.spName = company.companyName
        cooperater.spStatus = "Y"
        cooperater.effDate = new Date()
        cooperater.expDate = new Date()
        Affirm affirm = bsmpService.addCooperater(cooperater);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        println gson.toJson(affirm)
    }

    //不发请求

    public void editCooperater(String companyId) {
        Cooperater cooperater = new Cooperater();
        Company company = Company.findById(companyId)
        cooperater.spId = company.id
        cooperater.spName = company.companyName
        cooperater.spStatus = "Y"
        cooperater.effDate = new Date()
        cooperater.expDate = new Date()
        bsmpService.editCooperater(cooperater);
    }

    //不发请求

    public void removeCooperater(String companyId) {
        Cooperater cooperater = new Cooperater();
        cooperater.spId = companyId
        cooperater.spStatus = "N"
        cooperater.effDate = new Date()
        cooperater.expDate = new Date()
        bsmpService.removeCooperater(cooperater);
    }

    public void deployPorductPkgInfo(String commodityId) {
        PorductPackage porductPackage = new PorductPackage();
        Commodity commodity = Commodity.findById(commodityId)
        if ("10008".equals(commodity.chargeBillType.id))
            return

        if ("szds".equals(commodity.businessType.id) || "zzyw".equals(commodity.businessType.id)) {
            porductPackage.productId = commodity.id
            porductPackage.productName = commodity.name
            porductPackage.productState = commodity.status
            porductPackage.productDescription = commodity.description
            porductPackage.deployDate = new Date()
            porductPackage.totalClass = commodity.price
            porductPackage.totalNum = commodity.billingCycle.period 
            Calendar calendar=Calendar.getInstance()
            calendar.add(Calendar.YEAR,20)
            porductPackage.cancleDate=calendar.getTime()
            porductPackage.productType = commodity.businessType.id
            porductPackage.billType = commodity.chargeBillType.id
            bsmpService.savePorductPackage(porductPackage);
        }
    }

    public void suncAllCommodity() {
        String hql = "from Commodity c where c.billingCycle.periodType='" + BillingCycle.PERIODTYPE_MONTH + "' and c.chargeBillType.invoiceListType='zzf' "
        List<Commodity> commodities = Commodity.executeQuery(hql)
        for (Commodity commodity: commodities) {
//            if ("10008".equals(commodity.chargeBillType.id))
//                return

            if ("szds".equals(commodity.businessType.id) || "zzyw".equals(commodity.businessType.id)) {
                PorductPackage porductPackage = new PorductPackage();
                porductPackage.productId = commodity.id
                porductPackage.productName = commodity.name
                porductPackage.productState = commodity.status
                porductPackage.productDescription = commodity.description
                porductPackage.deployDate = commodity.createTime
                porductPackage.totalClass = commodity.price
                porductPackage.totalNum = commodity.billingCycle.period
                //todo
//          porductPackage.cancleDate=commodity.createTime
                porductPackage.productType = commodity.businessType.id
                porductPackage.billType = commodity.chargeBillType.id
                bsmpService.savePorductPackage(porductPackage);
            }
        }
    }
}
