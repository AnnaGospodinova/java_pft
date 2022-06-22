package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsInGroupsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Test1"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("group1"));
            app.goTo().goToHomePage();
        }
    }

    @Test
    public void testAddContactInGroup() {
        ContactData selectedContact = app.db().contacts().iterator().next();
        Groups groupsBefore = selectedContact.getGroups();
        GroupData selectedGroup = app.db().groups().iterator().next();
        app.contact().addGroup(selectedContact, selectedGroup);
        ContactData addedContact = app.db().contacts().stream().filter(x -> x.getId() == selectedContact.getId()).iterator().next();
        Groups groupsAfter = addedContact.getGroups();
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(selectedGroup)));
    }

    @Test
    public void testRemoveContactFromGroup() {
        if (app.db().contacts().stream().filter(g -> g.getGroups().size() > 0).collect(Collectors.toList()).size() == 0) {
            testAddContactInGroup();
        }
        ContactData selectedContact = app.db().contacts().stream().filter(g -> g.getGroups().size() > 0).iterator().next();
        Groups groupsBefore = selectedContact.getGroups();
        GroupData selectedGroup = groupsBefore.stream().iterator().next();
        app.contact().removeGroup(selectedContact, selectedGroup);
        ContactData removedContact = app.db().contacts().stream().filter(x -> x.getId() == selectedContact.getId()).iterator().next();
        Groups groupsAfter = removedContact.getGroups();
        assertThat(groupsAfter, equalTo(groupsBefore.without(selectedGroup)));
    }
}
