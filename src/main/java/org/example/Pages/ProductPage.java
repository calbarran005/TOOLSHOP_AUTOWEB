package org.example.Pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

public class ProductPage {


    public static Target PRODUCTO(String nombre) {
        return Target.the("Producto " + nombre)
                .located(By.xpath(
                        "//a[contains(@class,'card')][.//h5[@data-test='product-name' and normalize-space(.)='" + nombre + "']]"
                ));
    }

    public static final Target BTN_AGREGAR_CARRITO = Target.the("Botón Añadir al carrito")
            .located(By.cssSelector("button[data-test='add-to-cart']"));
}
