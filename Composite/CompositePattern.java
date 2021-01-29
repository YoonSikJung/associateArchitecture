
import java.util.ArrayList;
import java.util.Iterator;
// ******************************************************************************************* //
/*
 * component의 기본 꼴이 되는 클래스
 * abstract로 구성하고, leaf노드 composite 노드 모두 사용할 수 있는
 * 추상화 함수를 모두 만든다. 
 */
abstract class MenuCompoent {
    public void add(MenuCompoent menuCompoent) {
        throw new UnsupportedOperationException();
    }
    public void remove(MenuCompoent menuCompoent) {
        throw new UnsupportedOperationException();
    }
    public MenuCompoent getChild(int i) {
        throw new UnsupportedOperationException();
    }
    public String getName(){
        throw new UnsupportedOperationException();
    }
    public String getDescription(){
        throw new UnsupportedOperationException();
    }
    public double getPrice(){
        throw new UnsupportedOperationException();
    }
    public boolean isVegetarian(){
        throw new UnsupportedOperationException();
    }
    public void print(){
        throw new UnsupportedOperationException();
    }
}
// ******************************************************************************************* //
/*
 * 사실상 leaaf노드
 * 간단히 우리가 원하는 정보를 담는 구조로 클래스를 만든다.
 */
class MenuItem extends MenuCompoent {
    String name;
    String description;
    boolean isVegetarian;
    double price;

    public MenuItem(String name,String description, boolean vegetarian, double price)
    {
        this.name = name;
        this.description = description;
        this.isVegetarian = vegetarian;
        this.price= price;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public double getPrice(){
        return price;
    }
    public boolean isVegetarian(){
        return isVegetarian;
    }

    public void print() {
        System.out.print(" " + getName());
        if (isVegetarian()) {
            System.out.print("(v)");
        }
        System.out.println(", " + getPrice() );
        System.out.println("     -- " + getDescription() );
    }
}
// ******************************************************************************************* //
/*
 * composite 패턴의 핵심부분
 * leaf노드를 가지기 위해서 arraylist와 같이 자료구조를 가지고 있음
 * 그 arraylist를 탐색하기 위해 print문에서는 iterate를 사용했음
 */
class Menu extends MenuCompoent {
    /*
     * 핵심 부분 -> menu는 arraylist를 가지고 있어서 하위 menuItems에 대한 접근을 가지고 있다.
     * 그리고 이것을 아래쪽 Iterator를 활용해서 자신이 가진 개념적 자식들에게 접근 할 수가 있다.
     */
    ArrayList menuComponents = new ArrayList();  
    String name;
    String description;

    public Menu(String name,String description)
    {
        this.name = name;
        this.description = description;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
 
    public void add(MenuCompoent menuCompoent) {
        menuComponents.add(menuCompoent);
    }
    public void remove(MenuCompoent menuCompoent) {
        menuComponents.remove(menuCompoent);
        
    }
    public MenuCompoent getChild(int i) {
        return (MenuCompoent)menuComponents.get(i);
    }
    public void print() {
        System.out.print("\n" + getName());
        System.out.println("," + getDescription() );
        System.out.println("----------------------");

        Iterator iterator = menuComponents.iterator();
        while( iterator.hasNext()) {
            MenuCompoent menuCompoent = (MenuCompoent)iterator.next();
            menuCompoent.print();
        }
    }
}
// ******************************************************************************************* //
// 모두 출력하기 위한 출력용 클래스
class Waitress {
    public MenuCompoent allMenu;

    public Waitress(MenuCompoent allMenu) {
        this.allMenu = allMenu;
    }
    public void printMenu() {
        allMenu.print();
    }
}
// ******************************************************************************************* //
// ******************************************************************************************* //
// ******************************************************************************************* //

public class CompositePattern {
    
    public static void main(String[] args) {
        //다형성을 이용한다.
        // 그래야지 leaf 및 composite 노드 모두를 간단히 건드릴 수 있다.
        MenuCompoent pancakeHouseMenu = new Menu("팬케익하우스", "아침메뉴");
        MenuCompoent dinerMenu = new Menu("객체마을 식당 메뉴", "저녁메뉴");
        MenuCompoent desertMenu = new Menu("디저트 메뉴", "디저트를 즐겨 보세요!");

        dinerMenu.add( new MenuItem("파스타", "맛나는 파스타~", true, 3.89));
        desertMenu.add(new MenuItem("애플파이", "깔끔한 마무리~", true, 1.2));

        MenuCompoent allMenu = new Menu("전체메뉴", "전체메뉴");

        allMenu.add(pancakeHouseMenu);
        allMenu.add(dinerMenu);
        allMenu.add(desertMenu);

        Waitress waitress = new Waitress(allMenu);
        waitress.printMenu();
    }
}
