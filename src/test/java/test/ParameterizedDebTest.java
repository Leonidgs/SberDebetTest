package test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import test.base.BaseTests;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParameterizedDebTest extends BaseTests {


    @ParameterizedTest
    @CsvSource({"Петровна,Оля,Олеговна ", "Андреевич,Андрей,Васильев ", "Василич,Сашка,Михеев "})
    public void exampleScenario(String name, String soName, String midName) throws InterruptedException {

        //Стартуем
        //Нажать на меню - Карты
        String xDebet = "//a[@aria-label='Карты']";
        WebElement element = driver.findElement(By.xpath(xDebet));
        //scrollToElementJs(element);
        waitUtilElementToBeClickable(element);
        element.click();

        //Bыбрать подменю - "Дебетовые карты"
        String zagolov = "//a[text()='Дебетовые карты' and contains(@class, 'link_second')]";
        WebElement element2 = driver.findElement(By.xpath(zagolov));
        element2.click();

        // Проверка открытия страницы "Дебетовые карты"
        String zagolovForCheck = "//h1[text()='Дебетовые карты']";
        assertEquals("Дебетовые карты", driver.findElement(By.xpath(zagolovForCheck)).getText());

        //Заказать онлайн молодёжную карту
        String getCard = "//a[@data-product='Молодёжная карта' and @data-test-id='ProductCatalog_button']";
        WebElement element3 = driver.findElement(By.xpath(getCard));
        element3.click();


        //Проверить налчие заголовка Молодёжная карта
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        String xCheck = "//h1[@data-test-id='PageTeaserDict_header']";
        assertEquals("Молодёжная карта", driver.findElement(By.xpath(xCheck)).getText());

        //Оформить онлайн
        String goCard = "//a[@href='#order' and @data-test-id='PageTeaserDict_button']";
        WebElement element4 = driver.findElement(By.xpath(goCard));
        //scrollToElementJs(element4);
        waitUtilElementToBeVisible(element4);
        waitUtilElementToBeClickable(element4);
        element4.click();

        //Заполняем форму
        ((JavascriptExecutor) driver).executeScript("scroll(0,3800)");

        Thread.sleep(2000);
        //Поле Фамилия
        String xpathlastName = "//*[@data-name='lastName']";
        WebElement element7 = driver.findElement(By.xpath(xpathlastName));
        waitUtilElementToBeVisible(element7);
        waitUtilElementToBeClickable(element7);
        element7.sendKeys(soName);

        Assertions.assertEquals(soName, element7.getAttribute("value"));

        //Поле имя
        String xpathFirstName = "//*[@data-name='firstName']";
        WebElement elem8 = driver.findElement(By.xpath(xpathFirstName));
        elem8.sendKeys(name);

        Assertions.assertEquals(name, elem8.getAttribute("value"));

        //Поле Отчество
        String xpathMiddleName = "//*[@data-name='middleName']";
        WebElement elem9 = driver.findElement(By.xpath(xpathMiddleName));
        elem9.sendKeys(midName);

        Assertions.assertEquals(midName, elem9.getAttribute("value"));


        //Поле дата
        String numberes = "//input[@id='odc-personal__birthDate']";
        WebElement elem7 = driver.findElement(By.xpath(numberes));
        elem7.sendKeys("12122000");

        Assertions.assertEquals("12.12.2000", elem7.getAttribute("value"));

        //Клик что бы выйти из формы
        String ez = "//input[@id='odc-personal__email']";
        WebElement element5 = driver.findElement(By.xpath(ez));
        element5.click();

        //Поле эмайл
        WebElement elem4 = driver.findElement(By.xpath(ez));
        scrollToElementJs(elem4);
        waitUtilElementToBeVisible(elem4);
        elem4.sendKeys("qwerty@yandex.ru");

        Assertions.assertEquals("qwerty@yandex.ru", elem4.getAttribute("value"));

        //Поле телефон
        String xPhone = "//input[@data-name='phone']";
        WebElement elem5 = driver.findElement(By.xpath(xPhone));
        waitUtilElementToBeVisible(elem5);
        waitUtilElementToBeClickable(elem5);
        elem5.click();
        elem5.sendKeys("7777777777");

        Assertions.assertEquals("+7 (777) 777-77-77", elem5.getAttribute("value"));

        //Кликаем далее
        String buutonNext = "//button[@data-step='2']";
        WebElement elem6 = driver.findElement(By.xpath(buutonNext));
        elem6.click();


        //Проверяем наличие ошибок
        String fieldXPath = "//input[@id='%s']";
        checkErrorMessageAtField(driver.findElement(By.xpath
                (String.format(fieldXPath, "odc-personal__series"))), "Обязательное поле");
        checkErrorMessageAtField(driver.findElement(By.xpath
                (String.format(fieldXPath, "odc-personal__number"))), "Обязательное поле");
        checkErrorMessageAtField(driver.findElement
                (By.xpath(String.format(fieldXPath, "odc-personal__issueDate"))), "Обязательное поле");

        Thread.sleep(5000); //Просто что бы визуально полюбоваться на результат

    }

}
