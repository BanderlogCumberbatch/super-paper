import generator.TestDataGenerator;
import org.pages.BankManagerPage;
import org.pages.CustomersPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 * Класс тестов добавления пользователей для globalsqa.com.
 */
public class AddCustomerTest extends BaseTest {

    @DataProvider(name = "Add customers data")
    public Object[][] dpMethod() {
        return new Object[][]{
                {TestDataGenerator.generatePostCode(), "LastName", TestDataGenerator.generateFirstNameFromPostCode()},
                {TestDataGenerator.generatePostCode(), "LastName" , TestDataGenerator.generateFirstNameFromPostCode()},
                {TestDataGenerator.generatePostCode(), "LastName", TestDataGenerator.generateFirstNameFromPostCode()}
        };
    }

    BankManagerPage bankManagerPage;

    CustomersPage customersPage;

    /**
     * Действия перед тестом.
     */
    @BeforeMethod
    public final void setup() {
        bankManagerPage = startPage.goToBankManagerPage();
    }

    /**
     * Тест добавления пользователя.
     */
    @Test(description = "Add customer test", dataProvider = "Add customers data" )
    public void addCustomerTest(String postCode, String lastName, String firstName) {
        customersPage = bankManagerPage
                .goToAddCustomerPage()
                .addCustomer(firstName, lastName, postCode)
                .addCustomer(firstName, lastName, postCode) // Проверка на дубликаты
                .goToCustomersPage();
        Assert.assertEquals(customersPage.getSelectedCustomers(firstName, lastName, postCode), Collections.singletonList(String.format("%s %s %s Delete", firstName, lastName, postCode))); // Вид ожидаемой строки c данными: "firstName lastName postCode Delete"
        customersPage.goToStartPage();
    }


    /**
     * Действия после теста.
     */
    @AfterMethod
    public final void clearCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}