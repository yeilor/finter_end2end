package co.movistar.descubre.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EsperaExplicita {

    public void esperaElementoClickeable(WebDriver driver, By elemento) {
        WebDriverWait esperaExplicita = new WebDriverWait(driver, 30);
        esperaExplicita.until(ExpectedConditions.elementToBeClickable(elemento));
    }

    public void esperaElementoVisible(WebDriver driver, By elemento) {
        WebDriverWait esperaExplicita = new WebDriverWait(driver, 30);
        esperaExplicita.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elemento));
    }

}
