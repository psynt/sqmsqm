package calculatortest;

import javafx.scene.control.TextField;
import org.junit.Test;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.Assert.assertEquals;

public class TestDivByZero extends TestBase {


    @Test
    public void DivZeroTest() throws Exception{
        clickOn("8");
        clickOn("/");
        clickOn("0");
        clickOn("=");

        WaitForAsyncUtils.waitForFxEvents();

        assertEquals(((TextField)find("#textf")).getText(), "Infinity");
    }

    @Test
    public void ZeroDivZeroTest() throws Exception{
        clickOn("0");
        clickOn("/");
        clickOn("0");
        clickOn("=");

        WaitForAsyncUtils.waitForFxEvents();

        assertEquals(((TextField)find("#textf")).getText(), "NaN");
    }
}
