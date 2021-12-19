package pageFactories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerDashboard_Factory extends WebPageFactory{

    public ManagerDashboard_Factory(WebDriver driver) {
        super(
                driver,
                "https://www.way2automation.com/angularjs-protractor/banking/#/manager"
        );
        PageFactory.initElements(driver, this);
    }

    /**
     * Page Elements and their GETTERS
     */

    // LOCATORS
    final String button_addCustomer = "//button[@ng-click=\"addCust()\"]";
    final String button_openAccount = "//button[@ng-click=\"openAccount()\"]";
    final String button_customers = "//button[@ng-click=\"showCust()\"]";

    // FINDERS && GETTERS
    @FindBy(xpath = button_addCustomer)
    private WebElement buttonAddCustomer;
    public WebElement getButtonAddCustomer() {
        return buttonAddCustomer;
    }

    @FindBy(xpath = button_openAccount)
    private WebElement buttonOpenAccount;
    public WebElement getButtonOpenAccount() {
        return buttonOpenAccount;
    }

    @FindBy(xpath = button_customers)
    private WebElement buttonCustomers;
    public WebElement getButtonCustomers() {
        return buttonCustomers;
    }
}
