Feature: Registrar un nuevo usuario

  @RegistrarNuevoUsuario @Registrar
  Scenario: Registrar exitosamente un usuario con datos válidos

    ## CAMBIAR DATOS SEGN CORRESPODAN
    Given el usuario está en la página de inicio
    When selecciona el enlace "Iniciar sesión"
    And selecciona el enlace "Registrarse"
    And completa el formulario con los datos "Carlos", "Albarran", "2005-02-09", "PE", "13801", "123", "Avenida de Prueba", "Pacasmayo", "La Libertad", "987654321", "calbarran@gmail.com", "Pucca15978@"
    And confirma el registro
    Then debería visualizar un mensaje de registro exitoso

  @RegistroEmailExistente @Registrar
  Scenario: Registrar un usuario con un email ya registrado

    Given el usuario está en la página de inicio
    When selecciona el enlace "Iniciar sesión"
    And selecciona el enlace "Registrarse"
    And completa el formulario con los datos "Carlos", "Albarran", "2005-02-09", "PE", "13801", "123", "Avenida de Prueba", "Pacasmayo", "La Libertad", "987654321", "calbarran@gmail.com", "Pucca15978@"
    And confirma el registro
    Then debería visualizar el mensaje de error de registro "A customer with this email address already exists."
