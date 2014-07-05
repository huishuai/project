package com.dvn.iboss.sync

import org.apache.log4j.Logger
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.dvn.miniboss.jc.process.BsmpIbossResoureProcessService
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-11-23
 * Time: 17:11:39
 * To change this template use File | Settings | File Templates.
 */
class BsmpIbossResoureSyncJob {

  def BsmpIbossResoureProcessService bsmpIbossResoureProcessService

    def group = "SyncGroup"
    private static config = ConfigurationHolder.config
    private static final Logger log = Logger.getLogger("BsmpIbossResoureSyncJob");
     private static String cronExpression = config.job.bsmpIbossResoureSyncJob.cronExpression.toString()

    static triggers = {
        cron name: BsmpIbossResoureSyncJob.class.getName(), startDelay: 5000, cronExpression: cronExpression
    }

    def execute() {
        log.info "**********-----BsmpIbossResoureSyncJob Start!-----************"
        log.info "cronExpression:" + cronExpression
        bsmpIbossResoureProcessService.resoureDataSync();
        log.info "**********-----BsmpIbossResoureSyncJob End  !-----************"
    }
}
