package org.example.Pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

// Variables de la pag web
@DefaultUrl("https://practicesoftwaretesting.com/")
public class HomePage extends PageObject {


    public static final Target LINK_LOGIN = Target.the("Enlace para iniciar sesión")
            .located(By.cssSelector("a[data-test='nav-sign-in']"));

    public static final Target LINK_REGISTER = Target.the("Enlace para registrarse")
            .located(By.cssSelector("a[data-test='register-link']"));

    public static final Target LINK_INICIO = Target.the("Enlace para la pagina de Inicio")
            .located(By.cssSelector("a[data-test='nav-home']"));

        //APARECE CUANDO INICIAMOS SESION
    public static final Target MENU_USUARIO = Target.the("menú del usuario")
            .located(By.cssSelector("[data-test='nav-menu']"));

    public static final Target ICONO_CARRITO = Target.the("Icono del carrito")
            .located(By.cssSelector("a[data-test='nav-cart']"));

    public static final Target CANTIDAD_CARRITO = Target.the("Cantidad de productos en el carrito")
            .located(By.cssSelector("[data-test='cart-quantity']"));


}
