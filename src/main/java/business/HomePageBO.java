package business;

import pages.HomePage;

public class HomePageBO {
    private HomePage homePage;

    public void makeSearch(final String searchTerm){
        homePage = new HomePage();
        homePage.enterSearchPhrase(searchTerm);
        homePage.clickSearchButton();
    }
}
