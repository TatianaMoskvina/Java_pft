package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.group().getList().size() == 0) {
            app.group().createGroup(new GroupData().withName("Modification1"));
            //before = before+1;
        }
    }


    @Test
    public void testGroupModification() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().getList();
        int index = before.size() - 1;
        GroupData group = new GroupData().
                withId(before.get(index).getId()).withName("Group1(edited)").withHeader("Group header").withFooter("Group footer");
        app.group().modify(index, group);
        //int after = app.getGroupHelper().Count();
        List<GroupData> after = app.group().getList();
        Assert.assertEquals(after.size(), before.size());
        //Assert.assertEquals(after, before);


        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }




}
