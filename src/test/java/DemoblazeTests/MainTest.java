package DemoblazeTests;

import PageObject.HomePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    String uniq = String.valueOf(System.currentTimeMillis());
    String userLoginSingUp = "us" + uniq;
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



    @Test
    void sliderFlowTest() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась (ожидался заголовок STORE)");
        SelenideElement carousel = $("#carouselExampleIndicators")
                .shouldBe(visible);
        $$(HomePage.carousel).shouldBe(CollectionCondition.sizeGreaterThan(1));
        $(HomePage.carouselControlNext).shouldBe(visible, enabled);
        HomePage.clickSliderNextAndAssertChanged();
        $(HomePage.carouselControlNext).shouldBe(visible, enabled);
        HomePage.clickSliderNextAndAssertChanged();
        $(HomePage.carouselControlPrev).shouldBe(visible, enabled);
        HomePage.clickSlidePrevAndAssertChanged();
        $(HomePage.carouselControlPrev).shouldBe(visible, enabled);
        HomePage.clickSlidePrevAndAssertChanged();
    }

    @Test
    void testSliderAutoRotation() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        SelenideElement carousel = $("#carouselExampleIndicators").shouldBe(visible);
        $$(HomePage.carousel).shouldBe(CollectionCondition.sizeGreaterThan(1));
        int before1 = HomePage.getActiveSliderIndex();
        sleep(6000);
        int after1 = HomePage.getActiveSliderIndex();
        assertNotEquals(before1, after1, "Слайдер не переключился через 5 секунд (1-й переход)");
        sleep(6000);
        int after2 = HomePage.getActiveSliderIndex();
        assertNotEquals(after1, after2, "Слайдер не переключился через 5 секунд (2-й переход)");
        sleep(6000);
        int after3 = HomePage.getActiveSliderIndex();
        assertNotEquals(after2, after3, "Слайдер не переключился через 5 секунд (3-й переход)");
    }


    @Test
    void testPhonesCategory() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        SelenideElement phonesLink = $$("a.list-group-item")
                .findBy(exactText("Phones"))
                .shouldBe(visible, enabled);
        phonesLink.scrollIntoView(true).click();
        sleep(3000);
        var cards = $$(".card-title a").shouldBe(CollectionCondition.sizeGreaterThan(0));
        List<String> productNames = cards.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
        boolean allPhones = productNames.stream().allMatch(name ->
                name.toLowerCase().contains("galaxy") ||
                        name.toLowerCase().contains("iphone") ||
                        name.toLowerCase().contains("lumia") ||
                        name.toLowerCase().contains("nexus") ||
                        name.toLowerCase().contains("xperia") ||
                        name.toLowerCase().contains("htc")
        );
        assertTrue(allPhones, "На странице отображаются не только телефоны. Найдено: " + productNames);
    }

    @Test
    void testLaptopsCategory() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        SelenideElement laptopsLink = $$("a.list-group-item")
                .findBy(exactText("Laptops"))
                .shouldBe(visible, enabled);
        laptopsLink.scrollIntoView(true).click();
        sleep(3000);
        var cards = $$(".card-title a").shouldBe(CollectionCondition.sizeGreaterThan(0));
        List<String> productNames = cards.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
        boolean allLaptops = productNames.stream().allMatch(name ->
                name.toLowerCase().contains("vaio") ||
                        name.toLowerCase().contains("macbook") ||
                        name.toLowerCase().contains("dell")
        );
        assertTrue(allLaptops, "На странице отображаются не только телефоны. Найдено: " + productNames);
    }

    @Test
    void testMonitorsCategory() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        SelenideElement laptopsLink = $$("a.list-group-item")
                .findBy(exactText("Monitors"))
                .shouldBe(visible, enabled);
        laptopsLink.scrollIntoView(true).click();
        sleep(3000);
        var cards = $$(".card-title a").shouldBe(CollectionCondition.sizeGreaterThan(0));
        List<String> productNames = cards.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
        boolean allMonitors = productNames.stream().allMatch(name ->
                name.toLowerCase().contains("apple monitor") ||
                        name.toLowerCase().contains("asus full hd")
        );
        assertTrue(allMonitors, "На странице отображаются не только телефоны. Найдено: " + productNames);
    }


    @Test
    void addLumiaToCart() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        SelenideElement lumiaTitleOnMain = $$("#tbodyid .card .card-title a")
                .findBy(text("Nokia lumia 1520"))
                .shouldBe(visible, enabled);
        lumiaTitleOnMain.scrollIntoView(true).click();
        $("h2.name").shouldHave(exactText("Nokia lumia 1520"));
        SelenideElement addToCartBtn = $$("a.btn.btn-success.btn-lg")
                .findBy(exactText("Add to cart"))
                .shouldBe(visible, enabled);
        addToCartBtn.click();
        try {
            Selenide.switchTo().alert().accept();
        } catch (org.openqa.selenium.NoAlertPresentException ignored) {
            Selenide.sleep(500);
            try { Selenide.switchTo().alert().accept(); } catch (Exception e){}
        }
        $("#cartur").shouldBe(visible, enabled);
        open("https://www.demoblaze.com/cart.html#");
        sleep(3000);
        List<String> titles = $$("#tbodyid tr td:nth-child(2)").filter(visible).texts();
        assertTrue(titles.contains("Nokia lumia 1520"),
                "В корзине нет строки с 'Nokia lumia 1520'. Найдено: " + titles);
    }

    @Test
    void deleteLumiaFromCart() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        SelenideElement lumiaLink = $$("#tbodyid .card .card-title a")
                .findBy(exactText("Nokia lumia 1520"))
                .shouldBe(visible, enabled);
        lumiaLink.scrollIntoView(true).click();
        $("h2.name").shouldHave(exactText("Nokia lumia 1520")); // карточка открыта
        SelenideElement addToCartBtn = $$("a.btn.btn-success.btn-lg")
                .findBy(exactText("Add to cart"))
                .shouldBe(visible, enabled);
        addToCartBtn.click();
        try {
            switchTo().alert().accept();
        } catch (NoAlertPresentException ignored) {
            sleep(400);
            try { switchTo().alert().accept(); } catch (NoAlertPresentException ignored2) { /* ок, идем дальше */ }
        }
        $("#cartur").shouldBe(visible, enabled).click();
        sleep(3000);
        List<String> titlesBefore = $$("#tbodyid tr td:nth-child(2)").filter(visible).texts();
        assertTrue(titlesBefore.contains("Nokia lumia 1520"),
                "В корзине нет 'Nokia lumia 1520' после добавления. Найдено: " + titlesBefore);
        SelenideElement row = $$("#tbodyid tr")
                .findBy(text("Nokia lumia 1520"))
                .shouldBe(visible);
        row.$("td:last-child a").shouldBe(visible, enabled).click();
        boolean removed = Wait().withTimeout(Duration.ofSeconds(5)).until(webDriver ->
                $$("#tbodyid tr").filter(visible).texts().stream()
                        .noneMatch(s -> s.toLowerCase().contains("nokia lumia 1520"))
        );
        assertTrue(removed, "Строка 'Nokia lumia 1520' не исчезла из корзины");
        List<String> titlesAfter = $$("#tbodyid tr td:nth-child(2)").filter(visible).texts();
        assertFalse(titlesAfter.contains("Nokia lumia 1520"),
                "После удаления товар всё ещё виден в таблице. Осталось: " + titlesAfter);
    }

    @Test
    void placeOrder() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        SelenideElement lumiaLink = $$("#tbodyid .card .card-title a")
                .findBy(exactText("Nokia lumia 1520"))
                .shouldBe(visible, enabled);
        lumiaLink.scrollIntoView(true).click();
        $("h2.name").shouldHave(exactText("Nokia lumia 1520"));
        $$("a.btn.btn-success.btn-lg").findBy(exactText("Add to cart"))
                .shouldBe(visible, enabled)
                .click();
        try {
            switchTo().alert().accept();
        } catch (NoAlertPresentException ignored) {
            sleep(400);
            try { switchTo().alert().accept(); } catch (NoAlertPresentException ignored2) { }
        }
        $("#cartur").shouldBe(visible, enabled).click();
        sleep(3000);
        List<String> titles = $$("#tbodyid tr td:nth-child(2)").filter(visible).texts();
        assertTrue(titles.contains("Nokia lumia 1520"),
                "В корзине нет 'Nokia lumia 1520'. Найдено: " + titles);
        $$("button.btn.btn-success").findBy(text("Place Order"))
                .shouldBe(visible, enabled).click();
        $("#orderModal").shouldBe(visible);
        $("#name").shouldBe(enabled).setValue("Ivan Test");
        $("#country").shouldBe(enabled).setValue("Russia");
        $("#city").shouldBe(enabled).setValue("Moscow");
        $("#card").shouldBe(enabled).setValue("4111111111111111");
        $("#month").shouldBe(enabled).setValue("12");
        $("#year").shouldBe(enabled).setValue("2030");
        sleep(1000);
        $$("div.modal-footer .btn.btn-primary").findBy(exactText("Purchase"))
                .shouldBe(visible, enabled).click();
        boolean successShown = Selenide.Wait().withTimeout(Duration.ofSeconds(5)).until(wd -> {
            SelenideElement popup = $$(".sweet-alert, .swal2-container, .swal-modal")
                    .findBy(visible);
            if (popup.exists()) {
                String txt = popup.getText().toLowerCase();
                return txt.contains("thank") || txt.contains("success") || txt.contains("purchase");
            }
            try {
                String at = switchTo().alert().getText().toLowerCase();
                return at.contains("thank") || at.contains("purchase") || at.contains("success");
            } catch (NoAlertPresentException ignored) {
                return false;
            }
        });
        assertTrue(successShown, "Не появилось подтверждение об успешной покупке.");
    }

    @Test
    void contactFormSendsMessage() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        SelenideElement contactLink = $$("a.nav-link")
                .findBy(exactText("Contact"))
                .shouldBe(visible, enabled);
        contactLink.click();
        SelenideElement modal = $("#exampleModal").shouldBe(visible);
        $("#recipient-email").shouldBe(visible, enabled).setValue("qa@example.com");
        $("#recipient-name").shouldBe(visible, enabled).setValue("QA Tester");
        $("#message-text").shouldBe(visible, enabled).setValue("Hello! This is a feedback message.");
        $$(".modal-footer .btn.btn-primary").findBy(exactText("Send message"))
                .shouldBe(visible, enabled)
                .click();
        boolean confirmed = false;
        try {
            switchTo().alert().accept();
            confirmed = true;
        } catch (NoAlertPresentException e) {
            sleep(400);
            try {
                switchTo().alert().accept();
                confirmed = true;
            } catch (NoAlertPresentException ignored) { /* алерта нет — упадём по assert */ }
        }
        assertTrue(confirmed, "Не появилось всплывающее сообщение о подтверждении отправки.");
    }


    @Test
    void checkNextPageInCatalog() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        SelenideElement nextBtn = $("#next2").shouldBe(visible, enabled);
        sleep(2000);
        List<String> titlesBefore = $$("#tbodyid .card-title a")
                .filter(visible)
                .texts();
        assertFalse(titlesBefore.isEmpty(), "На первой странице нет товаров для сравнения.");
        nextBtn.scrollIntoView(true).click();
        sleep(2000);
        List<String> titlesAfter = $$("#tbodyid .card-title a")
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .texts();
        boolean pageChanged = !titlesAfter.equals(titlesBefore);
        assertTrue(pageChanged,
                "После нажатия 'Next' каталог не изменился. " +
                        "До: " + titlesBefore + ", После: " + titlesAfter);
    }
}
