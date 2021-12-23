package pages;

import elements.Button;
import elements.TextInput;
import org.openqa.selenium.devtools.v85.overlay.model.ColorFormat;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(name = "search")
    private TextInput searchInput;

    @FindBy(xpath = "//button[contains(@class, 'search-form__submit')]")
    private Button searchButton;

    public HomePage() {
        super();
    }

    public void enterSearchPhrase(final String searchPhrase){
        searchInput.enterTextInEmptyInput(searchPhrase);
    }

    public void clickSearchButton(){
        searchButton.safeClick();
    }

    public String getSearchButtonBackgroundColor(ColorFormat colorFormat){
        return searchButton.getButtonBackgroundColor(colorFormat);
    }

    public String getSearchInputPlaceholder(){
        return searchInput.getPlaceholderValue();
    }
}
