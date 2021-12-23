package elements;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.overlay.model.ColorFormat;
import org.openqa.selenium.support.Color;

import javax.swing.plaf.synth.ColorType;

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

    public String getButtonBackgroundColor(ColorFormat colorFormat){
        Color color = Color.fromString(webElement.getCssValue("background-color"));
        switch (colorFormat){
            case HEX:
                return color.asHex();
            case RGB:
                return color.asRgb();
            default:
                throw new IllegalArgumentException("The color format is not found");
        }

    }
}
