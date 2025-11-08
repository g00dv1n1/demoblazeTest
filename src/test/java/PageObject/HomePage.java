package PageObject;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class HomePage {

    static String adress = "https://www.demoblaze.com/";
    static SelenideElement goToLoginMenu = $(By.xpath("//*[@id=\"login2\"]"));
    static SelenideElement loginInput = $(By.xpath("//*[@id=\"loginusername\"]"));
    static SelenideElement loginPassInput = $(By.xpath("//*[@id=\"loginpassword\"]"));
    static SelenideElement loginButton = $(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));
    static SelenideElement logoutMenu = $(By.xpath("//*[@id=\"logout2\"]"));
    static SelenideElement goToSingUpMenu = $(By.xpath("//*[@id=\"signin2\"]"));
    static SelenideElement loginSingUpInput = $(By.xpath("//*[@id=\"sign-username\"]"));
    static SelenideElement passSingUpInput = $(By.xpath("//*[@id=\"sign-password\"]"));
    static SelenideElement singUpButton = $(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]"));
    public static ElementsCollection carouselItems = $$(".carousel-item");
    public static SelenideElement carouselControlNext = $("a.carousel-control-next");
    public static SelenideElement carouselControlPrev = $("a.carousel-control-prev");





    public static void openHomePage(){

        Selenide.open(adress);

    }

    public static void goToLogin(){

        goToLoginMenu.click();

    }

    public static void enterUserNameLogin(String name){

        loginInput.sendKeys(name);

    }

    public static void enterPassLogin(String pass){

        loginPassInput.sendKeys(pass);

    }

    public static void clickLogin(){

        loginButton.click();

    }

    public static void clickLogOut(){

        logoutMenu.click();

    }

    public static void goToSingUp(){

        goToSingUpMenu.click();

    }

    public static void enterUserNameSingUp(String name){

        loginSingUpInput.sendKeys(name);

    }

    public static void enterPassSingUp(String pass){

        passSingUpInput.sendKeys(pass);

    }

    public static void clickSingUp(){

        singUpButton.click();

    }

    public static int getActiveSliderIndex() {
        carouselItems.shouldBe(CollectionCondition.sizeGreaterThan(0));
        for (int i = 0; i < carouselItems.size(); i++) {
            if (carouselItems.get(i).has(Condition.cssClass("active"))) return i;
        }
        return -1;
    }

    public static void clickSliderNextAndAssertChanged() {
        int before = getActiveSliderIndex();
        carouselControlNext.shouldBe(Condition.visible, Condition.enabled);
        carouselControlNext.click();
        carouselItems.get(before).shouldNotHave(Condition.cssClass("active"));
        int after = getActiveSliderIndex();
        Assertions.assertNotEquals(before, after, "Слайдер не переключился на следующий слайд");
    }

    public static void clickSlidePrevAndAssertChanged() {
        int before = getActiveSliderIndex();
        carouselControlPrev.shouldBe(Condition.visible, Condition.enabled);
        carouselControlPrev.click();
        carouselItems.get(before).shouldNotHave(Condition.cssClass("active"));
        int after = getActiveSliderIndex();
        Assertions.assertNotEquals(before, after, "Слайдер не переключился на предыдущий слайд");
    }
}
