package pageFactories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ViewManageAllCustomers extends WebPageFactory {

    List<List<WebElement>> customerTable;
    public ViewManageAllCustomers(WebDriver driver) {
        super(
                driver,
                "https://www.way2automation.com/angularjs-protractor/banking/#/manager/list"
        );
        PageFactory.initElements(driver, this);
    }

    // LOCATORS

    final String input_searchBar = "//input[@ng-model=\"searchCustomer\"]";
    final String table_userAccounts = "//table[@class=\"table table-bordered table-striped\"]";

    // FINDERS

    @FindBy(xpath = input_searchBar)
    private WebElement inputSearchBar;

    @FindBy(xpath = table_userAccounts)
    private WebElement tableUserAccounts;

    // GETTERS


    public WebElement getInputSearchBar() {
        return inputSearchBar;
    }

    public WebElement getTable_userAccounts() { return tableUserAccounts; }
//    public void setCustomerTable(WebDriver driver, WebDriverWait wait) {
//        driver.navigate().to(getUrl());
//        wait.until(ExpectedConditions.elementToBeClickable(tableUserAccounts));
//        this.setTable(tableUserAccounts);
//        this.customerTable = this.getTable();
//    }
}
