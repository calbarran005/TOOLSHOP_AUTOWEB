package org.example.Pages;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class LoginPage extends PageObject {

    // Selector del botón de login (se usa también en esperas con WebDriverWait)
    public static final org.openqa.selenium.By SELECTOR_BTN_LOGIN = By.cssSelector("[data-test='login-submit']");

    public static final Target INPU_EMAIL = Target
            .the("Input para ingresar el email")
            .located(By.id("email"));

    public static final Target INPU_PASSWORD = Target
            .the("Input para ingresar la contraseña")
            .located(By.id("password"));

    public static final Target BTN_LOGIN = Target.the("botón de login")
            .located(SELECTOR_BTN_LOGIN);

    //eL MENSAJE DE ERROR "Invalid email or password"

    public static final Target MSG_ERROR_LOGIN = Target.the("mensaje de error del login")
            .located(By.cssSelector("[data-test='login-error']"));
}
