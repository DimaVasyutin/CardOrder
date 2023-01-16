import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardOderTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldValidDate() {
        $("[data-test-id=name] input").setValue("Дима ВА-сютин");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldYoName() {
        $("[data-test-id=name] input").setValue("Артём Вавилов");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldLatinName() {
        $("[data-test-id=name] input").setValue("Ivan Ivanov");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldEmptyName() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldSymbolName() {
        $("[data-test-id=name] input").setValue("#########!!!!@@@");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldNumbersName() {
        $("[data-test-id=name] input").setValue("78515151");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldMoreThanElevenPhone() {
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("+7999555555554555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldLessThanElevenPhone() {
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("+799");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldLettersPhone() {
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("fdfdfd");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldEmptyPhone() {
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldSymbolPhone() {
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("@@@###$#%#");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldEmptyCheckBox() {
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("button").click();
        $("label.input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));

    }

    @Test
    void shouldEmptyCheckBoxTwo() {
        $("[data-test-id=name] input").setValue("Артем Вавилов");
        $("[data-test-id=phone] input").setValue("+79995554555");
        $("button").click();
        $("label.input_invalid").shouldBe(visible);

    }
}
