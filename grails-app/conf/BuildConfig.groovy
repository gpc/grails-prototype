grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
    inherits "global"
    log "warn"

    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }

    String gebVersion = "0.7.0"
    String seleniumVersion = "2.21.0"

    dependencies {
        build(
                "net.sourceforge.nekohtml:nekohtml:1.9.15",
                {
                    excludes "xml-apis"
                    export = false
                })

        test(
                "hsqldb:hsqldb:1.8.0.10",
                "org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion",
                "org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion",
                "org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion",
                "org.codehaus.geb:geb-spock:$gebVersion",
                {
                    exclude "xml-apis"
                    export = false
                })
    }

    plugins {
        runtime ":resources:1.1.6"

        build(
                ":tomcat:$grailsVersion",
                ":release:2.2.0",
                {
                    excludes 'nekohtml'
                    export = false
                })

        test(
                ":geb:0.6.3",
                ":spock:0.6",
                {
                    export = false
                })
    }

}
