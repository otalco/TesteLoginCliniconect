package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TesteCasosLogin {

    private LoginPage paginaDeLogin;

    private static final String URL_LOGIN = "https://ambientetestejoao.cliniconect.com.br/login";
    public static final String URL_AGENDA = "https://ambientetestejoao.cliniconect.com.br/agenda";
    public ChromeDriver browser;


    @BeforeEach
    public void beforeEach(){
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach(){
        this.paginaDeLogin.closeBrowser();
    }
    @Test //Validado
    public void deveriaLogarComCredenciaisCorretasECheckBox() {
        paginaDeLogin.fulfillLogin("joaoguilherme.ferreira14@gmail.com", "26e7b781");
        paginaDeLogin.assignEULACheckbox();
        paginaDeLogin.submitForm();
        paginaDeLogin.esperarCarregamentoAgenda();
        Assertions.assertTrue(paginaDeLogin.isPrivateURL());
    }
    @Test //Validado.
    public void naoDeveriaLogarComDadosInvalidos(){
        paginaDeLogin.fulfillLogin("joaoguilherme.ferreira14@gmail.com", "26e7b781");
        paginaDeLogin.assignEULACheckbox();
        paginaDeLogin.submitForm();
        Assertions.assertTrue(paginaDeLogin.isLoginURL());
    }
    @Test //Validado.
    public void naoDeveriaLogarSemCheckBoxEDeveMostrarMensagemDeErro(){
        paginaDeLogin.fulfillLogin("joaoguilherme.ferreira14@gmail.com", "26e7b781");
        paginaDeLogin.submitForm();
        Assertions.assertTrue(paginaDeLogin.isLoginURL());
        Assertions.assertTrue(paginaDeLogin.verifiyEulaAssignmentWarningBoard());
    }
    @Test
    public void naoDeveriaAcessarPaginaRestritaSemAutenticacao(){
        paginaDeLogin.enterRestrictedURL();
        Assertions.assertTrue(paginaDeLogin.isLoginURL());
    }
}

