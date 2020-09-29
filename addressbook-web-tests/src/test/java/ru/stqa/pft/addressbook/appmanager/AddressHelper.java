package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.AddressData;

public class AddressHelper {
    private WebDriver wd;

    public AddressHelper(WebDriver wd) {
        this.wd = wd;
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
}
