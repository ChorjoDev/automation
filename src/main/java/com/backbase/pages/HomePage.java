package com.backbase.pages;

import com.backbase.data.Computer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    private By createComuterButton = By.id("add");

    static String url = "http://computer-database.herokuapp.com/computers";
    static String title = "computers database";
    static String heading = "computers found";
    static String[] expectedTableHeaders = {"Computer name", "Introduced", "Discontinued", "Company"};
    static By successBanner = By.xpath("//div[@class='alert-message warning']");
    static By searchByNameInput = By.id("searchbox");
    static By searchSubmitBtn = By.id("searchsubmit");
    static By computerTable = By.xpath("//table[@class='computers zebra-striped']");
    static By noResultsMessage = By.xpath("//*[@class='well']/em");

    public void goTo() {
        goTo(url);
    }

    public boolean isAt() {
        return super.isAt(heading, url);
    }

    public void clickCreateComputerButton() {
        clickButton(createComuterButton);
    }

    public boolean successBannerPresence() {
        return driver.findElement(successBanner).isDisplayed();
    }

    public boolean successBannerTextPresence(String companyName) {
        return driver.findElement(successBanner).getText().equals("Done! Computer " + companyName + " has been created");
    }

    public boolean deletionBannerTextPresence() {
        return driver.findElement(successBanner).getText().equals("Done! Computer has been deleted");
    }

    public void filterComputersByName(String computerName) {
        driver.findElement(searchByNameInput).sendKeys(computerName);
        clickButton(searchSubmitBtn);
    }

    public void locateAndClickComputerLink(String computerName) {
        WebElement computerLink = getComputerLink(computerName);
        computerLink.click();
    }

    private WebElement getComputerLink(String computerName) {
        return driver.findElement(By.xpath("//a[text() = '" + computerName + "']"));
    }

    public WebElement selectRandomComputer() {
        Random rand = new Random();
        List<WebElement> computers = getComputerLinks();
        return computers.get(rand.nextInt(computers.size()));
    }

    public String getNoResultMessage() {
        return driver.findElement(noResultsMessage).getText();
    }

    /**
     * Get the heading on home page and strip out alpha characters
     * to get the count
     */
    public int getComputerCount() {
        String computerCount = pageHeading().replaceAll("[A-Za-z]", "").trim();
        return Integer.parseInt(computerCount);
    }

    /**
     * Get a list of the headers from the html table
     *
     * @return
     */
    public List<String> getTableHeaders() {
        return driver.findElements(tableHeaders).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    /**
     * Get all table data on the visible page
     *
     * @return - Return a list of strings
     */
    public List<String> getPageTableData() {
        return driver.findElements(tableData).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    /**
     * Check if more than one page of computers exist
     *
     * @return - Return a list of the web elements
     */
    public boolean paginationExist() {
        return driver.findElements(paginationNextBtn).isEmpty();
    }


    /**
     * Filter rows by all the values of the computer to get an exact match
     *
     * @param rows     - filtered rows from the homepage
     * @param computer - Computer containing the values to filter on
     * @return List of List web elements
     */
    public List<List<WebElement>> filterRowByValues(List<List<WebElement>> rows, Computer computer) {
        return rows.stream()
                .filter(list ->
                        list.get(0).getText().equals(computer.getComputerName()) &&
                                list.get(1).getText().equals(computer.getIntroducedDisplayDate()) &&
                                list.get(2).getText().equals(computer.getDiscontinuedDisplayDate()) &&
                                list.get(3).getText().equals(computer.getManufacturerName())
                ).collect(Collectors.toList());
    }

    /**
     * Filter the rows using the company name as the value
     *
     * @param rows         - Matched rows from filtering
     * @param computerName - Name to filter by
     * @return - A list of the webelements
     */
    public List<List<WebElement>> findCompanyNameFromTableData(List<List<WebElement>> rows, String computerName) {
        return rows.stream()
                .filter(row -> row.get(0).getText().equals(computerName))
                .collect(Collectors.toList());
    }

    /**
     * This method will filter records and determine if the computer attr
     * match
     *
     * @param computer - The computer to match
     * @return - bool if matched or not. (Will return false if duplicate found)
     */
    public boolean filterAndAssertComputer(Computer computer) {
        String computerName = computer.getComputerName();
        // Find rows then get the table data for each row and finally filter empty lists
        List<List<WebElement>> rows = getAllTableData();

        // Find the computer name and return it
        List<List<WebElement>> matchingRows = findCompanyNameFromTableData(rows, computerName);

        // Compare the rest of the details: introduced, discontinued and manufacturer
        List<List<WebElement>> matchedList = filterRowByValues(matchingRows, computer);

        if (matchedList.size() == 1) {
            System.out.println("Matched record found on filter");
            return true;
        } else if (matchedList.size() > 1) {
            System.out.println("Duplicate records in the database with matching attributes");
            return false;
        } else {
            System.out.println("No records found");
            return false;
        }
    }
}
