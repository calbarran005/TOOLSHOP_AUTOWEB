package org.example.Pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;


public class CheckoutPage {


    public static final Target BTN_PROCEDER_CARRITO = Target.the("Botón Proceder al pago del carrito")
            .located(By.cssSelector("button[data-test='proceed-1']"));


    public static final Target MSG_SESION_INICIADA = Target.the("Mensaje de sesión iniciada")
            .located(By.xpath("//p[contains(.,'ya has iniciado sesión')]"));

    public static final Target BTN_PROCEDER_SESION = Target.the("Botón Proceder al pago de la sesión")
            .located(By.cssSelector("button[data-test='proceed-2']"));

    public static final Target BTN_PROCEDER_DIRECCION = Target.the("Botón Proceder al pago de la dirección")
            .located(By.cssSelector("button[data-test='proceed-3']"));


    public static final Target SELECT_METODO_PAGO = Target.the("Selector del método de pago")
            .located(By.cssSelector("select[data-test='payment-method']"));

    public static final Target INPU_NUMERO_TARJETA = Target.the("Input del número de tarjeta")
            .located(By.cssSelector("[data-test='credit_card_number']"));

    public static final Target INPU_FECHA_VENCIMIENTO = Target.the("Input de la fecha de vencimiento")
            .located(By.cssSelector("[data-test='expiration_date']"));

    public static final Target INPU_CVV = Target.the("Input del CVV")
            .located(By.cssSelector("[data-test='cvv']"));

    public static final Target INPU_TITULAR_TARJETA = Target.the("Input del titular de la tarjeta")
            .located(By.cssSelector("[data-test='card_holder_name']"));


    // Mismo botón para "Verificar pago" y luego "Confirmar"
    public static final Target BTN_FINALIZAR = Target.the("Botón Verificar pago / Confirmar")
            .located(By.cssSelector("button[data-test='finish']"));

    public static final Target MSG_PAGO_EXITOSO = Target.the("Mensaje de pago exitoso")
            .located(By.cssSelector("[data-test='payment-success-message']"));

    public static final Target MSG_CONFIRMACION_PEDIDO = Target.the("Mensaje de confirmación del pedido")
            .located(By.id("order-confirmation"));
}
