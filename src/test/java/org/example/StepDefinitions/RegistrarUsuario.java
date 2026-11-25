package org.example.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import org.example.Models.Usuario;
import org.example.Pages.HomePage;
import org.example.Pages.LoginPage;
import org.example.Pages.UserRegister;
import org.example.Tasks.CompletarFormularioRegistro;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.example.Tasks.NavigateHome;
import org.example.Utils.PreAndProsCondition;
import org.example.Utils.ScreenShot;

import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class RegistrarUsuario {
    
    @Given("el {actor} está en la página de inicio")
    public void elUsuarioEstaEnLaPaginaDeInicio(Actor actor) {
        //Para q es usuario entre a la pagina
        actor.attemptsTo(NavigateHome.theHomePage());
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(),"Abre la pagina");
        
    }

    @When("selecciona el enlace {string}")
    public void seleccionaElEnlace(String enlace) {
        Target enlaceTarget = switch (enlace) {
            case "Iniciar sesión" -> HomePage.LINK_LOGIN;
            case "Registrarse" -> HomePage.LINK_REGISTER;
            case "Inicio" -> HomePage.LINK_INICIO;
            default -> throw new IllegalArgumentException("Enlace no soportado en el paso: " + enlace);
        };

        theActorInTheSpotlight().attemptsTo(Click.on(enlaceTarget));

        ScreenShot.captureScreenshot(
                getDriver(),
                PreAndProsCondition.carpetaEvidencia(),
                "Seleccionar enlace " + enlace
        );
    }


    @And("completa el formulario con los datos {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void completaElFormularioConLosDatos(
            String nombre,
            String apellido,
            String fechaNacimiento,
            String pais,
            String codigoPostal,
            String numero,
            String calle,
            String ciudad,
            String estado,
            String telefono,
            String email,
            String password) {

        Usuario usuario = new Usuario(nombre, apellido, fechaNacimiento, pais, codigoPostal,
                numero, calle, ciudad, estado, telefono, email, password);

        theActorInTheSpotlight().attemptsTo(
                CompletarFormularioRegistro.con(usuario)
        );

        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(),"Ingreso de datos para el registro de un nuevo usuario");
    }

    @And("confirma el registro")
    public void confirmaElRegistro() {

        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(),"Confirmar el Registro del Usuario");
        theActorInTheSpotlight().attemptsTo(Click.on(UserRegister.BTN_REGISTRAR));
        
    }

    @Then("debería visualizar un mensaje de registro exitoso")
    public void deberiaVisualizarMensajeDeRegistroExitoso() {
        WebDriver driver = getDriver();

        // Esperar a lo que pase primero: redirige al login (éxito) o aparece el error del registro
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(LoginPage.SELECTOR_BTN_LOGIN),
                ExpectedConditions.visibilityOfElementLocated(UserRegister.MSG_ERROR_REGISTRO)
        ));

        List<String> errores = textosVisibles(driver, UserRegister.MSG_ERROR_REGISTRO);
        if (!errores.isEmpty()) {
            mostrarEnPantalla(driver, driver.findElement(UserRegister.MSG_ERROR_REGISTRO));
        }
        ScreenShot.captureScreenshot(driver, PreAndProsCondition.carpetaEvidencia(),
                errores.isEmpty() ? "Registro de Usuario Exitoso" : "Error en el registro");

        if (!errores.isEmpty()) {
            throw new AssertionError("El registro falló. Mensaje de la página: " + String.join(" | ", errores));
        }

        theActorInTheSpotlight().attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().contains("/auth/login")
        );
    }

    @Then("debería visualizar el mensaje de error de registro {string}")
    public void deberiaVisualizarElMensajeDeErrorDeRegistro(String mensajeEsperado) {
        WebDriver driver = getDriver();

        // Esperar a que aparezca el mensaje de error del registro
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(UserRegister.MSG_ERROR_REGISTRO));
        } catch (TimeoutException e) {
            ScreenShot.captureScreenshot(driver, PreAndProsCondition.carpetaEvidencia(), "No se mostró el error de registro");
            throw new AssertionError("No se mostró el mensaje de error del registro en 10 segundos");
        }

        WebElement mensaje = driver.findElement(UserRegister.MSG_ERROR_REGISTRO);
        String mensajeActual = mensaje.getText().trim();
        mostrarEnPantalla(driver, mensaje);
        ScreenShot.captureScreenshot(driver, PreAndProsCondition.carpetaEvidencia(), "Mensaje de error de registro");

        if (!mensajeActual.contains(mensajeEsperado)) {
            throw new AssertionError("Mensaje esperado: \"" + mensajeEsperado + "\" pero la página mostró: \"" + mensajeActual + "\"");
        }
    }

    // El mensaje de error aparece debajo del botón Registrar, fuera de la pantalla: lo centramos para la captura
    private static void mostrarEnPantalla(WebDriver driver, WebElement elemento) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", elemento);
    }

    private static List<String> textosVisibles(WebDriver driver, By selector) {
        return driver.findElements(selector).stream()
                .filter(WebElement::isDisplayed)
                .map(el -> el.getText().trim())
                .filter(texto -> !texto.isEmpty())
                .collect(Collectors.toList());
    }
}
