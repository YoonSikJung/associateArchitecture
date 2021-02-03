import java.util.ArrayList;




class PizzaStore{
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }
    public Pizza orderPizza (String type)
    {
        Pizza = pizzal

        pizza = factory.createPizza(); 
        //피자 factory에서 만들어 주세요!
        //만일 피자 가계가 여러 분점이 생긴다면, 마치 stratege처럼 여러 케이스의 객체를 만들어 놓고
        //각 케이스 (뉴욕점, 시카고점)에 맞는 객체를 생성해서,
        //공통된 인터페이스를 통해 피자를 만들면 된다. 
        //!! 그런데 몇몇 지점에서 독자적인 피자를 만들고 있다.(사업주로서 공통된 인터페이스를 원하는데..)
        // 그래서 하나의 프레임워크로 피자를 만들 필요가 생겼다.
        

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}



public class FactoryPattern {
    public static void main(String[] args) {
        System.out.println("yes.");
    }
}
