Feature: Registro de un usuario en la pagina DemoQA

  Background: precondicion

    Given ingreso a la pagina de registro de usuarios de DemoQA

  @regression
  Scenario Outline: Ingresar datos de registro de diferentes usuarios del sistema

    When Ingreso "<nombre>" "<apellido>" "<correo>" y "<telefono>" en el formulario y doy click en el boton de enviar

    Then Verifico que los datos "<nombre>" "<apellido>" "<correo>" y "<telefono>" se registren de manera exitosa

    Examples:

      | nombre | apellido | correo               | telefono   |
      | Ivan   | Fajardo  | iafajardoc@gmail.com | 3212534148 |
      | Maria  | Prueba   | prueba@gmail.com     | 3212534585 |

  @regression
  Scenario: Ingresar formulario omitiendo campos obligatorios

    When Envio el formulario con campos vacios

    Then Verifico que los campos obligatorios se marquen en rojo

  @regression
  Scenario: Ingresar formulario usando formato de correo no valido

    When Ingreso "Ivan" con un formato inválido

    Then Verifico que el campo de correo se marque en rojo