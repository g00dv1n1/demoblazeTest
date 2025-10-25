import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    static String adress = "https://www.demoblaze.com/";
    static String goToLoginMenu = "//*[@id=\"login2\"]";
    static String loginInput = "//*[@id=\"loginusername\"]";
    static String loginPassInput = "//*[@id=\"loginpassword\"]";
    static String loginButton = "//*[@id=\"logInModal\"]/div/div/div[3]/button[2]";
    static String logoutMenu = "//*[@id=\"logout2\"]";
    static String goToSingUpMenu = "//*[@id=\"signin2\"]";
    static String loginSingUpInput = "//*[@id=\"sign-username\"]";
    static String passSingUpInput = "//*[@id=\"sign-password\"]";
    static String singUpButton = "//*[@id=\"signInModal\"]/div/div/div[3]/button[2]";
// Коммент
    public static void openHomePage(){

        open(adress);

    }

    public static void goToLogin(){

        $(By.xpath(goToLoginMenu)).click();

    }

    public static void enterUserNameLogin(String name){

        $(By.xpath(loginInput)).sendKeys(name);

    }

    public static void enterPassLogin(String pass){

        $(By.xpath(loginPassInput)).sendKeys(pass);

    }

    public static void clickLogin(){

        $(By.xpath(loginButton)).click();

    }

    public static void clickLogOut(){

        $(By.xpath(logoutMenu)).click();

    }

    public static void goToSingUp(){

        $(By.xpath(goToSingUpMenu)).click();

    }

    public static void enterUserNameSingUp(String name){

        $(By.xpath(loginSingUpInput)).sendKeys(name);

    }

    public static void enterPassSingUp(String pass){

        $(By.xpath(passSingUpInput)).sendKeys(pass);

    }

    public static void clickSingUp(){

        $(By.xpath(singUpButton)).click();

    }

}
//New comment
