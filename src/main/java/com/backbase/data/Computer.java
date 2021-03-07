package com.backbase.data;

import java.util.*;

/**
 * Computer Object to be created and consumed within
 * the tests. Faker library used to generate random
 * data for the class attributes.
 */
public class Computer {

    private String computerName;
    private String introduced;
    private String introducedDisplayDate;
    private String discontinued;
    private String discontinuedDisplayDate;
    private String manufacturerValue;
    private String manufacturerName;
    private List<Manufacturer> manufacturers;

    public Computer() {
        manufacturers = new ArrayList<>(Arrays.asList(Manufacturer.values()));
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getIntroduced() {
        return introduced;
    }

    public String getIntroducedDisplayDate() {
        return introducedDisplayDate;
    }

    public void setIntroducedDisplayDate(String introducedDisplayDate) {
        this.introducedDisplayDate = introducedDisplayDate;
    }

    public String getDiscontinuedDisplayDate() {
        return discontinuedDisplayDate;
    }

    public void setDiscontinuedDisplayDate(String discontinuedDisplayDate) {
        this.discontinuedDisplayDate = discontinuedDisplayDate;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public String getManufacturerValue() {
        return manufacturerValue;
    }

    public void setManufacturerValue(String manufacturerValue) {
        this.manufacturerValue = manufacturerValue;
    }

    public void setManufacturers(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public String getManufacturerByValue(String value) {
        Optional<Manufacturer> collect = manufacturers.stream().filter(manufacturer -> manufacturer.getManufacturerValue().equals(value)).findFirst();
        return collect.get().getManufacturerName();
    }

}
