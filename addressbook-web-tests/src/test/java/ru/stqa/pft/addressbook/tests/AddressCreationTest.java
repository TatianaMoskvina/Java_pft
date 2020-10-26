package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Addresses;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressCreationTest extends TestBase {

    @Test
    public void testAddressCreation() throws Exception {
        app.goTo().homePage();
        Addresses before = app.getAddressHelper().all();
        AddressData address = new AddressData().withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk").withNumber("123123123");
        app.goTo().gotoAddNewPage();
        app.getAddressHelper().create(address);
        app.goTo().homePage();
        Addresses after = app.getAddressHelper().all();
        assertThat(after.size(), equalTo(before.size() + 1));

//        int max = 0;
//        for (AddressData a : after) {
//            if (a.getId() > max) {
//                max = a.getId();
//            }
//        }
//
//        address.withId(max);

        assertThat(after, equalTo(
                before.withAdded(address.withId(after.stream().mapToInt((a) -> a.getId()).max().getAsInt()))));

//        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    }


}
