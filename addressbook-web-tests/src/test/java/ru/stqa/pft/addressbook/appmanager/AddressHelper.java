package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Addresses;

import java.util.ArrayList;
import java.util.List;

public class AddressHelper extends HelperBase {

    private Addresses addressCache = null;

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
        type(By.name("email2"), addressData.getEmail2());
        type(By.name("email3"), addressData.getEmail3());
        type(By.name("home"), addressData.getHome());
        type(By.name("mobile"), addressData.getHome());
        type(By.name("work"), addressData.getHome());

    }

    public AddressData infoFromEditForm(AddressData address) {
        initAddressModificationById(address.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        return new AddressData().withId(address.getId()).withFirstName(firstName).withLastName(lastName).withHome(home).withMobile(mobile).withWork(work).withEmail(email).withEmail2(email2).withEmail3(email3);

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

    public void initAddressModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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
        addressCache = null;
        wd.findElement(By.cssSelector("div.msgbox"));
        //wd.findElement(By.linkText("add next"));

    }

    public void modify(AddressData address) {
        selectAddressById(address.getId());
        initAddressModificationById(address.getId());
        fillAddressForm(address, false);
        submitAddressModification();
        addressCache = null;
    }

    public void delete(int index) {
        selectAddress(index);
        deleteSelectedAddress();
    }

    public void delete(AddressData address) {
        selectAddressById(address.getId());
        deleteSelectedAddress();
        addressCache = null;
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
        if (addressCache != null) {
            return new Addresses(addressCache);
        }
        addressCache = new Addresses();
        List<WebElement> line = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : line) {
            List<WebElement> cell = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = cell.get(2).getText();
            String lastname = cell.get(1).getText();
            String allPhones = cell.get(5).getText();
            String allEmails = cell.get(4).getText();
            String allAdrs = cell.get(3).getText();
            AddressData address = new AddressData().withId(id).withFirstName(firstname).
                    withLastName(lastname).withAllPhones(allPhones).withAllEmails(allEmails).withAllAdrs(allAdrs);
            addressCache.add(address);
        }
        return addressCache;
    }



    public int Count() {
        return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
    }


}
