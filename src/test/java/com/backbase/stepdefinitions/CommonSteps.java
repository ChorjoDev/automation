package com.backbase.stepdefinitions;

import com.backbase.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import com.backbase.testdata.World;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.util.NoSuchElementException;

public class CommonSteps {

    private World world;

    public CommonSteps(World world) {
        this.world = world;
    }

    // Background steps
    @Given("I am on the homepage of the computer database application")
    public void iAmOnTheHomepageOfTheComputerDatabaseApplication() {
        Pages.homePage().goTo();
        Assert.assertTrue(Pages.homePage().isAt());
    }

    @And("I click the {string} button")
    public void iClickTheComputerButton(String button) {
        switch (button.toLowerCase()) {
            case "add a new computer":
                Pages.homePage().clickCreateComputerButton();
                break;
            case "create this computer":
                Pages.createComputerPage().clickCreateThisComputer();
                break;
            case "cancel":
                Pages.createComputerPage().clickCancel();
                break;
            case "back":
                Pages.createComputerPage().browserBackButton();
                break;
            case "save":
                Pages.updateComputerPage().clickSaveRecord();
                break;
            case "delete":
                Pages.updateComputerPage().clickDeleteRecord();
                break;
            default:
                throw new NoSuchElementException("Unable to match button string: '" + button + "'");
        }
    }

    @And("I am able to find the record in the database")
    public void iAmAbleToFindTheRecordInTheDatabase() {
        Pages.homePage().filterComputersByName(world.computer.getComputerName());
        Pages.homePage().filterAndAssertComputer(world.computer);
        Pages.homePage().locateAndClickComputerLink(world.computer.getComputerName());
    }

    @And("I enter the {string} into the {string} field")
    public void iEnterTheIntoTheFieldAndCompleteTheRestOfTheFields(String value, String field) throws Exception {
        world.setComputerValueByField(field, value);
        Pages.createComputerPage().fillInByField("name", world.computer);
        Pages.createComputerPage().fillInByField(field, world.computer);
    }

    @And("I select a record at random from the database")
    public void iSelectARecordAtRandomFromTheDatabase() throws ParseException {
        // Set current count of computers
        world.setComputerCount(Pages.homePage().getComputerCount());
        WebElement randomComputerLink = Pages.homePage().selectRandomComputer();
        randomComputerLink.click();
        // Build the previous computer object from the fields for comparison
        world.previousComputer = Pages.updateComputerPage().getAllInputValues();
        String introduced = "-";
        String discontinued = "-";
        if (!world.previousComputer.getIntroduced().isEmpty()) {
            introduced = world.helpers.convertToDisplayDate(world.previousComputer.getIntroduced());
        }
        if (!world.previousComputer.getDiscontinued().isEmpty()) {
            discontinued = world.helpers.convertToDisplayDate(world.previousComputer.getDiscontinued());
        }
        world.previousComputer.setIntroducedDisplayDate(introduced);
        world.previousComputer.setDiscontinuedDisplayDate(discontinued);
    }

    @And("the number of records has been {string}")
    public void theNumberOfRecordHasBeenUpdated(String value) {
        int currentCount = Pages.homePage().getComputerCount();
        if (value.equalsIgnoreCase("increased")) {
            Assert.assertEquals(currentCount, (world.getComputerCount() + 1));
        } else {
            Assert.assertEquals(currentCount, (world.getComputerCount() - 1));
        }
    }

    @When("I refresh the page")
    public void iRefreshThePage() {
        // refresh the browser
        Pages.homePage().refresh();
    }

    @Then("the validation error appears on the {string} field")
    public void theValidationErrorAppearsOnTheNameField(String field) throws Exception {
        Pages.createComputerPage().fieldValidationErrorPresence(field);
    }

    @Then("I am taken back to the homepage")
    public void iAmTakenBackToTheHomepage() {
        Assert.assertTrue(Pages.homePage().isAt());
    }
}
