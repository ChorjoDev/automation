package com.backbase.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class CreatePage extends HomePage {

    static String url = "http://computer-database.herokuapp.com/computers/new";
    static String title = "Add a computer";

    // Locators
    private By createThisComputerBtn = By.xpath("//input[@value='Create this computer']");

    public boolean isAt() {
        return super.isAt(title, url);
    }

    public void fillInForm(String computerName, String introduced, String discontinued, String company) {
        Select manufacturerDropDown = new Select(driver.findElement(manufacturerSelect));
        System.out.println("Filling out Add computer form");
        driver.findElement(computerNameInput).sendKeys(computerName);
        driver.findElement(introducedInput).sendKeys(introduced);
        driver.findElement(discontinuedInput).sendKeys(discontinued);
        manufacturerDropDown.selectByValue(company);
        System.out.println("Completed out Add computer form");
    }

    public void clickCreateThisComputer() {
        clickButton(createThisComputerBtn);
    }

    public void clickCancel() {
        clickButton(cancelBtn);
    }

    public void fieldValidationErrorPresence(String field) throws Exception {
        List<WebElement> errorFields = driver.findElements(errorFieldDiv);
        String labelColour;
        if (field.equalsIgnoreCase("introduced")) {
            labelColour = driver.findElement(introducedLabel).getCssValue("color");
        } else if (field.equalsIgnoreCase("discontinued")) {
            labelColour = driver.findElement(discontinuedLabel).getCssValue("color");
        } else if (field.equalsIgnoreCase("computer name")) {
            labelColour = driver.findElement(computerNameLabel).getCssValue("color");
        } else {
            throw new Exception("Field (" + field + ") does not match 'introduced' or 'discontinued'");
        }
        String labelHexValue = helpers.rgbToHex(labelColour);
        String errorDivHexValue = helpers.rgbToHex(errorFields
                .stream()
                .map(elm -> elm.getCssValue("background-color")).collect(Collectors.joining()));

        Assert.assertTrue(labelHexValue.equalsIgnoreCase(textErrorHex));
        Assert.assertTrue(errorDivHexValue.equalsIgnoreCase(errorBackgroundHex));
    }
}
