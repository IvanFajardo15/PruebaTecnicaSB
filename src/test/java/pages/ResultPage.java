package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class ResultPage extends BasePage {
    private final By nombreValue = By.xpath("//td[text()='Student Name']/following-sibling::td");
    private final By correoValue = By.xpath("//td[text()='Student Email']/following-sibling::td");
    private final By telefonoValue = By.xpath("//td[text()='Mobile']/following-sibling::td");
    private final By closeButton = By.id("closeLargeModal");

    @Override
    public void waitPageToLoad() {
        Logs.info("Esperando que cargue la pagina de resultados");
        waitPage(closeButton, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {
        Logs.info("Verificando elementos de la pagina");
        Assertions.assertTrue(find(closeButton).isDisplayed());
    }

    public void verifyData(String nombre, String apellido, String correo, String telefono) {
        Logs.info("Verificando que los datos se hayan registrado de manera correcta");
        Assertions.assertAll(
                () -> Assertions.assertEquals(find(nombreValue).getText(), nombre + " " + apellido),
                () -> Assertions.assertEquals(find(correoValue).getText(), correo),
                () -> Assertions.assertEquals(find(telefonoValue).getText(), telefono)
        );
    }
}
