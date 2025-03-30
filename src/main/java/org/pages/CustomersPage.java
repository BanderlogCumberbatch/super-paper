package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.pages.elements.BankManagerMenuElements;

import java.util.List;

public class CustomersPage extends BankManagerPage {

    public CustomersPage(final WebDriver webDriver) { super(webDriver); }

//    @Step("Get selected product names")
//    public final List<String> getSelectedProducts() {
//        productNameSelector.isDisplayed();
//        return driver
//                .findElements(productNameSelector)
//                .stream()
//                .map(WebElement::getText)
//                .collect(Collectors.toList());
//    }
}
