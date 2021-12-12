package e2e.support;

import e2e.support.config.ConfigUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import no.sanchezrolfsen.framework.selenium.AbstractHooks;
import no.sanchezrolfsen.framework.selenium.Browser;
import no.sanchezrolfsen.framework.selenium.config.BrowserConfig;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.logging.Level;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class Hooks extends AbstractHooks {
    @Before
    public void beforeEach() {
        if (runBeforeAll) {
            beforeAll();
        }
    }

    @Override
    public void beforeAll() {
        ConfigUtil.getConfig().printConfig();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            Browser.init(this.getBrowserConfig());
        } catch (RuntimeException | MalformedURLException var2) {
            var2.printStackTrace();
            this.unexpectedShutdown("Fail on browser configuration, stopping test-run");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            stopWatch.stop();
            log.info("Selenium has finished running the tests. Run-time: " + stopWatch);
            Browser.vanillaDriver().quit();
        }
        ));

        Browser.vanillaDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
        log.info("Starting running tests");
        LogEntries logEntries = Browser.vanillaDriver().manage().logs().get(LogType.BROWSER);
        if(logEntries != null) {
            Browser.jsExecutor().executeScript("console.clear();");
        }
        runBeforeAll = false;
    }

    @Override
    public BrowserConfig getBrowserConfig() {
        return ConfigUtil.getConfig().getBrowserConfig();
    }

    @After
    public void after() {
        clearBrowserStorage();
        checkBrowserConsoleErrors();
    }

    private void clearBrowserStorage() {
        try {
            Browser.jsExecutor().executeScript("window.sessionStorage.clear();");
        } catch (WebDriverException e) {
            log.warn("Couldn't delete session storage");
        }
        try {
            Browser.jsExecutor().executeScript("window.localStorage.clear();");
        } catch (WebDriverException e) {
            log.warn("Couldn't delete local storage");
        }
    }

    private void checkBrowserConsoleErrors() {
        LogEntries logEntries = Browser.vanillaDriver().manage().logs().get(LogType.BROWSER);

        for(LogEntry logEntry : logEntries) {
            if(logEntry.toString().contains("favicon.ico")) {
                continue;
            }
            if(logEntry.getLevel().equals(Level.SEVERE)){
                log.error("Console error: " + logEntry.getMessage());
            }
            assertThat(logEntry.getLevel().equals(Level.SEVERE))
                    .withFailMessage("There is a browser console-error: %s", logEntry.getMessage())
                    .isFalse();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        afterFailedScenario(scenario);
    }
}
