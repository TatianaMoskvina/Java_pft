package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Addresses;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Addresses before = app.getAddressHelper().all();
        AddressData deletedAddress = before.iterator().next();
        app.getAddressHelper().delete(deletedAddress);
        app.goTo().homePage();
        assertThat(app.getAddressHelper().Count(), CoreMatchers.equalTo(before.size()-1));
        Addresses after = app.getAddressHelper().all();
        assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.without(deletedAddress)));
    }


}
