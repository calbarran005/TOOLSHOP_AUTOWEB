package org.example.Tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import org.example.Pages.UserRegister;

public class CompletarDireccionFacturacion {

    public static Performable con(String pais, String codigoPostal, String numero, String calle) {
        return Task.where("{0} completa la dirección de facturación",
                SelectFromOptions.byValue(pais).from(UserRegister.SELECT_COUNTRY),
                Enter.theValue(codigoPostal).into(UserRegister.INPU_POSTAL_CODE),
                Enter.theValue(numero).into(UserRegister.INPU_HOUSENUMERO),
                EsperarBusquedaDireccion.termine(),
                Enter.theValue(calle).into(UserRegister.INPU_CALLE)
        );
    }
}
