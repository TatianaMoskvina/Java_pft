package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public WebDriver wd;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/index.php");
        login("admin", "secret");
    }

    private void login(String username, String password) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();

        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillGroupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    public void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void stop() {
        wd.findElement(By.linkText("Logout")).click();
        wd.quit();
    }

    public void deleteSelectedGroup() {
        wd.findElement(By.name("delete")).click();
    }

    public void selectGroup() {
        wd.findElement(By.name("selected[]")).click();
    }

    public void openHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void submitAddressCreation() {
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillAddressForm(AddressData addressData) {
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

    public void gotoAddNewPage() {
        wd.findElement(By.linkText("add new")).click();
    }

}
