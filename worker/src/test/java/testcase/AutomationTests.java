package testcase;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class AutomationTests {

    WebDriver driver;
    WebDriverWait wait;
    public static final String BASE_URL = "https://demowebshop.tricentis.com/";

    @BeforeClass
    public void setup() {

        System.out.println("===== SETUP STARTED: Launching Chrome in Headless Mode =====");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        System.out.println("===== SETUP COMPLETED SUCCESSFULLY =====");
    }

    // ===========================================================
    // 1️⃣ TEST – Verify Home Page Loads
    // ===========================================================
    @Test(priority = 1)
    public void test01_verifyHomePage() {
        System.out.println(">>> Running Test 01: Verify Home Page Loads");
        driver.get(BASE_URL);
        Assert.assertTrue(driver.getTitle().contains("Demo Web Shop"));
        System.out.println("✔ Test 01 Passed: Home Page Loaded Successfully");
    }

    // ===========================================================
    // 2️⃣ TEST – Search Product
    // ===========================================================
    @Test(priority = 2)
    public void test02_searchProduct() {
        System.out.println(">>> Running Test 02: Search Product 'laptop'");
        driver.get(BASE_URL);

        WebElement searchBox = driver.findElement(By.id("small-searchterms"));
        searchBox.sendKeys("laptop" + Keys.ENTER);

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".page-title h1")
                )
        );

        Assert.assertTrue(title.getText().contains("Search"));
        System.out.println("✔ Test 02 Passed: Search Page Displayed");
    }

    // ===========================================================
    // 3️⃣ TEST – Add Product to Cart
    // ===========================================================
    @Test(priority = 3)
    public void test03_addProductToCart() {
        System.out.println(">>> Running Test 03: Add Product to Cart");
        driver.get(BASE_URL);

        driver.findElement(By.id("small-searchterms")).sendKeys("laptop" + Keys.ENTER);

        WebElement firstProduct = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".product-item .product-title a")
                )
        );
        firstProduct.click();

        WebElement addToCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button-31"))
        );
        addToCartBtn.click();

        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".bar-notification.success")
                )
        );

        Assert.assertTrue(successMsg.getText().contains("The product has been added"));
        System.out.println("✔ Test 03 Passed: Product Added to Cart Successfully");
    }

    // ===========================================================
    // 4️⃣ TEST – Registration
    // ===========================================================
    @Test(priority = 4)
    public void test04_registerUser() {
        System.out.println(">>> Running Test 04: Register New User");
        driver.get(BASE_URL);

        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Test");
        driver.findElement(By.id("LastName")).sendKeys("User");

        String email = "testuser" + System.currentTimeMillis() + "@mail.com";
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys("Test@12345");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Test@12345");

        driver.findElement(By.id("register-button")).click();

        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".result")
                )
        );

        Assert.assertTrue(result.getText().contains("completed"));
        System.out.println("✔ Test 04 Passed: Registration Completed Successfully");
    }

    // ===========================================================
    // 5️⃣ TEST – Verify Category Navigation
    // ===========================================================
    @Test(priority = 5)
    public void test05_verifyCategoryNavigation() {
        System.out.println(">>> Running Test 05: Verify Category Navigation (Books)");
        driver.get(BASE_URL);

        driver.findElement(By.linkText("Books")).click();

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))
        );

        Assert.assertEquals(title.getText(), "Books");
        System.out.println("✔ Test 05 Passed: Books Category Opened Successfully");
    }

    @Test(priority = 6)
    public void test07_wishlistLink() {
        System.out.println(">>> Running Test 06: Wishlist Page Navigation");
        driver.get(BASE_URL);
        driver.findElement(By.linkText("Wishlist")).click();
        Assert.assertTrue(driver.getTitle().contains("Wishlist"));
        System.out.println("✔ Test 06 Passed: Wishlist Page Loaded");
    }

    @Test(priority = 7)
    public void test08_shoppingCartLink() {
        System.out.println(">>> Running Test 07: Shopping Cart Navigation");
        driver.get(BASE_URL);
        driver.findElement(By.linkText("Shopping cart")).click();
        Assert.assertTrue(driver.getTitle().contains("Shopping Cart"));
        System.out.println("✔ Test 07 Passed: Shopping Cart Page Loaded");
    }

    @Test(priority = 8)
    public void test09_contactUs() {
        System.out.println(">>> Running Test 08: Contact Us Page Navigation");
        driver.get(BASE_URL);
        driver.findElement(By.linkText("Contact us")).click();
        Assert.assertTrue(driver.getTitle().contains("Contact Us"));
        System.out.println("✔ Test 08 Passed: Contact Us Page Loaded");
    }

    @Test(priority = 9)
    public void test10_featuredProducts() {
        System.out.println(">>> Running Test 09: Verify Featured Products Section");
        driver.get(BASE_URL);
        WebElement section = driver.findElement(By.cssSelector(".product-grid.home-page-product-grid"));
        Assert.assertTrue(section.isDisplayed());
        System.out.println("✔ Test 09 Passed: Featured Products Section Visible");
    }

    @Test(priority = 10)
    public void test11_apparelCategory() {
        System.out.println(">>> Running Test 10: Apparel & Shoes Category");
        driver.get(BASE_URL);
        driver.findElement(By.linkText("Apparel & Shoes")).click();
        Assert.assertTrue(driver.getTitle().contains("Apparel & Shoes"));
        System.out.println("✔ Test 10 Passed: Apparel Category Loaded Successfully");
    }

    @Test(priority = 11)
    public void test13_sortingDropdown() {
        System.out.println(">>> Running Test 11: Verify Sorting Dropdown");
        driver.get(BASE_URL + "books");

        WebElement dropdown = driver.findElement(By.id("products-orderby"));
        Assert.assertTrue(dropdown.isDisplayed());
        System.out.println("✔ Test 11 Passed: Sorting Dropdown Displayed");
    }

    @Test(priority = 12)
    public void test15_footerLinks() {
        System.out.println(">>> Running Test 12: Validate Footer Links");
        driver.get(BASE_URL);

        List<WebElement> footerLinks = driver.findElements(By.cssSelector(".footer-menu-wrapper a"));
        Assert.assertTrue(footerLinks.size() > 5);
        System.out.println("✔ Test 12 Passed: Footer Links Verified");
    }

    @AfterClass
    public void teardown() {
        System.out.println("===== TEST EXECUTION COMPLETED: Closing Browser =====");
        if (driver != null) {
            driver.quit();
        }
    }
}
