package business;

import pages.HomePage;

public class MakeSearch {

    private HomePage homePage;

    public MakeSearch() {
        homePage = new HomePage();
    }

    public FilterProducts search(final String searchTerm){
        homePage.enterSearchPhrase(searchTerm);
        homePage.clickSearchButton();
        return new FilterProducts();
    }
}
