package com.backbase.stepdefinitions;

import com.backbase.pages.Pages;
import io.cucumber.java.en.Given;
import com.backbase.testdata.World;

import java.text.ParseException;

public class DataPrepSteps {

    private World world;

    // Inject the world object to the step
    public DataPrepSteps(World world) {
        this.world = world;
    }

    @Given("I have a computer to add to the database")
    public void iHaveAComputerToAddToTheDatabase() throws ParseException {
        world.constructComputer();
        world.setComputerCount(Pages.homePage().getComputerCount());
    }

    @Given("I have a computer to add that has a name length of {int} characters")
    public void iHaveAComputerToAddThatHasANameLengthOf(int namelength) throws ParseException {
        world.constructComputer();
        world.computer.setComputerName(world.faker.lorem().fixedString(namelength));
    }

    @Given("I have a computer to add with a blank name")
    public void iHaveAComputerToAddThatHasNoName() throws ParseException {
        world.constructComputer();
        world.computer.setComputerName("");
    }
}
