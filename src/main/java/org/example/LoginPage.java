package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginPage {
    private static final String URL_LOGIN = "https://ambientetestejoao.cliniconect.com.br/login";
    private static final String URL_AGENDA = "https://ambientetestejoao.cliniconect.com.br/agenda";
    private static final String URL_PACIENTE = "https://ambientetestejoao.cliniconect.com.br/paciente";
    public WebDriver browser;

    public LoginPage (){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Usuario\\Desktop\\Arquivos de Testes\\Chrome Driver\\chromedriver.exe");
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
    }
    public void closeBrowser(){
        this.browser.quit();
    }
    public void fulfillLogin(String username, String password){
        this.browser.findElement(By.xpath("//*[@id=\"login\"]")).sendKeys(username);
        this.browser.findElement(By.xpath("//*[@id=\"senha\"]")).sendKeys(password);
    }
    public void assignEULACheckbox(){
        this.browser.findElement(By.xpath("//*[@id=\"checkTermosLogin\"]")).click();
    }

    public void submitForm(){
        this.browser.findElement(By.xpath("//*[@id=\"page\"]/app-root/div/app-admin-layout/div/div/login/div/div/div/div/form/div[3]/div/button")).click();
    }
    public void enterRestrictedURL(){
        this.browser.navigate().to(URL_AGENDA);
    }
    public boolean isLoginURL(){
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }
    public boolean isPrivateURL(){
        return browser.getCurrentUrl().equals(URL_AGENDA);
    }

    public boolean verifiyEulaAssignmentWarningBoard(){
       return browser.getPageSource().contains("Você precisa aceitar os termos acima para continuar.");
    }

    public void esperarCarregamentoAgenda (){ //mover para o padrão PageObject
        browser.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(browser,10);
        wait.until(ExpectedConditions.urlToBe(URL_AGENDA));
    }
    public boolean verificaAberturaDeBoxDeAgendamento(){
        return browser.getPageSource().contains("Recorrência");
    }
}
