package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.WaitUtils;

public class LoginTest {

    WebDriver driver;
    WaitUtils wait;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WaitUtils(driver);

        driver.get("https://web-bung.vercel.app/login");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

//    correct id and pass
    @Test
    public void TC01_ValidLogin() {

        wait.element(By.id("username")).sendKeys("ADMIN0001");
        wait.element(By.id("passwords")).sendKeys("Gojo@1805");
        wait.click(By.id("loginButton"));
//        System.out.println("After Login URL: " + driver.getCurrentUrl());
        wait.waitForHomePage();
//        System.out.println("After Sidebar URL: " + driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("/home"));
    }

    @Test
    public void TC02_InvalidUsername() {

        wait.element(By.id("username")).sendKeys("WrongUser");
        wait.element(By.id("passwords")).sendKeys("Gojo@1805");
        wait.click(By.id("loginButton"));
    }

    @Test
    public void TC03_InvalidPassword() {
        wait.element(By.id("username")).sendKeys("ADMIN0001");
        wait.element(By.id("passwords")).sendKeys("Wrong@123");
        wait.click(By.id("loginButton"));
    }

    @Test
    public void TC04_EmptyUsername() {
        wait.element(By.id("username")).sendKeys("");
        wait.element(By.id("passwords")).sendKeys("Gojo@1805");
        wait.click(By.id("loginButton"));
        String actualError = wait.element(By.id("message")).getText();
        Assert.assertEquals(actualError,"User ID is required.");
    }

    @Test
    public void TC05_EmptyPassword() {
        wait.element(By.id("username")).sendKeys("ADMIN0001");
        wait.element(By.id("passwords")).sendKeys("");
        wait.click(By.id("loginButton"));
        String actualError = wait.element(By.id("message")).getText();
        Assert.assertEquals(actualError, "Password is required.");
    }

    @Test
    public void TC06_EmptyUsernameAndPassword() {
        wait.element(By.id("username")).sendKeys("");
        wait.element(By.id("passwords")).sendKeys("");
        wait.click(By.id("loginButton"));
        String actualError = wait.element(By.id("message")).getText();
        Assert.assertEquals(actualError, "User ID and Password are required.");

    }


}