package link.i;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import link.i.pages.VkusVillPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DeliveryTests {
    @BeforeEach
    void openVkusVillPage() {
        Configuration.browserSize = "1500x1200";
        Configuration.baseUrl = "https://www.delivery-club.ru/";
        VkusVillPage vkusVill = new VkusVillPage();
        vkusVill.openPage();
    }

    @AfterAll
    static void close() {
        Selenide.closeWebDriver();
    }

    @CsvSource(value = {
            "Мороженое|Эскимо",
            "Кулинария|Блины",
            "Хиты|Сыры",
            "Молочное|Топпинги",
    },
            delimiter = '|'
    )
    @ParameterizedTest(name = "Проверка открытия бокового меню {0} и поиска в нём {1}")
    void openSideMenuOptions(String first, String second) {
        VkusVillPage vkusVill = new VkusVillPage();
        vkusVill.openSideMenu(first, second);
    }

    @ValueSource(strings = {
            "Всё для пикника",
            "Неделя чаепития"
    }
    )
    @ParameterizedTest(name = "В боковом меню присутствует {0}")
    void checkSideMenu(String option) {
        VkusVillPage vkusVill = new VkusVillPage();
        vkusVill.sideMenuOptions(option);
    }

    static Stream<Arguments> methodWithArguments() {
        return Stream.of(
                Arguments.of("Хиты", "Напитки"),
                Arguments.of("Кафе", "Десерты")
        );
    }
    @MethodSource("methodWithArguments")
    @ParameterizedTest
    void methodSourceExampleTest(String option, String choice) {
        VkusVillPage vkusVill = new VkusVillPage();
        $(".shop-new-sidebar-catalog__wrapper")
                .find(byText(option))
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .click();
        $(".shop-new-sidebar-catalog__element__siblings")
                .find(byText(choice))
                .should(Condition.exist)
                .shouldBe(Condition.visible);
    }
}
