package elements;

import org.openqa.selenium.WebElement;

public class TextInput extends Element{

    public TextInput(WebElement webElement) {
        super(webElement);
    }

    public void enterTextInEmptyInput(CharSequence... charSequences){
        super.clear();
        super.sendKeys(charSequences);
    }

    public String getPlaceholderValue(){
        return webElement.getAttribute("placeholder");
    }
}
