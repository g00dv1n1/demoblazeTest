package config;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    @BeforeAll
    static void setUp() {
        // идём на Selenium-контейнер из docker-compose
        Configuration.remote = "http://selenium:4444/wd/hub";

        Configuration.browser = "chrome";        // для standalone-chromium это ок
        Configuration.headless = true;          // в контейнере GUI не нужен
        Configuration.timeout = 8000;

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080"
        );

        Configuration.browserCapabilities = options;
    }
}
