package com.backbase.stepdefinitions;

import com.backbase.pages.Pages;
import com.backbase.testdata.World;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class DeleteSteps {

    private World world;

    public DeleteSteps(World world) {
        this.world = world;
    }

    @Then("the record has been removed from the database")
    public void theRecordHasBeenRemovedFromTheDatabase() {
        Pages.homePage().filterComputersByName(world.previousComputer.getComputerName());
        Assert.assertEquals(Pages.homePage().pageHeading(), "No computers found");
        Assert.assertEquals(Pages.homePage().getNoResultMessage(), "Nothing to display");

    }

    @Then("the computer delete banner is present")
    public void theComputerDeleteBannerIsPresent() {
        Assert.assertTrue(Pages.homePage().isAt());
        Assert.assertTrue(Pages.homePage().successBannerPresence());
        Assert.assertTrue(Pages.homePage().deletionBannerTextPresence());
    }
}
