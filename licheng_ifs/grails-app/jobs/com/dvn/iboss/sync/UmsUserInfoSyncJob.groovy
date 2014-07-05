package com.dvn.iboss.sync

import com.dvn.miniboss.jc.ftp.JcUmsUserInfoService
import org.apache.log4j.Logger
import org.codehaus.groovy.grails.commons.ConfigurationHolder

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-8
 * Time: 17:09:17
 * To change this template use File | Settings | File Templates.
 */

class UmsUserInfoSyncJob {

    def JcUmsUserInfoService jcUmsUserInfoService

    def group = "SyncGroup"
    private static config = ConfigurationHolder.config
    private static final Logger log = Logger.getLogger("UmsUserInfoSyncJob");
    private static String cronExpression = config.job.umsUserInfoSyncJob.cronExpression.toString()

    static triggers = {
        cron name: JcUmsUserInfoService.class.getName(), startDelay: 5000, cronExpression: cronExpression
    }

    def execute() {
        log.info "**********-----UmsUserInfoSyncJob Start!-----************"
        log.info "cronExpression:" + cronExpression
        jcUmsUserInfoService.run()
        log.info "**********-----UmsUserInfoSyncJob End  !-----************"
    }


}