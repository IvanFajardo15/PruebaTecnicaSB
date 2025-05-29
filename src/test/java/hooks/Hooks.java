package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.DriverManager;
import utilities.FileManager;
import utilities.Logs;

public class Hooks {
    private final DriverManager driverManager = new DriverManager();

    @Before
    public void before(Scenario scenario) {
        Logs.info("Before: %s", scenario.getName());
        driverManager.buildDriver();
    }

    @After
    public void after(Scenario scenario) {
        Logs.info("After: %s Status: %s", scenario.getName(), scenario.getStatus());
        switch (scenario.getStatus()) {
            case FAILED, SKIPPED -> {
                FileManager.deletePreviusScreenshot();
                FileManager.attachScreenShot(scenario);
                FileManager.attachPageSource(scenario);
                FileManager.getScreenshot(scenario.getName());
                FileManager.getPageSource(scenario.getName());
            }
        }
        driverManager.killDriver();
    }
}
