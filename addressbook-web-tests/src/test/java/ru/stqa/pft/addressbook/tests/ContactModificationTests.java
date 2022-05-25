package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("TestName", "Surname", "Sochi", "111111", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData("Name", "Lastname", "Adler", "222222", null);
        app.getContactHelper().fillContactCreation(contact, false);
        app.getContactHelper().submitContactModification();
        app.goTo().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byLastName = Comparator.comparing(ContactData::getLastname);
        before.sort(byLastName);
        after.sort(byLastName);
        Assert.assertEquals(before, after);
    }
}
