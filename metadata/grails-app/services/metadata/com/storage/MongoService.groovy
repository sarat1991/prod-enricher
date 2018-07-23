package metadata.com.storage

import com.traits.Database
import metadata.Electronics

class MongoService implements Database {

    private static final String CATEGORY = "category"
    private static final String BRAND = "Brand"
    private static final String OPERATING_SYSTEM = "Operating System"
    private static final String OS = "OS"
    private static final String RAM = "RAM"
    private static final String itemWeight = "Item Weight"
    private static final String itemModelNumber = "Item model number"
    private static final String brand = "Brand"
    private static final String screenSize = "Screen Size"
    private static final String hardDriveSize = "Hard Drive Size"
    private static final String processorType = "Processor Type"
    private static final String SERIES = "Series"
    private static final String PRODUCT_IDENTIFIER = "productIdentifier"
    private static final String PRODUCT_URL = "productUrl"
    @Override
    void add(Map productDetails) {
//        println "Entered"
        Electronics product = new Electronics()
        product.url = productDetails.get(PRODUCT_URL)
        product.category = productDetails.get(CATEGORY)?:null
        product.brand = productDetails.get(BRAND)?:null
        product.os = productDetails.get(OPERATING_SYSTEM)?:productDetails.get(OS)?:null
        product.ram = productDetails.get(RAM)?:null
        product.itemWeight = productDetails.get(itemWeight)?:null
        product.itemModelNumber = productDetails.get(itemModelNumber)?:null
        product.screenSize = productDetails.get(screenSize)?:null
        product.hardDriveSize = productDetails.get(hardDriveSize)?:null
        product.processorType = productDetails.get(processorType)?:null
        product.series = productDetails.get(SERIES)?:null
        product.brand = productDetails.get(brand)?:null
        product.identifier = productDetails.get(PRODUCT_IDENTIFIER)?:null
        product.save(flush: true, failOnError:true)
    }


    @Override
    def get(String productUri) {
        return Electronics.findByUrl(productUri)
    }

    @Override
    void update(def identifier, List metadata) {
        Electronics product = Electronics.findByIdentifier(identifier as String)
        product.youtubeMetadata = metadata
        product.save(flush: true, failOnError: true)
    }


}
