package crawler

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import java.util.concurrent.TimeUnit

class CrawlService {
    def grailsApplication
    private final static String CATEGORY = "category"
    private final static String LAPTOP = "Laptop"
    private final static String MOBILE = "Mobile"
    private final static String BEYOUND_SCOPE = " The crawler can't scrap the particular product info."

    /**
     * Scraper works for camera, mobile, laptop..
     *
     * */
    private def initDriver() {
//        String exePath = "/home/saraths/Softwares/chromedriver"
        String driverPath = grailsApplication.config.crawler.driver
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        String exePath = grailsApplication.config.crawler.browser
        chromeOptions.setBinary(exePath);
        chromeOptions.addArguments("--headless");
        ChromeDriver driver=new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver
    }
/**
 * Sample uri url:   https://www.amazon.in/Sony-Cyber-shot-Camera-Optical-Memory/dp/B00IHS3RGQ
 *
 * */
    def getProductDetails(String uri){
        ChromeDriver driver =  initDriver()
        driver.get(uri)
        def response = [:]
        /* layout check or invalid category */
        if(!isTechTableExists(driver))
            return null
        /* Category check */
        updateCategory(response, driver)
        if(!response.get(CATEGORY))
            return null
        /* Retrieving product Identifier*/
       response.put("productIdentifier", getProductIdentifier(driver))
        /* Parsing the tech table */
        parseTechDetailsTable(driver, response)
        driver.close()
    return response
    }

    void parseTechDetailsTable(ChromeDriver driver, Map response){
        WebElement techTable = driver.findElement(By.xpath("//div[contains(@class,'column col1')]//table[@cellspacing='0']"));
        List<WebElement> rowVals = techTable.findElements(By.tagName("tr"));
        System.out.println("---------------");
        for(int i=0; i<rowVals.size(); i++){
            List<WebElement> colVals = rowVals.get(i).findElements(By.tagName("td"));
            if(colVals.get(0).getText().trim().size() > 0)
                response.put(colVals.get(0).getText().trim() , colVals.get(1).getText().trim())
            System.out.println(colVals.get(0).getText()+" "+colVals.get(1).getText().trim());
            System.out.println("---------------");
        }
    }

    boolean isTechTableExists(ChromeDriver driver){
        List<WebElement> technical_Details = driver.findElements(By.xpath("//span[contains(text(),'Technical Details')]"))
        boolean isTechDetailPresent = false;
        if (technical_Details.size() != 0){
            for (WebElement techDetail : technical_Details){
                if(techDetail.isDisplayed()){
                    isTechDetailPresent = true
                }
            }
        }
        if(!isTechDetailPresent || technical_Details.size() == 0){
            System.out.println(BEYOUND_SCOPE)
            return false
        }
        return true
    }

    String getProductIdentifier(ChromeDriver driver){
        /**
         * MSI GT72 2QE Dominator Pro G (Dragon edition) (GTX 980M 8GB GDDR5) w/ backlight multi color KB ==> MSI GT72 2QE Dominator Pro G (Dragon edition)
         * */
        List<WebElement> productTitle =driver.findElements(By.xpath("//span[@id='productTitle']"))
        String productName = "";
        if (productTitle.size() != 0){
            String title = driver.findElements(By.xpath("//span[@id='productTitle']")).get(0).text
            productName = title.substring(0,title.trim().lastIndexOf('(')>1?title.trim().lastIndexOf('(')-1: title.size());
        }
        return  productName
    }

    void updateCategory(Map response, ChromeDriver driver){
        List<WebElement> categoryHeading = driver.findElements(By.xpath("//div[@id='prodDetails']//h2"))
        String productCategory = categoryHeading.size() > 0 ? categoryHeading.get(0).text : null
        if(!productCategory){
            response << [category: null]
        }
        else if(productCategory.contains(LAPTOP)){
            response <<[category : "laptop"]
        }
        else if(productCategory.contains(MOBILE)){
            response <<[category: "mobile"]
        }

    }
}
