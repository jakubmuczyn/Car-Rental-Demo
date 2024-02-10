package pl.sda.carrental.configuration;

import org.springframework.stereotype.Component;

@Component
public interface DatasourceConfig {
    public void setup();
}
