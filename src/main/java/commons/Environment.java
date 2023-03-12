package commons;

import org.aeonbits.owner.Config;
@Config.Sources({"file:environmentList/${server}.properties"})
public interface Environment extends Config {
    @Key("UserURL")
    String getUserURL();

    @Key("AdminURL")
    String getAdminURL();
}
