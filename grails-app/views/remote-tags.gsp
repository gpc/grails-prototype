<!doctype html>
<html>
	<head>
		<title>Prototype-based remote tags</title>
		<g:javascript library="prototype"/>
		<style>
			h1, h2 { margin: 0.5em 0; }
			section, output { display: block; padding: 0.5em; }
			output { border: 2px dotted #ccc; height: 1em; width: 20em; margin: 0.5em 0 0 0; color: #666; }
		</style>
        <r:layoutResources/>
	</head>
	<body>
		<h1>Prototype-based remote tags</h1>
		
		<div id="remote-field">
			<h2>g:remoteField</h2>
			<g:remoteField controller="test" action="reverse" update="remote-field-output" name="value"/>
			<output id="remote-field-output"></output>
		</div>
		
		<section id="remote-function">
			<h2>g:remoteFunction</h2>
			<button type="button" onclick="${remoteFunction(controller: 'test', action: 'reverse', params: [value: 'Whatever!'], update: 'remote-function-output')}">Click Me!</button>
			<output id="remote-function-output"></output>
		</section>
		
		<section id="remote-link">
			<h2>g:remoteLink</h2>
			<g:remoteLink controller="test" action="reverse" params="[value: 'Whatever!']" update="remote-link-output">Click Me!</g:remoteLink>
			<output id="remote-link-output"></output>
		</section>
		
		<section id="submit-to-remote">
			<h2>g:submitToRemote</h2>
			<g:form controller="test" action="reverse">
				<input name="value">
				<g:submitToRemote controller="test" action="reverse" update="submit-to-remote-output" value="Submit"/>
			</g:form>
			<output id="submit-to-remote-output"></output>
		</section>
		
		<section id="form-remote">
			<h2>g:formRemote</h2>
			<g:formRemote name="test" url="[controller: 'test', action: 'reverse']" update="form-remote-output">
				<input name="value">
				<button type="submit">Submit</button>
			</g:formRemote>
			<output id="form-remote-output"></output>
		</section>
		
		<r:layoutResources/>
	</body>
</html>