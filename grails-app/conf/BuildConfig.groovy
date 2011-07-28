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
		test("org.spockframework:spock-grails-support:0.6-groovy-1.7-SNAPSHOT") {
			excludes "spock-core", "slf4j-log4j12", "slf4j-api", "log4j"
			export = false
		}
		test("org.spockframework:spock-core:0.6-groovy-1.8-SNAPSHOT") {
			exclude "groovy-all"
			export = false
		}
    }
	plugins {
		runtime ":resources:1.0"
		
		build( ":release:1.0.0.RC1") {
		    export = false
		}
		build( ":tomcat:$grailsVersion" ) {
		    export = false
		}
		test( ":geb:0.5.1" ) {
		    export = false
		}
		test(":spock:0.6-SNAPSHOT") {
			exclude "spock-grails-support"
			export = false
		}
	}
}
