import java.util.ArrayList;





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
    public ArrayList getMenuItems() {
        return menuItems;
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
    public MenuItem[] getMenuItems() {
        return menuItems;
    }
}
// ******************************************************************************************* //
// ******************************************************************************************* //
// ******************************************************************************************* //

public class IteratorPattern {
    
    public static void main(String[] args) {
       
    }
}
