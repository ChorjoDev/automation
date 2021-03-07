package com.backbase.stepdefinitions;

import com.backbase.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import com.backbase.testdata.World;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UpdateSteps {

    private World world;

    public UpdateSteps(World world) {
        this.world = world;
    }

    @And("I edit the {string} field(s)")
    public void iEditTheField(String field) throws Exception {
        world.constructComputer();
        Pages.updateComputerPage().fillInByField(field, world.computer);
    }

    @And("I edit the {string} field with {string}")
    public void iEditTheFieldWith(String field, String value) throws Exception {
        world.setComputerValueByField(field, value);
        Pages.updateComputerPage().fillInByField(field, world.computer);
    }

    @Then("the {string} field has been updated")
    public void theRecordHasBeenUpdated(String field) throws Exception {
        // Placeholders for the expected values to assert against
        String expectedComputerName = world.previousComputer.getComputerName();
        String expectedIntroducedDate = world.previousComputer.getIntroducedDisplayDate();
        String expectedDiscontinuedDate = world.previousComputer.getDiscontinuedDisplayDate();
        String expectedManufacturerName = "-";

        // Get the Name of the manufacturer from value
        if (!world.previousComputer.getManufacturerValue().isEmpty()) {
            expectedManufacturerName = world.previousComputer.getManufacturerByValue(world.previousComputer.getManufacturerValue());
        }

        // Set the expected as the field has been updated by the new computer values
        switch (field.toLowerCase()) {
            case "name":
                expectedComputerName = world.computer.getComputerName();
                break;
            case "introduced":
                expectedIntroducedDate = world.computer.getIntroducedDisplayDate();
                break;
            case "discontinued":
                expectedDiscontinuedDate = world.computer.getDiscontinuedDisplayDate();
                break;
            case "manufacturer":
                expectedManufacturerName = world.computer.getManufacturerByValue(world.computer.getManufacturerValue());
                break;
            default:
                throw new Exception("Unble to match " + field + ". Field names are name, introduced, discontinued, manufacturer");
        }
        Pages.homePage().filterComputersByName(expectedComputerName);
        // Merge <td> into a single list of web elements
        List<WebElement> tableData = Pages.updateComputerPage().getAllTableData()
                .stream().collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);

        // Assert values against table data
        Assert.assertEquals(tableData.get(0).getText(), expectedComputerName);
        Assert.assertEquals(tableData.get(1).getText(), expectedIntroducedDate);
        Assert.assertEquals(tableData.get(2).getText(), expectedDiscontinuedDate);
        Assert.assertEquals(tableData.get(3).getText(), expectedManufacturerName);
    }
}
