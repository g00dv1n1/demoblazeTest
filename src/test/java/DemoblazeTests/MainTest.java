package DemoblazeTests;

import PageObject.HomePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static PageObject.HomePage.*;
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
    SelenideElement nexus6 = $(By.xpath("//*[@id=\"tbodyid\"]/div[3]/div/div/h4/a"));
    SelenideElement buyButton = $(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a"));
    SelenideElement cartButton = $(By.xpath("//*[@id=\"cartur\"]"));

    @Test
    public void test(){
        HomePage.openHomePage();
        $(nexus6).shouldBe(visible).click();
        $(buyButton).shouldBe(visible).click();
        switchTo().alert().accept();
        cartButton.click();
        $(byText("Nexus 6")).shouldBe(visible);
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
        switchTo().alert().accept();
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
        switchTo().alert().accept();
    }

    @Test
    public void login(){
        HomePage.openHomePage();
        HomePage.goToLogin();
        HomePage.enterUserNameLogin(userLogin);
        HomePage.enterPassLogin(userPass);
        HomePage.clickLogin();
        $(byText("Welcome " + userLogin)).shouldBe(visible);
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
        switchTo().alert().accept();
    }



    @Test
    void sliderFlowTest() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась (ожидался заголовок STORE)");
        SelenideElement carousel = $("#carouselExampleIndicators")
                .shouldBe(visible);
        carouselItems.shouldBe(CollectionCondition.sizeGreaterThan(1));
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
        carouselItems.shouldBe(CollectionCondition.sizeGreaterThan(1));
        int before1 = HomePage.getActiveSliderIndex();
        Selenide.Wait().withTimeout(Duration.ofSeconds(10))
                .until(driver -> HomePage.getActiveSliderIndex() != before1);
        int after1 = HomePage.getActiveSliderIndex();
        assertNotEquals(before1, after1, "Слайдер не переключился через 5 секунд (1-й переход)");
        Selenide.Wait().withTimeout(Duration.ofSeconds(10))
                .until(driver -> HomePage.getActiveSliderIndex() != after1);
        int after2 = HomePage.getActiveSliderIndex();
        assertNotEquals(after1, after2, "Слайдер не переключился через 5 секунд (2-й переход)");
        Selenide.Wait().withTimeout(Duration.ofSeconds(10))
                .until(driver -> HomePage.getActiveSliderIndex() != after2);
        int after3 = HomePage.getActiveSliderIndex();
        assertNotEquals(after2, after3, "Слайдер не переключился через 5 секунд (3-й переход)");
    }


    @Test
    void testPhonesCategory() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        int beforeCount = $$(".card-title a").shouldBe(CollectionCondition.sizeGreaterThan(0)).size();
        SelenideElement phonesLink = $$("a.list-group-item")
                .findBy(exactText("Phones"))
                .shouldBe(visible, enabled);
        phonesLink.scrollIntoView(true).click();
        Wait().withTimeout(Duration.ofSeconds(10))
                .until(driver -> $$(".card-title a").size() != beforeCount);
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
        int beforeCount = $$("#tbodyid .card").filter(visible)
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .size();
        SelenideElement laptopsLink = $$("a.list-group-item")
                .findBy(exactText("Laptops"))
                .shouldBe(visible, enabled);
        laptopsLink.scrollIntoView(true).click();
        Selenide.Wait().withTimeout(Duration.ofSeconds(10)).until(driver -> {
            var names = $$("#tbodyid .card").filter(visible).stream()
                    .map(card -> card.$(".card-title a").getText())
                    .toList();
            boolean countChanged = names.size() != beforeCount;
            boolean atLeastOneLaptop = names.stream().anyMatch(n ->
                    n.toLowerCase().matches(".*(vaio|macbook|dell).*")
            );
            boolean nonePhones = names.stream().noneMatch(n ->
                    n.toLowerCase().matches(".*(galaxy|iphone|lumia|nexus|xperia|htc).*")
            );
            return countChanged || (atLeastOneLaptop && nonePhones);
        });
        var cards = $$("#tbodyid .card").filter(visible)
                .shouldBe(CollectionCondition.sizeGreaterThan(0));
        List<String> productNames = cards.stream()
                .map(c -> c.$(".card-title a").getText())
                .toList();
        boolean allLaptops = productNames.stream().allMatch(name ->
                name.toLowerCase().matches(".*(vaio|macbook|dell).*")
        );
        boolean nonePhones = productNames.stream().noneMatch(name ->
                name.toLowerCase().matches(".*(galaxy|iphone|lumia|nexus|xperia|htc).*")
        );
        assertTrue(allLaptops && nonePhones,
                "На странице отображаются не только ноутбуки. Найдено: " + productNames);
    }

    @Test
    void testMonitorsCategory() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        int beforeCount = $$("#tbodyid .card").filter(visible)
                .shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10))
                .size();
        $$("a.list-group-item").findBy(exactText("Monitors"))
                .shouldBe(visible, enabled).scrollIntoView(true).click();
        Selenide.Wait().withTimeout(Duration.ofSeconds(10)).until(driver -> {
            List<String> names = $$("#tbodyid .card .card-title a")
                    .filter(visible)
                    .texts(); // <- свежие значения, меньше шансов на stale
            boolean countChanged = names.size() != beforeCount;
            boolean monitorsOnly = !names.isEmpty()
                    && names.stream().allMatch(n ->
                    n.toLowerCase().matches(".*(apple monitor|asus full hd|monitor).*"));
            boolean noPhonesOrLaptops = names.stream().noneMatch(n ->
                    n.toLowerCase().matches(".*(galaxy|iphone|lumia|nexus|xperia|htc|vaio|macbook|dell).*"));
            return countChanged || (monitorsOnly && noPhonesOrLaptops);
        });
        List<String> productNames = $$("#tbodyid .card .card-title a")
                .filter(visible)
                .shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10))
                .texts();
        boolean allMonitors = productNames.stream().allMatch(n ->
                n.toLowerCase().matches(".*(apple monitor|asus full hd|monitor).*"));
        boolean nonePhonesOrLaptops = productNames.stream().noneMatch(n ->
                n.toLowerCase().matches(".*(galaxy|iphone|lumia|nexus|xperia|htc|vaio|macbook|dell).*"));
        assertTrue(allMonitors && nonePhonesOrLaptops,
                "На странице отображаются не только мониторы. Найдено: " + productNames);
    }


    @Test
    void addLumiaToCart() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        $$("#tbodyid .card .card-title a")
                .findBy(exactText("Nokia lumia 1520"))
                .shouldBe(visible, enabled)
                .scrollIntoView(true)
                .click();
        $("h2.name").shouldHave(exactText("Nokia lumia 1520"));
        $$("a.btn.btn-success.btn-lg")
                .findBy(exactText("Add to cart"))
                .shouldBe(visible, enabled)
                .click();
        boolean alertAccepted = Selenide.Wait().withTimeout(Duration.ofSeconds(5)).until(driver -> {
            try {
                switchTo().alert().accept();
                return true;
            } catch (org.openqa.selenium.NoAlertPresentException e) {
                return false;
            }
        });
        $("#cartur").shouldBe(visible, enabled).click();
        open("https://www.demoblaze.com/cart.html#");
        Selenide.Wait().withTimeout(Duration.ofSeconds(10)).until(driver ->
                $$("#tbodyid tr td:nth-child(2)").texts().contains("Nokia lumia 1520")
        );
        List<String> titles = $$("#tbodyid tr td:nth-child(2)")
                .filter(visible)
                .texts();
        assertTrue(titles.contains("Nokia lumia 1520"),
                "В корзине нет строки с 'Nokia lumia 1520'. Найдено: " + titles);
    }

    @Test
    void deleteLumiaFromCart() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        $$("#tbodyid .card .card-title a")
                .findBy(exactText("Nokia lumia 1520"))
                .shouldBe(visible, enabled)
                .scrollIntoView(true)
                .click();
        $("h2.name").shouldHave(exactText("Nokia lumia 1520"));
        $$("a.btn.btn-success.btn-lg")
                .findBy(exactText("Add to cart"))
                .shouldBe(visible, enabled)
                .click();
        Selenide.Wait().withTimeout(Duration.ofSeconds(5)).until(driver -> {
            try {
                switchTo().alert().accept();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        });
        $("#cartur").shouldBe(visible, enabled).click();
        open("https://www.demoblaze.com/cart.html#");
        Selenide.Wait().withTimeout(Duration.ofSeconds(10)).until(wd ->
                $$("#tbodyid tr td:nth-child(2)").texts().contains("Nokia lumia 1520")
        );
        List<String> titlesBefore = $$("#tbodyid tr td:nth-child(2)")
                .filter(visible)
                .texts();
        assertTrue(titlesBefore.contains("Nokia lumia 1520"),
                "В корзине нет 'Nokia lumia 1520' после добавления. Найдено: " + titlesBefore);
        $$("#tbodyid tr")
                .findBy(text("Nokia lumia 1520"))
                .shouldBe(visible)
                .$("td:last-child a").shouldBe(visible, enabled).click();
        boolean removed = Selenide.Wait().withTimeout(Duration.ofSeconds(10)).until(webDriver ->
                $$("#tbodyid tr").filter(visible).texts().stream()
                        .noneMatch(s -> s.toLowerCase().contains("nokia lumia 1520"))
        );
        assertTrue(removed, "Строка 'Nokia lumia 1520' не исчезла из корзины");
        List<String> titlesAfter = $$("#tbodyid tr td:nth-child(2)")
                .filter(visible)
                .texts();
        assertFalse(titlesAfter.contains("Nokia lumia 1520"),
                "После удаления товар всё ещё виден в таблице. Осталось: " + titlesAfter);
    }

    @Test
    void placeOrder() {
        HomePage.openHomePage();
        assertTrue(title().contains("STORE"), "Главная страница не открылась");
        $$("#tbodyid .card .card-title a")
                .findBy(exactText("Nokia lumia 1520"))
                .shouldBe(visible, enabled)
                .scrollIntoView(true)
                .click();
        $("h2.name").shouldHave(exactText("Nokia lumia 1520"));
        $$("a.btn.btn-success.btn-lg")
                .findBy(exactText("Add to cart"))
                .shouldBe(visible, enabled)
                .click();
        Selenide.Wait().withTimeout(Duration.ofSeconds(5)).until(driver -> {
            try {
                switchTo().alert().accept();
                return true;
            } catch (NoAlertPresentException ignored) {
                return false;
            }
        });
        $("#cartur").shouldBe(visible, enabled).click();
        open("https://www.demoblaze.com/cart.html#");
        Selenide.Wait().withTimeout(Duration.ofSeconds(10)).until(driver ->
                $$("#tbodyid tr td:nth-child(2)").texts().contains("Nokia lumia 1520")
        );
        List<String> titles = $$("#tbodyid tr td:nth-child(2)").texts();
        assertTrue(titles.contains("Nokia lumia 1520"),
                "В корзине нет 'Nokia lumia 1520'. Найдено: " + titles);
        $$("button.btn.btn-success")
                .findBy(text("Place Order"))
                .shouldBe(visible, enabled)
                .click();
        $("#orderModal").shouldBe(visible);
        $("#name").shouldBe(visible, enabled).setValue("Ivan Test");
        $("#country").shouldBe(visible, enabled).setValue("Russia");
        $("#city").shouldBe(visible, enabled).setValue("Moscow");
        $("#card").shouldBe(visible, enabled).setValue("4111111111111111");
        $("#month").shouldBe(visible, enabled).setValue("12");
        $("#year").shouldBe(visible, enabled).setValue("2030");
        $$("div.modal-footer .btn.btn-primary")
                .findBy(exactText("Purchase"))
                .shouldBe(visible, enabled)
                .click();
        boolean successShown = Selenide.Wait().withTimeout(Duration.ofSeconds(10)).until(driver -> {
            ElementsCollection popups = $$(".sweet-alert, .swal2-container, .swal-modal")
                    .filter(visible);
            if (!popups.isEmpty()) {
                String txt = popups.get(0).getText().toLowerCase();
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
        List<String> titlesBefore = $$("#tbodyid .card-title a")
                .shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10))
                .filter(visible)
                .texts();
        assertFalse(titlesBefore.isEmpty(), "На первой странице нет товаров для сравнения.");
        nextBtn.scrollIntoView(true).click();
        boolean changed = Selenide.Wait().withTimeout(Duration.ofSeconds(10)).until(d -> {
            List<String> after = $$("#tbodyid .card-title a")
                    .filter(visible)
                    .texts();
            return !after.isEmpty() && !after.equals(titlesBefore);
        });
        if (!changed) {
            executeJavaScript("arguments[0].click();", nextBtn);
            changed = Selenide.Wait().withTimeout(Duration.ofSeconds(10)).until(d -> {
                List<String> after = $$("#tbodyid .card-title a")
                        .filter(visible)
                        .texts();
                return !after.isEmpty() && !after.equals(titlesBefore);
            });
        }
        List<String> titlesAfter = $$("#tbodyid .card-title a")
                .filter(visible)
                .texts();
        assertTrue(changed && !titlesAfter.equals(titlesBefore),
                "После нажатия 'Next' каталог не изменился. " +
                        "До: " + titlesBefore + " | После: " + titlesAfter);
    }
}
