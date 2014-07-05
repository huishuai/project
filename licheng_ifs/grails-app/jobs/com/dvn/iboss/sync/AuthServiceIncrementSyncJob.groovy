package com.dvn.iboss.sync

import com.dvn.miniboss.jc.ftp.JcASAllService
import org.apache.log4j.Logger
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.dvn.miniboss.jc.ftp.JcASIncrementService

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-8
 * Time: 17:09:17
 * To change this template use File | Settings | File Templates.
 */

class AuthServiceIncrementSyncJob {

    def JcASIncrementService jcASIncrementService

    def group = "SyncGroup"
    private static config = ConfigurationHolder.config
    private static final Logger log = Logger.getLogger(AuthServiceIncrementSyncJob.class);
    private static String cronExpression = config.job.authServiceIncrementSyncJob.cronExpression.toString()

    static triggers = {
        cron name: AuthServiceIncrementSyncJob.class.getName(), startDelay: 5000, cronExpression: cronExpression
    }

    def execute() {
        log.info "**********-----AuthServiceIncrementSyncJob Start!-----************"
        log.info "cronExpression:" + cronExpression
        jcASIncrementService.run()
        log.info "**********-----AuthServiceIncrementSyncJob End  !-----************"
    }


}