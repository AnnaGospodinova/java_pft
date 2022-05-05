package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactCreation(new ContactData("TestName", "Surname", "Sochi", "111111"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToMainPage();
  }

}
