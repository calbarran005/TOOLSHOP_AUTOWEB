package org.example.Tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.example.Pages.UserRegister;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


public class EsperarBusquedaDireccion implements Interaction {

    private static final Duration POLLING = Duration.ofMillis(100);

    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb navegador = BrowseTheWeb.as(actor);
        WebDriver driver = navegador.getDriver();


        navegador.setImplicitTimeout(0, ChronoUnit.SECONDS);

        try {
            esperarQueAparezcaYDesaparezca(driver, Duration.ofSeconds(2));

            esperarQueAparezcaYDesaparezca(driver, Duration.ofMillis(800));
        } finally {
            navegador.resetImplicitTimeout();
        }
    }

    private void esperarQueAparezcaYDesaparezca(WebDriver driver, Duration esperaAparicion) {
        try {
            new WebDriverWait(driver, esperaAparicion, POLLING)
                    .until(ExpectedConditions.visibilityOfElementLocated(UserRegister.MSG_BUSCANDO_DIRECCION));
        } catch (TimeoutException noHuboBusqueda) {
            return;
        }

        new WebDriverWait(driver, Duration.ofSeconds(10), POLLING)
                .until(ExpectedConditions.invisibilityOfElementLocated(UserRegister.MSG_BUSCANDO_DIRECCION));
    }

    public static EsperarBusquedaDireccion termine() {
        return Tasks.instrumented(EsperarBusquedaDireccion.class);
    }
}
