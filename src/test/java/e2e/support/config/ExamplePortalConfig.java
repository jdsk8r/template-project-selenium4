package e2e.support.config;

import lombok.extern.slf4j.Slf4j;
import no.sanchezrolfsen.framework.selenium.config.BrowserConfig;
import no.sanchezrolfsen.framework.selenium.config.BrowserConfigImpl;
import no.sanchezrolfsen.framework.selenium.config.BrowserType;
import no.sanchezrolfsen.framework.selenium.rest.Config;

import java.time.format.DateTimeFormatter;

@Slf4j
public class ExamplePortalConfig implements Config {
    private final String frontend_url = "https://expenses-react.sanchezrolfsen.no/";

    @Override
    public String getBaseUrl() {
        return frontend_url;
    }

    @Override
    public String getSeleniumGridUrl() {
        return null; //
    }

    @Override
    public BrowserConfig getBrowserConfig() {
        return BrowserConfigImpl.with()
                .browserType(BrowserType.CHROME)
                .build();
    }

    @Override
    public void printConfig() {
        log.info("Base URL:        {}", getBaseUrl());
    }

    @Override
    public DateTimeFormatter getStandardDateFormat() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }
}
