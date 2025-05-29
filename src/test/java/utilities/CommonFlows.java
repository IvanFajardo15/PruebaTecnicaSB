package utilities;

import org.openqa.selenium.WebDriver;
import pages.RegisterPage;

public class CommonFlows {
    private WebDriver getDriver() {
        return new WebDriverProvider().get();
    }

    public void goToHomePage() {
        getDriver().get("https://demoqa.com/automation-practice-form");
        new RegisterPage().waitPageToLoad();
    }
}
