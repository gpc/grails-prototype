package grails.plugin.prototype

import geb.spock.GebReportingSpec
import spock.lang.Unroll

class ResourcesSpec extends GebReportingSpec {
	
	@Unroll
	def "prototype javascript libraries load correctly with #tag"() {
		given:
		go url
		
		expect:
		$("#prototype-version").text() == "1.7"
		$("#scriptaculous-version").text() == "1.8.3"
		$("#rico-version").text() == "1.1-beta2"
		
		where:
		url         | tag
		"libraries" | "g:javascript"
		"resources" | "r:use"
	}
	
}