package io.swagger;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "scr/test/resources/features",
        glue = "io.swagger.IT.steps",
        plugin = "pretty"
)

public class SwaggerApiIT {


}
