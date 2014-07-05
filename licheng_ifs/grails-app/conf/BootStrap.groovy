 import com.dvn.miniboss.acct.AcctBillingCycleService
 import com.dvn.miniboss.jc.process.PublishAssetProcessService

 class BootStrap {

    def init = { servletContext ->
        AcctBillingCycleService.init()
        PublishAssetProcessService.loadAllPublishAsset()
    }
    def destroy = {
    }
} 