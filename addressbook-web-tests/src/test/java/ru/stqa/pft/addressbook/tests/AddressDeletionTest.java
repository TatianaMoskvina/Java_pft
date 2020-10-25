package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

import java.util.List;

public class AddressDeletionTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.getAddressHelper().list().size() == 0) {
            app.goTo().gotoAddNewPage();
            app.getAddressHelper().create(new AddressData().withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddressDeletion() {
        app.goTo().homePage();
        List<AddressData> before = app.getAddressHelper().list();
        int index = before.size()-1;
        app.getAddressHelper().delete(index);
        app.goTo().homePage();
        List<AddressData> after = app.getAddressHelper().list();
        Assert.assertEquals(after.size(), index);


        before.remove(before.size()-1);
        Assert.assertEquals(before, after);
    }


}
