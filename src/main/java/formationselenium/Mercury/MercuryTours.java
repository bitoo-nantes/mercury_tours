package formationselenium.Mercury;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class MercuryTours {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    //driver = new FirefoxDriver();
    String s = System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testMercuryTours() throws Exception {
   // driver.get("http://demo.guru99.com/test/newtours/");
    // Lancement du site en local
    // Test lancement auto Jenkins
    driver.get("http://localhost:51/servlets/com.mercurytours.servlet.WelcomeServlet");
    driver.findElement(By.linkText("REGISTER")).click();
    driver.findElement(By.name("userName")).clear();
    driver.findElement(By.name("userName")).sendKeys("EAT10");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("EAT10");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("EAT10");
    driver.findElement(By.name("confirmPassword")).clear();
    driver.findElement(By.name("confirmPassword")).sendKeys("EAT10");
    driver.findElement(By.name("register")).click();
    driver.findElement(By.linkText("Home")).click();
    driver.findElement(By.name("userName")).clear();
    driver.findElement(By.name("userName")).sendKeys("EAT10");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("EAT10");
    driver.findElement(By.name("login")).click();
    driver.findElement(By.xpath("(//input[@name='tripType'])[2]")).click();
    new Select(driver.findElement(By.name("fromPort"))).selectByVisibleText("Frankfurt");
    new Select(driver.findElement(By.name("toPort"))).selectByVisibleText("Zurich");
    try {
      assertTrue(driver.findElement(By.name("servClass")).isSelected());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.name("findFlights")).click();
    String numero_vol = driver.findElement(By.xpath("//td[2]/font/b")).getText();
    driver.findElement(By.name("reserveFlights")).click();
    String prix_billet = driver.findElement(By.xpath("//td[2]/font/b")).getText();
    driver.findElement(By.name("passFirst0")).clear();
    driver.findElement(By.name("passFirst0")).sendKeys("Marc-Antoine");
    driver.findElement(By.name("passLast0")).clear();
    driver.findElement(By.name("passLast0")).sendKeys("Guislain");
    driver.findElement(By.name("creditnumber")).clear();
    driver.findElement(By.name("creditnumber")).sendKeys("1234");
    driver.findElement(By.name("buyFlights")).click();
    try {
      assertTrue(Pattern.compile("[${numero_vol}]").matcher(driver.findElement(By.xpath("//tr[3]/td/font")).getText()).find());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(Pattern.compile("^[${prix_billet}]").matcher(driver.findElement(By.xpath("//font/b/font/font/b/font")).getText()).find());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//td[3]/a/img")).click();
    driver.close();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
