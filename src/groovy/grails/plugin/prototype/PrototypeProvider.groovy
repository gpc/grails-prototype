package grails.plugin.prototype

import org.codehaus.groovy.grails.plugins.web.taglib.JavascriptProvider
import org.codehaus.groovy.grails.plugins.web.taglib.JavascriptValue

/**
 * Prototype implementation of JavaScript provider
 *
 * @author Graeme Rocher
 */
class PrototypeProvider implements JavascriptProvider {

    def doRemoteFunction(taglib,attrs, out) {
        out << 'new Ajax.'
        if (attrs.update) {
            out << 'Updater('
            if (attrs.update instanceof Map) {
                out << "{"
                def update = []
                if (attrs.update?.success) {
                    update << "success:'${attrs['update']['success']}'"
                }
                if (attrs.update?.failure) {
                    update << "failure:'${attrs['update']['failure']}'"
                }
                out << update.join(',')
                out << "},"
            }
            else {
                out << "'" << attrs.update << "',"
            }
            attrs.remove("update")
        }
        else {
            out << "Request("
        }
        out << "'"

        def url
        def jsParams = [:]

        if (attrs.params instanceof Map) {
            jsParams = attrs.params?.findAll { it.value instanceof JavascriptValue }
            jsParams?.each { attrs.params?.remove(it.key) }
        }

        if (attrs.url) {
            url = taglib.createLink(attrs.url)?.toString()
        }
        else {
            url = taglib.createLink(attrs)?.toString()
        }

        if (!attrs.params) attrs.params = [:]
        jsParams?.each { attrs.params[it.key] = it.value }

        def i = url?.indexOf('?')

        if (i > -1) {
            if (attrs.params instanceof String) {
                attrs.params += "+'&${url[i+1..-1].encodeAsJavaScript()}'"
            }
            else if (attrs.params instanceof Map) {
                def params = createQueryString(attrs.params)
                attrs.params = "'${params}${params ? '&' : ''}${url[i+1..-1].encodeAsJavaScript()}'"
            }
            else {
                attrs.params = "'${url[i+1..-1].encodeAsJavaScript()}'"
            }
            out << url[0..i-1]
        }
        else {
            out << url
        }
        out << "',"
        // process options
        out << getAjaxOptions(attrs)
        // close
        out << ');'
        attrs.remove('params')
    }

    private String createQueryString(params) {
        def allParams = []
        for (entry in params) {
            def value = entry.value
            def key = entry.key
            if (value instanceof JavascriptValue) {
                allParams << "${key.encodeAsURL()}='+${value.value}+'"
            }
            else {
                allParams << "${key.encodeAsURL()}=${value.encodeAsURL()}".encodeAsJavaScript()
            }
        }
        if (allParams.size() == 1) {
            return allParams[0]
        }

        return allParams.join('&')
    }

    // helper function to build ajax options
    def getAjaxOptions(options) {
        def ajaxOptions = []

        if (options.method) {
            ajaxOptions << "method:'${options.method.toLowerCase()}'"
        }

        // necessary defaults
        def optionsAttr = options.remove('options')
        def async = optionsAttr?.remove('asynchronous')
        if (async != null) {
            ajaxOptions << "asynchronous:${async}"
        }
        else {
            ajaxOptions << "asynchronous:true"
        }

        def eval = optionsAttr?.remove('evalScripts')
        if (eval != null) {
            ajaxOptions << "evalScripts:${eval}"
        }
        else {
            ajaxOptions << "evalScripts:true"
        }

        if (options) {
            // process callbacks
            def callbacks = options.findAll { k,v ->
                k ==~ /on(\p{Upper}|\d){1}\w+/
            }
            callbacks.each { k,v ->
                ajaxOptions << "${k}:function(e){${v}}"
                options.remove(k)
            }
            if (options.params) {
                def params = options.remove('params')
                if (params instanceof Map) {
                    params = createQueryString(params)
                }
                ajaxOptions << "parameters:${params}"
            }
        }
        // remaining options
        optionsAttr.each { k, v ->
            if (k != 'url') {
                switch(v) {
                    case 'true': ajaxOptions << "${k}:${v}"; break
                    case 'false': ajaxOptions << "${k}:${v}"; break
                    case ~/\s*function(\w*)\s*/: ajaxOptions << "${k}:${v}"; break
                    case ~/Insertion\..*/: ajaxOptions << "${k}:${v}"; break
                    default:ajaxOptions << "${k}:'${v}'"; break
                }
            }
        }

        return "{${ajaxOptions.join(',')}}"
    }

    def prepareAjaxForm(attrs) {
        if (!attrs.forSubmitTag) attrs.forSubmitTag = ""

        attrs.params = "Form.serialize(this${attrs.remove('forSubmitTag')})".toString()
    }
}
