package org.example.Tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import org.example.Models.Usuario;
import org.example.Pages.UserRegister;

public class CompletarFormularioRegistro {


    public static Performable con(Usuario usuario) {
        return Task.where("{0} completa el formulario de registro con " + usuario.getEmail(),
                Enter.theValue(usuario.getNombre()).into(UserRegister.INP_USERNAME),
                Enter.theValue(usuario.getApellido()).into(UserRegister.INPU_LASTNAME),
                Enter.theValue(usuario.getFechaNacimiento()).into(UserRegister.INPU_BIRTHDAY),
                SelectFromOptions.byValue(usuario.getPais()).from(UserRegister.SELECT_COUNTRY),
                Enter.theValue(usuario.getCodigoPostal()).into(UserRegister.INPU_POSTAL_CODE),
                Enter.theValue(usuario.getNumero()).into(UserRegister.INPU_HOUSENUMERO),

                EsperarBusquedaDireccion.termine(),

                Enter.theValue(usuario.getCalle()).into(UserRegister.INPU_CALLE),
                Enter.theValue(usuario.getCiudad()).into(UserRegister.INPU_CIUDAD),
                Enter.theValue(usuario.getEstado()).into(UserRegister.INPU_ESTADO),
                Enter.theValue(usuario.getTelefono()).into(UserRegister.INPU_TELEFONO),
                Enter.theValue(usuario.getEmail()).into(UserRegister.INPU_EMAIL),
                Enter.theValue(usuario.getPassword()).into(UserRegister.INPU_PASSWORD)
        );
    }
}
