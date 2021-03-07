package com.backbase.pages;

import com.backbase.data.Computer;
import org.openqa.selenium.By;

public class UpdateComputerPage extends HomePage {

    static By deleteBtn = By.xpath("//input[@value='Delete this computer']");

    public void clickSaveRecord() {
        driver.findElement(saveRecordBtn).click();
    }

    public String getComputerNameInput() {
        return driver.findElement(computerNameInput).getAttribute("value");
    }

    public String getIntroducedDateInput() {
        return driver.findElement(introducedInput).getAttribute("value");
    }

    public String getDiscontinuedDateInput() {
        return driver.findElement(discontinuedInput).getAttribute("value");
    }

    public String getManufacturerSelectValue() {
        return driver.findElement(manufacturerSelect).getAttribute("value");
    }

    public Computer getAllInputValues() {
        Computer computer = new Computer();
        computer.setComputerName(getComputerNameInput());
        computer.setIntroduced(getIntroducedDateInput());
        computer.setDiscontinued(getDiscontinuedDateInput());
        computer.setManufacturerValue(getManufacturerSelectValue());
        return computer;
    }

    public void clickDeleteRecord() {
        clickButton(deleteBtn);
    }
}
