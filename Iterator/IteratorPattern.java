import java.util.ArrayList;


interface Iterator {
    boolean hasNext();
    Object next();
}

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
