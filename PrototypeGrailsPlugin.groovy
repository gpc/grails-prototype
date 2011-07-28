import grails.plugin.prototype.PrototypeProvider
import static org.codehaus.groovy.grails.plugins.web.taglib.JavascriptTagLib.*

class PrototypeGrailsPlugin {
	
    def version = "1.0"
    def grailsVersion = "1.4 > *"
    def dependsOn = [:]
    def pluginExcludes = [
			"grails-app/controllers/**/*",
            "grails-app/views/**/*"
    ]

    def author = "Rob Fletcher"
    def authorEmail = "rob@energizedwork.com"
    def title = "Prototype Grails Plugin"
    def description = '''\\
This plugin adds the Prototype, Scriptaculous and Rico JavaScript libraries to 
Grails and provides integration of those libraries with Grails' Javascript tags.
'''

    def documentation = "http://grails.org/plugin/prototype"

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
		LIBRARY_MAPPINGS.prototype = ["prototype/prototype"]
        LIBRARY_MAPPINGS.scriptaculous = LIBRARY_MAPPINGS.prototype + ["prototype/scriptaculous"]
        LIBRARY_MAPPINGS.rico = LIBRARY_MAPPINGS.prototype + ["prototype/rico"]

		PROVIDER_MAPPINGS.prototype = PrototypeProvider
	    PROVIDER_MAPPINGS.scriptaculous = PrototypeProvider
	    PROVIDER_MAPPINGS.rico = PrototypeProvider
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }
}
