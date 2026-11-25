Feature: Iniciar sesión de un usuario

  @LoginExitoso @Login
  Scenario: Iniciar sesión con credenciales válidas

    Given el usuario está en la página de inicio
    When selecciona el enlace "Iniciar sesión"
    And ingresa su correo electrónico "calbarran@gmail.com" y contrasenia "Pucca15978@"
    And confirma el inicio de sesión
    Then debería visualizar su cuenta iniciada correctamente


  @LoginUsuarioInvalido @Login
  Scenario Outline: Iniciar sesión con credenciales incorrectas

    Given el usuario está en la página de inicio
    When selecciona el enlace "Iniciar sesión"
    And ingresa su correo electrónico "<correo>" y su contraseña "<password>"
    And confirma el inicio de sesión
    Then debería visualizar el mensaje de error "Invalid email or password"

    Examples:
      | correo                       | password           |
      | usuarioinexistente@gmail.com | Password123@       |
      | carlos@gmail.com             | PasswordIncorrecto |