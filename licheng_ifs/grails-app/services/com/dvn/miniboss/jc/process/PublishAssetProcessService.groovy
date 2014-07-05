package com.dvn.miniboss.jc.process

import com.dvn.miniboss.oldsms.ChargeBillType
import com.dvn.miniboss.product.BillingCycle
import com.dvn.miniboss.product.Product
import com.dvn.miniboss.product.ProductInstance
import com.dvn.miniboss.sync.PublishAsset
import com.miniboss.exception.DomainSaveException
import com.miniboss.sync.ftp.PublishAssetBean
import org.apache.log4j.Logger
import com.dvn.miniboss.product.Commodity

class PublishAssetProcessService {

    boolean transactional = false
    public final static Logger logger = Logger.getLogger(PublishAssetProcessService.class)
    public final static Hashtable<String, Long> publishAssetCommodity = new Hashtable<String, Long>()
    public static Commodity clickCommodity=null;

    public static void loadAllPublishAsset() {
        List<PublishAsset> publishAssets = PublishAsset.list()
        loadAllPublishAsset(publishAssets)
    }

    public static void loadAllPublishAsset(List<PublishAsset> publishAssets) {
        String hql = "from Commodity c where c.billingCycle.periodType='" + BillingCycle.PERIODTYPE_CLICK + "' and c.chargeBillType.id='" + ChargeBillType.SERVICE_VOD + "' "
        Commodity commodity = Commodity.find(hql)
        clickCommodity=commodity

        for (PublishAsset publishAsset: publishAssets) {
                publishAssetCommodity.put(publishAsset.publishAssetId, publishAsset.assetPrice)
        }
        println publishAssetCommodity
    }

    public static Long getPrice(String publishAssetId) {
        return publishAssetCommodity.get(publishAssetId)
    }

    public static Commodity getClickCommodity(){
        return clickCommodity;
    }

    def insertPublishAssetToDB(File file) throws Exception {

        logger.info("--Read And Save PublishAsset!----Begin----")
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))
            String line
            while ((line = br.readLine()) != null) {
                PublishAssetBean bean = new PublishAssetBean()
                bean.convertFtpLogToBean(line)
                //保存或更新PublishAsset
                PublishAsset publishAssetDB = PublishAsset.findByPublishAssetId(bean.getPublishAssetId())
                if(publishAssetDB){
                    publishAssetDB.properties = bean.properties
                }else{
                    publishAssetDB = new PublishAsset()
                    publishAssetDB.properties = bean.properties
                }
                publishAssetDB.setCreateDate(new Date())
                if (!publishAssetDB.save()) {
                    throw new DomainSaveException("Save PublishAsset Error!", publishAssetDB)
                }

            }
        } catch (IOException e) {
            logger.error("Read And Save PublishAsset Errir!", e)
            e.printStackTrace()
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("--Read And Save PublishAsset!----End----")



    }
}
