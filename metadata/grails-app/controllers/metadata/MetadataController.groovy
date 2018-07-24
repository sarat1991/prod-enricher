package metadata

import grails.converters.JSON
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation

@Api( tags = ["Product-Enricher API"] ,description = "Product Enricher service", position = 1)
class MetadataController {

    private static final String SERVICE_UP = "Metadata service is up and running"
    private static final String ERROR = "Internal Server Error"
    private static final String NOTFOUND = "Not found"

    def productInfoService
    def index() {
        render SERVICE_UP
    }
    def notFound(){
        render NOTFOUND
    }
    def error(){
        render ERROR
    }

    @ApiOperation(value = "Request product metadata", nickname = "/crawl", produces = "application/json", consumes = "application/json", httpMethod = "GET")
    @ApiImplicitParams([
            @ApiImplicitParam(name = "productUri" , paramType = "query", dataType = "string" ,required = true)])
    def requestMetadata(){
        def productUri = params.productUri
        render productInfoService.postForCrawling(cleanseProductURI(productUri)) as JSON
    }

    @ApiOperation(value = "GET product Info", nickname = "/product", produces = "application/json", consumes = "application/json", httpMethod = "GET")
    @ApiImplicitParams([
            @ApiImplicitParam(name = "productUri" , paramType = "query", dataType = "string" ,required = true)])
    def getProductInfo(){
        String productUri = params.productUri
        render productInfoService.getProductInfo(cleanseProductURI(productUri)) as JSON
    }

    private def cleanseProductURI(String productUri){
        return productUri.substring(0,productUri.lastIndexOf("/ref=")>1?productUri.lastIndexOf("/ref="):productUri.size())
    }
}
