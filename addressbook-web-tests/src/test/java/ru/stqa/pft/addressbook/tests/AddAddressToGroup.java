package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Addresses;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddAddressToGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().initGroupCreation();
            app.group().createGroup(new GroupData().withName("Group1").withHeader("Header").withFooter("Footer"));
            app.goTo().homePage();
            Addresses address = app.db().address();
            if (app.db().address().size() == 0) {
                AddressData newAddress = new AddressData().withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk").withHome("123123123").withMobile("+72342342342").withWork("123123");
                app.getAddressHelper().create(newAddress);
                app.goTo().homePage();
            }

        }
    }


    @Test
    public void testAddressAddToGroup() {
        Groups groups = app.db().groups();
        Addresses before = app.db().address();
        app.goTo().homePage();
        AddressData pickedAddress = before.iterator().next();
        if (pickedAddress.getGroups().size() == groups.size()) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData().withName("test1"));
        }
        Groups groupsForAddressBefore = pickedAddress.getGroups();
        GroupData groupWithNewAddress = new GroupData();
        for (GroupData group : app.db().groups()) {
            if (!group.addressPresent(pickedAddress)) {
                app.goTo().homePage();
                app.getAddressHelper().addToGroup(pickedAddress, group);
                groupWithNewAddress = group;
            } else if (group.addressPresent(pickedAddress)) {
            }
        }
        app.goTo().homePage();
        AddressData newAddress = app.db().GetAddressById(pickedAddress.getId());
        Groups groupsForAddressAfter = newAddress.getGroups();
        assertThat(groupsForAddressBefore.size() + 1, CoreMatchers.equalTo(groupsForAddressAfter.size()));
        assertThat(groupsForAddressAfter, CoreMatchers.equalTo(groupsForAddressBefore.withAdded(groupWithNewAddress)));
        Addresses after = app.db().address();
        assertThat(after, equalTo(before.without(pickedAddress).withAdded(newAddress)));

    }
}
