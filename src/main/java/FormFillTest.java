import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FormFillTest {
    public static FormPage formPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demoqa.com/automation-practice-form");
        formPage = new FormPage(driver);
    }

    @Test
    public void formFillTest() {

        String firstName = "Ivan";
        String lastName = "Ivanovich";
        String userEmail = "Ivanov@gmail.com";
        String userNumber = "1234567891";
        String subject = "Maths";
        String currentAddress = "Russia";
        String gender = "Male";
        String state = "Haryana";
        String city = "Karnal";
        boolean sports = true;
        boolean reading = false;
        boolean music = true;

        formPage.fillFirstName(firstName);
        formPage.fillLastName(lastName);
        formPage.fillUserEmail(userEmail);
        formPage.fillUserNumber(userNumber);
        formPage.fillSubjectsInput(subject);
        formPage.fillCurrentAddress(currentAddress);
        formPage.selectGender(gender);
        formPage.selectDateOfBirth();
        formPage.selectHobbies(sports, reading, music);
        formPage.uploadPicture();
        formPage.selectState(state);
        formPage.selectCity(city);
        formPage.submitButtonClick();

        formPage.checkModalTitle();
        formPage.checkDataInTable(firstName);
        formPage.checkDataInTable(lastName);
        formPage.checkDataInTable(userEmail);
        formPage.checkDataInTable(userNumber);
        formPage.checkDataInTable(subject);
        formPage.checkDataInTable(currentAddress);
        formPage.checkDataInTable(gender);
        formPage.checkDataInTable(state);
        formPage.checkDataInTable("pic1.jpg");
        formPage.checkDataInTable(city);
        if (sports) {
            formPage.checkDataInTable("Sports");
        }
        if (reading) {
            formPage.checkDataInTable("Reading");
        }
        if (music) {
            formPage.checkDataInTable("Music");
        }

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
