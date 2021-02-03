import java.util.ArrayList;

abstract class Pizza {
    String name;
    String dough;
    String sauce;
    ArrayList toppings = new ArrayList();

    void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dought...");
        System.out.println("Adding sauce..");
        System.out.println("Adding toppings: ");
        for ( int i =0 ;i < toppings.size() ; i++ ) {
            System.out.println(" " + toppings.get(i));
        }
    }
    void bake() {
        System.out.println("");
    }
    void cut() {
        System.out.println("");
    }
    void box() {
        System.out.println("");
    }
    String getName(){
        return name;
    }
}

abstract class PizzaStore{
    //SimplePizzaFactory factory;

    // public PizzaStore(SimplePizzaFactory factory) {
    //     //this.factory = factory;
    // }
    public Pizza orderPizza (String type)
    {
        Pizza pizza;

        pizza = createPizza(type);
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
    abstract Pizza createPizza(String type); 
    /*
     * 이것이 factory method 선언
     * 피자 객체의 인스턴스를 만드는 일은 pizzaStore의 서브클래스에 있는 createPizza() 메소드에서 처리합니다.
     * 팩토리 메소드는 객체 생성을 처리하며, 팩토리 매소드를 이용하면 객체를 생성하는 작업을 서브클래스에 캡슐화시킬수 있습니다.
     * 즉, 생성을 위임하는데 내부 추상 함수를 통해 캡슐화 시켰다.
     * 근데 이게 무슨 장점이 있는거지?
     */
}

//****** 실제 피자 가계 만드는 곳****** //
class NYPizzaStore extends PizzaStore {
    Pizza createPizza(String type) {
        if( type.equals("cheese")) {
            return new NYSyleCheesePizza();
        } else if( type.equals("veggie")) {
            return new NYSyleVeggiePizza();
        } else return null;
    }
}
// Pizza stores는 pizza를 생성하기 위해 factory method를 이용해서
// createPizza로 생성을 위임했다.
// createPizza에서 pizza를 생성하는데 이 concreate피자는 pizza interface를 이용해서 생성된 것이다.
// 즉, 고수준 구성요소인 pizzaStore와 저수준 구성요소인 피자 객체들이 모두 추상 클래스인 pizza에 의존하게 된다.(DIP)


class NYSyleCheesePizza extends Pizza {
    public NYSyleCheesePizza() {
        name = "NY style Chesse Pizza";
        dough = "Thin Crust Pizza";
        sauce = "Marinara";
        toppings.add("Grated Regginao  chgeese");
    }   
}

class NYSyleVeggiePizza extends Pizza {
    public NYSyleVeggiePizza() {
        name = "NY style Veggie Pizza";
        dough = "Very very Thin Crust Pizza";
        sauce = "good sauce";
        toppings.add("Kimchi");
    }   
}

public class FactoryPattern {
    public static void main(String[] args) {
        
        PizzaStore pizzastore = new NYPizzaStore();
        Pizza pizza = pizzastore.orderPizza("cheese");
        System.out.println("My pizza name is " + pizza.getName() );
    }
}
