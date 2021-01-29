

// OCP ( open close principle)
// component를 활용하는 것
/*
 * 이 아래는 그냥 우리가 알고 있는 일반적인 상속 관계이다.
 * 그저 Beverage라는 abstract 클래스를 활용해서 
 * 커피를 만들었다.
 */
abstract class Beverage {
    public String description = "unknown Beverage";
    public String getDescription() {
        return description;
    }
    public abstract int cost();
}

class Coffe extends Beverage {
    public Coffe() {
        description = "Coffe";
    }
    public int cost() {
        return 3;
    }
}

// ******************************************************************************************* //

/*
 * decorate의 핵심 부분
 * decorate의 base class에서 바로 위의 Beverage class를 component로 가지고 있어서 이를 생성자일 때 안쪽에 넣을 오브젝트를 저장해놓는다.
 */

abstract class CandimentDecorate extends Beverage {
    Beverage beverage;
    public abstract String getDescription(); //Beverage에서는 일반 함수로 정의 했지만, 상속을 받을 때 다시 abstract으로 바꾸었다. 반드시 구현하게 하도록
}


class Sugar extends CandimentDecorate {
    public Sugar(Beverage beverage) {
        this.beverage = beverage;
    }
    public String getDescription(){
        return beverage.getDescription() + " + sugar";
    }
    public int cost() {
        return beverage.cost() + 1;
    }  
}

class Mocha extends CandimentDecorate {
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }
    public String getDescription(){
        return beverage.getDescription() + " + Mocha";
    }
    public int cost() {
        return beverage.cost() + 2;
    }  
}


// ******************************************************************************************* //
// ******************************************************************************************* //
// ******************************************************************************************* //

public class DecoratePattern {
    public static void main(String[] args) {
        
        Beverage coffe = new Coffe();

        coffe = new Sugar(coffe);
        coffe = new Sugar(coffe);
        coffe = new Mocha(coffe);

        System.out.println(coffe.getDescription());
        System.out.println(coffe.cost());

        
        

    }
}
