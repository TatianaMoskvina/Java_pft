package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

import java.util.Comparator;
import java.util.List;


public class AddressModificationTest extends TestBase{

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
    public void testAddressModification() {
        app.goTo().homePage();
        List<AddressData> before = app.getAddressHelper().list();
        int index = before.size()-1;
        AddressData address = new AddressData().withId(before.get(before.size()-1).getId()).withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk");
        app.getAddressHelper().modify(index, address);
        app.goTo().homePage();
        List<AddressData> after = app.getAddressHelper().list();
        Assert.assertEquals(after.size(), before.size());


        before.remove(index);
        before.add(address);
        Comparator<? super AddressData> byId = (a1, a2) -> Integer.compare(a1.getId(), a2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);



    }

}
