package metadata

import com.external.ExternalServiceBuilder
import com.traits.ExternalService

class ProductInfoService {

    def queueService
    def mongoService
    def grailsApplication

    private static final String CATEGORY = "category"
    private static final String INVALID_CATEGORY = "invalidCategory"
    private static final String PRODUCT_IDENTIFIER = "productIdentifier"

    def postForCrawling(String productUri) {
        def productInfo = mongoService.get(productUri)
        if(productInfo)
            return productInfo
        queueService.postMessage(productUri)
        return [message : "Kindly check for the metadata shortly"]
    }

    def getProductMetadata(String productUri ){
        return mongoService.get(productUri)
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
        // get the list of external services fom the properties can(future scope)
        List externs = []
        externs << ExternalServiceBuilder.create(ExternalServiceBuilder.YOUTUBE, grailsApplication)
        return externs
    }

    void storeProductMetadata(List<ExternalService> externalServices, productIdentifier){
        // promises can be added to make asynchronous processing
        externalServices.each {
            List metadataInfo = it.get(productIdentifier);
            mongoService.update(productIdentifier, metadataInfo)
        }
    }
}
