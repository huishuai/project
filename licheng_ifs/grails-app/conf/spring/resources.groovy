// Place your Spring DSL code here
beans = {
    //Ŀǰ��ʱʹ��c3p0���ӳأ������ʹ��c3p0���ӳؾͰ�����Ĵ���ע�͵��Ϳ�����
    dataSource(com.mchange.v2.c3p0.ComboPooledDataSource) {bean ->
        bean.destroyMethod = 'close'
        driverClass = '${driverClassName}'
        jdbcUrl = '${url}'
        user = '${username}'
        password = '${password}'
        autoCommitOnClose = 'false'
        checkoutTimeout = '10000'
        maxPoolSize = '${maxPoolSize}'
        initialPoolSize = '${initialPoolSize}'
        minPoolSize = '${minPoolSize}'
        acquireIncrement = '3'
        maxIdleTime = '30'
        idleConnectionTestPeriod = '60'
    }

   //Ŀǰ��ʱʹ��c3p0���ӳأ������ʹ��c3p0���ӳؾͰ�����Ĵ���ע�͵��Ϳ�����
    bsmpDataSource(com.mchange.v2.c3p0.ComboPooledDataSource) {bean ->
        bean.destroyMethod = 'close'
        driverClass = '${bsmpDriverClassName}'
        jdbcUrl = '${bsmpUrl}'
        user = '${bsmpUsername}'
        password = '${bsmpPassword}'
        autoCommitOnClose = 'false'
        checkoutTimeout = '10000'
        maxPoolSize = '${bsmpMaxPoolSize}'
        initialPoolSize = '${bsmpInitialPoolSize}'
        minPoolSize = '${bsmpMinPoolSize}'
        acquireIncrement = '3'
        maxIdleTime = '30'
        idleConnectionTestPeriod = '60'
    }

    applicationContextUtil(com.miniboss.acct.utils.ApplicationContextUtil) {

    }
}