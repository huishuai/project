grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir	= "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.war.file = "target/${appName}.war"
grails.plugin.location.'cbsDomain'="../cbsDomain"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits( "global" ) {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {        
        grailsPlugins()
//        grailsHome()

        // uncomment the below to enable remote dependency resolution
//         from public Maven repositories
//        mavenLocal()
//        mavenRepo "http://snapshots.repository.codehaus.org"
//        mavenRepo "http://repository.codehaus.org"
//        mavenRepo "http://download.java.net/maven/2/"
   //     mavenRepo "http://172.16.1.117:8080/archiva/repository/internal"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.5' 
//       compile  "activemq:activemq-all:5.3.0",
//                "org.hibernate:ojdbc:14",
//                "commons-chain:commons-chain:1.0",
//                "commons-httpclient:commons-httpclient:3.0.1",
//                "commons-logging:commons-logging:1.1",
//                "commons-net:commons-net:2.0",
//                "concurrent:concurrent:1.3.4",
//                "gson:gson:1.4",
//                "joda-time:joda-time:1.6",
//                "log4j:log4j:1.2.14",
//                "mina:mina-core:1.0.1",
//                "mina:mina-integration-spring:1.0.1",
//                "web-service-base:web-service-base:1.0",
//                "com.springsource:backport:3.0.1",
//                "com.springsource:xbean-spring:3.3"

    }              
}
