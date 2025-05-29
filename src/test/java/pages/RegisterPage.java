package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import utilities.BasePage;
import utilities.Logs;

public class RegisterPage extends BasePage {

    private final By titulo = By.xpath("//h5[text()='Student Registration Form']");
    private final By nombreInput = By.id("firstName");
    private final By apellidoInput = By.id("lastName");
    private final By correoInput = By.id("userEmail");
    private final By telefonoInput = By.id("userNumber");
    private final By maleInput = By.id("gender-radio-1");
    private final By submitButton = By.id("submit");

    @Override
    public void waitPageToLoad() {
        Logs.debug("Esperando que cargue la pagina de registro");
        waitPage(titulo, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {
        Logs.info("Verificando los elementos de la página");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(nombreInput).isDisplayed()),
                () -> Assertions.assertTrue(find(apellidoInput).isDisplayed()),
                () -> Assertions.assertTrue(find(correoInput).isDisplayed()),
                () -> Assertions.assertTrue(find(telefonoInput).isDisplayed()),
                () -> Assertions.assertTrue(find(maleInput).isDisplayed()),
                () -> Assertions.assertTrue(find(submitButton).isDisplayed())
        );
    }

    public void clickSubmitButton() {
        Logs.info("Dando click en el boton de submit");
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", find(submitButton));
    }

    public void fillData(String nombre, String apellido, String correo, String telefono) {
        Logs.info("Rellenando el nombre");
        find(nombreInput).sendKeys(nombre);

        Logs.info("Rellenando el apellido");
        find(apellidoInput).sendKeys(apellido);

        Logs.info("Rellenando el correo");
        find(correoInput).sendKeys(correo);

        Logs.info("Rellenando el telefono");
        find(telefonoInput).sendKeys(telefono);

        Logs.info("Rellenando el genero");
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", find(maleInput));
    }

    public void fillMail(String correo) {
        find(correoInput).sendKeys(correo);
    }

    public void verifyColor() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(find(nombreInput).getCssValue("border-color"), "rgb(220, 53, 69)"),
                () -> Assertions.assertEquals(find(apellidoInput).getCssValue("border-color"), "rgb(220, 53, 69)"),
                () -> Assertions.assertEquals(find(telefonoInput).getCssValue("border-color"), "rgb(220, 53, 69)")
        );
    }

    public void verifyMailColor() {
        Assertions.assertEquals(find(correoInput).getCssValue("border-color"), "rgb(220, 53, 69)");
    }
}
