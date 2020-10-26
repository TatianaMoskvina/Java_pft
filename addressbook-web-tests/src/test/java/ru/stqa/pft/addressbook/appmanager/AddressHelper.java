package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Addresses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddressHelper extends HelperBase {

    public AddressHelper(WebDriver wd) {
        super(wd);
    }

    public void submitAddressCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillAddressForm(AddressData addressData, boolean creation) {
        type(By.name("firstname"), addressData.getFirstName());
        type(By.name("middlename"), addressData.getMiddleName());
        type(By.name("lastname"), addressData.getLastName());
        type(By.name("address"), addressData.getAddress());
        type(By.name("email"), addressData.getEmail());

    }


    public void selectAddress(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectAddressById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initAddressModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitAddressModification() {
        click(By.name("update"));
    }

    public void deleteSelectedAddress() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));

    }


    public boolean isThereAAddress() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public void create(AddressData addressData) {
        fillAddressForm(addressData, true);
        submitAddressCreation();
        wd.findElement(By.cssSelector("div.msgbox"));
        //wd.findElement(By.linkText("add next"));

    }

    public void modify(AddressData address) {
        selectAddressById(address.getId());
        initAddressModification();
        fillAddressForm(address, false);
        submitAddressModification();
    }

    public void delete(int index) {
        selectAddress(index);
        deleteSelectedAddress();
    }

    public void delete(AddressData address) {
        selectAddressById(address.getId());
        deleteSelectedAddress();
    }


    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public List<AddressData> list() {
        List<AddressData> addresses = new ArrayList<AddressData>();
        List<WebElement> line = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : line) {
            List<WebElement> cell = element.findElements(By.tagName("td"));
            String firstname = cell.get(2).getText();
            String lastname = cell.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            AddressData contact = new AddressData().withId(id).withFirstName(firstname).withLastName(lastname);
            addresses.add(contact);
        }
        return addresses;
    }

    public Addresses all() {
        Addresses addresses = new Addresses();
        List<WebElement> line = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : line) {
            List<WebElement> cell = element.findElements(By.tagName("td"));
            String firstname = cell.get(2).getText();
            String lastname = cell.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            AddressData contact = new AddressData().withId(id).withFirstName(firstname).withLastName(lastname);
            addresses.add(contact);
        }
        return addresses;
    }



    public int Count() {
        return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
    }


}
