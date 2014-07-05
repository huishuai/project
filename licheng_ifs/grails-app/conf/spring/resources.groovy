// Place your Spring DSL code here
beans = {
    //目前暂时使用c3p0连接池，如果不使用c3p0连接池就把下面的代码注释掉就可以了
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

   //目前暂时使用c3p0连接池，如果不使用c3p0连接池就把下面的代码注释掉就可以了
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