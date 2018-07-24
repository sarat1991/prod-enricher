# Prod-Enricher #
This service helps in enriching the product with information like reviews, feedbacks etc. based on the given e-commerce product details page. The
service is designed only for electronics products. In particular this service takes only mobile and laptop as sub-category and for other electronics
products, the product assumes it as "invalidCategory". 
## Technologies used ##

  -  Mongo DB
  
  -  RabbitMQ
  
  -  Selenium 
  
  -  Grails (3.3.0)
    
## Installation of 3rd softwares and framework ##
   Install the following softwares in your local machine. 
  - mongodb
  
  - rabbit mq ( product currently supports without username and password for rabbitmq)
  
  - Download chrome web driver.
  
  - Grails ( Grails Version: 3.3.0, JVM Version: 1.8.0_171. Use SDKMAN for installing grails)
  
  - Robomogo ( mongodb client)
  
  - Chrome browser

## How to run from terminal ##
   ### Metadata ###
   
   1. Now navigate to the metadata directory (cd metadata)
   
   2. Set the following environment variables.
   
            export YOUTUBE_API_KEY= #YOUTUBE_KEY
            export QUEUE_HOST=localhost 
   
   3. Run the following command.
    
                grails run-app 
   4. The following message can be seen
   
     [*] Waiting for messages
    Grails application running at http://localhost:8002/metadata/ in environment: development
   
   ### Crawler ###
   
   1. Now navigate to the crawler directory (cd crawler)
   2. Set the following environment variables.
    
            export QUEUE_HOST=localhost
            export BROWSER_WEB_DRIVER=/home/saraths/Softwares/chromedriver
            export BROWSER_LOCATION=/usr/bin/google-chrome 
        
   3. Run the following command.
 
             grails run-app 
   4. The following message can be seen  
    
    Running application...
     [*] Waiting for messages
    Grails application running at http://localhost:8005/crawler/ in environment: development


#See the Product work

  1. Now hit the url in the browser. This opens up a swagger document 
  
            http://localhost:8002/metadata/webjars/swagger-ui/3.0.17/index.html?url=/metadata/apidoc
      
  2. Copy a laptop or mobile product url from amazon.in website and paste in the productUri

  3. Next use the /product api to get the review and feedbacks of the product(only from youtube)
  
  
#Assumptions and Limitations

   1. The service accepts only Amazon.in url product details page as input.
   2. Only electronics is the category, the service is tested for. Mobiles and laptop are only sub-category are valid categories. 
   3. Huge number of request like 100 requests/seconds cannot be done since the e-commerce website will block if it is done.

#Future scope
   1. logging needs to be done
   (under construction)
   
 
 
    

       
        
