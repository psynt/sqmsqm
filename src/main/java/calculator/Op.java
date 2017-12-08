package calculator;

public enum Op {
    NOOP, ADD, SUBTRACT, MULTIPLY, DIVIDE;

    public static Op fromString (String s){
        switch (s){
            case "+" : return ADD;
            case "-" : return SUBTRACT;
            case "*" : return MULTIPLY;
            case "/" : return DIVIDE;
            default: return NOOP;
        }
    }

}
