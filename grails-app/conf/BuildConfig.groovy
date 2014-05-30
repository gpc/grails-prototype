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
		mavenRepo "http://m2repo.spockframework.org/releases/"
		mavenRepo "http://m2repo.spockframework.org/snapshots/"
    }
    dependencies {
		test("org.seleniumhq.selenium:selenium-firefox-driver:latest.integration") {
		    export = false
		}
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
    }
	plugins {
		runtime ":resources:1.0"
		
		build( ":release:2.2.1") {
		    export = false
		}
		build( ":tomcat:$grailsVersion" ) {
		    export = false
		}
		test( ":geb:0.5.1" ) {
		    export = false
		}
        test(":spock:0.7") {
            exclude "spock-grails-support"
        }
	}
}
