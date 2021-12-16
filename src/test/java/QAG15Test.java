
import config.ProjConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QAG15Test {
    ProjConfig config = ConfigFactory.create(ProjConfig.class, System.getProperties());
    public static WebDriver driver = null;

    @BeforeEach
    public void setUp() throws RuntimeException {
        if (config.getEnvironment().equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(config.getBrowser());
            capabilities.setVersion(config.getVersion());
            try {
                driver = new RemoteWebDriver(new URL(config.getURL()), capabilities);
//                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities); //for debug
            } catch (MalformedURLException e) {
                System.out.println("Invalid URL");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (config.getEnvironment().equals("local")){
            if (config.getBrowser().equals("firefox")) {
                driver = new FirefoxDriver();
            } else {
                driver = new ChromeDriver();
            }
        } else {
            System.out.println("Incorrect environment set, please use 'local' or 'remote' -Denvironment");
            System.exit(1);
        }
    }

    @Test
    public void t001() {
        driver.get(config.getBaseUrl());
        assertEquals("GitHub: Where the world builds software · GitHub", driver.getTitle());
        driver.quit();
    }
}