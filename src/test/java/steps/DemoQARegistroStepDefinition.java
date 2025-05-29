package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegisterPage;
import pages.ResultPage;
import utilities.CommonFlows;

public class DemoQARegistroStepDefinition {
    private final CommonFlows commonFlows = new CommonFlows();
    private final RegisterPage registerPage = new RegisterPage();
    private final ResultPage resultPage = new ResultPage();

    @Given("ingreso a la pagina de registro de usuarios de DemoQA")
    public void signUpUser() {
        commonFlows.goToHomePage();
    }


    @When("Ingreso {string} {string} {string} y {string} en el formulario y doy click en el boton de enviar")
    public void fillForm(String nombre, String apellido, String correo, String telefono) {
        registerPage.fillData(nombre, apellido, correo, telefono);
        registerPage.clickSubmitButton();
    }

    @Then("Verifico que los datos {string} {string} {string} y {string} se registren de manera exitosa")
    public void verifyData(String nombre, String apellido, String correo, String telefono) {
        resultPage.verifyData(nombre, apellido, correo, telefono);
    }

    @When("Envio el formulario con campos vacios")
    public void submitEmptyFields() {
        registerPage.clickSubmitButton();
    }

    @Then("Verifico que los campos obligatorios se marquen en rojo")
    public void verifyFieldColor() throws InterruptedException {
        Thread.sleep(2000);
        registerPage.verifyColor();
    }

    @When("Ingreso {string} con un formato inválido")
    public void invalidFormatMail(String correo) {
        registerPage.fillMail(correo);
        registerPage.clickSubmitButton();
    }

    @Then("Verifico que el campo de correo se marque en rojo")
    public void verifyMailFieldColor() throws InterruptedException {
        Thread.sleep(2000);
        registerPage.verifyMailColor();
    }
}
