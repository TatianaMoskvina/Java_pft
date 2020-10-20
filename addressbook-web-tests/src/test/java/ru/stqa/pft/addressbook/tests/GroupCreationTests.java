package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        List<GroupData> before = app.group().getList();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "Group1(edited)", "Group header", "Group footer");
        app.group().createGroup(group);
        List<GroupData> after = app.group().getList();
        Assert.assertEquals(after.size(), before.size() + 1);


        //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2)-> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
