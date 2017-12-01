package calculatortest

import javafx.scene.control.TextField
import org.junit.Before
import org.junit.Test
import org.testfx.util.WaitForAsyncUtils

import static org.junit.Assert.assertEquals

class TestNormalOperations extends TestBase{

    @Before
    void setUpClass() throws Exception {
        super.setUpClass()

    }

    @Test
    void testOperations(){

        def operators = ["+","-","*","/"]
        def results = [13,3,40,1]

        operators.eachWithIndex { String it, int i ->
            clickOn("8")
            clickOn(it)
            clickOn("5")
            clickOn("=")

            WaitForAsyncUtils.waitForFxEvents()

            assertEquals(((TextField)find("#textf")).getText(), results[i].toString())

            clickOn("c")
        }
    }
}
