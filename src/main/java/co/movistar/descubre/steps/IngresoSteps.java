package co.movistar.descubre.steps;

import co.movistar.descubre.pageObject.IngresoPageObject;
import co.movistar.descubre.utils.*;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;

public class IngresoSteps {

    IngresoPageObject ingresoPageObject = new IngresoPageObject();
    DatosExcel datosExcel = new DatosExcel();
    Datos datos = new Datos();
    EsperaExplicita esperaExplicita = new EsperaExplicita();
    Scroll scroll = new Scroll();
    List<Login> datosLogin = datos.obtenerLogin("DatosUsuario");
    private static String urlLoginAdmin = "https://api.movistar-money-co-hmlg.credisfera.com.br/api/v1/oauth/token";
    private static String urlRequest = "https://api.movistar-money-co-hmlg.credisfera.com.br";
    private static String urlAuthToken = "https://api.movistar-money-co-hmlg.credisfera.com.br/api/v1/oauth/token";
    private static String urlApp = "https://api.movistar-money-co-hmlg.credisfera.com.br/api/v1/application/";

    @Step
    public void abrirNavegador(int iteracion) {

        System.out.println("..." + datosLogin.get(iteracion).toString());
        ingresoPageObject.openUrl(datosLogin.get(iteracion).getUrl());
    }

    @Step
    public void seleccionarTerminal() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getLnkCard()).click();
    }

    @Step
    public void clickBotonComprar() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getBtnFinanciar()).click();
    }

    @Step
    public void ingresarTelefono(int iteracion) {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getInputTelef()).sendKeys(datosLogin.get(iteracion).getCelular());
    }

    @Step
    public void ingresarCedula(int iteracion) {
        esperaExplicita.esperaElementoVisible(ingresoPageObject.getDriver(), ingresoPageObject.getInputCedula());
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getInputCedula()).sendKeys(datosLogin.get(iteracion).getCedula());
    }

    @Step
    public void ingresarCorreo(int iteracion) {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getInputCorreo()).sendKeys(datosLogin.get(iteracion).getCorreo());
    }

    @Step
    public void aceptarTerminos() {
        esperaExplicita.esperaElementoVisible(ingresoPageObject.getDriver(), ingresoPageObject.getTxtTerminos());
        scroll.scrollAlElemento(ingresoPageObject.getDriver(), ingresoPageObject.getTxtTerminos());
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getTxtTerminos()).click();
    }

    @Step
    public void cerrarPopUp() {
        esperaExplicita.esperaElementoVisible(ingresoPageObject.getDriver(), ingresoPageObject.getPopUpTerminos());
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getPopUpTerminos()).click();
    }


    @Step
    public String devolverEquipo(int iteracion) {
        return datosLogin.get(iteracion).getEquipo();
    }

    @Step
    public String devolverCuota(int iteracion) {
        return datosLogin.get(iteracion).getCuota();
    }

    @Step
    public void aceptarPublicidad() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getRaidBoxPublicidad()).click();
    }

    @Step
    public void clickEnContinuar() {
        esperaExplicita.esperaElementoVisible(ingresoPageObject.getDriver(), ingresoPageObject.getBtnContinuar());
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getBtnContinuar()).click();
    }


    @Step
    public void ingresoOtp1(int iteracion) {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getInputNumero1()).sendKeys(Character.toString(datosLogin.get(iteracion).getOtp().charAt(0)));
    }

    @Step
    public void ingresoOtp2(int iteracion) {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getInputNumero2()).sendKeys(Character.toString(datosLogin.get(iteracion).getOtp().charAt(1)));
    }

    @Step
    public void ingresoOtp3(int iteracion) {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getInputNumero3()).sendKeys(Character.toString(datosLogin.get(iteracion).getOtp().charAt(2)));
    }

    @Step
    public void ingresoOtp4(int iteracion) {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getInputNumero4()).sendKeys(Character.toString(datosLogin.get(iteracion).getOtp().charAt(3)));
    }

    @Step
    public void clickBotonContinuar() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getBtnContinuarOtp()).click();
    }

    @Step
    public void numeroDeCuotas(int iteracion) throws InterruptedException {
        //String cuota = datosLogin.get(iteracion).getCuota();
        List<WebElement> cuotas = ingresoPageObject.getDriver().findElements(By.xpath("//div[@class='btn-group-toggle btn-group bv-no-focus-ring']/label/input"));
        WebElement pivote = ingresoPageObject.getDriver().findElement(By.xpath("//label[contains(text()," + "'" + datosLogin.get(iteracion).getCuota() + "'" + ")]"));
        WebDriverWait wait = new WebDriverWait(ingresoPageObject.getDriver(), 25);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text()," + "'" + datosLogin.get(iteracion).getCuota() + "'" + ")]")));
        sleep(4000);
        pivote.click();
        System.out.println("Probando.." + pivote.getText());
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getBttnInfoCuotas()).click();
    }

    @Step
    public void botonConfirmar() {
        esperaExplicita.esperaElementoVisible(ingresoPageObject.getDriver(), ingresoPageObject.getBtnConfirmarCuota());
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getBtnConfirmarCuota()).click();
    }

    @Step
    public void clickSeleccionarDireccion() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getTxtEnviarDireccionActual()).click();
    }

    @Step
    public void clickBotonContinuarEnvio() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getBtnContinuarEnvio()).click();
    }

    @Step
    public void aceptarAcuerdoContrato() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getbtnAceptoContrato()).click();
    }

    @Step
    public void cerrarPopUpContrato() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getPopUpContrato()).click();
    }

    @Step
    public void clickBtnFinalizar() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getbtnFinalizar()).click();
    }

    @Step
    public void clickBtnPermitir() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getbtnPermitir()).click();
    }

    @Step
    public void clickBtnEnviar() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getbtnFinalizar()).click();
    }

    @Step
    public void clickBtnCerrarCalificacion() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getPopUpCalificacion()).click();
    }

    @Step
    public void clicEnBotonCerrar() {
        esperaExplicita.esperaElementoVisible(ingresoPageObject.getDriver(), ingresoPageObject.getBtnCerrar());
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getBtnCerrar()).click();
    }

    @Step
    public void verEncuesta() {
        assertThat(ingresoPageObject.getDriver().findElement(ingresoPageObject.getFrmEncuesta()).isDisplayed(), Matchers.is(true));
    }

    @Step
    public String verPregunta() {
        String pregunta = ingresoPageObject.getDriver().findElement(ingresoPageObject.getMsjPregunta()).getText();
        return pregunta;
    }

    @Step
    public void desmarcarEstrella5() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getBtnEstrella()).click();
    }

    @Step
    public void escribirMensaje() throws IOException {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getTxtEscribirMensaje()).sendKeys("Test");
    }

    @Step
    public void clicEnBtnEnviar() {
        ingresoPageObject.getDriver().findElement(ingresoPageObject.getBtnEnviar()).click();
    }

    @Step
    public void verVentanaEmergenteGracias() {
        assertThat(ingresoPageObject.getDriver().findElement(ingresoPageObject.getFrmVentanaEmergente()).isDisplayed(), Matchers.is(true));
    }

}
