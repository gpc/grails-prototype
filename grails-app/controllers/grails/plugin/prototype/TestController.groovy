package grails.plugin.prototype

class TestController {
	
	def reverse = {
		render contentType: "text/plain", text: params.value.reverse()
	}
	
}