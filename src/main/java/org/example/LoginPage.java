package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginPage {
    private static final String URL_LOGIN = "https://ambientetestejoao.cliniconect.com.br/login";
    private static final String URL_AGENDA = "https://ambientetestejoao.cliniconect.com.br/agenda";
    private static final String URL_PACIENTE = "https://ambientetestejoao.cliniconect.com.br/paciente";
    private WebDriver browser;

    public LoginPage (){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\checc\\Downloads\\chromedriver.exe");
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
        this.browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS).pageLoadTimeout(10, TimeUnit.SECONDS);
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
        this.browser.navigate().to(URL_PACIENTE);
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
}
