package metadata

import com.external.ExternalServiceBuilder
import com.traits.ExternalService

class ProductInfoService {

    def rabbitQueueService
    def mongoService
    def grailsApplication

    private static final String CATEGORY = "category"
    private static final String INVALID_CATEGORY = "invalidCategory"
    private static final String PRODUCT_IDENTIFIER = "productIdentifier"
    private static final String POST_MESSAGE =  "Your request has been sent for processing. Kindly check the response using /product API after few minutes."
    private static final String ITEM_NOT_FOUND = "No item found"

    def postForCrawling(String productUri) {
        def productInfo = mongoService.get(productUri)
        if(productInfo)
            return productInfo
        rabbitQueueService.postMessage(productUri)
        return [message : POST_MESSAGE]
    }

    def getProductInfo(String productUri){
        return mongoService.get(productUri)?:[message: ITEM_NOT_FOUND]
    }

    def productDetailHandler(Map productDetails){
        mongoService.add(productDetails)
        if(productDetails.get(CATEGORY) == INVALID_CATEGORY)
            return
        def productIdentifier =  productDetails.get(PRODUCT_IDENTIFIER)
        def externSites = externalService()
        storeProductMetadata(externSites, productIdentifier)
    }

    private List externalService(){
        // todo: get the list of external services fom the properties can(future scope)
        List externs = []
        externs << ExternalServiceBuilder.create(ExternalServiceBuilder.YOUTUBE, grailsApplication)
        return externs
    }

    void storeProductMetadata(List<ExternalService> externalServices, productIdentifier){
        // Todo: promises can be added to make asynchronous processing
        externalServices.each {
            List metadataInfo = it.get(productIdentifier);
            mongoService.update(productIdentifier, metadataInfo)
        }
    }
}
