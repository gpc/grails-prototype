# Prototype Grails Plugin

This plugin integrates the [Prototype][1], [Scriptaculous][2] and [Rico][3] Javascript libraries into Grails. In Grails versions prior to 1.4 these libraries were included by default in Grails. As of Grails 1.4 [jQuery][5] is the default Javascript library in the Grails distribution with this plugin providing an alternative for those who would rather use Prototype or want to upgrade an existing Grails application that uses Prototype based script.

# Using the plugin

In order to use Prototype rather than jQuery in a Grails app you just need to:

	grails uninstall-plugin jquery
	grails install-plugin prototype

## Including libraries

The plugin provides three Javascript libraries, _prototype_, _scriptaculous_ and _rico_. The _scriptaculous_ and _rico_ libraries depend on _prototype_ so if you are including either of those it is _not_ necessary to include _prototype_ separately.

For example:

	<g:javascript plugin="prototype" library="scriptaculous"/>
	
Will be converted to:

	<script type="text/javascript" src="/myapp/plugins/prototype-1.0-SNAPSHOT/js/prototype/prototype.js"></script>
	<script type="text/javascript" src="/myapp/plugins/prototype-1.0-SNAPSHOT/js/prototype/scriptaculous.js"></script>
	
## Using the Resources plugin

If you are using the [Resources plugin][4] you can include Prototype libraries that way. e.g.:

	<r:require module="scriptaculous"/>
	
## Using Grails' AJAX tags

In order to have Grails' built-in AJAX tags such as `g:formRemote` and `g:remoteLink` to generate Prototype code you need to add the following declaration to your GSP:

	<g:setProvider library="prototype"/>

[1]:http://prototypejs.org/
[2]:http://script.aculo.us/
[3]:http://openrico.org/
[4]:http://grails.org/plugin/resources
[5]:http://jquery.org/