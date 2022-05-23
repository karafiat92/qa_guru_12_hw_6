package link.i;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import link.i.pages.VkusVillPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

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
    @ParameterizedTest (name = "Проверка открытия бокового меню {0} и поиска в нём {1}")
    void openSideMenuOptions (String first, String second){
        VkusVillPage vkusVill = new VkusVillPage();
        vkusVill.openSideMenu(first, second);
    }




}
