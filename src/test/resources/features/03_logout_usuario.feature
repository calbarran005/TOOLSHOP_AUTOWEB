
Feature: Navegación por categorías y cierre de sesión

  @NavegacionCierreSesion
  Scenario: Seleccionar subcategorías, navegar por productos y cerrar sesión

    Given el usuario ya inició sesión
    And selecciona el enlace "Inicio"
    When selecciona las subcategorías "Hammer", "Wrench" y "Drill"
    And selecciona la siguiente página de productos
    Then quiero visualizar la siguiente página de productos
    When selecciona la opción "Mi perfil"
    And selecciona la opción "Cerrar sesión"
    Then debería visualizar que la sesión se cerró correctamente