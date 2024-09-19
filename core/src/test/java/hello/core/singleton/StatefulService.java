package hello.core.singleton;

public class StatefulService {

    // 상태를 유지하는 필드를 두어서는 안된다
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}
