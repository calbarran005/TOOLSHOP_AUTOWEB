package org.example.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.example.Pages.HomePage;
import org.example.Pages.LoginPage;
import org.example.Utils.PreAndProsCondition;
import org.example.Utils.ScreenShot;

import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class LoginUsuario {

    @And("ingresa su correo electrónico {string} y contrasenia {string}")
    public void ingresaSuCorreoElectronicoYContrasenia(String correo, String contrasenia) {


        theActorInTheSpotlight().attemptsTo(
                Enter.theValue(correo).into(LoginPage.INPU_EMAIL),
                Enter.theValue(contrasenia).into(LoginPage.INPU_PASSWORD)
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(),"Ingreso de datos para Inicio de Sesión");

    }

    @And("confirma el inicio de sesión")
    public void confirmaElInicioDeSesion() {
        theActorInTheSpotlight().attemptsTo(Click.on(LoginPage.BTN_LOGIN));
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(),"Inciar Sesion");
    }

    @Then("debería visualizar su cuenta iniciada correctamente")
    public void deberiaVisualizarSuCuentaIniciadaCorrectamente() {

        theActorInTheSpotlight().attemptsTo(

                WaitUntil.the(HomePage.MENU_USUARIO, isVisible()).forNoMoreThan(10).seconds(),
                Ensure.that(HomePage.MENU_USUARIO).text().isNotEmpty(),
                // Tras el login la página lleva a "Mi cuenta"
                Ensure.thatTheCurrentPage().currentUrl().contains("/account")
        );

        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Cuenta iniciada correctamente");
    }

    
    //Casos de Inicio de Sesion Negativos

    @And("ingresa su correo electrónico {string} y su contraseña {string}")
    public void ingresaSuCorreoElectronicoYSuContrasena(String correo, String contrasenia) {

        theActorInTheSpotlight().
                attemptsTo(Enter.theValue(correo).into(LoginPage.INPU_EMAIL));

        theActorInTheSpotlight().
                attemptsTo(Enter.theValue(contrasenia).into(LoginPage.INPU_PASSWORD));

        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(),"Ingreso de datos inválidos  para Inicio de Sesión");
    }

    @Then("debería visualizar el mensaje de error {string}")
    public void deberiaVisualizarElMensajeDeError(String mensajeEsperado) {

        theActorInTheSpotlight().attemptsTo(
                // Esperar a que la página muestre el mensaje de error
                WaitUntil.the(LoginPage.MSG_ERROR_LOGIN, isVisible()).forNoMoreThan(10).seconds(),
                // Validar que el mensaje contiene el texto esperado
                Ensure.that(LoginPage.MSG_ERROR_LOGIN).text().contains(mensajeEsperado),
                // Seguir en la página de login (no se inició sesión)
                Ensure.thatTheCurrentPage().currentUrl().contains("/auth/login")
        );

        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Mensaje de error de login");
    }
}
