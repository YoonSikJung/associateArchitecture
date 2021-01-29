import java.util.ArrayList;



/*
 * observer 명칭
 * publisher : subscriber = subject : observer
 */


 /*
  * observer, subscriber에 관한 부분
  * 단순하게 업데이트 부분만 있다.
  */
interface observer {
    public void update(int d);
}

class Display implements observer{
    public int data ;
    public void update(int d) {
        data = d;
        System.out.println(data);
    }
}

// ******************************************************************************************* //

/*
 * publisher(subject)는 아주 간단하게 oberver(subscriber)만 알고 있으면 된다.
 *  그래서 coupling을 획기적으로 낮출 수 있다.
 */
interface subject {
    public void addObserver(observer o);
    public void Notify();
}

// publisher(subject) 객체가 생성될 때 observer의 객체만 가지고 있으면 된다.
// 그냥 observer의 함수만 호출 해주면 되거든
class WeatherCenter implements subject{
    public ArrayList observers;
    public int data;

    public WeatherCenter()
    {
        observers = new ArrayList();
    }
    public void addObserver(observer o)
    {
        observers.add(o);
    }
    public void SetData(int a)
    {
        this.data = a;
    }
    public void Notify()
    {
        for( int i = 0 ; i <  observers.size(); i++)
            {
                observer o = (observer)observers.get(i);
                o.update(data);
            }
    }
}

// ******************************************************************************************* //
// ******************************************************************************************* //
// ******************************************************************************************* //

public class ObserverPattern {
    public static void main(String[] args) {

        Display d = new Display();
        WeatherCenter w = new WeatherCenter();
        w.addObserver(d);

        w.SetData(100);
        w.Notify();

    }
}

