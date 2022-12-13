import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://yandex.ru/images/");
        JavascriptExecutor je = (JavascriptExecutor) driver;
        WebElement input = driver.findElement(By.xpath("//input[@name='text']"));
        input.sendKeys("сиамские котики");
        WebElement search = driver.findElement(By.xpath("//div[@class='websearch-button__text mini-suggest__button-text']"));
        search.click();

        int x = 0;
        while (x < 4) {
            je.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            x++;
            Thread.sleep(1500);
        }
        je.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

        List<WebElement> images = driver.findElements(By.xpath("//a[@class='serp-item__link' and contains(.,'1600×1200')]"));

        for (int i = 0; i < 5; i++) {
            WebElement image = images.get(i);
            new Actions(driver)
                    .scrollToElement(image)
                    .perform();
            String text = image.getAttribute("href");
            System.out.println(text);
        }

        driver.quit();
    }
}
