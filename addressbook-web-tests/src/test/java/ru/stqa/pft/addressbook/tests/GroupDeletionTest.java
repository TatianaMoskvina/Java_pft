package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData().withName("Deletion1"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        //Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().Count(), CoreMatchers.equalTo(before.size()-1));
        //Groups after = app.group().all();
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }

}
