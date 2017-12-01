package calculatortest

import javafx.scene.control.TextField
import org.junit.Before
import org.junit.Test
import org.testfx.util.WaitForAsyncUtils

import java.util.concurrent.TimeoutException

import static com.xlson.groovycsv.CsvParser.parseCsv
import static org.junit.Assert.assertEquals

class TestNormalOperations extends TestBase{

    @Before
    void setUpClass() throws Exception {
        super.setUpClass()
    }

    @Override
    void afterEachTest() throws TimeoutException {
        super.afterEachTest()
    }

    @Override
    String toString() {
        return super.toString()
    }

    @Test
    void testOperations(){

        List<Map<String,String>> test_data = new ArrayList<Map<String,String>>()
        def data = parseCsv(new FileReader("build/resources/test/testData.csv"))
        data.each {test_data << (["a":it.a, "b":it.b, "+":it.add, "-":it.sub, "*":it.mul, "/":it.div] as Map<String,String>)}
        def operations = ["+","-","*","/"]

        test_data.each { Map<String,String> entry ->
            operations.each { String op ->
                clickOn entry.get('a')
                clickOn op
                clickOn entry.get('b')
                clickOn "="

                WaitForAsyncUtils.waitForFxEvents()

                assertEquals(((TextField) find("#textf")).getText(), entry[op])

                clickOn "c"
            }
        }
    }
}
