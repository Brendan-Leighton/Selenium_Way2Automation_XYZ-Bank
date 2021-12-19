package pageFactories;
// SELENIUM
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomer_Factory extends WebPageFactory {
    public AddCustomer_Factory(WebDriver driver) {
        super(
                driver,
                "https://www.way2automation.com/angularjs-protractor/banking/#/manager/addCust"
        );
        PageFactory.initElements(driver, this);
    }

    // LOCATORS

    final String input_firstName = "//input[@ng-model=\"fName\"]";
    final String input_lastName = "//input[@ng-model=\"lName\"]";
    final String input_postCode = "//input[@ng-model=\"postCd\"]";
    final String button_addCustomer = "//button[@type=\"submit\"]";
    final String button_openAccount = "//button[@ng-click=\"openAccount()\"]";
    final String button_customers = "//button[@ng-click=\"showCust()\"]";

    // FINDERS

    @FindBy(xpath = input_firstName)
    private WebElement inputFirstName;

    @FindBy(xpath = input_lastName)
    private WebElement inputLastName;

    @FindBy(xpath = input_postCode)
    private WebElement inputPostCode;

    @FindBy(xpath = button_addCustomer)
    private WebElement buttonAddCustomer;

    @FindBy(xpath = button_openAccount)
    private WebElement buttonOpenAccount;

    @FindBy(xpath = button_customers)
    private WebElement buttonCustomers;

    // GETTERS

    public WebElement getInputFirstName() { return inputFirstName; }
    public WebElement getInputLastName() { return inputLastName; }
    public WebElement getInputPostCode() { return inputPostCode; }
    public WebElement getButtonAddCustomer() { return buttonAddCustomer; }
    public WebElement getButtonOpenAccount() {
        return buttonOpenAccount;
    }
    public WebElement getButtonCustomers() {
        return buttonCustomers;
    }
}
