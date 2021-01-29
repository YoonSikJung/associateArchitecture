



//duck의 interface
interface Duck {
    public void fly();
    
}

//duck을 가지고 redDuck object 생성
class RedDuck implements Duck {
    public void fly()
    {
        System.out.println("I am Duck!");
    }
}
// ******************************************************************************************* //

// turkey interface
interface Turkey {
    public void fly();
}

// turkey를 가지고 RedTurkey object 생성함
class RedTurkey implements Turkey{
    public void fly()
    {
        System.out.println("I am Turkey!!");
    }
}
// ******************************************************************************************* //

/* 
 * 겉은 Duck이다. 그래서 외부 사용자는 마치 Duck인 것처럼 호출을 하지만 
 * 실제 내부에서는 turkey를 호출한다.
 * adapter는 실제 class 이다. 
 */
class TurkeyAdapter implements Duck {
    public Turkey turkey;
    public TurkeyAdapter(Turkey turkey)
    {
        this.turkey = turkey;
    }
    public void fly()
    {
        turkey.fly();
    }
}

// ******************************************************************************************* //
// ******************************************************************************************* //
// ******************************************************************************************* //
// ******************************************************************************************* //
// main class
public class Adapter {
    public static void main(String[] args) {
        RedTurkey redturkey = new RedTurkey();
        TurkeyAdapter turkeyadapter = new TurkeyAdapter(redturkey);

        test(turkeyadapter);
    }

    //test를 위한 함수이다.
    //같은 duck을 파라미터로 받는다. 
    //turkey adapter도 duck 인터페이스를 가지고 만들었기 때문에
    // polymorphism으로 접근이 가능하다.
    static public void test(Duck duck)
    {
        duck.fly();
    }

}




