class PrototypeGrailsPlugin {
	
    def version = "1.4.BUILD-SNAPSHOT"
    def grailsVersion = "1.3.7 > *"
    def dependsOn = [:]
    def pluginExcludes = [
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
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }
}
