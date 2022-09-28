#language: es
Característica: Un usuario recibe un mensaje con un link para financiar un celular

  Escenario: Autotesting para Finter Movistar Money
    Dado que se tiene una configuracion para el servicio
    Y Se consume el servicio de token admin
    Y Se consume el servicio de auth token
    Y Se cancela la solicitud
    Cuando el usuario se encuentra activo en la campana
    Y llena todos los campos aceptando T&C y cierra el pop UP
    Y da click en el boton continuar
    Y que el usuario recibe un codigo e ingresa los numeros
    Y da click al boton continuar
    Entonces avanza en la pantalla y elige el numero de cuotas
    Y se muestra el celular seleccionado
    Y se oprime el boton confirmar
    Y Se consume el servicio de application
    Y elige la dirección de envio del celular
    Y da click al boton continuar en la pantalla de envio
    Y da click al acuerdo del contrato y cierra el popup
    Y da click en el boton finalizar
    Y valida el mensaje de confirmacion de envio
    Entonces verifica si existe más datos para probar