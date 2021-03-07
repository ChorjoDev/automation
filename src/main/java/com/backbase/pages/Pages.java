package com.backbase.pages;

/**
 * Class to manage the instantiation of pages
 */
public class Pages {

    public static HomePage homePage() {
        return new HomePage();
    }

    public static CreatePage createComputerPage() {
        return new CreatePage();
    }

    public static UpdateComputerPage updateComputerPage() {
        return new UpdateComputerPage();
    }

    public static void cleanUp() {
        BasePage.close();
    }
}
