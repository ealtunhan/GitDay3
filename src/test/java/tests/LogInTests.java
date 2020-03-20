package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

// LoginTests are maintained here
// without Page Object approach
public class LogInTests {

   WebDriver driver;

   @BeforeMethod
   public void setUp(){

       WebDriverManager.chromedriver().setup();

       driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

   }

    @Test
    public void negativeLogInTest1(){

        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester2");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test2" + Keys.ENTER);

        String errorMessage = driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertEquals(errorMessage, "Invalid Login or Password.");

    }

    @Test
    public void LogOutTest(){

        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test" + Keys.ENTER);

        driver.findElement(By.id("ctl00_logout")).click();

        Assert.assertEquals(driver.getTitle(), "Web Orders Login");

    }

    @AfterMethod
    public void cleanUp(){

        driver.close();

    }
}
