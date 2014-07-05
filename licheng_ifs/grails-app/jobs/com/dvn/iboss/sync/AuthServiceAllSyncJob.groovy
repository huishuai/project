package com.dvn.iboss.sync

import com.dvn.miniboss.jc.ftp.JcUmsUserInfoService
import org.apache.log4j.Logger
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.dvn.miniboss.jc.ftp.JcASAllService

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-8
 * Time: 17:09:17
 * To change this template use File | Settings | File Templates.
 */

class AuthServiceAllSyncJob {

    def JcASAllService jcASAllService

    def group = "SyncGroup"
    private static config = ConfigurationHolder.config
    private static final Logger log = Logger.getLogger(AuthServiceAllSyncJob.class);
    private static String cronExpression = config.job.authServiceAllSyncJob.cronExpression.toString()

    static triggers = {
        cron name: AuthServiceAllSyncJob.class.getName(), startDelay: 5000, cronExpression: cronExpression
    }

    def execute() {
        log.info "**********-----AuthServiceAllSyncJob Start!-----************"
        log.info "cronExpression:" + cronExpression
        jcASAllService.run()
        log.info "**********-----AuthServiceAllSyncJob End  !-----************"
    }


}