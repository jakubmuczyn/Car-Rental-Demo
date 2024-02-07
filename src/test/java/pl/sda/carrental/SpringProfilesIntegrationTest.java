package pl.sda.carrental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.carrental.configuration.DatasourceConfig;

@SpringBootTest
public class SpringProfilesIntegrationTest {
    
    DatasourceConfig datasourceConfig;
    
    @Autowired
    public SpringProfilesIntegrationTest(DatasourceConfig datasourceConfig) {
        this.datasourceConfig = datasourceConfig;
    }
    
    @Test
    public void setupDatasource() {
        datasourceConfig.setup();
    }
}