package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class AddressDeletionTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (! app.getAddressHelper().isThereAAddress()) {
            app.goTo().gotoAddNewPage();
            app.getAddressHelper().createAddress(new AddressData("First name", "Middle name", "Last name", "Nickname", "title", "company", "address", null, "123456", "123456", "123456", "q@q.ru", "q1@q.ru", "q3@q.ru", "homepage", "1", "February", "1990", "6", "January", "2010","Group1" ,"address 2", "123678", "text"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddressDeletion() {
        app.goTo().homePage();
        List<AddressData> before = app.getAddressHelper().getAddressList();
        app.getAddressHelper().selectAddress(before.size()-1);
        app.getAddressHelper().deleteSelectedAddress();
        app.goTo().homePage();
        List<AddressData> after = app.getAddressHelper().getAddressList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }
}
