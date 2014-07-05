package com.dvn.iboss.sync

import org.apache.log4j.Logger
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.dvn.miniboss.jc.ftp.UserInfoForBMPTService

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-8
 * Time: 17:09:17
 * To change this template use File | Settings | File Templates.
 */

class UserInfoSyncForBMPTJob {

    def UserInfoForBMPTService userInfoForBMPTService

    def group = "SyncGroup"
    private static config = ConfigurationHolder.config
    private static final Logger log = Logger.getLogger("UserInfoSyncForBMPTJob");
    private static String cronExpression = config.job.userInfoSyncForBMPTJob.cronExpression.toString()

    static triggers = {
        cron name: UserInfoForBMPTService.class.getName(), startDelay: 5000, cronExpression: cronExpression
    }

    def execute() {
        log.info "**********-----UserInfoSyncForBMPTJob Start!-----************"
        log.info "cronExpression:" + cronExpression
        userInfoForBMPTService.run()
        log.info "**********-----UserInfoSyncForBMPTJob End  !-----************"
    }


}