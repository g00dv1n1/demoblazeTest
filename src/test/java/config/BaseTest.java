package config;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {
    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless = true; // или оставь, если уже задаёшь где-то

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-setuid-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1366,768"
        );

        Configuration.browserCapabilities = options;
    }
}
