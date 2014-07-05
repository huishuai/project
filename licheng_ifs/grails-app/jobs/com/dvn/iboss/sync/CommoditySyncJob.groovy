package com.dvn.iboss.sync

import com.dvn.miniboss.jc.ftp.JcPublishAssetService
import org.apache.log4j.Logger
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.dvn.miniboss.jc.webservice.JcBsmpProcessService

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-8
 * Time: 17:09:17
 * To change this template use File | Settings | File Templates.
 */

class CommoditySyncJob {

    def JcBsmpProcessService jcBsmpProcessService

    def group = "SyncGroup"
    private static config = ConfigurationHolder.config
    private static final Logger log = Logger.getLogger("CommoditySyncJob");
    private static String cronExpression = config.job.commoditySyncJob.cronExpression.toString()

    static triggers = {
        cron name: CommoditySyncJob.class.getName(), startDelay: 5000, cronExpression: cronExpression
    }

    def execute() {
        log.info "**********-----CommoditySyncJob Start!-----************"
        log.info "cronExpression:" + cronExpression
        jcBsmpProcessService.suncAllCommodity()
        log.info "**********-----CommoditySyncJob End  !-----************"
    }





}