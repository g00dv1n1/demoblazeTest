package PageObject;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;


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
    public static String carousel = ".carousel-item";
    public static String carouselControlNext = "a.carousel-control-next";
    public static String carouselControlPrev = "a.carousel-control-prev";





    public static void openHomePage(){

        Selenide.open(adress);

    }

    public static void goToLogin(){

        Selenide.$(By.xpath(goToLoginMenu)).click();

    }

    public static void enterUserNameLogin(String name){

        Selenide.$(By.xpath(loginInput)).sendKeys(name);

    }

    public static void enterPassLogin(String pass){

        Selenide.$(By.xpath(loginPassInput)).sendKeys(pass);

    }

    public static void clickLogin(){

        Selenide.$(By.xpath(loginButton)).click();

    }

    public static void clickLogOut(){

        Selenide.$(By.xpath(logoutMenu)).click();

    }

    public static void goToSingUp(){

        Selenide.$(By.xpath(goToSingUpMenu)).click();

    }

    public static void enterUserNameSingUp(String name){

        Selenide.$(By.xpath(loginSingUpInput)).sendKeys(name);

    }

    public static void enterPassSingUp(String pass){

        Selenide.$(By.xpath(passSingUpInput)).sendKeys(pass);

    }

    public static void clickSingUp(){

        Selenide.$(By.xpath(singUpButton)).click();

    }

    public static int getActiveSliderIndex() {
        var items = Selenide.$$(carousel).shouldBe(CollectionCondition.sizeGreaterThan(0));
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).has(Condition.cssClass("active"))) return i;
        }
        return -1;
    }

    public static void clickSliderNextAndAssertChanged() {
        int before = getActiveSliderIndex();
        SelenideElement next = Selenide.$(carouselControlNext).shouldBe(Condition.visible, Condition.enabled);
        next.click();
        Selenide.$$(carousel).get(before).shouldNotHave(Condition.cssClass("active"));
        int after = getActiveSliderIndex();
        Assertions.assertNotEquals(before, after, "Слайдер не переключился на следующий слайд");
    }

    public static void clickSlidePrevAndAssertChanged() {
        int before = getActiveSliderIndex();
        SelenideElement prev = Selenide.$(carouselControlPrev).shouldBe(Condition.visible, Condition.enabled);
        prev.click();
        Selenide.$$(carousel).get(before).shouldNotHave(Condition.cssClass("active"));
        int after = getActiveSliderIndex();
        Assertions.assertNotEquals(before, after, "Слайдер не переключился на предыдущий слайд");
    }
}
