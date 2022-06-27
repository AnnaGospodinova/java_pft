package ru.stqa.pft.mantis.appmanager;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void userEditPage(int id) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_edit_page.php?user_id=" + id);
    }
}
