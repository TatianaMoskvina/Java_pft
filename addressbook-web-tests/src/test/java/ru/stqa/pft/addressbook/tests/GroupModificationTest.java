package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().getList().size() == 0) {
            app.group().createGroup(new GroupData().withName("Modification1"));
            //before = before+1;
        }
    }


    @Test
    public void testGroupModification() {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().
                withId(modifiedGroup.getId()).withName("Group1(edited)").withHeader("Group header").withFooter("Group footer");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());


        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }




}
