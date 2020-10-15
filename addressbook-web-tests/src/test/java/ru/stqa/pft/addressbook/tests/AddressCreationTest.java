package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.AddressData;

public class AddressCreationTest extends TestBase {

    @Test
    public void testAddressCreation() throws Exception {
        app.getNavigationHelper().openHomePage();
        int before = app.getGroupHelper().Count();
        app.getNavigationHelper().gotoAddNewPage();
        app.getAddressHelper().createAddress(new AddressData("First name", "Middle name", "Last name", "Nickname", "title", "company", "address", null, "123456", "123456", "123456", "q@q.ru", "q1@q.ru", "q3@q.ru", "homepage", "1", "February", "1990", "6", "January", "2010", "Group1", "address 2", "123678", "text"), true);
        app.getNavigationHelper().openHomePage();
        int after = app.getGroupHelper().Count();
        Assert.assertEquals(after, before +1);

    }


}
