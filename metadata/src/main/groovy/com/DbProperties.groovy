package com

enum DbProperties {

   CATEGORY ("category"),
   BRAND  ("Brand"),
   OPERATING_SYSTEM ("Operating System") ,
   OS("OS"),
   RAM ("RAM"),
   WEIGHT("Item Weight"),
   MODEL_NUMBER("Item model number"),
   SCREEN_SIZE("Screen Size"),
   HARD_DRIVE_SIZE("Hard Drive Size"),
   PROCESSOR_TYPE("Processor Type"),
   SERIES ("Series"),
   PRODUCT_IDENTIFIER("productIdentifier"),
   PRODUCT_URL("productUrl")


   String name

   DbProperties(String name){
      this.name = name
   }
}