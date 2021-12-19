package pageFactories;
// JAVA
import java.util.*;
// SELENIUM
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
// TEST NG
import org.testng.Assert;

/**
 * <h3>WebPageFactory METHODS:</h3>
 * <ul>
 *      <li>getButton_Home(): get "Home" button from navbar</li>
 *      <li>getUrl(): gets this pages url</li>
 *      <li>navigateTo(): given an ArrayList of WebElements,
 *          <ol><li>The driver will go to the site's Home-page</li>
 *          <li>loop and click the WebElements in the list</li>
 *          <li>assert the final URL matches this.url</li></ol></li>
 * </ul>
 */
public abstract class WebPageFactory {

    // PROPS

    private final String url;
    final String url_home = "https://www.way2automation.com/angularjs-protractor/banking/#/login";
    private final WebDriverWait wait;
    private WebElement table;

    private ArrayList<WebElement> navigationElements = new ArrayList<>(0);
    private String loggedInUser = "";

    // CONSTRUCTORS

    public WebPageFactory(WebDriver driver, String webPageUrl) {
        url = webPageUrl;
        this.wait = new WebDriverWait(driver, 10);
    }

    // FINDERS && GETTERS

    @FindBy(xpath = "//button[@class=\"btn home\"]")
    private WebElement button_Home;

    @FindBy(xpath = "//button[@class=\"btn logout\"]")
    private WebElement button_Logout;

    public WebElement getButton_Home() {
        return button_Home;
    }

    public WebElement getButton_Logout() {
        return button_Logout;
    }

    public String getLoggedInUser() { return loggedInUser; }

    // METHODS

    /**
     * @return this webpage's url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Navigate to this page.
     * 1. Start at website's home-page
     * 2. Follow a sequence of steps to traverse the site until coming to this page.
     *
     * *****
     *      navigateToWithLogin requires adding JUST the dropdown_userSelect
     *      from the account login page into the List of WebElements to traverse.
     * *****
     */
    public void navigateTo(WebDriver driver) {
        if (driver.getCurrentUrl().equals(this.url)) {
            return;
        }
        // Home page
        driver.navigate().to(this.url_home);
        wait.until(ExpectedConditions.urlToBe(this.url_home));

        // sequence of elements to click in order to get to this.url
        if (navigationElements.size() != 0) {
            for (WebElement el : navigationElements) {
                wait.until(ExpectedConditions.elementToBeClickable(el));
                el.click();
            }
        }

        // assert we arrived at this.url
        wait.until(ExpectedConditions.urlToBe(url));
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    public void navigateTo(WebDriver driver, int userIndexToLogin, CustomerLogin_Factory customerLoginFactory) {
        if (driver.getCurrentUrl().equals(this.url)) {
            return;
        }
        // Home page
        driver.navigate().to(this.url_home);
        System.out.println("\nNEW NAVIGATION:\nnavigating to home page");
        wait.until(ExpectedConditions.urlToBe(this.url_home));
        System.out.println("@ home page");

        // sequence of elements to click in order to get to this.url
        if (navigationElements.size() > 0) {
            System.out.println("New Navigation Sequence:");
            for (WebElement el : navigationElements) {
                wait.until(ExpectedConditions.elementToBeClickable(el));
                System.out.println("element name: " + el.getText() + ", id: " + el.getAttribute("id"));
                // if clicking this element DOESN'T take us to login then click,
                // else perform login function
                if (!el.getAttribute("ng-click").equals("customer()")) {
                    el.click();
                    System.out.println("clicked");
                }
                else {  // login function
                    el.click();
                    wait.until(ExpectedConditions.urlToBe(customerLoginFactory.getUrl()));
                    System.out.println("logging in...");
                    // elements
                    List<WebElement> userOptions = customerLoginFactory.getUserOptions();
                    WebElement loginButton = customerLoginFactory.getButton_Login();
                    WebElement selectUserDropdown = customerLoginFactory.getUserDropdown();
                    // interact
                    wait.until(ExpectedConditions.elementToBeClickable(selectUserDropdown));
                    selectUserDropdown.click();
                    wait.until(ExpectedConditions.elementToBeClickable(userOptions.get(0))); // 0th = "---Your Name---" = default option
                    this.loggedInUser = userOptions.get(userIndexToLogin).getText().trim();
                    userOptions.get(userIndexToLogin).click();
                    wait.until(ExpectedConditions.elementToBeClickable(loginButton));
                    loginButton.click();
                    System.out.println("logged in\n");
                }
            }
        }

        // wait for this.url page to load
        wait.until(ExpectedConditions.urlToBe(this.url));
    }

    public void setNavigationElements(ArrayList<WebElement> elements) {
        this.navigationElements = elements;
    }

    public void addNavigationElement(WebElement element) {
        this.navigationElements.add(element);
    }

//    public void addNavigationElement(WebElement element) {
//        Object el = element;
//        this.navigationElements.add((Objects) el);
//    }

    /*
     * TABLE METHODS
     */

    // @return Gives a matrix of the set <table>
    public List<List<WebElement>> getTable(WebElement table) {
        this.setTable(table);
        List<List<WebElement>> tableMatrix = new ArrayList<>();
        List<WebElement> tableRows = this.table.findElements(By.xpath(".//tr"));

        for (WebElement row : tableRows) {
            List<WebElement> tds = row.findElements(By.xpath(".//td"));
            tableMatrix.add(tds);
        }
        return tableMatrix;
    }

    public void setTable(WebElement table) {
        this.table = table;
    }

    public List<List<WebElement>> sortTableColumn(List<List<WebElement>> tableMatrix, int columnNumber) {
        // make copy of matrix without header-row
        List<List<WebElement>> tableMatrixHeadless = new ArrayList<>();
        for (int i = 1; i < tableMatrix.size(); i++) {
            tableMatrixHeadless.add(tableMatrix.get(i));
        }

        // sort rows by given col#
        tableMatrixHeadless.sort((row1, row2) -> row1.get(columnNumber).getText().compareTo(row2.get(columnNumber).getText()));

        // add the header back in
        tableMatrixHeadless.add(0,tableMatrix.get(0));

        // return that bad-boy
        return tableMatrixHeadless;
    }

    /*
     *  TEST NAVBAR BUTTONS
     */

    public void testNavbar_homeButton(WebDriver driver) {
        if (!driver.getCurrentUrl().equals(this.url)) {
            this.navigateTo(driver);
        }
        this.getButton_Home().click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(this.url_home));
        Assert.assertEquals(driver.getCurrentUrl(), this.url_home);
        this.navigateTo(driver);
    }

    public void testNavbar_homeButton(WebDriver driver, int userIndexToLogin, CustomerLogin_Factory customerLoginFactory) {
        if (!driver.getCurrentUrl().equals(this.url)) {
            this.navigateTo(driver, userIndexToLogin, customerLoginFactory);
        }
        this.getButton_Home().click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(this.url_home));
        Assert.assertEquals(driver.getCurrentUrl(), this.url_home);
        this.navigateTo(driver, userIndexToLogin, customerLoginFactory);
    }

    public void testNavbar_logoutButton(WebDriver driver, CustomerLogin_Factory customerLoginFactory) {
        if (!driver.getCurrentUrl().equals(this.url)) {
            this.navigateTo(driver);
        }
        this.getButton_Logout().click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(customerLoginFactory.getUrl()));
        Assert.assertEquals(driver.getCurrentUrl(), customerLoginFactory.getUrl());
        this.navigateTo(driver);
    }

    public void testNavbar_logoutButton(WebDriver driver, int userIndexToLogin, CustomerLogin_Factory customerLoginFactory) {
        if (!driver.getCurrentUrl().equals(this.url)) {
            this.navigateTo(driver, userIndexToLogin, customerLoginFactory);
        }
        this.getButton_Logout().click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(customerLoginFactory.getUrl()));
        Assert.assertEquals(driver.getCurrentUrl(), customerLoginFactory.getUrl());
        this.navigateTo(driver, userIndexToLogin, customerLoginFactory);
    }
}
