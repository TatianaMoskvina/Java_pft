package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.AddressData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class AddressCreationTest extends TestBase {

    @Test
    public void testAddressCreation() throws Exception {
        app.goTo().homePage();
        List<AddressData> before = app.getAddressHelper().list();
        AddressData address = new AddressData(before.get(before.size()-1).getId(), "First name", "Middle name", "Last name", "Nickname", "title", "company", "address", "123456", "123456", "123456", "123456", "q@q.ru", "q1@q.ru", "q3@q.ru", "homepage", "1", "February", "1990", "6", "January", "2010","Group1" ,"address 2", "123678", "text");
        create(address);
        app.goTo().homePage();
        List<AddressData> after = app.getAddressHelper().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        int max = 0;
        for (AddressData a : after) {
            if (a.getId() > max) {
                max = a.getId();
            }
        }
        address.setId(max);
        before.add(address);
        Comparator<? super AddressData> byId = (a1, a2) -> Integer.compare(a1.getId(), a2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    }

    private void create(AddressData address) {
        app.goTo().gotoAddNewPage();
        app.getAddressHelper().fillAddressForm(address, true);
        app.getAddressHelper().submitAddressCreation();
    }


}
