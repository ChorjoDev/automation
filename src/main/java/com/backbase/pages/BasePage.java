package com.backbase.pages;

import com.backbase.data.Computer;
import com.backbase.utilities.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class BasePage {
    //Locators
    static By heading = By.xpath("//*[@id='main']/h1");
    static By computerTable = By.xpath("//table[@class='computers zebra-striped']");
    static By tableHeaders = By.xpath("//table[@class='computers zebra-striped']/thead/tr/th");
    static By tableData = By.xpath("//table[@class='computers zebra-striped']/tbody/tr/td");
    static By introducedInput = By.id("introduced");
    static By introducedLabel = By.xpath("//*[@for='introduced']");
    static By discontinuedInput = By.id("discontinued");
    static By discontinuedLabel = By.xpath("//*[@for='discontinued']");
    static By manufacturerSelect = By.id("company");
    static By errorFieldDiv = By.cssSelector("form div.clearfix.error");
    static By cancelBtn = By.xpath("//a[text()='Cancel']");
    static By paginationNextBtn = By.xpath("//li[@class='next']");
    static By pageCount = By.xpath("//*[@id='pagination']/ul/li[@class='current']/a");
    static By computerLinks = By.xpath("//tbody/tr/td/a");
    static By computerNameInput = By.id("name");
    static By computerNameLabel = By.xpath("//*[@for='name']");
    static By saveRecordBtn = By.xpath("//input[@value='Save this computer']");

    static String textErrorHex = "#9d261d";
    static String errorBackgroundHex = "#fae5e3";
    private static int COLUMN_COUNT = 4;
    public static Helpers helpers;
    static WebDriver driver = new FirefoxDriver();

    public BasePage() {
        helpers = new Helpers();
    }

    public static void goTo(String url) {
        driver.get(url);
    }

    public static String title() {
        return driver.getTitle();
    }

    public static String pageHeading() {
        return driver.findElement(heading).getText();
    }

    public static void close() {
        driver.quit();
    }

    public static void clickButton(By button) {
        driver.findElement(button).click();
        waitForLoad();
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static boolean isAt(String heading, String currentUrl) {
        return pageHeading().contains(heading) && getCurrentUrl().equals(currentUrl);
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * Method to check if page is ready and wait if not
     */
    public static void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = driverLoad -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(pageLoadCondition);
    }

    public void browserBackButton() {
        driver.navigate().back();
    }

    public void fillInNameField(String value) {
        driver.findElement(computerNameInput).sendKeys(value);
    }

    public void fillInIntroducedField(String value) {
        driver.findElement(introducedInput).sendKeys(value);
    }

    public void fillInDiscontinuedField(String value) {
        driver.findElement(discontinuedInput).sendKeys(value);
    }

    /**
     * Get all the computer a tags which contain the href to the computers
     *
     * @return - Return a list of the web elements
     */
    public List<WebElement> getComputerLinks() {
        return driver.findElements(computerLinks);
    }

    /**
     * Choose a dropdown box by the attributes value
     *
     * @param value the value to locate and choose
     */
    public void selectManufacturerByValue(String value) {
        Select select = new Select(driver.findElement(manufacturerSelect));
        select.selectByValue(value);
    }

    /**
     * A method to allow the option of a field to be filled in
     *
     * @param field    - The field to fill in - name, introduced, discontinued, manufacturer
     * @param computer - The object that contains the value to be entered
     * @throws Exception - Thrown if no field matched in case statement
     */
    public void fillInByField(String field, Computer computer) throws Exception {
        switch (field.toLowerCase()) {
            case "name":
                System.out.println("Changing the name to: " + computer.getComputerName());
                driver.findElement(computerNameInput).clear();
                fillInNameField(computer.getComputerName());
                break;
            case "introduced":
                System.out.println("Changing the introduced date to: " + computer.getIntroduced());
                driver.findElement(introducedInput).clear();
                fillInIntroducedField(computer.getIntroduced());
                break;
            case "discontinued":
                System.out.println("Changing the discontinued date to: " + computer.getDiscontinued());
                driver.findElement(discontinuedInput).clear();
                fillInDiscontinuedField(computer.getDiscontinued());
                break;
            case "manufacturer":
                System.out.println("Changing the manufacturer to: " + computer.getManufacturerName());
                selectManufacturerByValue(computer.getManufacturerValue());
                break;
            default:
                throw new Exception("No matching field found for: " + field.toLowerCase());
        }
    }

    /**
     * Complete all fields on the computer database form
     *
     * @param computer - The object which contains the computer data
     */
    public void completeAllFields(Computer computer) {
        fillInNameField(computer.getComputerName());
        fillInIntroducedField(computer.getIntroduced());
        fillInDiscontinuedField(computer.getDiscontinued());
        selectManufacturerByValue(computer.getManufacturerValue());
    }

    /**
     * Locate and filter a HTML table of all the td tags visible on the page
     *
     * @return List of all the td in a list of webelements
     */
    public List<List<WebElement>> getAllTableData() {
        return driver.findElement(computerTable)
                .findElements(By.tagName("tr"))
                .stream()
                .map(tr -> tr.findElements(By.tagName("td")))
                .filter(row -> !row.isEmpty()).collect(Collectors.toList());
    }

}