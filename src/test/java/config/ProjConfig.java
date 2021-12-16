package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:${environment}.properties",
        "file:src/test/resources/local.properties"
})
public interface ProjConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://github.com")
    String getBaseUrl();

    @Key("cookies")
    String getCookies();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("version")
    @DefaultValue("94.0")
    String getVersion();

    @Key("environment")
    @DefaultValue("local")
    String getEnvironment();

    @Key("URL")
    @DefaultValue("http://localhost:4444/wd/hub")
    String getURL();
}