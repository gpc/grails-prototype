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
		test "org.seleniumhq.selenium:selenium-firefox-driver:latest.integration"
		test("org.spockframework:spock-grails-support:0.6-groovy-1.7-SNAPSHOT") {
			exclude "spock-core"
		}
		test("org.spockframework:spock-core:0.6-groovy-1.8-SNAPSHOT") {
			exclude "groovy-all"
		}
    }
	plugins {
		runtime ":resources:1.0-RC2-SNAPSHOT"
		runtime ":tomcat:1.4.0.BUILD-SNAPSHOT"
		test ":geb:0.5.1"
		test(":spock:0.6-SNAPSHOT") {
			exclude "spock-grails-support"
		}
	}
}
