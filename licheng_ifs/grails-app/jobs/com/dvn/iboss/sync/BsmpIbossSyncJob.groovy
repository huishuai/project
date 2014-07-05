package com.dvn.iboss.sync

import org.apache.log4j.Logger
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.dvn.miniboss.jc.process.BsmpIbossProcessService

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-8
 * Time: 17:09:17
 * To change this template use File | Settings | File Templates.
 */

class BsmpIbossSyncJob {

    def BsmpIbossProcessService bsmpIbossProcessService

    def group = "SyncGroup"
    private static config = ConfigurationHolder.config
    private static final Logger log = Logger.getLogger("BsmpIbossSyncJob");
     private static String cronExpression = config.job.bsmpIbossSyncJob.cronExpression.toString()

    static triggers = {
        cron name: BsmpIbossSyncJob.class.getName(), startDelay: 5000, cronExpression: cronExpression
    }

    def execute() {
        log.info "**********-----BsmpIbossSyncJob Start!-----************"
        log.info "cronExpression:" + cronExpression
        bsmpIbossProcessService.dataSync();
        log.info "**********-----BsmpIbossSyncJob End  !-----************"
    }
}