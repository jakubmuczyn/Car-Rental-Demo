package pl.sda.carrental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.carrental.configuration.DatasourceConfig;

@SpringBootTest
public class SpringProfilesIntegrationTest {
    
    @Autowired
    DatasourceConfig datasourceConfig;
    
    public void setupDatasource() {
        datasourceConfig.setup();
    }
}