import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.concurrent.TimeUnit

/**
 * Scraper works for camera, mobile, laptop..
 * */
class ExampleScrapper {
    static Logger log = LoggerFactory.getLogger(ExampleScrapper.class)
    static void main(String[] args) {
/*        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/google-chrome")*/
        String exePath = "/home/saraths/Softwares/chromedriver"
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("/usr/bin/google-chrome");
        chromeOptions.addArguments("--headless");
        ChromeDriver driver=new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        getProductDetails(driver);
/*       driver.get("https://gadgets.ndtv.com/");
        WebElement element=driver.findElement(By.xpath("//*[@id=\"searchtext\"]"));
        element.sendKeys("moto x play");
        WebElement button=driver.findElement(By.xpath("//*[@id=\"searchicon\"]"));
        button.click();*/
        driver.close()
    }
    /**
     * //*[@id="prodDetails"]/div[2]/div[1]/div[1]/div[2]/div/div/table
     *
     * */
   static void getProductDetails(ChromeDriver driver){
       driver.get("https://www.amazon.in/MSI-GT72-2QE-Dominator-backlight/dp/B01560RGKA")
       //driver.get("https://www.amazon.in/Sony-Cyber-shot-Camera-Optical-Memory/dp/B00IHS3RGQ")
       //WebElement element = driver.findElement(By.xpath("//*[@id=\"prodDetails\"]/div[2]/div[1]/div[1]/div[2]/div/div/table/tbody"));
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
           log.info(" The crawler can't scrap the particular product info.")
       }

       String title = driver.findElements(By.xpath("//span[@id='productTitle']")).size() > 0 ? driver.findElements(By.xpath("//span[@id='productTitle']")).get(0).text: null
       String productName = "";
       if(title != null){
           productName = title.substring(0,title.trim().lastIndexOf('(')-1);
       }
       println productName
       WebElement techTable = driver.findElement(By.xpath("//div[contains(@class,'column col1')]//table[@cellspacing='0']"));
       //Get all web elements by tag name 'tr'
       List<WebElement> rowVals = techTable.findElements(By.tagName("tr"));

       System.out.println("---------------");
       //Loop through the remaining rows
       for(int i=0; i<rowVals.size(); i++){
           //Get each row's column values by tag name
           List<WebElement> colVals = rowVals.get(i).findElements(By.tagName("td"));
           //Loop through each column
           for(int j=0; j<colVals.size(); j++){
               //Print the coulumn values to console
               System.out.println(colVals.get(j).getText());
           }
           //Just a separator for each row
           System.out.println("---------------");
       }

    }


}
