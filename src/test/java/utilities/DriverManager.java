package utilities;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private final boolean runServer = System.getenv("JOB_NAME") != null;

    public void buildDriver() {
        if (runServer) {

        } else {
            buildLocalDriver();
        }
    }

    public void buildLocalDriver() {
        final var headlessMode = System.getProperty("headless") != null;
        var browserProperty = System.getProperty("browser");
        if (browserProperty == null) {
            browserProperty = "CHROME";
        }

        final var browser = Browser.valueOf(browserProperty.toUpperCase());

        Logs.debug("Inicializando el driver");
        final var driver = switch (browser) {
            case EDGE -> {
                final var edgeOptions = new EdgeOptions();
                if (headlessMode) {
                    edgeOptions.addArguments("--headless=new");
                }
                yield new EdgeDriver(edgeOptions);
            }
            case CHROME -> {
                final var chromeOptions = new ChromeOptions();
                if (headlessMode) {
                    chromeOptions.addArguments("--headless=new");
                }
                yield new ChromeDriver(chromeOptions);
            }
            case SAFARI -> new SafariDriver();
            case FIREFOX -> new FirefoxDriver();
        };

        Logs.debug("Maximizando la pantalla");
        driver.manage().window().maximize();

        Logs.debug("Borrando las cookies");
        driver.manage().deleteAllCookies();

        new WebDriverProvider().set(driver);
    }

    public void killDriver() {
        new WebDriverProvider().get().quit();
    }

    private enum Browser {
        CHROME,
        EDGE,
        FIREFOX,
        SAFARI
    }
}
