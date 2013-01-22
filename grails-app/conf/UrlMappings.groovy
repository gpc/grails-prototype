import grails.util.Environment

class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')

        if (Environment.current == Environment.TEST) {
            "/libraries"(view: '/libraries')
            "/remote-tags"(view: '/remote-tags')
            "/resources"(view: '/resources')
        }
	}
}
