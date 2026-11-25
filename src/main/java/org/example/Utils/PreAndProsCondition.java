package org.example.Utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class PreAndProsCondition {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreAndProsCondition.class);


    private static String carpetaEvidencia = Parameters.RUTA_EVIDENCIA;

    public static String carpetaEvidencia() {
        return carpetaEvidencia;
    }

    private static void iniciarCarpetaEvidencia(String nombreEscenario) {
        carpetaEvidencia = Parameters.RUTA_EVIDENCIA + ScreenShot.getCurrentDateTime() + "_" + nombreEscenario;
        ScreenShot.createFolder(carpetaEvidencia);
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        String nombre = sinSaltosDeLinea(scenario.getName());
        LOGGER.info("_____________________________________________________");
        LOGGER.info("Running: {}", nombre);

        OnStage.setTheStage(new OnlineCast());
        iniciarCarpetaEvidencia(nombre);

        // limpiar sesión del usuario
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("localStorage.removeItem('settings');");
        jsExecutor.executeScript("sessionStorage.removeItem('username');");
        jsExecutor.executeScript("sessionStorage.removeItem('NRBA_SESSION_ID');");
    }

    @After
    public void afterScenario(Scenario scenario) {
        LOGGER.info("Finishing: {}", sinSaltosDeLinea(scenario.getName()));
    }


    private static String sinSaltosDeLinea(String texto) {
        return texto.replace('\n', ' ').replace('\r', ' ');
    }
}
