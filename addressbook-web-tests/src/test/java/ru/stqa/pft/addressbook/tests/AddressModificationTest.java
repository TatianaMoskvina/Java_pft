package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Addresses;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
        if (app.db().address().size()== 0) {
            app.goTo().gotoAddNewPage();
            app.getAddressHelper().create(new AddressData().withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk").withHome("123123123"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddressModification() {
        app.goTo().homePage();
        Addresses before = app.db().address();
        //Addresses before = app.getAddressHelper().all();
        AddressData modifiedAddress = before.iterator().next();
        AddressData address = new AddressData().withId(modifiedAddress.getId()).withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk");
        app.getAddressHelper().modify(address);
        app.goTo().homePage();
        assertThat(app.getAddressHelper().Count(), CoreMatchers.equalTo(before.size()));
        //Addresses after = app.getAddressHelper().all();
        Addresses after = app.db().address();

        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedAddress).withAdded(address)));



    }

}
