package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Addresses;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddAddressToGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().address().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().initGroupCreation();
                app.group().createGroup(new GroupData().withName("Group1").withHeader("Header").withFooter("Footer"));
                app.goTo().homePage();
            }
            AddressData address = new AddressData().withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk").withHome("123123123").withMobile("+72342342342").withWork("123123");
            app.getAddressHelper().create(address);
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddressAddToGroup() {

        Addresses before = app.db().address();
        AddressData pickAddress = before.iterator().next();
        AddressData address = new AddressData().withId(pickAddress.getId()).withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk").withHome("123123123").withMobile("+72342342342").withWork("123123");
        app.getAddressHelper().addToGroup(address);
        app.goTo().homePage();
        Addresses after = app.db().address();
        assertThat(after, equalTo(before.without(pickAddress).withAdded(address)));

    }

}
