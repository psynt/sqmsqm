package calculatortest;

import javafx.scene.control.TextField;
import org.junit.Test;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.Assert.assertEquals;

public class TestDivByZero extends TestBase {

    @Test
    public void DivZeroTest() {
        clickOn("8");
        clickOn("/");
        clickOn("0");
        clickOn("=");

        WaitForAsyncUtils.waitForFxEvents();

        assertEquals(((TextField)find("#textf")).getText(), "Infinity");
    }

    @Test
    public void ZeroDivZeroTest() {
        clickOn("0");
        clickOn("/");
        clickOn("0");
        clickOn("=");

        WaitForAsyncUtils.waitForFxEvents();

        assertEquals(((TextField)find("#textf")).getText(), "NaN");
    }

    @Test
    public void InfTimesZeroTest() {
        clickOn("8");
        clickOn("/");
        clickOn("0");
        clickOn("=");
        clickOn("*");
        clickOn("0");
        clickOn("=");

        WaitForAsyncUtils.waitForFxEvents();

        assertEquals(((TextField)find("#textf")).getText(), "NaN");
    }
}
