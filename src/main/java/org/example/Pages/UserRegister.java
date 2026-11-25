package org.example.Pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;


public class UserRegister extends PageObject{

    public static final Target INP_USERNAME = Target
            .the("Input para ingresar username")
            .located(By.id("first_name"));

    public static final Target INPU_LASTNAME = Target
            .the("Input para ingresar lastname")
            .located(By.id("last_name"));

    public static final Target INPU_BIRTHDAY = Target
            .the("Input para ingresar lastname")
            .located(By.id("dob"));

    public static final Target SELECT_COUNTRY = Target
            .the("Selector para seleccionar el país")
            .located(By.id("country"));


    public static final Target INPU_POSTAL_CODE = Target
            .the("Input para ingresar postalcode")
            .located(By.id("postal_code"));

    public static final Target INPU_HOUSENUMERO = Target
            .the("Input para ingresar numero de la casa")
            .located(By.id("house_number"));

    public static final Target INPU_CALLE = Target
            .the("Input para ingresar la calle donde vivimos")
            .located(By.id("street"));

    public static final Target INPU_CIUDAD = Target
            .the("Input para ingresar nuestra ciudad")
            .located(By.id("city"));

    public static final Target INPU_ESTADO = Target
            .the("Input para ingresar nuestro estado")
            .located(By.id("state"));

    public static final Target INPU_TELEFONO = Target
            .the("Input para ingresar telefono personal")
            .located(By.id("phone"));

    public static final Target INPU_EMAIL = Target
            .the("Input para ingresar el email")
            .located(By.id("email"));

    public static final Target INPU_PASSWORD = Target
            .the("Input para ingresar la contraseña")
            .located(By.id("password"));


    // Mensaje que muestra la página mientras autocompleta la dirección
    public static final org.openqa.selenium.By MSG_BUSCANDO_DIRECCION = org.openqa.selenium.By.xpath(
            "//*[contains(text(),'Buscando direcci') or contains(text(),'Looking up address')]");

    // Mensaje de error general del registro (ej: email ya registrado). Solo aparece si falla.
    public static final org.openqa.selenium.By MSG_ERROR_REGISTRO =
            org.openqa.selenium.By.cssSelector("[data-test='register-error']");

    // Cualquier mensaje de error visible del formulario (incluye validaciones de cada campo)
    public static final org.openqa.selenium.By MSGS_ERROR_FORMULARIO =
            org.openqa.selenium.By.cssSelector(".alert-danger");

    public static final Target BTN_REGISTRAR = Target
            .the("Botón para confirmar el registro")
            .located(By.cssSelector("button[data-test='register-submit']"));
}
