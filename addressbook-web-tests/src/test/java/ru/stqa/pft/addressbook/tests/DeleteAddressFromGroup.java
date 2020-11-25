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
            AddressData newAddress = new AddressData().withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk").withHome("123123123").withMobile("+72342342342").withWork("123123").inGroup(groups.iterator().next());
            app.getAddressHelper().create(newAddress);
            app.goTo().homePage();
        }


    }

    @Test
    public void testRemovingContactFromGroup() {
        Groups groups = app.db().groups();
        Addresses before = app.db().address();
        app.goTo().homePage();
        AddressData removingContact = before.iterator().next(); //removingContact
        GroupData group = groups.iterator().next();

        if (removingContact.getGroups().size() == 0) {
            app.goTo().groupPage();
            app.getAddressHelper().addToGroup(removingContact, group);
        }

        int contactId = removingContact.getId();
        AddressData newAddrress = app.db().GetAddressById(contactId);
        Groups groupsOfContactBefore = newAddrress.getGroups();
        app.goTo().homePage();
        GroupData groupWithoutContact = newAddrress.getGroups().iterator().next();
        app.getAddressHelper().removeAddressFromGroup(removingContact, groupWithoutContact);
        Groups groupsOfContactAfter = app.db().GetAddressById(contactId).getGroups();
        assertThat(groupsOfContactBefore.size() - 1, equalTo(groupsOfContactAfter.size()));
        assertThat(groupsOfContactAfter, equalTo(groupsOfContactBefore.without(groupWithoutContact)));
    }
}
