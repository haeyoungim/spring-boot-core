package hello.core.discount;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//공연기획자 역할
@Configuration
public class AppConfig {
    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //싱글톤 깨진다?

    //예상 로그
    //Call AppConfig.memberService
    //Call AppConfig.memberRepository
    //Call AppConfig.orderService
    //Call AppConfig.memberRepository
    //Call AppConfig.memberRepository


    //실 로그
    //Call AppConfig.memberService
    //Call AppConfig.memberRepository
    //Call AppConfig.orderService

    //@Configuration 때문에


    //MemberServiceImpl은 밖에서 생성을해서 주입
    //생성자를 통해서 객체가 들어간다 ( 생성자 주입 )


    @Bean
    public MemberService memberService(){
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("Call AppConfig.orderService");
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();

    }


}
