//Ŀǰ��ʱʹ��c3p0���ӳأ����ӳص����÷���resources.groovy����
// ��dataSource����spring�е�dataSource����ֻ��һ���հ�����,������ʱ����������spring���Ƿ�����ΪdataSource����
//����о�ʹ���������û�о�ֱ�ӽ���hibernate�Լ�ȥ�������ӳأ���hibernateĬ�ϵ�dbcp���ӳ�
// dialect �� driverClassName �����������Ϊ������ddl�ļ���ʱ��ʹ��
// dbCreate ���� hibernate �����ɲ���
// logSql   ���� hibernate �Ƿ���ʾsql���
// dataSource ������ô�õĿ��Կ�����HibernatePluginSupport.groovy
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