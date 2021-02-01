import java.util.Random;
/*
 * 기존의 문제점.
 * 새로운 상태가 추가되면 다른 행동에 영향을 미쳐서 변한다.
 * *변하는 것을 추상화해라
 * 즉, 행동을 추상화한것이다.
 */


//행동을 추상화 함 -> 핵심 부분
//상태에 따른 행동을 추상화 한다는 것은, 상태라른 구체적인 object에 공통된 행동을 넣는 것이다.
interface State {
    public void insertQuarer();
    public void ejectQuater();
    public void turnCrank();
    public void dispense();
}

// ******************************************************************************************* //
// 사용자가 원하는 상태를 하나씩 구현한다. 

class HasQuarterState implements State{
    Random randonWinner = new Random(System.currentTimeMillis());
    GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine)
    {
        this.gumballMachine = gumballMachine;
    }
    public void insertQuarer() {
        System.out.println("돈은 이미 들어가 있어요~~ 어서와 호구");
    }
    public void ejectQuater() {
        System.out.println("돈을 반환해드릴께요..");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }
    public void turnCrank() {
        System.out.println("돌아갑니다~ 무엇이 나올까요~");
        int winner = randonWinner.nextInt(10);
        if( (winner==0) && (gumballMachine.getCount()> 1)) {
            gumballMachine.setState(gumballMachine.getWinnerState());
        }
        else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }
    public void dispense() {
        System.out.println("나왔습니다~ 가져가세요~");
    }
}

class SoldState implements State{
    GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine)
    {
        this.gumballMachine = gumballMachine;
    }
    public void insertQuarer() {
        System.out.println("잠시만요, 알맹이가 나가고 있어요!");
    }
    public void ejectQuater() {
        System.out.println("이미 알맹이를 뽑았어요!");
    }
    public void turnCrank() {
        System.out.println("손잡이는 한번만 돌려주세요!");
    }
    public void dispense() {
        //새롭게 추가된 상태(winner)에 따른 수정사항 이다. 
        // 이 부분만 수정해주면 되므로 전체 시스템에 대한 couping 낮다고 볼 수 있다.
        gumballMachine.releaseBall();
        if( gumballMachine.getCount() > 0 ){
            System.out.println("가져가세요!");
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        }
        else {
            System.out.println("매진입니다");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}

class SoldOutState implements State{
    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine)
    {
        this.gumballMachine = gumballMachine;
    }
    public void insertQuarer() {
        System.out.println("매진 상태 입니다.");
    }
    public void ejectQuater() {
        System.out.println("매진 입니다.");
    }
    public void turnCrank() {
        System.out.println("매진 입니다.");
    }
    public void dispense() {
        System.out.println("매진 입니다.");
    }
}

class NoQuaterState implements State{
    GumballMachine gumballMachine;

    public NoQuaterState(GumballMachine gumballMachine)
    {
        this.gumballMachine = gumballMachine;
    }
    public void insertQuarer() {
        System.out.println("게임을 시작하지..");
        gumballMachine.setState(gumballMachine.getHasQurter());
    }
    public void ejectQuater() {
        System.out.println("돈을 먼저 넣지 그래?");
    }
    public void turnCrank() {
        System.out.println("돈을 먼저 넣어라...");
    }
    public void dispense() {
        System.out.println("돈 먼저 넣어라...");
    }
}

//새롭게 추가된 win state
//상태는 추가되었지만 다른 상태의 코드에는 거의 영향이 없다.
//새롭게 추가된 상태와 연관 있는 상태만 수정사항이 있다.
class WinnerState implements State{
    GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine)
    {
        this.gumballMachine = gumballMachine;
    }
    public void insertQuarer() {
        System.out.println("잠시만요, 알맹이가 나가고 있어요!");
    }
    public void ejectQuater() {
        System.out.println("이미 알맹이를 뽑았어요!");
    }
    public void turnCrank() {
        System.out.println("손잡이는 한번만 돌려주세요!");
    }
    public void dispense() {
        System.out.println("축하합니다. 당첨 되셨어요!");
        gumballMachine.releaseBall();
        if( gumballMachine.getCount() == 0) {
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
        else {
            gumballMachine.releaseBall();
            if( gumballMachine.getCount() >0){
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            }
            else {
                System.out.println("더 이상 알맹이가 없어요");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
            
        }
    }
}

// ******************************************************************************************* //
// 구현한 각 상태의 클래스(행동을 추상화하여 구현한 것)를 호출하는 전체 시스템
// 전체 시스템은 현재 상태에 그저 호출만 하면 된다.
// 구체적인 행동은 상태 오브젝트에서 알아서 처리할 것이다. 
// 각 상태 오브젝트에서 적절한 행동이 들어오면 다음 상태로 세팅해준다. (그래서 state pattern)
// 만일 상태 오브젝트에서 구현하지 않은 행동이 들어오면 그냥 무시하면 된다.  <<-- 이 말에 대한 검증 및 이해 필요
class GumballMachine {
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;
    State winnerState;

    State state = soldOutState;
    int count;

    public GumballMachine(int numberGumballs) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuaterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);
        this.count = numberGumballs;
        if ( this.count > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarer(){
        state.insertQuarer();
    }

    public void ejectQuater() {
        state.ejectQuater();
    }

    public void turnCrank(){
        state.turnCrank();
        state.dispense();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void releaseBall() {
        if( count != 0)
            count--;
    }

    public int getCount() {
        return count;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }
    public State getSoldOutState() {
        return soldOutState;
    }
    public State getSoldState() {
        return soldState;
    }
    public State getHasQurter() {
        return hasQuarterState;
    }
    public State getWinnerState() {
        return winnerState;
    }
}

// ******************************************************************************************* //
// ******************************************************************************************* //
// ******************************************************************************************* //

public class StatePattern {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(10);

        for( int i = 0 ; i < 11 ; i++) {
            gumballMachine.insertQuarer();
            gumballMachine.turnCrank();
        }
    }

}



