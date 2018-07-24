package metadata.com.storage

import com.DbProperties
import com.traits.Database
import metadata.Electronics

class MongoService implements Database {

    @Override
    void add(Map productDetails) {
       Electronics product = get(productDetails.get(DbProperties.PRODUCT_URL.name))
        if(product)
            return
        product = new Electronics()
        product.url = productDetails.get(DbProperties.PRODUCT_URL.name)
        product.category = productDetails.get(DbProperties.CATEGORY.name)?:null
        product.brand = productDetails.get(DbProperties.BRAND.name)?:null
        product.os = productDetails.get(DbProperties.OPERATING_SYSTEM.name)?:productDetails.get(DbProperties.OS.name)?:null
        product.ram = productDetails.get(DbProperties.RAM.name)?:null
        product.itemWeight = productDetails.get(DbProperties.WEIGHT.name)?:null
        product.itemModelNumber = productDetails.get(DbProperties.MODEL_NUMBER.name)?:null
        product.screenSize = productDetails.get(DbProperties.SCREEN_SIZE.name)?:null
        product.hardDriveSize = productDetails.get(DbProperties.HARD_DRIVE_SIZE.name)?:null
        product.processorType = productDetails.get(DbProperties.PROCESSOR_TYPE.name)?:null
        product.series = productDetails.get(DbProperties.SERIES.name)?:null
        product.brand = productDetails.get(DbProperties.BRAND.name)?:null
        product.identifier = productDetails.get(DbProperties.PRODUCT_IDENTIFIER.name)?:null
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
