Feature: Comprar productos desde el carrito

  @CompraCarrito
  Scenario: Comprar dos productos pagando con tarjeta de crédito

    Given el usuario ya inició sesión
    And selecciona el enlace "Inicio"
    When agrega el producto "Combination Pliers" al carrito
    And selecciona el enlace "Inicio"
    And agrega el producto "Claw Hammer" al carrito
    And abre el carrito de compras
    And procede al pago
    Then debería visualizar el mensaje de sesión iniciada "Hola Carlos Albarran, ya has iniciado sesión. Puedes proceder al pago."
    When continúa con el pago
    And completa la dirección de facturación con "PE", "13801", "123", "Avenida de Prueba"
    And confirma la dirección de facturación
    And paga con tarjeta de crédito "1111-1111-1111-1111", "12/2030", "123", "Carlos Albarran"
    Then debería visualizar el mensaje de pago "Payment was successful"
    When confirma el pedido
    Then debería visualizar la confirmación del pedido con su número de factura
