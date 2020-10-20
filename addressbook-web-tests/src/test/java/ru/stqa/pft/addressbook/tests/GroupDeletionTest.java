package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.group().getList().size() == 0) {
            app.group().createGroup(new GroupData("Group1", "Group header", "Group footer"));
            //before = before+1;
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        app.goTo().groupPage();
        List<GroupData> before = app.group().getList();
        int index = before.size() - 1;
        app.group().delete(index);
        //int after = app.getGroupHelper().Count();
        List<GroupData> after = app.group().getList();
        Assert.assertEquals(after.size(), before.size() - 1);
        //Assert.assertEquals(after, before - 1);


        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
