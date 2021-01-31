import java.util.ArrayList;


/*
 * SRS 원칙을 적용 시킨 결과이다.
 * 보관하는 것과 꺼내는 것의 책임을 나누었다.
 * 이를 활용하여 high cohesion을 도달할 수있다. 
 */
// ******************************************************************************************* //
//diner와 pancake 집의 메뉴 리스트를 공통의 방식으로 접근하기 위해 
//iterator라는 인터페이스를 선언
interface Iterator {
    boolean hasNext();
    Object next();
}

//선언한 인터페이를 가지고 diner class를 위한 iterator institute
class DinerMenuIterator implements Iterator {
    MenuItem[] items;
    int position;

    public DinerMenuIterator(MenuItem[] items)
    {
        this.items = items;
    }
    public Object next() {
        MenuItem menuItem = items[position];
        position++;
        return menuItem;
    }
    public boolean hasNext() {
        if(position >= items.length || items[position]== null){
            return false;
        }
        return true;
    }
}

//선언한 인터페이를 가지고 Pancake class를 위한 iterator institute
class PancakeHouseIterator implements Iterator {
    ArrayList items;
    int position;

    public PancakeHouseIterator(ArrayList items)
    {
        this.items = items;
    }
    public Object next() {
        Object menuItem = items.get(position);
        position++;
        return menuItem;
    }
    public boolean hasNext() {
        if(position >= items.size() || items.get(position) == null){
            return false;
        }
        return true;
    }
}

// ******************************************************************************************* //
// 메뉴 아이템에 관한 공통 클래스
class MenuItem  {
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
}
// ******************************************************************************************* //
//합병 하는 가계 중 하나인 팬케이크 집
class PancakeHouseMenu {
    ArrayList menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList();

        addItem("레귤러 팬케이크 세트", "달걀 후라이가 곁들인 팬케이크", false, 4.99);
        addItem("윤쓰 팬케이크 세트", "맛있는것만 다 넣음", false, 4.99);
    }
    public void addItem(String name,String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }
    // public ArrayList getMenuItems() {
    //     return menuItems;
    // }

    //위에서 구현한 pancake iterator 구초제를 하나 만들어서 사용자에게 리턴시켜준다.
    //그래서 클래스의 관계는 composite 관계로 볼 수 있다.
    public Iterator createIterator() {
        return new PancakeHouseIterator(menuItems);
        
    }
}
// ******************************************************************************************* //
//합병 하는 가계 중 하나인 저녁 가계
class DinerMenu {
    static final int MAX_ITEMS = 6;
    int numOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];


        addItem("체식주의자용!", "오로지 샐러드만!", true, 3.99);
        addItem("고기 폭탄 식단", "넘나 맛있는거!", false, 1.99);
    }
    public void addItem(String name,String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (numOfItems >= MAX_ITEMS) {
            System.out.println("미안 메뉴가 꽉찼어");
        }
        else {
            menuItems[numOfItems++] = menuItem;            
        }
    }

    //위에서 구현한 diner iterator 구초제를 하나 만들어서 사용자에게 리턴시켜준다.
    //그래서 클래스의 관계는 composite 관계로 볼 수 있다.
    public Iterator createIterator() {
        return new DinerMenuIterator(menuItems);
        
    }
    // public MenuItem[] getMenuItems() {
    //     return menuItems;
    // }
}

// ******************************************************************************************* //


class Waitress {
    PancakeHouseMenu pancakeHouseMenu;
    DinerMenu dinerMenu;

    public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;     
    }
    public void printMenu() {
        Iterator pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator dinerIterator = dinerMenu.createIterator();
        System.out.println("아침메뉴");
        printMenu(pancakeIterator);
        System.out.println("저녁메뉴");
        printMenu(dinerIterator);
    }

    private void printMenu(Iterator iterator)
    {
        while( iterator.hasNext() ) {
            MenuItem menuItem = (MenuItem)iterator.next();
            System.out.print(menuItem.getName() + " ");
            System.out.print(menuItem.getPrice() + " ");
            System.out.println(menuItem.getDescription() + " ");
        }
    }
}

// ******************************************************************************************* //
// ******************************************************************************************* //
// ******************************************************************************************* //

public class IteratorPattern {
    
    public static void main(String[] args) {
       PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
       DinerMenu dinerMenu = new DinerMenu();

       Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);
       waitress.printMenu();
    }
}
