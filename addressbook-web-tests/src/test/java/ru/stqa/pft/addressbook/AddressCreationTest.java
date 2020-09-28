package ru.stqa.pft.addressbook;


import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AddressCreationTest {
    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.get("http://localhost/addressbook/index.php");
        login("admin", "secret");
    }

    private void login(String username, String password) {
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void testAddressCreation() throws Exception {
        gotoAddNewPage();
        fiiAddressForm(new AddressData("First name", "Middle name", "Last name", "Nickname", "title", "company", "address", "123456", "123456", "123456", "123456", "q@q.ru", "q1@q.ru", "q3@q.ru", "homepage", "1", "February", "1990", "6", "January", "2010", "address 2", "123678", "text"));
        submitAddressCreation();
        openHomePage();

    }

    private void openHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    private void submitAddressCreation() {
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    private void fiiAddressForm(AddressData addressData) {
        wd.findElement(By.name("firstname")).sendKeys(addressData.getFirstName());
        wd.findElement(By.name("middlename")).sendKeys(addressData.getMiddleName());
        wd.findElement(By.name("lastname")).sendKeys(addressData.getLastName());
        wd.findElement(By.name("nickname")).sendKeys(addressData.getNickname());
        //wd.findElement(By.name("photo")).sendKeys("Users/tatiana_moskvina/Desktop/День рождения Жени 2020 уже на хард)\DSCF4588.jpg");
        wd.findElement(By.name("title")).sendKeys(addressData.getTitle());
        wd.findElement(By.name("company")).sendKeys(addressData.getCompany());
        wd.findElement(By.name("address")).sendKeys(addressData.getAddress());
        wd.findElement(By.name("home")).sendKeys(addressData.getHomeNumber());
        wd.findElement(By.name("mobile")).sendKeys(addressData.getMobileNumber());
        wd.findElement(By.name("work")).sendKeys(addressData.getWorkNumber());
        wd.findElement(By.name("fax")).sendKeys(addressData.getFaxNumber());
        wd.findElement(By.name("email")).sendKeys(addressData.getEmail());
        wd.findElement(By.name("email2")).sendKeys(addressData.getSecondEmail());
        wd.findElement(By.name("email3")).sendKeys(addressData.getThirdEmail());
        wd.findElement(By.name("homepage")).sendKeys(addressData.getHomepage());
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(addressData.getbDay());
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(addressData.getbMonth());
        wd.findElement(By.name("byear")).sendKeys(addressData.getbYear());
        new Select(wd.findElement(By.name("aday"))).selectByVisibleText(addressData.getAday());
        new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(addressData.getAmonth());
        wd.findElement(By.name("ayear")).sendKeys(addressData.getAyear());
        wd.findElement(By.name("address2")).sendKeys(addressData.getAddress2());
        wd.findElement(By.name("phone2")).sendKeys(addressData.getPhone2());
        wd.findElement(By.name("notes")).sendKeys(addressData.getNotes());
    }

    private void gotoAddNewPage() {
        wd.findElement(By.linkText("add new")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.findElement(By.linkText("Logout")).click();
        wd.quit();
    }

}
