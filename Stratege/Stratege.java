
/*
 *  fly가 실제 변하는 부분이다. 
 *  새로운 duck을 만들때마다 계속 각 특성에 맞게 고쳐야 한다. 
 *  단, display와는 다르게 특정 범위 안에서 바뀐다.
 *  그점이 이것을 object composition 할 수 있는 것이다.
 */
interface Fly {
    public void fly();
}

/*
 * 우리가 바꾸길 원하는 것을 class로 만들어 놓은 것이다.
 *  앞으로 이것을 필요할 때마다 object로 만들어서 끼워 넣으면 된다.
 */
class FlyWithWing implements Fly {
    public void fly() 
    {
        System.out.println("I can fly!");
    }
}
class FlyNo implements Fly {
    public void fly() 
    {
        System.out.println("I can't fly!");
    }
}

// ******************************************************************************************* //

/*
 * 실제 우리가 원하는 abstract duck 구조체이다. 
 *  이것을 밑그림삼아 필요한 duck들을 만들 것이다. 
 *  fly를 원하는 것으로 바꿔 끼우기만 하면 된다. 
 */
abstract class Duck {
    public Fly flybehavior;

    public void fly() {
        flybehavior.fly();
    }

    public abstract void display();
}

// ******************************************************************************************* //

/*
 * redDuck을 만들었다.
 * 필요한 fly는 실제 main에서 관리할 것이다. 
 * 여기선 생성자에서 원하는 fly를 할당 한것을 볼 수 있다.
 */
class RedDuck extends Duck {
    public RedDuck(Fly f) {
        flybehavior = f;
    }
    public void display()
    {
       System.out.println("I am Red Duck!!");
    }
}
// ******************************************************************************************* //
// ******************************************************************************************* //
// ******************************************************************************************* //

public class Stratege {
    public static void main(String[] args) {
        
        //필요한 fly 객체를 생성해놓는다.
        FlyWithWing flywithwing = new FlyWithWing();
        //원하는 fly를 duck 생성시 넣어준다.
        //이로서 원하는 알고리즘이 끼워졌다.
        Duck duck = new RedDuck(flywithwing);

        duck.fly();
    }
}




