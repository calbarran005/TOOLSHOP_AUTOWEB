package org.example.Tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.example.Pages.ProductPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarProductoAlCarrito {

    public static Performable llamado(String nombreProducto) {
        return Task.where("{0} agrega " + nombreProducto + " al carrito",
                WaitUntil.the(ProductPage.PRODUCTO(nombreProducto), isVisible()).forNoMoreThan(10).seconds(),
                Click.on(ProductPage.PRODUCTO(nombreProducto)),
                WaitUntil.the(ProductPage.BTN_AGREGAR_CARRITO, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(ProductPage.BTN_AGREGAR_CARRITO)
        );
    }
}
