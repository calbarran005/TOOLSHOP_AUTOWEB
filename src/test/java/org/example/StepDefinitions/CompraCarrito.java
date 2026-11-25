package org.example.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.example.Pages.CheckoutPage;
import org.example.Pages.HomePage;
import org.example.Pages.UserRegister;
import org.example.Tasks.AgregarProductoAlCarrito;
import org.example.Tasks.CompletarDireccionFacturacion;
import org.example.Tasks.PagarConTarjeta;
import org.example.Utils.PreAndProsCondition;
import org.example.Utils.ScreenShot;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class CompraCarrito {

    // Esperar esperar a que el carrito se actualice y llevar cuenta
    private int productosEnCarrito = 0;

    @When("agrega el producto {string} al carrito")
    public void agregaElProductoAlCarrito(String producto) {
        theActorInTheSpotlight().attemptsTo(AgregarProductoAlCarrito.llamado(producto));
        productosEnCarrito++;

        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(HomePage.CANTIDAD_CARRITO, containsText(String.valueOf(productosEnCarrito)))
                        .forNoMoreThan(10).seconds()
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Producto agregado al carrito - " + producto);
    }

    @And("abre el carrito de compras")
    public void abreElCarritoDeCompras() {
        theActorInTheSpotlight().attemptsTo(
                Click.on(HomePage.ICONO_CARRITO),
                // Esperamos un momento q cargue el carrito
                WaitUntil.the(CheckoutPage.BTN_PROCEDER_CARRITO, isVisible()).forNoMoreThan(20).seconds()
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Carrito de compras");
    }

    @And("procede al pago")
    public void procedeAlPago() {
        theActorInTheSpotlight().attemptsTo(Click.on(CheckoutPage.BTN_PROCEDER_CARRITO));
    }

    @Then("debería visualizar el mensaje de sesión iniciada {string}")
    public void deberiaVisualizarElMensajeDeSesionIniciada(String mensajeEsperado) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(CheckoutPage.MSG_SESION_INICIADA, isVisible()).forNoMoreThan(10).seconds(),
                Ensure.that(CheckoutPage.MSG_SESION_INICIADA).text().contains(mensajeEsperado)
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Sesión iniciada en el checkout");
    }

    @When("continúa con el pago")
    public void continuaConElPago() {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(CheckoutPage.BTN_PROCEDER_SESION, isEnabled()).forNoMoreThan(10).seconds(),
                Click.on(CheckoutPage.BTN_PROCEDER_SESION)
        );
    }

    @And("completa la dirección de facturación con {string}, {string}, {string}, {string}")
    public void completaLaDireccionDeFacturacion(String pais, String codigoPostal, String numero, String calle) {
        theActorInTheSpotlight().attemptsTo(
                CompletarDireccionFacturacion.con(pais, codigoPostal, numero, calle),

                Ensure.that(UserRegister.INPU_CALLE).value().isEqualTo(calle)
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Dirección de facturación");
    }

    @And("confirma la dirección de facturación")
    public void confirmaLaDireccionDeFacturacion() {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(CheckoutPage.BTN_PROCEDER_DIRECCION, isEnabled()).forNoMoreThan(10).seconds(),
                Click.on(CheckoutPage.BTN_PROCEDER_DIRECCION)
        );
    }

    @And("paga con tarjeta de crédito {string}, {string}, {string}, {string}")
    public void pagaConTarjetaDeCredito(String numero, String vencimiento, String cvv, String titular) {
        theActorInTheSpotlight().attemptsTo(
                PagarConTarjeta.con(numero, vencimiento, cvv, titular)
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Datos de la tarjeta de crédito");

        // Botón "Verificar pago"
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(CheckoutPage.BTN_FINALIZAR, isEnabled()).forNoMoreThan(10).seconds(),
                Click.on(CheckoutPage.BTN_FINALIZAR)
        );
    }

    @Then("debería visualizar el mensaje de pago {string}")
    public void deberiaVisualizarElMensajeDePago(String mensajeEsperado) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(CheckoutPage.MSG_PAGO_EXITOSO, isVisible()).forNoMoreThan(15).seconds(),
                Ensure.that(CheckoutPage.MSG_PAGO_EXITOSO).text().contains(mensajeEsperado)
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Pago verificado");
    }

    @When("confirma el pedido")
    public void confirmaElPedido() {
        theActorInTheSpotlight().attemptsTo(Click.on(CheckoutPage.BTN_FINALIZAR));
    }

    @Then("debería visualizar la confirmación del pedido con su número de factura")
    public void deberiaVisualizarLaConfirmacionDelPedido() {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(CheckoutPage.MSG_CONFIRMACION_PEDIDO, isVisible()).forNoMoreThan(15).seconds(),
                Ensure.that(CheckoutPage.MSG_CONFIRMACION_PEDIDO).text().contains("¡Gracias por tu pedido!"),
                Ensure.that(CheckoutPage.MSG_CONFIRMACION_PEDIDO).text().contains("INV-")
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.carpetaEvidencia(), "Pedido confirmado");
    }
}
