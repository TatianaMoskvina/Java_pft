package ru.stqa.pft.addressbook.tests;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Addresses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressCreationTest extends TestBase {

    @DataProvider
        public Iterator<Object[]> validAddressesFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/addresses.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<AddressData> contacts = gson.fromJson(json.toString(), new TypeToken<List<AddressData>>() {
        }.getType());
        return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }

    @Test (dataProvider = "validAddressesFromJson")
    public void testAddressCreation(AddressData address) throws Exception {
        app.goTo().homePage();
        File photo = new File("src/test/resources/photo.jpg");
        Addresses before = app.getAddressHelper().all();
        //AddressData address = new AddressData().withFirstName("Ivan").withLastName("Petrov").withEmail("q@q.ru").withAddress("Tomsk").withHome("123123123").withMobile("+72342342342").withWork("123123").withPhoto(photo);
        app.goTo().gotoAddNewPage();
        app.getAddressHelper().create(address);
        app.goTo().homePage();
        assertThat(app.getAddressHelper().Count(), CoreMatchers.equalTo(before.size()+1));
        Addresses after = app.getAddressHelper().all();

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
