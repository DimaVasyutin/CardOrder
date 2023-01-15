import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;

public class CardOderTest {
    @Test
    void shouldValidDate() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Дима ВА-сютин");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("p").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldYoName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Артём Вавилов");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldLatinName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Ivan Ivanov");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldEmptyName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldSymbolName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("#########!!!!@@@");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldNumbersName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("78515151");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldMoreThanElevenPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("+7999555555554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone] span.input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldLessThanElevenPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("+799");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone] span.input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldLettersPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("fdfdfd");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone] span.input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldEmptyPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone] span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldSymbolPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("@@@###$#%#");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone] span.input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }
}
