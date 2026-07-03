package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.WaitUtils;

public class SidebarTest {
    WebDriver driver;
    WaitUtils wait;

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WaitUtils(driver);

        // Login
        driver.get("https://web-bung.vercel.app/login");

        wait.element(By.id("username")).sendKeys("ADMIN0001");
        wait.element(By.id("passwords")).sendKeys("Gojo@1805");
        wait.click(By.id("loginButton"));

        wait.waitForHomePage();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void TC07_OpenSidebar() {

        wait.click(By.id("header-menu-toggle"));

        Assert.assertTrue(wait.element(By.id("sidebar")).isDisplayed());
    }
    @Test
    public void TC08_VerifySidebarItems() {

        wait.click(By.id("header-menu-toggle"));

        Assert.assertTrue(wait.element(By.xpath("//*[text()='Dashboard']")).isDisplayed());
        Assert.assertTrue(wait.element(By.xpath("//*[text()='Manual Operation']")).isDisplayed());
        Assert.assertTrue(wait.element(By.xpath("//*[text()='Alarm']")).isDisplayed());
        Assert.assertTrue(wait.element(By.xpath("//*[text()='Audit']")).isDisplayed());
        Assert.assertTrue(wait.element(By.xpath("//*[text()='Parameters Settings']")).isDisplayed());
        Assert.assertTrue(wait.element(By.xpath("//*[text()='Machine Parameters']")).isDisplayed());
        Assert.assertTrue(wait.element(By.xpath("//*[text()='Recipe']")).isDisplayed());
        Assert.assertTrue(wait.element(By.xpath("//*[text()='Report']")).isDisplayed());
        Assert.assertTrue(wait.element(By.xpath("//*[text()='User Management']")).isDisplayed());
        Assert.assertTrue(wait.element(By.xpath("//*[text()='Profile']")).isDisplayed());
        Assert.assertTrue(wait.element(By.xpath("//*[text()='Change Password']")).isDisplayed());
        Assert.assertTrue(wait.element(By.xpath("//*[text()='Logout']")).isDisplayed());
    }
}
