package co.movistar.descubre.pageObject;


import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;


public class IngresoPageObject extends PageObject {
    By inputTelef = By.xpath("//input[contains(@placeholder, 'Número de celular')]");
    By inputCedula= By.xpath("//input[contains(@placeholder, 'Documento de identidad')]");
    By inputCorreo= By.xpath("//input[contains(@placeholder, 'Ingresa tu correo electrónico')]");
    By txtTerminos= By.xpath("//input[@id='termsAndConditions']");
    By PopUpTerminos= By.xpath("//body/div[@id='modal-terms-and-conditions___BV_modal_outer_']/div[@id='modal-terms-and-conditions']/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]");
    By raidBoxPublicidad= By.xpath("//input[@id='dataPolicy']");
    By btnContinuarInicio = By.xpath("//div[@class='container-sticky']/div[1]/div[1]/button[1]");
    By msjError = By.xpath("//span[contains(text(),'Ahora no podemos procesar tu solicitud')]");
    By lnkCard = By.id("0");
    By btnFinanciar = By.xpath("//body/div[@id='wrapper']/main[1]/div[1]/div[3]/button[1]");
    By msjBienvenida = By.xpath("//p[@id='wellcomeEngage-phoenix']");
    By msjBotonTerminal = By.xpath("//h3[contains(text(),'ALCATEL TIGER X3')]");
    By msjCardTerminal = By.xpath("//h5[contains(text(),'ALCATEL TIGER X3')]");
    By inputNumero1 = By.xpath("(//input[contains(@class,'otp-input')])[1]");
    By inputNumero2 = By.xpath("(//input[contains(@class,'otp-input')])[2]");
    By inputNumero3 = By.xpath("(//input[contains(@class,'otp-input')])[3]");
    By inputNumero4 = By.xpath("(//input[contains(@class,'otp-input')])[4]");
    By btnContinuarOtp = By.xpath("//button[@class='continue-button continue-button-text']");
    By msjErrorOtp = By.xpath("//p[contains(text(),'Código Incorrecto.')]");
    By btnCuota3 = By.xpath("//div[@class='btn-group-toggle btn-group bv-no-focus-ring'][1]");
    By btnCuota6 = By.xpath("//div[@class='btn-group-toggle btn-group bv-no-focus-ring'][2]");
    By btnCuota12 = By.xpath("//div[@class='btn-group-toggle btn-group bv-no-focus-ring'][3]");
    By btnCuota18 = By.xpath("//div[@class='btn-group-toggle btn-group bv-no-focus-ring'][4]");
    By btnCuota24 = By.xpath("//div[@class='btn-group-toggle btn-group bv-no-focus-ring'][5]");
    By btnCuota36 = By.xpath("//div[@class='btn-group-toggle btn-group bv-no-focus-ring'][6]");
    By btnConfirmarCuota = By.xpath("//button[@class='continue-button continue-button-text']");
    By txtCelular = By.xpath("//p[contains(text(),'SAMSUNG ANDRES')]");
    By listCuotas = By.xpath("//fieldset[@class='form-group py-2 offer-form-group']/div");
    By bttnInfoCuotas = By.xpath("/html/body/div[1]/div/div[2]/div/div[3]/div/div[1]/div/div/div[2]/div[2]/button");
    // By txtVeridas = By.xpath("//p[contains(text(),' Por tu seguridad, vamos a validar tu identidad mediante biometria ')]");
    By txtVeridas = By.xpath("//p[contains(text(),'Por tu seguridad vamos a validar tu identidad con unas preguntas!')]");
    By txtEnviarDireccionActual = By.xpath("//span[contains(text(),'Enviar a mi dirección actual')]");
    By btnContinuarEnvio = By.xpath("//button[@class='continue-button continue-button-text']");
    By btnAceptoContrato = By.xpath("//img[contains(@alt,'Contract icon')]");
    By PopUpContrato = By.xpath("//button[@class='btn btn-none']");
    By PopUpCalificacion = By.xpath("//button[@class='btn btn-blank']");
    By btnFinalizar = By.xpath("//button[@class='continue-button continue-button-text']");
    By btnPermitir = By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[7]/div[2]/div[1]/div[1]/button[1]");
    By txtSolicitudExitosa = By.xpath("//p[@class='onboard-finish-title']");
    By txtRecibirInfoCorreo = By.xpath("//p[contains(text(),'Recibirás la información de tu solicitud de crédito en tu correo electrónico')]");
    By txtNumeroCuotas = By.xpath("//p[@class='onboard-finish-title']");
    By btnCerrar = By.xpath("//span[contains(text(),'Cerrar')]");
    By frmEncuesta = By.xpath("//*[@id=\"modal-finish___BV_modal_body_\"]/div/p[1]");
    By msjPregunta = By.xpath("//*[@id=\"modal-finish___BV_modal_body_\"]/div/p[3]");
    By btnEstrella = By.xpath("(//img[contains(@src,'/img/star-blue.b1efd805.svg')])[4]");
    By txtEscribirMensaje = By.xpath("//*[@id=\"textarea\"]");
    By btnEnviar = By.xpath("//span[contains(text(),'Enviar')]");
    By frmVentanaEmergente = By.xpath("//*[@id=\"modal-basic___BV_modal_body_\"]/div/div[2]/p");

    public By getBtnCerrar() {
        return btnCerrar;
    }

    public By getFrmEncuesta() {
        return frmEncuesta;
    }

    public By getMsjPregunta() {
        return msjPregunta;
    }

    public By getBtnEstrella() {
        return btnEstrella;
    }

    public By getTxtEscribirMensaje() {
        return txtEscribirMensaje;
    }

    public By getBtnEnviar() {
        return btnEnviar;
    }

    public By getFrmVentanaEmergente() {
        return frmVentanaEmergente;
    }

    public By getBttnInfoCuotas() {
        return bttnInfoCuotas;
    }

    public void setBttnInfoCuotas(By bttnInfoCuotas) {
        this.bttnInfoCuotas = bttnInfoCuotas;
    }

    public By getListCuotas() {
        return listCuotas;
    }

    public void setListCuotas(By listCuotas) {
        this.listCuotas = listCuotas;
    }

    public By getInputTelef() {
        return inputTelef;
    }

    public By getInputCedula() {
        return inputCedula;
    }

    public By getInputCorreo() {
        return inputCorreo;
    }

    public By getTxtTerminos() {return txtTerminos;}

    public By getPopUpTerminos() {
        return PopUpTerminos;
    }

    public By getRaidBoxPublicidad() {return raidBoxPublicidad;}

    public By getBtnContinuar() {return btnContinuarInicio;}

    public By getLnkCard() {
        return lnkCard;
    }

    public By getBtnFinanciar() {return btnFinanciar;}

    public By getMsjBienvenida() {
        return msjBienvenida;
    }

    public By getMsjBotonTerminal() {return msjBotonTerminal;}

    public By getMsjCardTerminal() {return msjCardTerminal;}

    public By getInputNumero1() {
        return inputNumero1;
    }

    public By getInputNumero2() {
        return inputNumero2;
    }

    public By getInputNumero3() {
        return inputNumero3;
    }

    public By getInputNumero4() {
        return inputNumero4;
    }

    public By getBtnContinuarOtp() {
        return btnContinuarOtp;
    }

    public By getMsjErrorOtp() {return msjErrorOtp;}

    public By getBtnCuota3() {
        return btnCuota3;
    }
    public By getBtnCuota6() {
        return btnCuota6;
    }
    public By getBtnCuota12() {
        return btnCuota12;
    }
    public By getBtnCuota18() {
        return btnCuota18;
    }
    public By getBtnCuota24() {
        return btnCuota24;
    }
    public By getBtnCuota36() {
        return btnCuota36;
    }

    public By getBtnConfirmarCuota() {
        return btnConfirmarCuota;
    }

    public By getTxtCelular() {
        return txtCelular;
    }

    public By getTxtVeridas() {
        return txtVeridas;
    }

    public By getTxtEnviarDireccionActual() {
        return txtEnviarDireccionActual;
    }

    public By getBtnContinuarEnvio() {
        return btnContinuarEnvio;
    }

    public By getbtnAceptoContrato() {
        return btnAceptoContrato;
    }

    public By getPopUpContrato() {
        return PopUpContrato;
    }

    public By getbtnFinalizar() {
        return btnFinalizar;
    }

    public By getbtnPermitir() {
        return btnPermitir;
    }

    public By getPopUpCalificacion() {
        return PopUpCalificacion;
    }

    public By getTxtSolicitudExitosa() {
        return txtSolicitudExitosa;
    }
}
