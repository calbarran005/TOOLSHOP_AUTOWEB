# TOOLSHOP_AUTOWEB

Proyecto de automatizacion web de la pagina Toolshop, hecho con Serenity BDD + Cucumber usando el patron Screenplay.
- Página consultada para las automatizaciones: https://practicesoftwaretesting.com/
## Que se prueba

- Registro de usuario (datos validos y email ya registrado)
- Inicio de sesion (credenciales validas e incorrectas)
- Navegacion por categorias y cierre de sesion
- Compra de productos desde el carrito pagando con tarjeta

## Herramientas

- Java 17
- Maven
- Serenity BDD 4.0.3
- Cucumber
- JUnit 4
- Chrome

## Estructura

```
src/main/java/org/example
  Pages/    -> elementos de cada pagina
  Tasks/    -> acciones del usuario
  Models/   -> clases de datos
  Utils/    -> utilidades (capturas, pre y post condiciones)

src/test/java/org/example
  StepDefinitions/  -> pasos de los features
  RunnerTest.java   -> runner de cucumber

src/test/resources/features  -> escenarios en Gherkin
```

## Como ejecutar

Todo:

```
mvn clean verify
```

Por tag:

```
mvn clean verify -Dcucumber.filter.tags="@Login"
```

El reporte de Serenity queda en `target/site/serenity/index.html` y las capturas de cada escenario se guardan en la carpeta `evidencias/`.

## Nota

Antes de correr el registro hay que cambiar el correo en `01_registrar_usuario.feature`, porque si ya existe el escenario falla.
