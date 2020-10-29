package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddressData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressPhoneTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getAddressHelper().all().size() == 0) {
            app.getAddressHelper().create(
                    new AddressData().withFirstName("Ivan").withLastName("Petrov").withEmail("t@test.ru").withHome("(322)123123").withMobile("89765431212").withWork("123123"));
        }
    }

    @Test
    public void testAddressPhone() {
        AddressData address = app.getAddressHelper().all().iterator().next();
        AddressData addressInfoFromEditForm = app.getAddressHelper().infoFromEditForm(address);

        assertThat(address.getAllPhones(), equalTo(mergePhones(addressInfoFromEditForm)));
    }

    private String mergePhones(AddressData address) {
        return Arrays.asList(address.getHome(), address.getMobile(), address.getWork())
                .stream().filter((s) -> !s.equals(""))
                .map(AddressPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }


}
