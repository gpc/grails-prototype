modules = {
	"prototype" { 
		defaultBundle "prototype"
		resource url: [plugin: "prototype", dir: "js/prototype", file: "prototype.js"]
	}
	"scriptaculous" {
		dependsOn 'prototype'
		defaultBundle "prototype"
		resource url: [plugin: "prototype", dir: "js/prototype", file: "scriptaculous.js"]
	}
	"rico" { 
		dependsOn 'prototype'
		defaultBundle "prototype"
		resource url: [plugin: "prototype", dir: "js/prototype", file: "rico.js"]
	}
}