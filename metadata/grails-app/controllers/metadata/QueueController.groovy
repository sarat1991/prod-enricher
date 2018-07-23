package metadata

import grails.converters.JSON
/*import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation

@Api( tags = ["Product-Enricher API"] ,description = "Product Enricher service", position = 1)*/
class QueueController {

    def queueService
    def productInfoService
    def index() {
        queueService.bootstrap()
        render "bootstrap"
    }

    /*@ApiOperation(value = "Request product metadata", nickname = "/product/{productUri}", produces = "application/json", consumes = "application/json", httpMethod = "GET")
    @ApiImplicitParams([
            @ApiImplicitParam(name = "productUri" , paramType = "path", dataType = "string" ,required = true)])*/
    def websiteName(){
        def message = params.productUri
        render productInfoService.postForCrawling(message) as JSON
    }

  /*  @ApiOperation(value = "GET product Info", nickname = "/product/{productUri}", produces = "application/json", consumes = "application/json", httpMethod = "GET")
    @ApiImplicitParams([
            @ApiImplicitParam(name = "productUri" , paramType = "path", dataType = "string" ,required = true)])
*/
    def getProductMetadata(){
        def producturi = params.productUri
        render productInfoService.getProductMetadata(producturi) as JSON
    }
}
