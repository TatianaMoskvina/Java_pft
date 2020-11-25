package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Addresses;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteAddressFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        if (app.db().address().size() == 0) {
            AddressData newAddress = new AddressData().withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk").withHome("123123123").withMobile("+72342342342").withWork("123123").withGroup(groups.iterator().next());
            app.getAddressHelper().create(newAddress);
            app.goTo().homePage();
        }


    }

    @Test
    public void testDeleteAddressFromGroup() {
        Groups groups = app.db().groups();
        Addresses before = app.db().address();
        app.goTo().homePage();
        AddressData removedAddress = before.iterator().next();
        GroupData group = groups.iterator().next();

        if (removedAddress.getGroups().size() == 0) {
            app.goTo().groupPage();
            app.getAddressHelper().addToGroup(removedAddress, group);
        }

        int addressId = removedAddress.getId();
        AddressData newAddrress = app.db().GetAddressById(addressId);
        Groups groupsForAddressBefore = newAddrress.getGroups();
        app.goTo().homePage();
        GroupData groupWithDeletedAddress = newAddrress.getGroups().iterator().next();
        app.getAddressHelper().removeAddressFromGroup(removedAddress, groupWithDeletedAddress);
        Groups groupsForAddressAfter = app.db().GetAddressById(addressId).getGroups();
        assertThat(groupsForAddressBefore.size() - 1, equalTo(groupsForAddressAfter.size()));
        assertThat(groupsForAddressAfter, equalTo(groupsForAddressBefore.without(groupWithDeletedAddress)));
    }
}
