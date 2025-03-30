import com.beust.jcommander.defaultprovider.PropertyFileDefaultProvider;
import generator.TestDataGenerator;
import org.pages.AddCustomerPage;
import org.pages.CustomersPage;
import org.pages.StartPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Класс тестов для www.globalsqa.com.
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

    AddCustomerPage addCustomerPage;

    @BeforeMethod
    public final void setup() {
        addCustomerPage = startPage.goToBankManagerPage()
                .goToAddCustomerPage();
    }

    /**
     * Тест перехода на страницу менеджера.
     */
    @Test(description = "Go to bank manager page", dataProvider = "Add customers data" )
    public void addCustomerTest(String postCode, String lastName, String firstName) {
        SoftAssert softAssert = new SoftAssert();
        List<String> selectedCustomers = addCustomerPage.addCustomer(firstName, lastName, postCode)
                .goToCustomersPage()
                .getSelectedCustomers(firstName);
        Assert.assertEquals(selectedCustomers, Collections.singletonList(firstName));
        addCustomerPage.goToStartPage();
    }

//    Arrays.asList(firstName, lastName, postCode)
//    @Test(description = "Go to bank manager page", dataProvider = "Add customers data" )
//    public void addCustomerTest(String postCode, String lastName, String firstName) {
//        SoftAssert softAssert = new SoftAssert();
//        CustomersPage customersPage = addCustomerPage.addCustomer(firstName, lastName, postCode)
//                .goToCustomersPage()
//                .checkCustomer(firstName, lastName, postCode);
//        customersPage.goToStartPage();
//    }

    @AfterMethod
    public final void clearCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}