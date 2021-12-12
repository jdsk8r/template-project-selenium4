package e2e.support.config;

import no.sanchezrolfsen.framework.selenium.rest.Config;

public class ConfigUtil {

    public static Config getConfig() {
        return new ExamplePortalConfig();
    }
}
