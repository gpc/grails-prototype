<!doctype html>
<html>
	<head>
		<g:layoutHead/>
	</head>
	<body>
		<g:layoutBody/>
		<dl>
			<dt>Prototype Version</dt>
			<dd id="prototype-version">not loaded!</dd>
			<dt>Scriptaculous Version</dt>
			<dd id="scriptaculous-version">not loaded!</dd>
			<dt>Rico Version</dt>
			<dd id="rico-version">not loaded!</dd>
		</dl>
		<script>
			if (typeof Prototype != 'undefined') document.getElementById('prototype-version').innerHTML = Prototype.Version;
			if (typeof Scriptaculous != 'undefined') document.getElementById('scriptaculous-version').innerHTML = Scriptaculous.Version;
			if (typeof Rico != 'undefined') document.getElementById('rico-version').innerHTML = Rico.Version;
		</script>
	</body>
</html>