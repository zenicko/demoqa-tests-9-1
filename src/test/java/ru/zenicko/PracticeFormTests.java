package ru.zenicko;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void screenSize() {
        Configuration.startMaximized = true;
    }

    @Test
    void practiceFormTests() {
        //The test data
        String firstName = "Nick",
                lastName = "Ivanov",
                userEmail = "aaa@aa.aa",
                genderRadio = "gender-radio-1",
                userNumber = "9991234567",
                dateOfBirthInput = "5 Nov 2000",
                checkedDateOfBirthInput = "5 November,2000",
                subjectsInput = "Hindi",
                currentAddress = "RU, Moscow, st. Baba Galya, 1",
                fullPath = "D:\\IDEAProjects\\demoqa-tests-9\\src\\test\\resources\\photo_2020-11-17_15-25-27.jpg";
        Boolean hobbiesCheckbox1 = true,
                hobbiesCheckbox2 = true,
                hobbiesCheckbox3 = true;
        //The some variables
        String
                gender = "",
                stemp,
                currentDate = "",
                Hobbies = "",
                stateAndCity = "";
        int pos;
        File cv = new File(fullPath);


        open("https://demoqa.com/automation-practice-form");
        //Name
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        //Email
        $("#userEmail").setValue(userEmail);

        //Gender
        switch (genderRadio) {
            case "gender-radio-2":
                $$(".custom-control-label").get(1).click();
                gender = "Female";
                break;
            case "gender-radio-3":
                $$(".custom-control-label").get(2).click();
                gender = "Other";
                break;
            default:
                $$(".custom-control-label").get(0).click();
                gender = "Male";
        }

        //Mobile(10 Digits)
        $("#userNumber").setValue(userNumber);

        //Date of Birth
        $("#dateOfBirthInput").click();
        currentDate = $("#dateOfBirthInput").getAttribute("value");
        System.out.println("currentDate " + currentDate);
        //$("#dateOfBirthInput").clear();
        for (int i = 1; i <= dateOfBirthInput.length(); i++) {
            $("#dateOfBirthInput").sendKeys(Keys.BACK_SPACE);
        }
        $("#dateOfBirthInput").setValue(dateOfBirthInput);

        //Subjects
        $("#subjectsInput").setValue(subjectsInput);
        $("#react-select-2-option-0").click();

        //Hobbies
        if (hobbiesCheckbox1) {
            $("#hobbiesWrapper").$$(".custom-control-label").get(0).click();
            Hobbies = "Sports";
        }
        if (hobbiesCheckbox2) {
            $("#hobbiesWrapper").$$(".custom-control-label").get(1).click();
            Hobbies +=  Hobbies == "" ? "Reading": ", Reading";
        }
        if (hobbiesCheckbox3) {
            $("#hobbiesWrapper").$$(".custom-control-label").get(2).click();
            Hobbies +=  Hobbies == "" ? "Music": ", Music";
        }

        //Scroll page
        $("#submit").scrollIntoView(true);

        //Picture
        $("#uploadPicture").uploadFile(cv);

        //Current Address
        $("#currentAddress").setValue(currentAddress);

        //State and City
        $("#state .css-yk16xz-control").click();
        $("#react-select-3-option-0").click(); //.shouldBe(visible)
        stateAndCity = $(".css-1uccc91-singleValue").getText();

        $("#city .css-yk16xz-control").click();
        $("#react-select-4-option-0").click(); //.shouldBe(visible)
        stateAndCity += " " + $$(".css-1uccc91-singleValue").get(1).getText();

        //Submit
        $("#submit").click();

        //Check Student Name: fist name + last name
        System.out.println("Check Student Name:" + $$(".table-responsive tbody tr").get(0).$$("td").get(1));
        $$(".table-responsive tbody tr").get(0).$$("td").get(1).shouldHave(text(firstName + ' ' + lastName));

        //Check Student Email
        System.out.println("Check Student Name:" + $$(".table-responsive tbody tr").get(1).$$("td").get(1));
        $$(".table-responsive tbody tr").get(1).$$("td").get(1).shouldHave(text(userEmail));

        //Check Gender
        System.out.println("Check Student Name:" + $$(".table-responsive tbody tr").get(2).$$("td").get(1));
        $$(".table-responsive tbody tr").get(2).$$("td").get(1).shouldHave(text(gender));

        //Check Mobile
        System.out.println("Check Student Name:" + $$(".table-responsive tbody tr").get(3).$$("td").get(1));
        $$(".table-responsive tbody tr").get(3).$$("td").get(1).shouldHave(text(userNumber));

        //Check Date of Birth
        System.out.println("Check Student Name:" + $$(".table-responsive tbody tr").get(4).$$("td").get(1));
        stemp = currentDate.substring(0, 0) + checkedDateOfBirthInput;
        $$(".table-responsive tbody tr").get(4).$$("td").get(1).shouldHave(text(stemp));

        //Check Subjects
        System.out.println("Check Student Name:" + $$(".table-responsive tbody tr").get(5).$$("td").get(1));
        $$(".table-responsive tbody tr").get(5).$$("td").get(1).shouldHave(text(subjectsInput));

        //Check Hobbies
        System.out.println("Check Student Name:" + $$(".table-responsive tbody tr").get(6).$$("td").get(1));
        $$(".table-responsive tbody tr").get(6).$$("td").get(1).shouldHave(text(Hobbies));

        //Check Picture
        System.out.println("Check Student Name:" + $$(".table-responsive tbody tr").get(7).$$("td").get(1));
        pos = fullPath.lastIndexOf("\\") + 1;
        stemp = fullPath.substring(pos);
        $$(".table-responsive tbody tr").get(7).$$("td").get(1).shouldHave(text(stemp));

        //Check Current Address
        System.out.println("Check Student Name:" + $$(".table-responsive tbody tr").get(8).$$("td").get(1));
        $$(".table-responsive tbody tr").get(8).$$("td").get(1).shouldHave(text(currentAddress));

        //Check State and City
        System.out.println("Check Student Name:" + $$(".table-responsive tbody tr").get(9).$$("td").get(1));
        $$(".table-responsive tbody tr").get(9).$$("td").get(1).shouldHave(text(stateAndCity));

        System.out.println("The test is done!");
    }
}

