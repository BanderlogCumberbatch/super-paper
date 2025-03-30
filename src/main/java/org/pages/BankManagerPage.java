package org.pages;

import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;
import org.pages.elements.BankManagerMenuElements;

/**
 * Класс страницы менеджера.
 */
public class BankManagerPage extends BasePage {

    /**
     * Кнопка перехода на страницу добавления пользователя.
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'addCust()')]")
    WebElement addCustomerPageButton;

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

    public BankManagerPage(final WebDriver webDriver) { super(webDriver); }

    /**
     * Переходит на страницу.
     * @return текущий экземпляр класса
     */
    @Step("Перейти на страницу")
    public AddCustomerPage goToAddCustomerPage() {
        Wait.waitThenCLick(driver, addCustomerPageButton);
        return new AddCustomerPage(driver);
    }

    /**
     * Переходит на страницу.
     * @return текущий экземпляр класса
     */
    @Step("Перейти на страницу")
    public OpenAccountPage goToOpenAccountPage() {
        Wait.waitThenCLick(driver, openAccountButton);
        return new OpenAccountPage(driver);
    }

    /**
     * Переходит на страницу.
     * @return текущий экземпляр класса
     */
    @Step("Перейти на страницу")
    public CustomersPage goToCustomersPage() {
        Wait.waitThenCLick(driver, customersButton);
        return new CustomersPage(driver);
    }


    /**
     * Проверяет кнопки.
     * @return текущий экземпляр класса
     */
    @Step("Проверить кнопки")
    public BankManagerPage checkButtons() {
        Wait.waitUntilVisible(driver, addCustomerPageButton);
        addCustomerPageButton.isEnabled();
        addCustomerPageButton.isDisplayed();
        addCustomerPageButton.click();

        Wait.waitUntilVisible(driver, openAccountButton);
        openAccountButton.isEnabled();
        openAccountButton.isDisplayed();
        openAccountButton.click();

        Wait.waitUntilVisible(driver, customersButton);
        customersButton.isEnabled();
        customersButton.isDisplayed();
        customersButton.click();
        return new BankManagerPage(driver);
    }


}

