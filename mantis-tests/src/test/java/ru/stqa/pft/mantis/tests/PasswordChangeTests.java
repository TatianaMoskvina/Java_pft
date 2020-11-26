package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

    private String link(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordChange() throws IOException, MessagingException {
        Users users = app.db().users();
        UserData user = users.iterator().next();
        System.out.println(user.getId());

        app.usersHelper().start("administrator", "root");
        app.usersHelper().reset(Integer.toString(user.getId()));

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String linkm = link(mailMessages, user.getEmail());
        System.out.println(linkm);
        app.usersHelper().changeAcc(linkm, user.getUsername());
        HttpSession session = app.newSession();

        assertTrue(session.isLoggedAs(user.getUsername()));
        assertTrue(session.login(user.getUsername(), "new_password"));
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}

