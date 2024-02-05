package pl.sda.carrental.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevDatasourceConfig implements DatasourceConfig {
    
    @Override
    public void setup() {
        System.out.println("Setting up datasource for DEVELOPMENT environment.");
    }
}
