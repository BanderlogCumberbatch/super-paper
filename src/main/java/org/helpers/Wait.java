package org.helpers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wait {

    // Ожидание видимости элемента (стандартный таймаут 10 секунд)
    public static void waitUntilVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    // С кастомным таймаутом
    public static void waitUntilVisible(WebDriver driver, WebElement element, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    // Ожидание видимости элемента (стандартный таймаут 10 секунд)
    public static void waitThenCLick(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element))
                .click();
    }

    // С кастомным таймаутом
    public static void waitThenClick(WebDriver driver, WebElement element, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element))
                .click();
    }
}
