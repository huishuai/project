package com.dvn.iboss.sync

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.apache.log4j.Logger
import com.dvn.miniboss.jc.ftp.JcPublishAssetService

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-8
 * Time: 17:09:17
 * To change this template use File | Settings | File Templates.
 */
class PublishAssetSyncJob {

    def JcPublishAssetService jcPublishAssetService

    def group = "SyncGroup"
    private static config = ConfigurationHolder.config
    private static final Logger log = Logger.getLogger("PublishAssetSyncJob");
    private static String cronExpression = config.job.publishAssetSyncJob.cronExpression.toString()

    static triggers = {
        cron name: PublishAssetSyncJob.class.getName(), startDelay: 5000, cronExpression: cronExpression
    }

    def execute() {
        log.info "**********-----PublishAssetSyncJob Start!-----************"
        log.info "cronExpression:" + cronExpression
//        jcPublishAssetService.run()
        log.info "**********-----PublishAssetSyncJob End  !-----************"
    }





}
