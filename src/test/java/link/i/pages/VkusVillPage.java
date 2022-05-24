package link.i.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class VkusVillPage {
    // locators
    SelenideElement sideMenuOption = $(".shop-new-sidebar-catalog__wrapper"),
            sideMenuOptionSibling = $(".shop-new-sidebar-catalog__element__siblings"),
            contentTitle = $(".shop-page__content-title");

    public VkusVillPage openPage() {
        open("store/Vkusvill_msk");
        $("h1[class='shop-headline__title']").shouldHave(Condition.text("ВкусВилл Экспресс"));
        return this;
    }

    public VkusVillPage openSideMenu(String option, String item) {
        sideMenuOption.find(byText(option)).click();
        contentTitle.shouldHave(Condition.text(option + " "));
        sideMenuOptionSibling.find(byText(item))
                .should(Condition.exist)
                .shouldBe(Condition.enabled)
                .click();
        $$(".shop-products-list__category-title")
                .find(Condition.text(item)).shouldBe(Condition.visible);
        return this;
    }

    public VkusVillPage sideMenuOptions(String option) {
        sideMenuOption.find(byText(option))
                .should(Condition.exist)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled);
        return this;
    }
}
