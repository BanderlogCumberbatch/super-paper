
import org.pages.BankManagerPage;
import org.pages.CustomersPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.utils.StringFinder;

import java.util.Collections;

/**
 * Класс тестов для добавления пользователя на www.globalsqa.com.
 */
public class DeleteCustomerTest extends BaseTest {

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
     * Тест удаления пользователей.
     */
    @Test(description = "Delete customer test")
    public void deleteCustomerTest() {
        customersPage = bankManagerPage
                .goToCustomersPage();
        String firstName = StringFinder.getTheMostAverage(customersPage.getCustomersFirstNames());
        Assert.assertEquals(customersPage.getCustomersFirstNames(firstName), Collections.singletonList(firstName));     // До удаления
        customersPage.deleteCustomerWithFirstName(firstName);
        Assert.assertNotEquals(customersPage.getCustomersFirstNames(firstName), Collections.singletonList(firstName));  // После удаления
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