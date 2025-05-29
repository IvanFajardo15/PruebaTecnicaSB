package utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;


public class BaseTest {
    protected final String regression = "regression";
    protected final String smoke = "smoke";
    private final DriverManager driverManager = new DriverManager();

    @Before
    public void setUpMaster() {
        //driverManager.buildLocalDriver();
    }

    @After
    public void tearDownMaster() {
        driverManager.killDriver();
    }
}
