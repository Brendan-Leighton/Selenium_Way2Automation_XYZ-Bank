package pageFactories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Home_Factory extends WebPageFactory {

    public Home_Factory(WebDriver driver) {
        super(
                driver,
                "https://www.way2automation.com/angularjs-protractor/banking/#/login"
        );
        PageFactory.initElements(driver, this);
    }

    // FINDERS
    @FindBy (xpath = "//button[@class=\"btn home\"]")
    private WebElement button_Home;

    @FindBy (xpath = "//button[@ng-click='customer()']")
    private WebElement button_CustomerLogin;

    @FindBy (xpath = "//button[@ng-click='manager()']")
    private WebElement button_ManagerLogin;

    // GETTERS
    public WebElement getButton_CustomerLogin() {
        return button_CustomerLogin;
    }

    public WebElement getButton_ManagerLogin() {
        return button_ManagerLogin;
    }

    public void navigateTo(WebDriver driver) {
        super.navigateTo(driver);
    }
}
