// WEB DRIVER
import io.github.bonigarcia.wdm.WebDriverManager;
// SELENIUM
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
// TEST NG
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
// FACTORIES
import pageFactories.*;

public class BaseTest {

    WebDriver driver;  // init here for use in @BeforeTest and @AfterTest
    WebDriverWait wait_forNavigation;
    WebDriverWait wait_forElement;
    // FACTORIES
    Home_Factory homeFactory;
    // customer pages
    CustomerLogin_Factory customerLoginFactory;
    CustomerAccount_Factory customerAccountFactory;
    CustomerTransaction_Factory customerTransactionFactory;
    //manager pages
    ManagerDashboard_Factory managerDashboardFactory;
    AddCustomer_Factory addCustomerFactory;
    OpenAccount_Factory openAccountFactory;
    ViewManageAllCustomers viewManageAllCustomers;

    @BeforeTest
    public void setup() {
        // SETUP WEB DRIVER
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver(); // new ChromeOptions().addArguments("--incognito")

        // CHROME DRIVER SETTINGS
        this.driver.manage().window().maximize();

        // WEB DRIVER WAIT(s)
        this.wait_forNavigation = new WebDriverWait(driver, 5);
        this.wait_forElement = new WebDriverWait(driver, 10);

        // SET FACTORIES
        this.homeFactory = new Home_Factory(driver);
        // customer pages
        this.customerLoginFactory = new CustomerLogin_Factory(driver);
        this.customerAccountFactory = new CustomerAccount_Factory(driver);
        this.customerTransactionFactory = new CustomerTransaction_Factory(driver);
        // manager pages
        this.managerDashboardFactory = new ManagerDashboard_Factory(driver);
        this.addCustomerFactory = new AddCustomer_Factory(driver);
        this.openAccountFactory = new OpenAccount_Factory(driver);
        this.viewManageAllCustomers = new ViewManageAllCustomers(driver);

    }

    @AfterTest
    public void shutdown() {
        driver.quit();
    }
}


