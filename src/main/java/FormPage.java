import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;
import java.util.Random;

public class FormPage {

    private final WebDriver driver;

    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(css= "input[id='userNumber']")
    private WebElement userNumber;

    @FindBy(css= "input[id='subjectsInput']")
    private WebElement subjectsInput;

    @FindBy(css = "textarea[id='currentAddress']")
    private WebElement currentAddress;

    @FindAll({
            @FindBy(xpath = ".//input[@name='gender']/..")
    })
    private List<WebElement> gender;

    @FindBy(xpath = ".//input[@id='dateOfBirthInput']")
    private WebElement dateOfBirth;

    @FindAll({
            @FindBy(xpath = ".//div[contains (@class, 'react-datepicker__day react-datepicker__day')]")
    })
    private List<WebElement> dayInCalendar;

    @FindAll({
            @FindBy(xpath = ".//input[contains (@id, 'hobbies-checkbox')]/..")
    })
    private List<WebElement> hobbies;

    @FindBy(xpath = ".//input[@id='uploadPicture']")
    private WebElement picture;

    @FindBy(xpath = ".//input[@id='react-select-3-input']/../../..")
    private WebElement selectState;

    @FindBy(xpath = ".//input[@id='react-select-4-input']/../../..")
    private WebElement selectCity;

    @FindBy(xpath = ".//button[@id='submit']")
    private WebElement submitButton;

    @FindBy(xpath = ".//div[@class='modal-header']")
    private WebElement modalTitle;

    private WebElement textInTable(String value) {
        return driver.findElement(By.xpath(".//div[@class='modal-content']//table//*[contains (text(),'" + value + "')]"));
    }

    /**
     * Метод заполнения поля First name
     */
    public void fillFirstName(String value) {
        firstName.sendKeys(value);
    }

    /**
     * Заполнение поля Last Name
     */
    public void fillLastName(String value) {
        lastName.sendKeys(value);
    }

    /**
     * Заполнение поля Email
     */
    public void fillUserEmail(String value) {
        userEmail.sendKeys(value);
    }

    /**
     * Заполнение поля Number
     */
    public void fillUserNumber(String value) {
        userNumber.sendKeys(value);
    }

    /**
     * Заполнение поля Subjects
     */
    public void fillSubjectsInput(String value) {
        subjectsInput.sendKeys(value);
        subjectsInput.sendKeys(Keys.TAB);
    }

    /**
     * Заполнение поля Current Address
     */
    public void fillCurrentAddress(String value) {
        currentAddress.sendKeys(value);
    }

    public void selectGender(String sex) {
        switch (sex) {
            case ("Male"):
                gender.get(0).click();
                break;
            case ("Female"):
                gender.get(1).click();
                break;
            case ("Other"):
                gender.get(2).click();
                break;
        }
    }

    public void selectDateOfBirth() {
        dateOfBirth.click();
        dayInCalendar.get((int) (Math.random() * dayInCalendar.size())).click();
    }

    public void selectHobbies(boolean sports, boolean reading, boolean music) {
        if (sports) {
            hobbies.get(0).click();
        }
        if (reading) {
            hobbies.get(1).click();
        }
        if (music) {
            hobbies.get(2).click();
        }
    }

    public void uploadPicture() {
        picture.sendKeys(new File("src/main/resources/pic1.jpg").getAbsolutePath());
    }


    public void submitButtonClick() {
        submitButton.click();
    }

    public void checkModalTitle() {
        Assert.assertTrue(modalTitle.isDisplayed());
        Assert.assertEquals(modalTitle.getText(), "Thanks for submitting the form");

    }

    public void checkDataInTable(String value) {
        Assert.assertTrue(textInTable(value).isDisplayed());
    }

    public void selectState(String value) {
        selectState.click();
        driver.findElement(By.xpath(".//*[contains (text(),'" + value + "')]")).click();
    }

    public void selectCity(String value) {
        selectCity.click();
        driver.findElement(By.xpath(".//*[contains (text(),'" + value + "')]")).click();
    }
}
