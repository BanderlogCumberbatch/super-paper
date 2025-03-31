import generator.TestDataGenerator;
import org.pages.BankManagerPage;
import org.pages.CustomersPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

/**
 * Класс тестов для добавления пользователя на www.globalsqa.com.
 */
public class XYZBankTests extends BaseTest {

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

    @BeforeMethod
    public final void setup() {
        bankManagerPage = startPage.goToBankManagerPage()
                .goToAddCustomerPage();
    }

    /**
     * Тест добавления пользователя.
     */
    @Test(description = "Add customer test", dataProvider = "Add customers data" )
    public void addCustomerTest(String postCode, String lastName, String firstName) {
        customersPage = bankManagerPage
                .goToAddCustomerPage()
                .addCustomer(firstName, lastName, postCode)
                .goToCustomersPage();
        List<String> selectedCustomers = customersPage.getCustomers(firstName);
        Assert.assertEquals(selectedCustomers, Collections.singletonList(firstName));
        startPage.goToStartPage();
    }

    /**
     * Тест сортировки пользователей.
     */
    @Test(description = "Sort customers")
    public void sortCustomersTest() {
//        SoftAssert softAssert = new SoftAssert();
        customersPage = bankManagerPage
                .goToCustomersPage();
        customersPage = customersPage.sortByFirstName();
        Assert.assertTrue(customersPage.isSortedByFirstNameReverse());
        customersPage = customersPage.sortByFirstName();
        Assert.assertTrue(customersPage.isSortedByFirstName());
        customersPage.goToStartPage();
    }

//    new WebDriverWait(driver, Duration.ofSeconds(10));
//    Arrays.asList(firstName, lastName, postCode)

    @AfterMethod
    public final void clearCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}