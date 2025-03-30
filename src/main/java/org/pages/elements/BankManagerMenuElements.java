package org.pages.elements;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class BankManagerMenuElements {
    protected final WebDriver driver;
    /**
     * Кнопка перехода на страницу добавления пользователя.
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'addCust()')]")
    WebElement addCustomerButton;

    /**
     * Кнопка перехода на страницу входа в аккаунт.
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'openAccount()')]")
    WebElement openAccountButton;

    /**
     * Кнопка перехода на страницу вывода списка пользователей.
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'showCust()')]")
    WebElement customersButton;

    public BankManagerMenuElements(final WebDriver webDriver) {
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

}
