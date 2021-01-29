


/*
 * template 패턴은 아주 간단함
 * 그냥 abstract class에 template을 만들어 놓고 중간중간만 abstrack 함수로 만들어서
 * 자식이 구현할 수 있도록만 하면 된다.
 * 상속을 이용한다. 
 * 훅을 사용할 수 있다.
 */
abstract class FullStep {
    final void prepareRecipe()
    {
        step1_fix();
        step2_fix();
        step3_variable();
        step4_fix();
        step5_variable();
        if( customerWantsHook() ) step_hook();
    }
    abstract public void step3_variable();
    abstract public void step5_variable();
    abstract public void step_hook();

    public void step1_fix() {
        System.out.println("abstract step1");
    }
    public void step2_fix() {
        System.out.println("abstract step2");
    }
    public void step4_fix() {
        System.out.println("abstract step4");
    }
    public boolean customerWantsHook(){
        return true;
    }
}

class YoonStep extends FullStep {
    public void step3_variable() {
        System.out.println("Yoons step4");
    }
    public void step5_variable() {
        System.out.println("Yoons step4");

    }
    public void step_hook() {
        System.out.println("Yoons hook");
    }
}

// ******************************************************************************************* //
// ******************************************************************************************* //
// ******************************************************************************************* //

public class TemplatePattern {
    public static void main(String[] args) {
       YoonStep ys = new YoonStep();
       ys.prepareRecipe();

    }
}
