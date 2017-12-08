package calculatortest

import calculator.CalculatorController
import calculator.CalculatorModel
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static calculator.Op.NOOP
import static calculator.Op.fromString
import static Double.parseDouble
import static com.xlson.groovycsv.CsvParser.parseCsv

class CalculatorControllerTest extends Specification {
    @Shared List<Map<String,String>> test_data = new ArrayList<Map<String,String>>()
    @Shared def operations = ["+","-","*","/"]

    def setupSpec(){
        def inter = new ArrayList<Map<String,String>>()
        def data = parseCsv(new FileReader("build/resources/test/testData.csv"))
        data.each {inter << (["a":it.a, "b":it.b, "+":it.add, "-":it.sub, "*":it.mul, "/":it.div] as Map<String,String>)}

        inter.each {
            test_data << ["a":it.a , "b":it.b , "op":"+", "res":it."+"]
            test_data << ["a":it.a , "b":it.b , "op":"-", "res":it."-"]
            test_data << ["a":it.a , "b":it.b , "op":"*", "res":it."*"]
            test_data << ["a":it.a , "b":it.b , "op":"/", "res":it."/"]
        }
    }

    @Unroll
    def "test calculate: #a #op #b = #res"() {
        given:
        @Subject
        def controller = new CalculatorController()
        controller.setModel(new CalculatorModel())

        when:
        controller.setCurOp fromString(op)
        controller.setValue parseDouble(a)
        controller.setValue parseDouble(b)
        def result = controller.calculate()

        then:
        result == parseDouble(res)
        result == controller.getValue()
        NOOP == controller.getCurOp()

        where:
        entry << test_data
        res = entry['res']
        op = entry['op']
        a = entry['a']
        b = entry['b']
    }
}
