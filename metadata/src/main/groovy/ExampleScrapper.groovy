import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver

class ExampleScrapper {
    static void main(String[] args) {
/*        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/google-chrome")*/
        String exePath = "/home/saraths/Softwares/chromedriver"
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeDriver driver=new ChromeDriver();
        //getProductDetails(driver);
       driver.get("https://gadgets.ndtv.com/");
        WebElement element=driver.findElement(By.xpath("//*[@id=\"searchtext\"]"));
        element.sendKeys("moto x play");
        WebElement button=driver.findElement(By.xpath("//*[@id=\"searchicon\"]"));
        button.click();

    }
    /**
     * //*[@id="prodDetails"]/div[2]/div[1]/div[1]/div[2]/div/div/table
     *
     * */
   static void getProductDetails(ChromeDriver driver){
       driver.get("https://www.amazon.in/Samsung-Galaxy-Tab-7-0-Calling/dp/B078LVWLJX")
       //WebElement element = driver.findElement(By.xpath("//*[@id=\"prodDetails\"]/div[2]/div[1]/div[1]/div[2]/div/div/table/tbody"));
       List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"prodDetails\"]/div[2]/div[1]/div[1]/div[2]/div/div/table/tbody/tr[1]"))

       for(WebElement row : rows){
           row.findElements(By.xpath("/td[@class=]"))
       }
    }


}
