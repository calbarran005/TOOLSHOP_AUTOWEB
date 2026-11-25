package org.example.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import org.example.Pages.HomePage;
import org.example.Pages.NavigateLogOut;
import org.example.Tasks.IniciarSesion;
import org.example.Tasks.NavigateHome;
import org.example.Utils.PreAndProsCondition;
import org.example.Utils.ScreenShot;


import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class NavLogOut {


    private List<WebElement> productosAntes;
    
    
    @Given("el {actor} ya inició sesión")
    public void elUsuarioYaInicioSesion(Actor actor) {
        actor.attemptsTo(
                NavigateHome.theHomePage(),
                IniciarSesion.con("calbarran@gmail.com", "Pucca15978@")
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Sesión iniciada");
    }

    @When("selecciona las subcategorías {string}, {string} y {string}")
    public void seleccionaLasSubcategorias(
            String categoria1,
            String categoria2,
            String categoria3
            ) {
        
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(NavigateLogOut.SUBCATEGORY(categoria1), isVisible()).forNoMoreThan(8).seconds(),
                Click.on(NavigateLogOut.SUBCATEGORY(categoria1)),
                Click.on(NavigateLogOut.SUBCATEGORY(categoria2)),
                Click.on(NavigateLogOut.SUBCATEGORY(categoria3))
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(),
                "Seleccionar subcategorías " + categoria1 + ", " + categoria2 + " y " + categoria3);
    }



    @And("selecciona la siguiente página de productos")
    public void seleccionaLaSiguientePaginaDeProductos() {
        productosAntes = getDriver().findElements(NavigateLogOut.PRODUCTOS);
        theActorInTheSpotlight().attemptsTo(
                Click.on(NavigateLogOut.SIGUIENTE_PAGINA)
        );
    }

    @Then("quiero visualizar la siguiente página de productos")
    public void quieroVisualizarLaSiguientePaginaDeProductos() {
        WebDriver driver = getDriver();
        WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(10));
        if (productosAntes != null && !productosAntes.isEmpty()) {
            try {
                espera.until(ExpectedConditions.stalenessOf(productosAntes.get(0)));
            } catch (TimeoutException ignored) {
            }
        }
        WebElement primerProducto = espera.until(
                ExpectedConditions.visibilityOfElementLocated(NavigateLogOut.PRODUCTOS));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'start'});", primerProducto);
        ScreenShot.captureScreenshot(
                getDriver(),
                PreAndProsCondition.carpetaEvidencia(),
                "Navegación de la siguiente página de Productos"
        );
    }



    @When("selecciona la opción {string}")
    public void seleccionaLaOpcion(String opcion) {

        // Opciones
        Target opcionTarget = switch (opcion) {
            case "Mi perfil" -> NavigateLogOut.OPCION_MI_PERFIL;
            case "Cerrar sesión" -> NavigateLogOut.OPCION_CERRAR_SESION;
            default -> throw new IllegalArgumentException("Opción no soportada en el paso: " + opcion);
        };

        // Abrir el menú del usuario
        theActorInTheSpotlight().attemptsTo(
                Click.on(HomePage.MENU_USUARIO),
                WaitUntil.the(opcionTarget, isVisible()).forNoMoreThan(10).seconds()
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Menú del usuario desplegado - " + opcion);
        theActorInTheSpotlight().attemptsTo(Click.on(opcionTarget));

        if ("Mi perfil".equals(opcion)) {
            WebDriver driver = getDriver();
            WebElement nombre = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(NavigateLogOut.PERFIL_NOMBRE));
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.attributeToBeNotEmpty(nombre, "value"));
            ScreenShot.captureScreenshot(driver, PreAndProsCondition.carpetaEvidencia(), "Mi perfil");
        }
    }

    @Then("debería visualizar que la sesión se cerró correctamente")
    public void deberiaVisualizarQueLaSesionSeCerroCorrectamente() {

        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(HomePage.LINK_LOGIN, isVisible()).forNoMoreThan(10).seconds(),
                Ensure.that(HomePage.MENU_USUARIO).isNotDisplayed()
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Sesión cerrada correctamente");
    }


}
