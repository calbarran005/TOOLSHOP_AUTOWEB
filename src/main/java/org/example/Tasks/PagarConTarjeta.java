package org.example.Tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import org.example.Pages.CheckoutPage;

public class PagarConTarjeta {

    public static Performable con(String numero, String vencimiento, String cvv, String titular) {
        return Task.where("{0} ingresa los datos de su tarjeta de crédito",
                SelectFromOptions.byValue("credit-card").from(CheckoutPage.SELECT_METODO_PAGO),
                Enter.theValue(numero).into(CheckoutPage.INPU_NUMERO_TARJETA),
                Enter.theValue(vencimiento).into(CheckoutPage.INPU_FECHA_VENCIMIENTO),
                Enter.theValue(cvv).into(CheckoutPage.INPU_CVV),
                Enter.theValue(titular).into(CheckoutPage.INPU_TITULAR_TARJETA)
        );
    }
}
