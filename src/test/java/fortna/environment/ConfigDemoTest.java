package fortna.environment;

import org.junit.jupiter.api.Test;
import utilities.ConfigReader;

public class ConfigDemoTest {
    @Test
    public void test1(){
        System.out.println(ConfigReader.getProperty("serenity.project.name"));
        System.out.println(ConfigReader.getProperty("environment.editor.username"));

    }
}
