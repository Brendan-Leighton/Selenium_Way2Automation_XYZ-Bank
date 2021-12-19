package pageFactories;
// JAVA

import java.lang.reflect.Method;
import java.util.List;
// SELENIUM
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.BaseTestMethod;

public class CustomerLogin_Factory extends WebPageFactory {

    public CustomerLogin_Factory(WebDriver driver) {
        super(
                driver,
                "https://www.way2automation.com/angularjs-protractor/banking/#/customer"
        );
        PageFactory.initElements(driver, this);
    }

    // SELECTORS

    final String select_user = "//select[@id=\"userSelect\"]";
    final String option_user = "//option";

    // FINDERS

    @FindBy(xpath = select_user)
    private WebElement userDropdown;

    @FindBy(xpath = select_user + option_user)
    private List<WebElement> userOptions;

    @FindBy(xpath = "//button[@class=\"btn btn-default\"]")
    private WebElement button_Login;

    // GETTERS

    public WebElement getUserDropdown() {
        return userDropdown;
    }

    public List<WebElement> getUserOptions() {
        return userOptions;
    }

    public WebElement getButton_Login() {
        return button_Login;
    }

    // DOERS

    public void loginUser(WebDriver driver, int userIndex) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(userDropdown));
        userDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(userOptions.get(0))); // 0th should be the "---Your Name---" default option
        userOptions.get(userIndex).click();
    }
}
