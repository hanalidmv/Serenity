package fortna.environment.allEnvs;

import org.junit.jupiter.api.Test;
import utilities.ConfigReader;

public class EnvsTest {
    @Test
    public void test1(){

        System.out.println(ConfigReader.getProperty("webdriver.base.url"));

    }
}
