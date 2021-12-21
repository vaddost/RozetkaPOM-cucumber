package business;

import pages.SearchResultsPage;

public class FilterProducts {
    private SearchResultsPage searchResultsPage;

    public FilterProducts() {
        searchResultsPage = new SearchResultsPage();
    }

    public FilterProducts filterResults(String brandFilterName){
        searchResultsPage.enterBrandNameInSearchInput(brandFilterName);
        searchResultsPage.clickOnBrandFilterCheckbox(brandFilterName);
        searchResultsPage.showOnlyAvailableProducts();
        return this;
    }
}
