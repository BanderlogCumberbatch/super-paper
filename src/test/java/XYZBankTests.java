import com.beust.jcommander.defaultprovider.PropertyFileDefaultProvider;
import generator.TestDataGenerator;
import org.pages.StartPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

    /**
     * Тест перехода на страницу менеджера.
     */
    @Test(description = "Go to bank manager page", dataProvider = "Add customers data" )
    public void addCustomerTest(String firstName, String lastName, String postCode) {
        startPage.goToBankManagerPage()
                .goToAddCustomerPage()
                .addCustomer(firstName, lastName, postCode)
                .goToStartPage();
    }

    @AfterMethod
    public final void clearCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}