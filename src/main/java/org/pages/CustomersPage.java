package org.pages;

import io.qameta.allure.Step;
import org.helpers.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс страницы со списком пользователей.
 */
public class CustomersPage extends BankManagerPage {

    public CustomersPage(final WebDriver webDriver) { super(webDriver); }

    String productNameSelector = "//table[contains(@class, 'table-bordered')]/tbody/tr/td[1]";

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
     * Возвращает список имён пользователей.
     * @return List<String>
     */
    @Step("Get customers names list")
    public final List <String> getCustomers() {
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
    @Step("Get customers names list")
    public final List<String> getCustomers(String firstName) {
        Wait.waitUntilVisible(driver, productsName);
        return driver
                .findElements(By.xpath(productNameSelector))
                .stream()
                .map(WebElement::getText)
                .filter(s -> s.equals(firstName))
                .collect(Collectors.toList());
    }

    /**
     * Проверяет отсортирован ли список.
     * @return List<String>
     */
    @Step("Check if the list is sorted")
    public boolean isSortedByFirstName() {
        List<String> names = getCustomers();
        boolean isSorted = true;
        for (int i = 0; i < names.size() - 1; i++) {
            if (names.get(i).compareToIgnoreCase(names.get(i + 1)) > 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    /**
     * Проверяет отсортирован ли список в обратном порядке.
     * @return List<String>
     */
    @Step("Check if the list is sorted in reverse order")
    public boolean isSortedByFirstNameReverse() {
        List<String> names = getCustomers();
        boolean isSorted = true;
        for (int i = 0; i < names.size() - 1; i++) {
            // current String is > than the next one (if there are equal list is still sorted)
            if (names.get(i).compareToIgnoreCase(names.get(i + 1)) < 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    /**
     * Сортирует таблицу по именам.
     * @return текущий экземпляр класса
     */
    @Step("Отсортировать таблицу по именам")
    public CustomersPage sortByFirstName() {
        Wait.waitThenCLick(driver, sortByFirstNameButton);
        return new CustomersPage(driver);
    }

    @Step("Delete first customer")
    public void deleteFirstCustomer() {
        WebElement firstDeleteButton = driver.findElement(By.cssSelector("tbody tr:first-child td:nth-child(5) button"));
        Wait.waitThenCLick(driver, firstDeleteButton);


    }

}
