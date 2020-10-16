package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> beforeList = app.getGroupHelper().getGroupList();
        int before = app.getGroupHelper().Count();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("Group1", "Group header", "Group footer"));
            before = before+1;
        }
        app.getGroupHelper().selectGroup(before-1);
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().Count();
        List<GroupData> afterList = app.getGroupHelper().getGroupList();
        Assert.assertEquals(afterList.size(), beforeList.size() - 1);
        Assert.assertEquals(after, before - 1);
    }

}
