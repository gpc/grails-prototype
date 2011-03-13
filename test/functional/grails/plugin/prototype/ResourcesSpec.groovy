package grails.plugin.prototype

import spock.lang.*
import grails.plugin.geb.*

class ResourcesSpec extends GebSpec {
	
	@Unroll("prototype javascript libraries load correctly with #tag")
	def "prototype javascript libraries load correctly"() {
		given:
		go url
		
		expect:
		$("#prototype-version").text() == "1.7"
		$("#scriptaculous-version").text() == "1.8.3"
		$("#rico-version").text() == "1.1-beta2"
		
		where:
		url             | tag
		"libraries.gsp" | "g:javascript"
		"resources.gsp" | "r:use"
	}
	
}