package pageFactories;
// JAVA

import java.util.List;
// SELENIUM
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenAccount_Factory extends WebPageFactory {

    public OpenAccount_Factory(WebDriver driver) {
        super(
                driver,
                "https://www.way2automation.com/angularjs-protractor/banking/#/manager/openAccount"
        );
        PageFactory.initElements(driver, this);
    }

    // ELEMENT'S SELECTORS

    final String input_customerName_id = "userSelect";
    final String input_currencyType_id = "currency";
    final String button_process = "//button[@type=\"submit\"]";
    final String button_addCustomer = "//button[@ng-click=\"addCust()\"]";
    final String button_customers = "//button[@ng-click=\"showCust()\"]";

    // FIND ELEMENTS

    @FindBy(id = input_customerName_id)
    private WebElement inputCustomerName;

    @FindBy(id = input_currencyType_id)
    private WebElement inputCurrencyType;

    @FindBy(xpath = button_process)
    private WebElement buttonProcess;

    @FindBy(xpath = button_addCustomer)
    private WebElement buttonAddCustomer;

    @FindBy(xpath = button_customers)
    private WebElement buttonCustomers;


    // ELEMENT GETTERS

    public WebElement getInputCustomerName() {
        return inputCustomerName;
    }

    public List<WebElement> getInputCustomerNameOptions() {
        return inputCustomerName.findElements(By.xpath(".//option"));
    }

    public WebElement getInputCurrencyType() {
        return inputCurrencyType;
    }

    public List<WebElement> getInputCurrencyTypeOptions() {
        return inputCurrencyType.findElements(By.xpath(".//option"));
    }

    public WebElement getButtonProcess() {
        return buttonProcess;
    }

    public WebElement getButtonAddCustomer() {
        return buttonAddCustomer;
    }

    public WebElement getButtonCustomers() {
        return buttonCustomers;
    }
}
