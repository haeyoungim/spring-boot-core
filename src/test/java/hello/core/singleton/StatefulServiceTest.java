package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A 사용자 10000원 주문
        statefulService1.order("userA",10000);

        //ThreadB: B 사용자 20000원 주문
        statefulService2.order("userB",20000);

        //A가 주문하고 금액을 조회하는 사이에 B가 주문

        //ThreadA: 사용자 A가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
        //20000원이 나옴.
        //왜냐면 statefulService1 이든 statefulService2 이든 같은 인스터스를 사용하기 때문에 (isSame 했을때 똑같았음)


        /**스프링 빈 무상태 설계*/
        //지역 변수기 때문에 사용자A,사용자B 값이 다름 (지역 변수는 공유되지 않음)
        int userAprice = statefulService1.order("userA",10000);
        int userBprice = statefulService1.order("userB",20000);

        System.out.println("userAprice = " + userAprice);


//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }
    static class TestConfig{

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}