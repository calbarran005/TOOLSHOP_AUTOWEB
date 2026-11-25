package org.example.Tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.example.Pages.HomePage;
import org.example.Pages.LoginPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarSesion {
    public static Performable con(String email, String password) {
        return Task.where("{0} inicia sesión con " + email,
                Click.on(HomePage.LINK_LOGIN),
                Enter.theValue(email).into(LoginPage.INPU_EMAIL),
                Enter.theValue(password).into(LoginPage.INPU_PASSWORD),
                Click.on(LoginPage.BTN_LOGIN),
                WaitUntil.the(HomePage.MENU_USUARIO, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}