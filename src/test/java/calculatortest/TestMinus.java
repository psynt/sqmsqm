package calculatortest;


import javafx.scene.control.TextField;
import org.junit.Test;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.Assert.assertEquals;

public class TestMinus extends TestBase {
    @Test
    public void minusTest() {
        clickOn("8");
        clickOn("-");
        clickOn("5");
        clickOn("=");

        WaitForAsyncUtils.waitForFxEvents();

        assertEquals(((TextField)find("#textf")).getText(), "3");
    }

   
}