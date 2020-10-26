package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Addresses;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;


public class AddressModificationTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.getAddressHelper().list().size() == 0) {
            app.goTo().gotoAddNewPage();
            app.getAddressHelper().create(new AddressData().withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk").withNumber("123123123"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddressModification() {
        app.goTo().homePage();
        Addresses before = app.getAddressHelper().all();
        AddressData modifiedAddress = before.iterator().next();
        AddressData address = new AddressData().withId(modifiedAddress.getId()).withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk");
        app.getAddressHelper().modify(address);
        app.goTo().homePage();
        Addresses after = app.getAddressHelper().all();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedAddress).withAdded(address)));



    }

}
