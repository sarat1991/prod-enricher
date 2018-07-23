package metadata

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/apidoc/$action?/$id?"(controller: "apiDoc", action: "getDocuments")
        "/bootstrap"(controller: Queue, action: [GET:'index'])
        "/url"(controller: Queue, action: [GET:'websiteName'])
        "/product/"(controller: Queue, action: [GET:'getProductMetadata'])
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
