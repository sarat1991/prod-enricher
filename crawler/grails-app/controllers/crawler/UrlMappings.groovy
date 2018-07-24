package crawler

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/"(controller: 'crawler', action: 'index')
        "500"(controller: 'crawler', action: 'error')
        "404"(controller: 'crawler', action: 'notFound')
    }
}
