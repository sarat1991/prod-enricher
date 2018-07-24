package metadata

import org.bson.types.ObjectId

class Electronics {

    ObjectId id
    String url
    String identifier
    String os
    String ram
    String itemWeight
    String series
    String itemModelNumber
    String brand
    String screenSize
    String hardDriveSize
    String processorType
    String category
    List youtubeMetadata

    static mapping = {
        version false
        url index: true
        version false
        collection "electronics"
        database "prodEnricher"
    }
    static constraints = {
        url unique: true
        identifier nullable: true
        os nullable: true
        ram nullable: true
        itemWeight nullable: true
        series nullable: true
        itemModelNumber nullable: true
        brand nullable: true
        screenSize nullable: true
        hardDriveSize nullable: true
        processorType nullable: true
        category nullable: true
        youtubeMetadata nullable: true

    }
}
