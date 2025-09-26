import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    String userLoginSingUp = "Us824534fgvkr";
    String userLogin = "Usehvjhvjvkr";
    String userPass = "dgqwrgqwrgqwrgq";
    String userLoginIncorrect = "erfwerfqw";
    String userLoginRepeated = "User666";

    @Test
    public void test(){
        HomePage.openHomePage();
        $(By.xpath("//*[@id=\"tbodyid\"]/div[3]/div/div/h4/a")).click();
        $(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
        switchTo().alert().accept();
        $(By.xpath("//*[@id=\"cartur\"]")).click();
        $(byText("Nexus 6")).shouldBe(visible);
        sleep(3000);
    }

    @Test
    public void testSingUp(){
        HomePage.openHomePage();
        HomePage.goToSingUp();
        HomePage.enterUserNameSingUp(userLoginSingUp);
        HomePage.enterPassSingUp(userPass);
        HomePage.clickSingUp();
        String alertText = switchTo().alert().getText();
        assertEquals("Sign up successful.", alertText);
        sleep(1000);
        switchTo().alert().accept();
        sleep(3000);
    }

    @Test
    public void testSingUpRepeated(){
        HomePage.openHomePage();
        HomePage.goToSingUp();
        HomePage.enterUserNameSingUp(userLoginRepeated);
        HomePage.enterPassSingUp(userPass);
        HomePage.clickSingUp();
        String alertText = switchTo().alert().getText();
        assertEquals("This user already exist.", alertText);
        sleep(1000);
        switchTo().alert().accept();
        sleep(3000);
    }

    @Test
    public void login(){
        HomePage.openHomePage();
        HomePage.goToLogin();
        HomePage.enterUserNameLogin(userLogin);
        HomePage.enterPassLogin(userPass);
        HomePage.clickLogin();
        $(byText("Welcome " + userLogin)).shouldBe(visible);
        sleep(3000);
        HomePage.clickLogOut();
    }

    @Test
    public void loginIncorrect(){
        HomePage.openHomePage();
        HomePage.goToLogin();
        HomePage.enterUserNameLogin(userLoginIncorrect);
        HomePage.enterPassLogin(userPass);
        HomePage.clickLogin();
        String alertText = switchTo().alert().getText();
        assertEquals("User does not exist.", alertText);
        sleep(3000);
        switchTo().alert().accept();
    }
}
