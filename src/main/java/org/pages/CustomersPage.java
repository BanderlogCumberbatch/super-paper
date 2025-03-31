package org.pages;

import io.qameta.allure.Step;
import org.helpers.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.utils.SortChecker;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс страницы со списком пользователей.
 */
public class CustomersPage extends BankManagerPage {

    public CustomersPage(final WebDriver webDriver) { super(webDriver); }

    /**
     * Столбец таблицы с именами.
     */
    @FindBy(xpath = "//table[contains(@class, 'table-bordered')]/tbody/tr/td[1]")
    WebElement productsName;

    /**
     * Кнопка сортировки по имени.
     */
    @FindBy(xpath = "//table[contains(@class, 'table-bordered')]/thead/tr/td[1]/a")
    private WebElement sortByFirstNameButton;

    /**
     * Возвращает список со строкой со всеми данными выбранного пользователя.
     * @return List<String>
     */
    @Step("Get selected customers data string")
    public final List<String> getSelectedCustomers(String firstName, String lastName, String postCode) {
        String productNameSelector = String.format("//table[contains(@class, 'table-bordered')]/tbody/tr[" +
                "td[1][text()='%s'] and " +
                "td[2][text()='%s'] and " +
                "td[3][text()='%s']]", firstName, lastName, postCode);
        Wait.waitUntilVisible(driver, productsName);
        return driver
                .findElements(By.xpath(productNameSelector))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список имён пользователей.
     * @return List<String>
     */
    @Step("Get customers first names list")
    public final List<String> getCustomersFirstNames() {
        String productNameSelector = "//table[contains(@class, 'table-bordered')]/tbody/tr/td[1]";
        Wait.waitUntilVisible(driver, productsName);
        return driver
                .findElements(By.xpath(productNameSelector))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список имён пользователей с определённым именем.
     * @return List<String>
     */
    @Step("Get customers first names with first name list")
    public final List<String> getCustomersFirstNames(String firstName) {
        String productNameSelector = "//table[contains(@class, 'table-bordered')]/tbody/tr/td[1]";
        Wait.waitUntilVisible(driver, productsName);
        return driver
                .findElements(By.xpath(productNameSelector))
                .stream()
                .map(WebElement::getText)
                .filter(s -> s.equals(firstName))
                .collect(Collectors.toList());
    }

    /**
     * Проверяет отсортирована ли таблица по именам.
     * @return boolean
     */
    @Step("Check if the table is sorted")
    public boolean isSortedByFirstName() {
        List<String> names = getCustomersFirstNames();
        return SortChecker.isSorted(names);
    }

    /**
     * Проверяет отсортирована ли таблица по именам в обратном порядке.
     * @return boolean
     */
    @Step("Check if the table is sorted in reverse order")
    public boolean isSortedByFirstNameInReverse() {
        List<String> names = getCustomersFirstNames();
        return SortChecker.isSortedInReverse(names);
    }

    /**
     * Сортирует таблицу по именам.
     */
    @Step("Sort table by first names")
    public void sortByFirstName() {
        Wait.waitThenCLick(driver, sortByFirstNameButton);
    }


    /**
     * Удаляет из таблицы пользователя с именем.
     */
    @Step("Delete customer with first name")
    public void deleteCustomerWithFirstName(String firstName) {
        WebElement deleteButton = driver.findElement(By.xpath("//table[contains(@class, 'table-bordered')]/tbody/tr[td[1][text()='" + firstName + "']]/td[5]/button"));
        Wait.waitThenCLick(driver, deleteButton);
    }

}
