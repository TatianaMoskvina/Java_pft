package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

import java.util.List;


public class AddressModificationTest extends TestBase{

    @Test (enabled = false)
    public void testAddressModification() {
        app.goTo().homePage();
        int before = app.group().Count();
        List<AddressData> beforeList = app.getAddressHelper().getAddressList();
        if (! app.getAddressHelper().isThereAAddress()) {
            app.goTo().gotoAddNewPage();
            app.getAddressHelper().createAddress(new AddressData("First name", "Middle name", "Last name", "Nickname", "title", "company", "address", null, "123456", "123456", "123456", "q@q.ru", "q1@q.ru", "q3@q.ru", "homepage", "1", "February", "1990", "6", "January", "2010","Group1" ,"address 2", "123678", "text"), true);
            app.goTo().homePage();
            before=before+1;
        }
        app.getAddressHelper().selectAddress(before-1);
        app.getAddressHelper().initAddressModification();
        app.getAddressHelper().fillAddressForm(new AddressData("First name (edited)", "Middle name(edited)", "Last name(edited)", "Nickname", "title", "company", "address", "123456", "123456", "123456", "123456", "q@q.ru", "q1@q.ru", "q3@q.ru", "homepage", "1", "February", "1990", "6", "January", "2010",null ,"address 2", "123678", "text"), false);
        app.getAddressHelper().submitAddressModification();
        app.goTo().homePage();
        int after = app.group().Count();
        List<AddressData> afterList = app.getAddressHelper().getAddressList();
        Assert.assertEquals(afterList.size(), beforeList.size());
        Assert.assertEquals(after, before);


    }

}
