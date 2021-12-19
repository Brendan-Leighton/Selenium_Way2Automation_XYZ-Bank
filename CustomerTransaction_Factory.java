package pageFactories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerTransaction_Factory extends WebPageFactory {

    public CustomerTransaction_Factory(WebDriver driver) {
        super(
                driver,
                "https://www.way2automation.com/angularjs-protractor/banking/#/listTx"
        );
        PageFactory.initElements(driver, this);
    }

    // LOCATORS

    private final String button_back = "//button[@ng-click=\"back()\"]";
    private final String button_reset = "//button[@ng-click=\"reset()\"]";
    private final String input_start_id = "start";
    private final String input_end_id = "end";
    private final String table_transactions = "//table[@class=\"table table-bordered table-striped\"]";
    private final String table_sortDates = "//a[@ng-click=\"sortType = 'date'; sortReverse = !sortReverse\"]";
    private final String table_prevPage = "//button[@ng-click=\"scrollLeft()\"]";
    private final String table_nextPage = "//button[@ng-click=\"scrollRight()\"]";

    // FINDERS

    @FindBy(xpath = button_back)
    private WebElement buttonBack;

    @FindBy(xpath = button_reset)
    private WebElement buttonReset;

    @FindBy(id = input_start_id)
    private WebElement inputDateStart;

    @FindBy(id = input_end_id)
    private WebElement inputDateEnd;

    @FindBy(xpath = table_transactions)
    private WebElement tableTransactions;

    public WebElement getTableSortDates() {
        return tableSortDates;
    }

    @FindBy(xpath = table_sortDates)
    private WebElement tableSortDates;

    @FindBy(xpath = table_prevPage)
    private WebElement tableButtonPrevPage;

    @FindBy(xpath = table_nextPage)
    private WebElement tableButtonNextPage;

    // GETTERS

    public WebElement getButtonBack() {
        return buttonBack;
    }

    public WebElement getButtonReset() {
        return buttonReset;
    }

    public WebElement getInputDateStart() {
        return inputDateStart;
    }

    public WebElement getInputDateEnd() {
        return inputDateEnd;
    }

    public WebElement getTableTransactions() {
        return tableTransactions;
    }

    public WebElement getTableButtonPrevPage() {
        return tableButtonPrevPage;
    }

    public WebElement getTableButtonNextPage() {
        return tableButtonNextPage;
    }
}
