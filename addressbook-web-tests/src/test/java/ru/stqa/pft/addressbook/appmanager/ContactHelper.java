package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstname());
      type(By.name("lastname"), contactData.getLastname());
      //attach(By.name("photo"), contactData.getPhoto());
      type(By.name("address"), contactData.getAddress());
      type(By.name("mobile"), contactData.getMobilePhone());
      type(By.name("email"), contactData.getEmail1());

      if (creation) {
          if (contactData.getGroups().size() > 0) {
              Assert.assertTrue(contactData.getGroups().size() == 1);
              new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups()
                      .iterator().next().getName());
          } else {
              new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("[none]");
          }
      } else {
          Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }

    public void initContactCreation() {
      click(By.linkText("add new"));
    }

    public void returnToMainPage() {
      click(By.id("logo"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void selectToGroupById(int id) {
        wd.findElement(By.cssSelector("select[name=\"to_group\"] option[value='" + id + "']")).click();
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("select[name=\"group\"] option[value='" + id + "']")).click();
    }

    public void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        returnToMainPage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToMainPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void addGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        selectToGroupById(group.getId());
        click(By.name("add"));
        returnToMainPage();
    }

    public void removeGroup(ContactData contact, GroupData group) {
        selectGroupById(group.getId());
        selectContactById(contact.getId());
        click(By.name("remove"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String homePhone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3).withAddress(address)
                .withHomePhone2(homePhone2);
    }
}
