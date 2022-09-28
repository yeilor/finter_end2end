package co.movistar.descubre.stepdefinitions;

import co.movistar.descubre.pageObject.IngresoPageObject;
import co.movistar.descubre.steps.IngresoSteps;
import co.movistar.descubre.utils.Datos;
import co.movistar.descubre.utils.Login;
import io.cucumber.java.es.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.*;
import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class IngresoStepDefinition {

    @Steps
    IngresoSteps ingresoSteps;

    @Steps
    IngresoPageObject ingresoPageObject;

    private static String urlLoginAdmin = "https://api.movistar-money-co-hmlg.credisfera.com.br/api/v1/oauth/token";
    private static String urlRequest = "https://api.movistar-money-co-hmlg.credisfera.com.br";
    private static String urlAuthToken = "https://api.movistar-money-co-hmlg.credisfera.com.br/api/v1/oauth/token";
    private static String urlApp = "https://api.movistar-money-co-hmlg.credisfera.com.br/api/v1/application/";
    public String id_proposta = null, cuotaServicio = null, valorCuota = null, resposta = null, order = null, message = null, direction = "https://certfinter.movistarmoney.co/direction",
            txtVeridas = "Por tu seguridad vamos a validar tu identidad con unas preguntas!";
    public static Response responseAdmin, responseToken, responseApp, responseCancelarSolicitud, responseCambiarEstado, responseListIntegrations;
    Datos datos = new Datos();
    List<Login> datosLogin = datos.obtenerLogin("DatosUsuario");
    public int iteracion = 0;

    @Dado("que se tiene una configuracion para el servicio")
    public void queSeTieneUnaConfiguracionParaElServicio() {
        System.out.println("Se Tiene la configuracion Lista.");
    }

    @Y("Se consume el servicio de token admin")
    public void seConsumeElServicioDeTokenAdmin() {
        HashMap<String, String> headers = new HashMap<>();
        HashMap<String, String> postContentBody = new HashMap<>();
        try {
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            headers.put("Accept", "*/*");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            headers.put("Connection", "keep-alive");

            postContentBody.put("grant_type", "password");
            postContentBody.put("username", "04485370000");
            postContentBody.put("password", "1234");

            responseAdmin = given().auth()
                    .basic("credisfera", "credisfera")
                    .log().all()
                    .formParams(postContentBody)
                    .contentType(ContentType.URLENC)
                    .when()
                    .headers(headers)
                    .post(urlLoginAdmin);
            responseAdmin.prettyPrint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Y("Se consume el servicio de auth token")
    public void seConsumeElServicioDeAuthToken() {
        HashMap<String, String> headers = new HashMap<>();
        HashMap<String, String> postContentBody = new HashMap<>();
        try {
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            headers.put("Accept", "*/*");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            headers.put("Connection", "keep-alive");

            postContentBody.put("grant_type", "password");
            postContentBody.put("username", datosLogin.get(iteracion).getCedula());
            postContentBody.put("password", "1234");

            responseToken = given().auth()
                    .basic("credisfera", "credisfera")
                    .log().all()
                    .formParams(postContentBody)
                    .contentType(ContentType.URLENC)
                    .when()
                    .headers(headers)
                    .post(urlAuthToken);
            responseToken.prettyPrint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Y("Se cancela la solicitud")
    public void seCancelaLaSolicitud() {
        System.out.println("***********************CANCELANDO SOLICITUD****************************");
        System.out.println("**************************************************************************");
        HashMap<String, String> headers = new HashMap<>();
        try {
            JsonPath objectAdmin = responseAdmin.jsonPath();
            JsonPath objectToken = responseToken.jsonPath();
            String access_token_admin = objectAdmin.getString("access_token");
            String documentNumber = objectToken.getString("documentNumber");

            headers.put("Accept", "*/*");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            headers.put("Connection", "keep-alive");
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Bearer " + access_token_admin);

            String urlSolid = urlRequest + "/api/v1/cert/cancel-client-proposals/" + documentNumber;
            responseCancelarSolicitud = given()
                    .log().all()
                    .when()
                    .headers(headers)
                    .post(urlSolid);
            System.out.println("  ************ responseCancelarSolicitud " + responseCancelarSolicitud.getBody().print());
            responseCancelarSolicitud.then().assertThat().statusCode(is(equalTo(200)));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Cuando("^el usuario se encuentra activo en la campana$")
    public void elUsuarioSeEncuentraEnLaCampana() {
        try {
            ingresoSteps.abrirNavegador(this.iteracion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Y("^llena todos los campos aceptando T&C y cierra el pop UP$")
    public void llenaTodosLosCamposYAceptaTCYCierraElPopUP() throws InterruptedException {
        try {
            ingresoSteps.ingresarCedula(this.iteracion);
            ingresoSteps.ingresarCorreo(this.iteracion);
            ingresoSteps.ingresarTelefono(this.iteracion);
            ingresoSteps.aceptarTerminos();
            ingresoSteps.cerrarPopUp();
            ingresoSteps.aceptarPublicidad();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Y("^da click en el boton continuar$")
    public void daClickEnElBotonContinuar() {
        try {
            ingresoSteps.clickEnContinuar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Y("^que el usuario recibe un codigo e ingresa los numeros$")
    public void queElUsuarioRecibeUnCodigoEIngresaLosNumeros() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(ingresoPageObject.getDriver(), 15);
            wait.until(ExpectedConditions.elementToBeClickable(ingresoPageObject.getInputNumero1()));
            ingresoSteps.ingresoOtp1(this.iteracion);
            ingresoSteps.ingresoOtp2(this.iteracion);
            ingresoSteps.ingresoOtp3(this.iteracion);
            ingresoSteps.ingresoOtp4(this.iteracion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Y("^da click al boton continuar$")
    public void daClickAlBotonContinuar() {
        try {
            ingresoSteps.clickBotonContinuar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Entonces("^avanza en la pantalla y elige el numero de cuotas$")
    public void eligeUnNumeroDeCuotas() throws InterruptedException {
        ingresoSteps.numeroDeCuotas(this.iteracion);
    }

    @Y("^se muestra el celular seleccionado$")
    public void seMuestraElCelularSeleccionado() throws Exception {
        try {
            if (!ingresoSteps.devolverEquipo(this.iteracion).equals("0") && !ingresoSteps.devolverEquipo(this.iteracion).isEmpty()) {
                System.out.println("Si valida");
                String celularPage = ingresoPageObject.getDriver().findElement(By.xpath("//p[contains(text()," + "'" + ingresoSteps.devolverEquipo(this.iteracion) + "'" + ")]")).getText();
                Assert.assertEquals(ingresoSteps.devolverEquipo(this.iteracion), celularPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Y("^se oprime el boton confirmar$")
    public void seOprimeElBotonConfirmar() {
        try {
            ingresoSteps.botonConfirmar();
            ingresoSteps.clickBtnCerrarCalificacion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    @Y("^se muestra la pantalla de veridas y da clic en el boton permitir$")
    public void seMuestraLaPantallaDeVeridasYDaClicEnElBotonPermitir() throws InterruptedException {
        try{
            sleep(3000);
            Assert.assertThat(ingresoPageObject.getDriver().findElement(ingresoPageObject.getTxtVeridas()).isDisplayed(), Matchers.is(true));
            System.out.println("***********************************");
            System.out.println("Validación texto - evidente");
            System.out.println("***********************************");
            System.out.println("Texto esperado: " + txtVeridas);
            System.out.println("***********************************");
            System.out.println("Texto en pantalla: " + ingresoPageObject.getDriver().findElement(ingresoPageObject.getTxtVeridas()).getText());
            System.out.println("***********************************");
            sleep(2000);
            // ingresoSteps.clickBtnPermitir();
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/

    public void consultarApplicacion() {
        System.out.println("***********************CONSULTANDO APPLICATION****************************");
        System.out.println("**************************************************************************");
        HashMap<String, String> headers = new HashMap<>();
        try {
            JSONObject objectToken = new JSONObject(responseToken.getBody().asString());
            String access_token_auth = objectToken.get("access_token").toString();

            headers.put("Accept", "*/*");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            headers.put("Connection", "keep-alive");
            headers.put("Authorization", "Bearer " + access_token_auth);
            responseApp = RestAssured.given().log()
                    .all()
                    .when()
                    .headers(headers)
                    .get(urlApp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Y("Se consume el servicio de application")
    public void seConsumeElServicioDeApplication() {
        consultarApplicacion();
    }

    @Y("se cambia el estado y redirige a la pantalla de direccion")
    public void seCambiaElEstadoYRedirigeALaPantallaDeDireccion() throws InterruptedException {
        System.out.println("***********************  CAMBIANDO ESTADO  ****************************");
        System.out.println("**************************************************************************");
        HashMap<String, String> headers = new HashMap<>();
        try {
            JsonPath jsonResponse = responseApp.jsonPath();
            List<Map<String, String>> lista = jsonResponse.getList("applications");
            this.id_proposta = lista.get(0).get("applicationId");
            System.out.println("*************** ID PROPOSTA " + id_proposta);
            JsonPath jsonResponseAdmin = responseAdmin.jsonPath();
            String access_token_admin = jsonResponseAdmin.getString("access_token");
            headers.put("Accept", "*/*");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            headers.put("Connection", "keep-alive");
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Bearer " + access_token_admin);

            responseCambiarEstado = given()
                    .log().all()
                    .when()
                    .headers(headers)
                    .post(urlRequest + "/api/v1/cert/" + id_proposta + "/change-status/DELIVERY");

            System.out.println("responseCambiarEstado " + responseCambiarEstado.getBody().print());
            sleep(2000);
            ingresoPageObject.getDriver().navigate().to(direction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Y("^elige la dirección de envio del celular$")
    public void eligeLaDireccionDeEnvioDelCelular() {
        try {
            String currentUrl = ingresoPageObject.getDriver().getCurrentUrl();
            System.out.println("************* URL ************" + currentUrl);
            WebDriverWait wait = new WebDriverWait(ingresoPageObject.getDriver(), 10);
            wait.until(ExpectedConditions.elementToBeClickable(ingresoPageObject.getTxtEnviarDireccionActual()));
            ingresoSteps.clickSeleccionarDireccion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Y("^da click al boton continuar en la pantalla de envio$")
    public void daClickAlBotonContinuarEnLaPantallaDeEnvio() {
        try {
            WebDriverWait wait = new WebDriverWait(ingresoPageObject.getDriver(), 10);
            wait.until(ExpectedConditions.elementToBeClickable(ingresoPageObject.getBtnContinuarEnvio()));
            ingresoSteps.clickBotonContinuarEnvio();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Y("^da click al acuerdo del contrato y cierra el popup$")
    public void daClickAlAcuerdoDelContratoYCierraElPopup() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(ingresoPageObject.getDriver(), 5);
            wait.until(ExpectedConditions.elementToBeClickable(ingresoPageObject.getbtnAceptoContrato()));
            ingresoSteps.aceptarAcuerdoContrato();
            wait.until(ExpectedConditions.elementToBeClickable(ingresoPageObject.getPopUpContrato()));
            ingresoSteps.cerrarPopUpContrato();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Y("^da click en el boton finalizar$")
    public void daClickEnElBotonFinalizar() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(ingresoPageObject.getDriver(), 5);
            wait.until(ExpectedConditions.elementToBeClickable(ingresoPageObject.getbtnFinalizar()));
            ingresoSteps.clickBtnFinalizar();
            sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Y("valida el mensaje de confirmacion de envio")
    public void validaElMensajeDeConfirmacionDeEnvio() throws InterruptedException {
        HashMap<String, String> headers = new HashMap<>();
        try {
            JsonPath jsonResponse = responseApp.jsonPath();
            List<Map<String, String>> lista = jsonResponse.getList("applications");
            this.id_proposta = lista.get(0).get("applicationId");
            ///Michael
            String celularOrden = "";
            if (ingresoSteps.devolverEquipo(this.iteracion).isEmpty()) {
                celularOrden = ingresoPageObject.getDriver().findElement(By.xpath("//p[contains(text()," + "'" + ingresoSteps.devolverEquipo(this.iteracion) + "'" + ")]")).getText();
                Assert.assertEquals(ingresoSteps.devolverEquipo(this.iteracion), celularOrden);
            }
            System.out.println("**************** CONSULTANDO LIST INTEGRATIONS *********************");
            System.out.println("**************************************************************************");
            JsonPath objectAdmin = responseAdmin.jsonPath();
            String access_token_admin = objectAdmin.getString("access_token");
            headers.put("Accept", "*/*");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            headers.put("Connection", "keep-alive");
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Bearer " + access_token_admin);
            sleep(5000);
            String urlSolid = urlRequest + "/api/v1/cert/" + id_proposta + "/list-integrations-data";
            responseListIntegrations = given()
                    .log().all()
                    .when()
                    .headers(headers)
                    .get(urlSolid);
            responseListIntegrations.prettyPrint();
            JsonPath jsonPath = responseListIntegrations.jsonPath();
            List<Map<String, String>> jsonApis = jsonPath.getList("");
            JSONObject jsonObject = null;
            if (jsonApis.size() >= 5) {
                for (String key : jsonApis.get(jsonApis.size() - 1).keySet()) {
                    if (jsonApis.get(jsonApis.size() - 1).get(key) != null) {
                        if (key.equals("resposta")) {
                            jsonObject = new JSONObject(jsonApis.get(jsonApis.size() - 1).get(key));
                        }

                    }
                }
                if (jsonObject.getString("returncode").equals("0")) {
                    //Assert.assertNotNull(jsonObject.getString("returnmessage"), jsonObject.getString("orderId"));
                    order = jsonObject.getString("orderId");
                    message = jsonObject.getString("returnmessage");

                    consultarApplicacion();
                    responseApp.prettyPrint();
                    JSONObject objectApp = new JSONObject(responseApp.getBody().asString());
                    JSONArray jArrayApp = new JSONArray(objectApp.getJSONArray("applications"));

                    for (int i = 0; i < jArrayApp.length(); i++) {
                        JSONObject objectInit = jArrayApp.getJSONObject(0);
                        cuotaServicio = objectInit.get("intendedInstallmentsNumber").toString();
                        JSONObject objOffer = objectInit.getJSONObject("offer");
                        for (int j = 0; j < objOffer.length(); j++) {
                            valorCuota = objOffer.get("totalFmt").toString();
                        }
                    }
                    System.out.println("***********************************");
                    System.out.println(" Validación orden exitosa ");
                    System.out.println("***********************************");
                    System.out.println("Mensaje de retorno " + message);
                    System.out.println("***********************************");
                    String orderValidar = " Orden " + order + " ";
                    String orderPagina = ingresoPageObject.getDriver().findElement(By.xpath("//span[contains(text()," + "'" + orderValidar + "'" + ")]")).getText();
                    if (orderValidar.equals(orderPagina)) {
                        System.out.println("N° Orden if " + order);
                        System.out.println("***********************************");
                    } else {
                        System.out.println("N° Orden " + order);
                        System.out.println("***********************************");
                    }
                    String nombreEquipo = ingresoPageObject.getDriver().findElement(By.xpath("//p[contains(text()," + "'" + ingresoSteps.devolverEquipo(this.iteracion) + "'" + ")]")).getText();
                    if (ingresoSteps.devolverEquipo(this.iteracion).isEmpty()) {
                        nombreEquipo = ingresoPageObject.getDriver().findElement(By.xpath("//p[@class='onboard-finish-card']")).getText();
                        System.out.println("Nombre equipo - página: " + nombreEquipo);
                        System.out.println("***********************************");
                    } else {
                        if (nombreEquipo.equals(ingresoSteps.devolverEquipo(this.iteracion))) {
                            System.out.println("Nombre equipo: " + celularOrden);
                            System.out.println("***********************************");
                        }
                    }
                    String cuotaFinalizar = cuotaServicio.trim();
                    String cuotaPagina = ingresoPageObject.getDriver().findElement(By.xpath("//p[contains(text()," + "'" + cuotaFinalizar + "'" + ")]")).getText();
                    if (ingresoSteps.devolverCuota(this.iteracion).equals(cuotaFinalizar) && ingresoSteps.devolverCuota(this.iteracion).equals(cuotaPagina)) {
                        System.out.println("Numero de cuotas: " + cuotaFinalizar);
                        System.out.println("***********************************");
                    }
                    String valorCuotaPagina = ingresoPageObject.getDriver().findElement(By.xpath("//p[contains(text()," + "'" + valorCuota + "'" + ")]")).getText();
                    if (valorCuotaPagina.trim().equals(valorCuota.trim())) {
                        System.out.println("Valor de la cuota: " + valorCuotaPagina);
                        System.out.println("***********************************");
                    } else {
                        System.out.println("Valor de la cuota: " + valorCuota);
                        System.out.println("***********************************");
                    }
                    System.out.println("Esperando...");
                    sleep(7000);
                    ingresoSteps.clickBtnFinalizar();
                    sleep(5000);
                    ingresoSteps.clickBtnCerrarCalificacion();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Entonces("verifica si existe más datos para probar")
    public void verificaSiExisteMásDatosParaProbar() throws Exception {
        ingresoSteps.clicEnBotonCerrar();
        ingresoSteps.verEncuesta();
        ingresoSteps.verPregunta();
        ingresoSteps.desmarcarEstrella5();
        ingresoSteps.escribirMensaje();
        ingresoSteps.clicEnBtnEnviar();
        // ingresoSteps.verVentanaEmergenteGracias();

      /*  Datos datos= new Datos();
        System.out.println("Tamaño...\t "+datos.obtenerLogin("DatosUsuario").size());
        for(int i=1; i <datos.obtenerLogin("DatosUsuario").size();i++){
            this.iteracion = i;
            System.out.println("Iteración:\t"+iteracion);
            sleep(4000);
            seConsumeElServicioDeAuthToken();
            sleep(3000);
            seCancelaLaSolicitud();
            sleep(3000);
            ingresoPageObject.getDriver().manage().deleteAllCookies();
            seConsumeElServicioDeTokenAdmin();
            elUsuarioSeEncuentraEnLaCampana();
            llenaTodosLosCamposYAceptaTCYCierraElPopUP();
            daClickEnElBotonContinuar();
            queElUsuarioRecibeUnCodigoEIngresaLosNumeros();
            daClickAlBotonContinuar();
            eligeUnNumeroDeCuotas();
            seMuestraElCelularSeleccionado();
            seOprimeElBotonConfirmar();
            seConsumeElServicioDeApplication();
            eligeLaDireccionDeEnvioDelCelular();
            daClickAlBotonContinuarEnLaPantallaDeEnvio();
            daClickAlAcuerdoDelContratoYCierraElPopup();
            daClickEnElBotonFinalizar();
            validaElMensajeDeConfirmacionDeEnvio();
        } */
    }

}

