package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.AddressData;

public class AddressHelper extends HelperBase{

    public AddressHelper(WebDriver wd) {
        super(wd);
    }

    public void submitAddressCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillAddressForm(AddressData addressData) {
        type(By.name("firstname"), addressData.getFirstName());
        type(By.name("middlename"),addressData.getMiddleName());
        type(By.name("lastname"), addressData.getLastName());
        type(By.name("nickname"), addressData.getNickname());
        //wd.findElement(By.name("photo")).sendKeys("Users/tatiana_moskvina/Desktop/День рождения Жени 2020 уже на хард)\DSCF4588.jpg");
        type(By.name("title"),addressData.getTitle());
        type(By.name("company"),addressData.getCompany());
        type(By.name("address"),addressData.getAddress());
        type(By.name("home"),addressData.getHomeNumber());
        type(By.name("mobile"),addressData.getMobileNumber());
        type(By.name("work"),addressData.getWorkNumber());
        type(By.name("fax"),addressData.getFaxNumber());
        type(By.name("email"),addressData.getEmail());
        type(By.name("email2"),addressData.getSecondEmail());
        type(By.name("email3"),addressData.getThirdEmail());
        type(By.name("homepage"),addressData.getHomepage());
        select(wd.findElement(By.name("bday")), addressData.getbDay());
        select(wd.findElement(By.name("bmonth")), addressData.getbMonth());
        type(By.name("byear"),addressData.getbYear());
        select(wd.findElement(By.name("aday")), addressData.getAday());
        select(wd.findElement(By.name("amonth")), addressData.getAmonth());
        type(By.name("ayear"),addressData.getAyear());
        type(By.name("address2"), addressData.getAddress2());
        type(By.name("phone2"),addressData.getPhone2());
        type(By.name("notes"),addressData.getNotes());
    }

    public void selectAddress() {
        click(By.name("selected[]"));
    }

    public void initAddressModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitAddressModification() {
        click(By.name("update"));
    }
}
