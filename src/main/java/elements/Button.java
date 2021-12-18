package elements;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class Button extends Element{
    public Button(WebElement webElement) {
        super(webElement);
    }

    public void safeClick(){
        if(!super.isEnabled()){
            throw new AssertionError("The button is disabled");
        } else{
            try{
                super.click();
            } catch(WebDriverException e){
                super.click();
            }
        }
    }
}
