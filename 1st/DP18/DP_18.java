
// Design Pattern 18

abstract class Value {
    public String value;
    abstract public String getValue();
}

class MyInterger extends Value {
    public MyInterger(String integer){
        value = integer;
    }
    public String getValue() {
        return value;
    }
}

abstract class OperatorDecorator extends Value {
    public Value upperValue;
    public abstract String getValue();
}

class Add extends OperatorDecorator {
    public Add(Value upperValue, String value) {
        this.upperValue = upperValue;
        this.value = value;
    }
    public String getValue() {
        return upperValue.getValue() + "+" + value;
    }
}

class Sub extends OperatorDecorator {
    public Sub(Value upperValue, String value) {
        this.upperValue = upperValue;
        this.value = value;
    }
    public String getValue() {
        return upperValue.getValue() + "-" + value;
    }
}

class Mul extends OperatorDecorator {
    public Mul(Value upperValue, String value) {
        this.upperValue = upperValue;
        this.value = value;
    }
    public String getValue() {
        return upperValue.getValue() + "*" + value;
    }
}

class Div extends OperatorDecorator {
    public Div(Value upperValue, String value) {
        this.upperValue = upperValue;
        this.value = value;
    }
    public String getValue() {
        return upperValue.getValue() + "/" + value;
    }
}

class Bracket extends OperatorDecorator {
    public Bracket(Value upperValue) {
        this.upperValue = upperValue;
    }
    public String getValue() {
        return "("+upperValue.getValue()+")";
    }
}

public class DP_18 {
    public static void main(String[] args) {
        
        // 3
        Value integer = new MyInterger("3");
        System.out.println(integer.getValue());

        //2+5
        integer = new MyInterger("2");
        integer = new Add(integer, "5");
        System.out.println(integer.getValue());

        //( (4+2)*2 - (8-1))/2
        integer = new MyInterger("4");
        integer = new Add(integer, "2");

        integer = new Bracket(integer);
        integer = new Mul(integer, "2");
        
        Value integer2 = new MyInterger("8");
        integer2 = new Sub(integer2, "1");
        integer2 = new Bracket(integer2);

        integer = new Sub(integer, integer2.getValue());
        integer = new Bracket(integer);
        
        integer = new Mul(integer, "2");
        
        System.out.println(integer.getValue());
    }
}