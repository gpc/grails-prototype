# Prototype Grails Plugin

This plugin integrates the [Prototype][1], [Scriptaculous][2] and [Rico][3] Javascript libraries into Grails. In Grails versions prior to 1.4 these libraries were included by default in Grails.

# Using the plugin

## Including libraries

The plugin provides three Javascript libraries, _prototype_, _scriptaculous_ and _rico_. The _scriptaculous_ and _rico_ libraries depend on _prototype_ so if you are including either of those it is _not_ necessary to include _prototype_ separately.

For example:

	<g:javascript plugin="prototype" library="scriptaculous"/>
	
Will be converted to:

	<script type="text/javascript" src="/myapp/plugins/prototype-1.0-SNAPSHOT/js/prototype/prototype.js"></script>
	<script type="text/javascript" src="/myapp/plugins/prototype-1.0-SNAPSHOT/js/prototype/scriptaculous.js"></script>
	
## Using the Resources plugin

If you are using the [Resources plugin][4] you can include Prototype libraries that way. e.g.:

	<r:use module="scriptaculous"/>
