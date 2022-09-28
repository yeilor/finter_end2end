package co.movistar.descubre.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/ingreso.feature",
        glue = "co.movistar.descubre.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE)

public class IngresoRunner {
}
