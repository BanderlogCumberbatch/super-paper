package org.pages;

import com.google.common.collect.Ordering;
import io.qameta.allure.Step;
import org.helpers.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.elements.BankManagerMenuElements;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CustomersPage extends BankManagerPage {

    public CustomersPage(final WebDriver webDriver) { super(webDriver); }

    String productNameSelector = "//table[contains(@class, 'table-bordered')]/tbody/tr/td[1]";

    @FindBy(xpath = "//table[contains(@class, 'table-bordered')]/tbody/tr/td[1]")
    WebElement productsName;

    @Step("Get selected customers names")
    public final List<String> getSelectedCustomers(String firstName) {
        Wait.waitUntilVisible(driver, productsName);
        return driver
                .findElements(By.xpath(productNameSelector))
                .stream()
                .map(WebElement::getText)
                .filter(s -> s.equals(firstName))
                .collect(Collectors.toList());
    }

    public boolean isSortedByFirstNameAscending(String firstName) {


        List<String> names = getSelectedCustomers(firstName);
        return Ordering.natural().isOrdered(names);
    }

    @Step("Delete first customer")
    public void deleteFirstCustomer() {
        WebElement firstDeleteButton = driver.findElement(By.cssSelector("tbody tr:first-child td:nth-child(5) button"));
        Wait.waitThenCLick(driver, firstDeleteButton);


    }

    @Step("Get selected product names")
    public final CustomersPage checkCustomer(String firstName, String lastName, String postCode) {
        Wait.waitUntilVisible(driver, mainHeading);
        Wait.waitUntilVisible(driver, driver.findElement(By.xpath("//*[contains(text(), '" + firstName + "')]")), 5000);
        return new CustomersPage(driver);
    }
}
