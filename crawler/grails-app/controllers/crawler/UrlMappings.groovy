package crawler

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/bootstrap"(controller: Queue, action: [GET:'index'])
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
