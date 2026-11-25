package org.example.Pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;



public class NavigateLogOut {

    public static Target SUBCATEGORY(String categoria) {
        return Target.the("Subcategoría " + categoria)
                .located(By.xpath(
                        "//label[normalize-space(.)='" + categoria + "']"
                ));
    }
    // Tarjetas de productos del listado
    public static final org.openqa.selenium.By PRODUCTOS =
            org.openqa.selenium.By.cssSelector("a.card[data-test^='product-']");

    // Opción "Mi perfil" dentro del menú del usuario
    public static final Target OPCION_MI_PERFIL = Target.the("Opción Mi perfil")
            .located(By.cssSelector("[data-test='nav-my-profile']"));

    // Campo "Nombre" de la página Mi perfil (se llena cuando cargan los datos del usuario)
    public static final org.openqa.selenium.By PERFIL_NOMBRE =
            org.openqa.selenium.By.cssSelector("[data-test='first-name']");

    // Opción "Cerrar sesión" dentro del menú del usuario
    public static final Target OPCION_CERRAR_SESION = Target.the("Opción Cerrar sesión")
            .located(By.cssSelector("[data-test='nav-sign-out']"));

    public static final Target SIGUIENTE_PAGINA = Target.the("Página 2 de productos")
            .located(By.cssSelector("button[aria-label='Page-2']"));
}
