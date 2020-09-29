package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class AddressDeletionTest extends TestBase{

    @Test
    public void testAddressDeletion() {
        //app.getNavigationHelper().openHomePage();
        app.getAddressHelper().selectAddress();
        app.getAddressHelper().deleteSelectedAddress();
        //app.getNavigationHelper().openHomePage();
    }
}
