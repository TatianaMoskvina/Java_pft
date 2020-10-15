package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;


public class AddressModificationTest extends TestBase{

    @Test
    public void testAddressModification() {
        app.getNavigationHelper().openHomePage();
        int before = app.getGroupHelper().Count();
        if (! app.getAddressHelper().isThereAAddress()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getAddressHelper().createAddress(new AddressData("First name", "Middle name", "Last name", "Nickname", "title", "company", "address", null, "123456", "123456", "123456", "q@q.ru", "q1@q.ru", "q3@q.ru", "homepage", "1", "February", "1990", "6", "January", "2010","Group1" ,"address 2", "123678", "text"), true);
            before=before+1;
        }
        app.getAddressHelper().selectAddress();
        app.getAddressHelper().initAddressModification();
        app.getAddressHelper().fillAddressForm(new AddressData("First name (edited)", "Middle name(edited)", "Last name(edited)", "Nickname", "title", "company", "address", "123456", "123456", "123456", "123456", "q@q.ru", "q1@q.ru", "q3@q.ru", "homepage", "1", "February", "1990", "6", "January", "2010",null ,"address 2", "123678", "text"), false);
        app.getAddressHelper().submitAddressModification();
        app.getNavigationHelper().openHomePage();
        int after = app.getGroupHelper().Count();
        Assert.assertEquals(after, before);

    }

}
