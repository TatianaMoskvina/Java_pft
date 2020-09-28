package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.GroupData;

public class AddressCreationTest extends TestBase{

    @Test
    public void testAddressCreation() throws Exception {
        app.gotoAddNewPage();
        app.fillAddressForm(new AddressData("First name", "Middle name", "Last name", "Nickname", "title", "company", "address", "123456", "123456", "123456", "123456", "q@q.ru", "q1@q.ru", "q3@q.ru", "homepage", "1", "February", "1990", "6", "January", "2010", "address 2", "123678", "text"));
        app.submitAddressCreation();
        app.openHomePage();

    }




}
