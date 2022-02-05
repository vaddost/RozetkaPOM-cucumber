package business;

import pages.SearchResultsPage;

public class SearchPageBO {
    private SearchResultsPage searchPage;

    public void filterProductsByBrand(final String brand){
        if (searchPage == null) {
            searchPage = new SearchResultsPage();
        }
        searchPage.enterBrandNameInSearchInput(brand);
        searchPage.clickOnBrandFilterCheckbox(brand);
    }

    public void filterProductsByPriceDesc(){
        if (searchPage == null) {
            searchPage = new SearchResultsPage();
        }
        searchPage.sortProductsByOption("2: expensive");
    }

    public void showOnlyAvailableProducts(){
        if (searchPage == null) {
            searchPage = new SearchResultsPage();
        }
        searchPage.showOnlyAvailableProducts();
    }

    public void openFirstPDP(){
        if (searchPage == null) {
            searchPage = new SearchResultsPage();
        }
        searchPage.clickOnFirstProduct();
    }
}
