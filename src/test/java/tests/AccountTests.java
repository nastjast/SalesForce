package tests;

import jdk.jfr.Description;
import objects.Account;
import objects.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTests extends BaseTest {


    //TODO: is that possible to use several Data Providers in one test? F.e.dataProvider = "Create account", "Create contact"
    @Test(dataProvider = "Create account",
            dataProviderClass = DataProviderTest.class)
    @Description("Login -> create account -> compare name, website and phone of created account on account details page (right after clicking Save btn")
    public void createAccount1Test(User user, Account account) {
        basePage.openUrl()
                .login(user)
                .clickLoginBtn();
        newAccountPage.openModalWindowPage()
                .createAccount(account)
                .clickSave();
        Assert.assertEquals(createdAccountPage.getCreatedAccountName(), account.getAccountName());
        Assert.assertEquals(createdAccountPage.getCreatedAccountWebsite(), account.getWebsite());
        Assert.assertEquals(createdAccountPage.getCreatedAccountPhone(), account.getPhone());
    }

    @Test(dataProvider = "Create account" ,
            dataProviderClass = DataProviderTest.class)
    @Description("Login -> create account -> compare name, website and phone of created account on Account list page (last created)")
    public void createAccount2Test(User user, Account account) {
        basePage.openUrl()
                .login(user)
                .clickLoginBtn();
        newAccountPage.openModalWindowPage()
                .createAccount(account)
                .clickSave();
        accountsListPage.openAccountsList();
        Assert.assertEquals(accountsListPage.getLastAccountName(), account.getAccountName());
        Assert.assertEquals(accountsListPage.getLastAccountPhone(), account.getPhone());
    }
}