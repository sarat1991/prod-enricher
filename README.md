# Prod-Enricher #
This service helps in enriching the product with information like reviews, feedbacks etc. based on the given e-commerce product details page. The
service is designed only for mobile and laptop as category and for other electronics products, the service assumes it as "invalidCategory". 

## Architecture diagram ##
[Architecture Diagram](images/architecture.jpg)

## Technologies used ##

  -  Mongo DB
  
  -  RabbitMQ
  
  -  Selenium 
  
  -  Grails (3.3.0)
    
## Installation of 3rd softwares and framework ##
   Install the following softwares in your local machine. 
  - mongodb
  
  - rabbit mq ( service currently supports without username and password for rabbitmq)
  
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
   2. Download chrome web driver and chrome browser(if doesn't exist). Then, set the following environment variables.
    
            export QUEUE_HOST=localhost
            export BROWSER_WEB_DRIVER=/home/saraths/Softwares/chromedriver
            export BROWSER_LOCATION=/usr/bin/google-chrome 
        
   3. Run the following command.
 
             grails run-app 
   4. The following message can be seen  
    
    Running application...
     [*] Waiting for messages
    Grails application running at http://localhost:8005/crawler/ in environment: development


## See the Product work ##

  1. Now hit the url in the browser. This opens up a swagger document 
  
            http://localhost:8002/metadata/webjars/swagger-ui/3.0.17/index.html?url=/metadata/apidoc
      
  2. Copy a laptop or mobile product url from amazon.in website and paste in the productUri

  3. Next use the /product api to get the review and feedbacks of the product(only from youtube)
  
  
## Assumptions and Limitations ##

   1. The service only accepts amazon.in electronics product detail page as input.
   2. In Electronics only mobiles and laptop are valid categories, others are invalid. 
   3. Huge number of request like 100 requests/seconds cannot be done since the e-commerce website will block if it is done.
   

## Future scope ##
   1. Logging needs to be done
   2. Retrieving the product information from other Ecommerce websites since it will be a verified and validated information. 
   3. Adding relevant testcases. 
   4. Extending to other categories of product.
   5. Closing the connection immediately after taking the html source. Parsing can be done later on the retrieved html page. 
   6. Storing the configuration of crawler seperately for each and every website. 
   7. Having a fleet of proxies for crawling the same Ecommerce website without getting blocked. 
   8. Getting input form another Ecommerce website (like Flipkart) and generating product identifier using the attributes from that website and matching it with already exisisting record(i.e. from Amazon.in)
   
 
 
    

       
        
