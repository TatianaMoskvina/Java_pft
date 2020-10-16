package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> beforeList = app.getGroupHelper().getGroupList();
        int before = app.getGroupHelper().Count();
        app.getGroupHelper().createGroup(new GroupData("Group1", "Group header", "Group footer"));
        int after = app.getGroupHelper().Count();
        List<GroupData> afterList = app.getGroupHelper().getGroupList();
        Assert.assertEquals(afterList.size(), beforeList.size() + 1);
        Assert.assertEquals(after, before + 1);
    }

}
