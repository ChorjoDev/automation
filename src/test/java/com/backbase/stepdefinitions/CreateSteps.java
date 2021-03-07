package com.backbase.stepdefinitions;

import com.backbase.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import com.backbase.testdata.World;

public class CreateSteps {

    private World world;

    public CreateSteps(World world) {
        this.world = world;
    }

    @And("I complete all the fields to create a new computer")
    public void iCompleteAllTheFieldsToCreateANewComputer() {
        Pages.createComputerPage().fillInForm(
                world.computer.getComputerName(),
                world.computer.getIntroduced(),
                world.computer.getDiscontinued(),
                world.computer.getManufacturerValue());
    }

    @Then("^the computer (?:created|updated) banner is present$")
    public void theComputerCreatedBannerIsPresent() {
        Assert.assertTrue(Pages.homePage().isAt());
        Assert.assertTrue(Pages.homePage().successBannerPresence());
        Assert.assertTrue(Pages.homePage().successBannerTextPresence(world.computer.getComputerName()));
    }
}
