package metadata

class UrlMappings {

    static mappings = {
        "/crawl"(controller: 'metadata', action: [GET:'requestMetadata'])
        "/product"(controller: 'metadata', action: [GET:'getProductInfo'])
        "/"(controller: 'metadata', action: 'index')
        "500"(controller: 'metadata', action: 'error')
        "404"(controller: 'metadata', action: 'notFound')
    }
}
