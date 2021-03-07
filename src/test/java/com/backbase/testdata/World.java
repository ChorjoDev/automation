package com.backbase.testdata;

import com.backbase.data.Computer;
import com.github.javafaker.Faker;
import com.backbase.utilities.Helpers;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Class to keep the state of data throughout the tests
 */
public class World {
    public Computer computer;
    public Computer previousComputer;
    private int computerCount;
    public Helpers helpers;
    public Faker faker;

    public World() {
        this.helpers = new Helpers();
        // Instantiate faker and set locale for relevant data
        faker = new Faker(new Locale("en-GB"));
        // Object to be used throughout the steps
        computer = new Computer();
        previousComputer = new Computer();
    }

    public void setComputerValueByField(String field, String value) {
        switch (field.toLowerCase()) {
            case "name":
                this.computer.setComputerName(value);
                break;
            case "introduced":
                this.computer.setIntroduced(value);
                break;
            case "discontinued":
                this.computer.setDiscontinued(value);
                break;
            case "manufacturer":
                this.computer.setManufacturerValue(value);
                break;
        }
    }

    public void constructComputer() throws ParseException {
        Date pastDate = faker.date().past(faker.random().nextInt(3600), TimeUnit.DAYS);
        Date futureDate = faker.date().future(faker.random().nextInt(3600), TimeUnit.DAYS);
        computer.setComputerName(faker.funnyName().name());
        computer.setIntroduced(helpers.convertDateToFormat("yyyy-MM-dd", faker.date().between(pastDate, futureDate)));
        computer.setDiscontinued(helpers.convertDateToFormat("yyyy-MM-dd", faker.date().between(pastDate, futureDate)));
        int randomIndex = faker.number().numberBetween(0, computer.getManufacturers().size());
        computer.setManufacturerValue(computer.getManufacturers().get(randomIndex).getManufacturerValue());
        computer.setManufacturerName(computer.getManufacturers().get(randomIndex).getManufacturerName());
        computer.setIntroducedDisplayDate(helpers.convertToDisplayDate(computer.getIntroduced()));
        computer.setDiscontinuedDisplayDate(helpers.convertToDisplayDate(computer.getDiscontinued()));
    }

    public int getComputerCount() {
        return this.computerCount;
    }

    public void setComputerCount(int count) {
        this.computerCount = count;
    }
}
