package hello.core.singleton;

public class StatefulService {

    //클라이언트 의도 주문 -> price에 값 저장

//    private int price; // 상태를 유지하는 필드 10000 -> 20000
//
//    public void order(String name, int price) {
//        System.out.println("name + = " + name + "price = " + price);
//        this.price = price;//여기가 문제!
//    }
//
//    public int getPrice() {
//        return price;
//    }


    //스프링 빈 무상태
    private int price; // 상태를 유지하는 필드 10000 -> 20000

    public int order(String name, int price) {
        System.out.println("name + = " + name + "price = " + price);
//        this.price = price;//여기가 문제!
        return price;
    }

    public int getPrice() {
        return price;
    }
}
