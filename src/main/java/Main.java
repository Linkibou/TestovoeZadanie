import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        try {
            // 1. Перейти на сайт https://demoqa.com/
            driver.get("https://demoqa.com/");

            // 2. Нажать elements
            driver.findElement(By.xpath("//div[@class='card mt-4 top-card'][1]")).click();

            // 3. Нажать Text Box
            driver.findElement(By.xpath("//span[contains(text(), 'Text Box')]")).click();

            // 4. Заполнить все 4 поля любыми значениями
            String fullName = "Testtest";
            String email = "Testtest@test.com";
            String currentAddress = "221 baker St";
            String permanentAddress = "221 baker St";

            WebElement nameField = driver.findElement(By.id("userName"));
            WebElement emailField = driver.findElement(By.id("userEmail"));
            WebElement currentAddressField = driver.findElement(By.id("currentAddress"));
            WebElement permanentAddressField = driver.findElement(By.id("permanentAddress"));

            nameField.sendKeys(fullName);
            emailField.sendKeys(email);
            currentAddressField.sendKeys(currentAddress);
            permanentAddressField.sendKeys(permanentAddress);

            // 5. Нажать на Submit
            driver.findElement(By.id("submit")).click();


            // 6. Проверить, что вывелись правильные данные
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));

            WebElement outputName = driver.findElement(By.id("name"));
            assert outputName.getText().equals("Name: Testtest") : "Имя не совпадает";

            WebElement outputEmail = driver.findElement(By.id("email"));
            assert outputEmail.getText().equals("Email: Testtest@test.com"): "Почта не совпадает";

            WebElement outputCurrentAddress = driver.findElement(By.id("currentAddress"));
            assert outputCurrentAddress.getText().equals("Current Address : 221 baker St") : "Текущий адрес не совпадает";

            WebElement outputPermanentAddress = driver.findElement(By.id("permanentAddress"));
            assert outputPermanentAddress.getText().equals("Permananet Address : 221 baker St") : "Адрес регистрации не совпадает";


        } finally {
            // Закрытие драйвера
           driver.quit();
        }
    }
}
