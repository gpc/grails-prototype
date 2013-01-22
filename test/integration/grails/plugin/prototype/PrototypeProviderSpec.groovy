package grails.plugin.prototype

import grails.plugin.spock.GroovyPagesSpec
import spock.lang.Issue
import spock.lang.Unroll

class PrototypeProviderSpec extends GroovyPagesSpec {
	
	def setup() {
        request.currentRequest.setAttribute("org.codehaus.grails.INCLUDED_JS_LIBRARIES", ['prototype'])
	}

    def "formRemote with exact params"() {
		when:
        template = '<g:formRemote name="myForm" url="[controller:\'person\', action:\'show\', params:[id:\'one\']]" ><g:textField name="foo" /></g:formRemote>'

		then:
		output == '<form onsubmit="new Ajax.Request(\'/person/show/one\',{asynchronous:true,evalScripts:true,parameters:Form.serialize(this)});return false" method="post" action="/person/show/one" id="myForm"><input type="text" name="foo" value="" id="foo" /></form>'
    }

    def "formRemote with extra params"() {
		when:
        template = '<g:formRemote name="myForm" url="[controller:\'person\', action:\'show\', params:[id:\'one\', var2:\'two\']]" ><g:textField name="foo" /></g:formRemote>'

		then:
        output == '<form onsubmit="new Ajax.Request(\'/person/show/one\',{asynchronous:true,evalScripts:true,parameters:Form.serialize(this)+\'&var2=two\'});return false" method="post" action="/person/show/one?var2=two" id="myForm"><input type="text" name="foo" value="" id="foo" /></form>'
    }

    /**
     * <p>Tests that the 'formRemote' tag defaults to supplied 'method'
     * and 'action' attributes in fallback mode, i.e. when javascript
     * is unavailable or disabled.</p>
     *
     * <p>Also makes sure no regressions on http://jira.codehaus.org/browse/GRAILS-3330</p>
     */
    def "formRemote with overrides"() {
		when:
        template = '<g:formRemote name="myForm" method="GET" action="/person/showOld?id=one&var2=two" url="[controller:\'person\', action:\'show\', params: [id:\'one\', var2:\'two\']]" ><g:textField name="foo" /></g:formRemote>'

		then:
        output == '<form onsubmit="new Ajax.Request(\'/person/show/one\',{method:\'get\',asynchronous:true,evalScripts:true,parameters:Form.serialize(this)+\'&var2=two\'});return false" method="GET" action="/person/showOld?id=one&var2=two" id="myForm"><input type="text" name="foo" value="" id="foo" /></form>'
    }

    @Issue("http://jira.grails.org/browse/GPPROTO-4")
    def "remoteLink with extra params"() {
		when:
        template = '<g:remoteLink controller="person" action="show" params="[id:\'one\', var2:\'two\']" elementId="myid">hello</g:remoteLink>'

		then:
        output == '<a href="/person/show/one?var2=two" onclick="new Ajax.Request(\'/person/show/one\',{asynchronous:true,evalScripts:true,parameters:\'var2=two\'});return false;" id="myid">hello</a>'
    }

    @Issue("http://jira.grails.org/browse/GPPROTO-4")
    @Issue("http://jira.grails.org/browse/GPPROTO-5")
    def "remoteLink for updating with extra params"() {
		when:
        template = '<g:remoteLink update="update-target" controller="person" action="show" params="[id:\'one\', var2:\'two\']" elementId="myid">hello</g:remoteLink>'

		then:
        output == '<a href="/person/show/one?var2=two" onclick="new Ajax.Updater(\'update-target\',\'/person/show/one\',{asynchronous:true,evalScripts:true,parameters:\'var2=two\'});return false;" id="myid">hello</a>'
    }

	@Unroll
    def "remoteFunction"() {
		when:
		template = fragment
		
		then:
		output == expectedOutput
		
		where:
		fragment                                                                                                                             | expectedOutput
		'<g:remoteFunction controller="test" action="action"/>'                                                                              | "new Ajax.Request('/test/action',{asynchronous:true,evalScripts:true});"
		'<g:remoteFunction controller="test" action="action" params="[test: \'hello\']"/>'                                                   | "new Ajax.Request('/test/action',{asynchronous:true,evalScripts:true,parameters:'test=hello'});"
		'<g:remoteFunction controller="test" action="action" update="[success: \'updateMe\']" options="[insertion: \'Insertion.Bottom\']"/>' | "new Ajax.Updater({success:'updateMe'},'/test/action',{asynchronous:true,evalScripts:true,insertion:Insertion.Bottom});"
	}

    def "submitToRemote with extra params"() {
		when:
        template = '<g:submitToRemote name="myButton" url="[controller:\'person\', action:\'show\', params:[id:\'one\', var2:\'two\']]" ></g:submitToRemote>'
        
		then:
        output == '<input onclick="new Ajax.Request(\'/person/show/one\',{asynchronous:true,evalScripts:true,parameters:Form.serialize(this.form)+\'&var2=two\'});return false" type="button" name="myButton"></input>'
    }

    def "remoteFunction with asynchronous property"() {
		when:
        template = '<g:remoteFunction controller="bar" action="foo" options="[asynchronous:false]" />'
        
		then:
        output == "new Ajax.Request('/bar/foo',{asynchronous:false,evalScripts:true});"
    }

    def "remoteFunction with extra params"() {
		when:
        template = '<g:remoteFunction controller="person" action="show" params="[id:\'one\', var2:\'two\']" />'
        
		then:
        output == "new Ajax.Request('/person/show/one',{asynchronous:true,evalScripts:true,parameters:'var2=two'});"
    }

    def "remoteField"() {
		when:
        template = '<g:remoteField controller="test" action="changeTitle" update="titleDiv" name="title" value="testValue"/>'

		then:
		output == "<input type=\"text\" name=\"title\" value=\"testValue\" onkeyup=\"new Ajax.Updater('titleDiv','/test/changeTitle',{asynchronous:true,evalScripts:true,parameters:'value='+this.value});\" />"
    }

    def "remoteField with additional args"() {
		when:
        template = '<g:remoteField controller="bar" action="storeField" id="2" name="nv" paramName="pnv" params="\'a=b&\'" />'

		then:
        output == '<input type="text" name="nv" value="" onkeyup="new Ajax.Request(\'/bar/storeField/2\',{asynchronous:true,evalScripts:true,parameters:\'a=b&\'+\'pnv=\'+this.value});" />'
    }

    def "remoteField with extra params"() {
		when:
        template = '<g:remoteField controller="test" action="hello" id="1" params="[var1: \'one\', var2: \'two\']" update="success" name="myname" value="myvalue"/>'
        
		then:
        output == '<input type="text" name="myname" value="myvalue" onkeyup="new Ajax.Updater(\'success\',\'/test/hello/1\',{asynchronous:true,evalScripts:true,parameters:\'value=\'+this.value+\'&var1=one&var2=two\'});" />'
    }

	@Issue("http://jira.codehaus.org/browse/GRAILS-1304")
    def "remoteLink"() {
		when:
        template = '<g:remoteLink controller="person" action="show" update="async" params="[id:\'0\']">Show async</g:remoteLink>'

		then:
        output == '<a href="/person/show/0" onclick="new Ajax.Updater(\'async\',\'/person/show/0\',{asynchronous:true,evalScripts:true});return false;">Show async</a>'
    }

	@Issue("http://jira.codehaus.org/browse/GRAILS-7062")
    def "remoteLink with events"() {
		when:
        template = '<g:remoteLink controller="person" action="show" update="success" onComplete="doSomething();" on404="handleNotFound();">Test</g:remoteLink>'

		then:
        output == '<a href="/person/show" onclick="new Ajax.Updater(\'success\',\'/person/show\',{asynchronous:true,evalScripts:true,onComplete:function(e){doSomething();},on404:function(e){handleNotFound();}});return false;">Test</a>'
    }

	@Issue("http://jira.codehaus.org/browse/GRAILS-2468")
    def "remoteLink with method"() {
		when:
        template = '<g:remoteLink controller="person" action="show" update="success" method="GET">Test</g:remoteLink>'
        
		then:
        output == '<a href="/person/show" onclick="new Ajax.Updater(\'success\',\'/person/show\',{method:\'get\',asynchronous:true,evalScripts:true});return false;">Test</a>'
    }
	
	@Issue("http://jira.codehaus.org/browse/GRAILS-4672")
    void "remoteLink with space before GString variable"() {
		when:
        template = '<g:remoteLink controller="people" action="theAction" params="someParams" update="success" onComplete="doSomething();" title="The Album Is ${variable}" class="hoverLT">${variable}</g:remoteLink>'
		params = [variable: 'Undertow']

		then:
        output.contains('title="The Album Is Undertow"')
    }

	@Issue("http://jira.codehaus.org/browse/GRAILS-4672")
    def "remoteLink with space before and after GString variable"() {
		when:
        template = '<g:remoteLink controller="people" action="theAction" params="someParams" update="success" onComplete="doSomething();" title="The Album Is ${variable} By Tool" class="hoverLT">${variable}</g:remoteLink>'
		params = [variable: 'Undertow']
		
		then:
        output.contains('title="The Album Is Undertow By Tool"')
    }

}
