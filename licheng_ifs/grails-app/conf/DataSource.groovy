//目前暂时使用c3p0连接池，连接池的配置放在resources.groovy上面
// 此dataSource并非spring中的dataSource，这只是一个闭包而已,启动的时候程序会检测在spring中是否有名为dataSource的类
//如果有就使用它，如果没有就直接交给hibernate自己去创建连接池，即hibernate默认的dbcp连接池
// dialect 和 driverClassName 参数在这儿是为了生成ddl文件的时候使用
// dbCreate 定义 hibernate 表生成策略
// logSql   定义 hibernate 是否显示sql语句
// dataSource 具体怎么用的可以看看在HibernatePluginSupport.groovy
dataSource {
    pooled = true
    dialect = "org.hibernate.dialect.Oracle9Dialect"
    driverClassName = "oracle.jdbc.driver.OracleDriver"
//    dbCreate = "update"
//    url = '${url}'
//    username = '${username}'
//    password = '${password}'
//    logSql=true
}

hibernate {
//    cache.use_second_level_cache = true
//    cache.use_query_cache = true
//    cache.provider_class = 'org.hibernate.cache.OSCacheProvider'
    flush.mode = "manual"
}