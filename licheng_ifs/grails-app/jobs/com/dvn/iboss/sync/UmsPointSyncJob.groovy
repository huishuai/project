package com.dvn.iboss.sync

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.apache.log4j.Logger
import com.dvn.miniboss.jc.ftp.JcUmsPointService

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-8
 * Time: 17:09:17
 * To change this template use File | Settings | File Templates.
 */
class UmsPointSyncJob {

    def JcUmsPointService jcUmsPointService

    def group = "SyncGroup"
    private static config = ConfigurationHolder.config
    private static final Logger log = Logger.getLogger("UmsPointSyncJob");
    private static String cronExpression = config.job.umsPointSyncJob.cronExpression.toString()

    static triggers = {
        cron name: UmsPointSyncJob.class.getName(), startDelay: 5000, cronExpression: cronExpression
    }

    def execute() {
        log.info "**********-----UmsPointSyncJob Start!-----************"
        log.info "cronExpression:" + cronExpression
        jcUmsPointService.run()
        log.info "**********-----UmsPointSyncJob End  !-----************"
    }
}
