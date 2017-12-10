package calculatortest

import javafx.scene.control.TextField
import org.junit.Test
import org.testfx.util.WaitForAsyncUtils

import static org.junit.Assert.assertEquals

class TestAbusiveBehaviour extends TestBase {

    @Test
    void clickManyTimes(){

        def many = 42
        def res = "5" * 41 + 7
        def five = find "5"
        many.times { clickOn five }
        clickOn "+"
        clickOn "2"
        clickOn "="

        WaitForAsyncUtils.waitForFxEvents()

        assertEquals(res, ((TextField) find("#textf")).getText())

        clickOn "c"

    }

}
