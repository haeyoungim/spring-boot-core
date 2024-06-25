package hello.core.singleton;

public class SingletonService {

    //자기 자신을 내부에 static(class 레벨에 올라가기 때문에 하나만 존재) 선언
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

//    자바가 뜨고 -> static 영역에 있는 instance를 초기화 하면서 생성해서 가지고 있고 instance의 참조를 꺼낼 수 있는
//    방법은 getInstance() 이것밖에 없음. 이 외에 SingletonService를 생성할 수 있는 곳은 아무곳도 없다


    //외부에서 호출하는 부분 안됨 X
//    public static void main(String[] args) {
//        SingletonService singletonService1 = SingletonService.getInstance();
//        SingletonService singletonService2 = SingletonService.getInstance();
//    }
}
