package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

import java.util.HashSet;
import java.util.List;


public class AddressModificationTest extends TestBase{

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
    public void testAddressModification() {
        app.goTo().homePage();
        List<AddressData> before = app.getAddressHelper().getAddressList();
        app.getAddressHelper().selectAddress(before.size()-1);
        app.getAddressHelper().initAddressModification();
        AddressData address = new AddressData(before.get(before.size()-1).getId(), "First name (edited)", "Middle name(edited)", "Last name(edited)", "Nickname", "title", "company", "address", "123456", "123456", "123456", "123456", "q@q.ru", "q1@q.ru", "q3@q.ru", "homepage", "1", "February", "1990", "6", "January", "2010",null ,"address 2", "123678", "text");
        app.getAddressHelper().fillAddressForm( address, false);
        app.getAddressHelper().submitAddressModification();
        app.goTo().homePage();
        List<AddressData> after = app.getAddressHelper().getAddressList();
        Assert.assertEquals(after.size(), before.size());



        before.remove(before.size()-1);
        before.add(address);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));




    }

}
