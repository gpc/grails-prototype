package grails.plugin.prototype

import spock.lang.*
import grails.plugin.geb.*

class RemoteTagsSpec extends GebSpec {
	
	def setupSpec() {
		go "remote-tags.gsp"
	}
	
	def "g:remoteField works with prototype"() {
		when:
		$("#remote-field").value = "Whatever!"
		
		then:
		waitFor {
			$("#remote-field output").text() == "!revetahW"
		}
	}
	
	@Unroll("#tag works with prototype")
	def "remote link and function work with prototype"() {
		when:
		$(container).find("a, button").click()
		
		then:
		waitFor {
			$(container).find("output").text() == "!revetahW"
		}
		
		where:
		tag                | container
		"g:remoteFunction" | "#remote-function"
		"g:remoteLink"     | "#remote-link"
	}
	
	@Unroll("#tag works with prototype")
	def "remote forms work with prototype"() {
		when:
		$(container).value = "Whatever!"
		$(container).find("input[type=button], button[type=submit]").click()
		
		then:
		waitFor {
			$(container).find("output").text() == "!revetahW"
		}
		
		where:
		tag                | container
		"g:submitToRemote" | "#submit-to-remote"
		"g:formRemote"     | "#form-remote"
	}
	
}