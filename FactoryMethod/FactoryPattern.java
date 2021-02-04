import java.util.ArrayList;


interface Dough {
    public String name = "";    
}

class ThinCrustDough implements Dough {
    public String name = "Thin Crust Dough";    
}

interface Sauce {
    public String name = "";    
}

class MarinaraSauce implements Sauce {
    public String name = "Thin MarinaraSauce";    
}

interface Chesse {
    public String name = "";    
}

class RegginaoCheese implements Chesse {
    public String name = "Thin RegginaoCheese";    
}



/////************************************************
/// add for abstact factory
interface PizzaIngredientFactory {
    public Dough createDough();
    public Sauce createSauce();
    public Chesse createCheese();
}

class NYPizaaIngredientFactory implements PizzaIngredientFactory {
    public Dough createDough() {
        return new ThinCrustDough();
    }
    public Sauce createSauce() {
        return new MarinaraSauce();
    }
    public Chesse createCheese() {
        return new RegginaoCheese();
    }
}

abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Chesse chesse;
    ArrayList toppings = new ArrayList();

    // void prepare() {
    //     System.out.println("Preparing " + name);
    //     System.out.println("Tossing dought...");
    //     System.out.println("Adding sauce..");
    //     System.out.println("Adding toppings: ");
    //     for ( int i =0 ;i < toppings.size() ; i++ ) {
    //         System.out.println(" " + toppings.get(i));
    //     }
    // }
    abstract void prepare();
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
    void setName(String name){
        this.name = name;
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
        
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizaaIngredientFactory();
        //각 지역마다 달라지는 ingredient

        if( type.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("뉴욕 치즈 피자야");
        } else if( type.equals("veggie")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("뉴욕 Clam 피자야");
        } else return null;

        return pizza;
    }

}
// Pizza stores는 pizza를 생성하기 위해 factory method를 이용해서
// createPizza로 생성을 위임했다.
// createPizza에서 pizza를 생성하는데 이 concreate피자는 pizza interface를 이용해서 생성된 것이다.
// 즉, 고수준 구성요소인 pizzaStore와 저수준 구성요소인 피자 객체들이 모두 추상 클래스인 pizza에 의존하게 된다.(DIP)


class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public void prepare() {
        System.out.println("prepareing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        chesse = ingredientFactory.createCheese();

    }   
}

class ClamPizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public void prepare() {
        System.out.println("prepareing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
    }   
}

public class FactoryPattern {
    public static void main(String[] args) {
        
        PizzaStore pizzastore = new NYPizzaStore();
        Pizza pizza = pizzastore.orderPizza("cheese");
        System.out.println("My pizza name is " + pizza.getName() );
    }
}
