package test.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTests {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        String baseUrl = "https://www.sberbank.ru/ru/person";
        driver.get(baseUrl);

        new WebDriverWait(driver, 1).until(
                webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));
        wait = new WebDriverWait(driver, 10, 10000);
    }

    @AfterEach
    void afterEach()
        {
            driver.quit();
        }

        protected void scrollToElementJs(WebElement element) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        }

         protected void waitUtilElementToBeClickable(WebElement element) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }

        protected void waitUtilElementToBeVisible(By locator) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }

        protected void waitUtilElementToBeVisible(WebElement element) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }

        protected void checkErrorMessageAtField(WebElement element, String errorMessage) {

        assertEquals("Обязательное поле",
                errorMessage, element.getText());
    }

}

