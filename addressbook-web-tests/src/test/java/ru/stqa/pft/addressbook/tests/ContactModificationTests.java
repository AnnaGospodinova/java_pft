package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("TestName", "Surname", "Sochi", "111111", "test1"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactCreation(new ContactData("Name", "Lastname", "Adler", "222222", null), false);
        app.getContactHelper().submitContactModification();
    }
}
