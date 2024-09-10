import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoQaTest {

    @BeforeAll
    static void setUp () {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillForm() {
        open("/automation-practice-form");

        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Petrov");
        $("#userEmail").setValue("ab@ya.ru");
        $("#genterWrapper").find("label[for='gender-radio-2']").click();
        $("#userNumber").setValue("0123456789");

        // Выбираем дату рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").find("option[value='0']").click();
        $(".react-datepicker__year-select").find("option[value='1980']").click();
        $(".react-datepicker__day--010").click();


        $("#hobbiesWrapper").find("label[for='hobbies-checkbox-1']").click();
        $("#hobbiesWrapper").find("label[for='hobbies-checkbox-2']").click();
        $("#hobbiesWrapper").find("label[for='hobbies-checkbox-3']").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#subjectsInput").setValue("Arts").pressEnter();

        $("#uploadPicture").uploadFromClasspath("1.jpg");


        $("#currentAddress").setValue("Somewhere Street 21/23");

        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").click();

        // Проверка результатов теста
        $(".modal-body").shouldHave(text("Ivan Petrov"));
        $(".modal-body").shouldHave(text("ab@ya.ru"));
        $(".modal-body").shouldHave(text("Female"));
        $(".modal-body").shouldHave(text("0123456789"));
        $(".modal-body").shouldHave(text("10 January,1980"));
        $(".modal-body").shouldHave(text("Computer Science, Arts"));
        $(".modal-body").shouldHave(text("Sports, Reading, Music"));
        $(".modal-body").shouldHave(text("1.jpg"));
        $(".modal-body").shouldHave(text("Somewhere Street 21/23"));
        $(".modal-body").shouldHave(text("Haryana Karnal"));
        $("#closeLargeModal").click();
        System.out.println("Тест пройден успешно");
    }

}
