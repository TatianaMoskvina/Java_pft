package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class AddressDeletionTest extends TestBase{

    @Test (enabled = false)
    public void testAddressDeletion() {
        app.getNavigationHelper().openHomePage();
        List<AddressData> beforeList = app.getAddressHelper().getAddressList();
        int before = app.getGroupHelper().Count();
        if (! app.getAddressHelper().isThereAAddress()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getAddressHelper().createAddress(new AddressData("First name", "Middle name", "Last name", "Nickname", "title", "company", "address", null, "123456", "123456", "123456", "q@q.ru", "q1@q.ru", "q3@q.ru", "homepage", "1", "February", "1990", "6", "January", "2010","Group1" ,"address 2", "123678", "text"), true);
            app.getNavigationHelper().openHomePage();
            before = before+1;
        }
        app.getAddressHelper().selectAddress(before-1);
        app.getAddressHelper().deleteSelectedAddress();
        app.getNavigationHelper().openHomePage();
        int after = app.getGroupHelper().Count();
        List<AddressData> afterList = app.getAddressHelper().getAddressList();
        Assert.assertEquals(afterList.size(), beforeList.size() - 1);
        Assert.assertEquals(after, before-1);
    }
}
