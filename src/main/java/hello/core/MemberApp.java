package hello.core;

import hello.core.discount.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    //psvm
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();

        //spring 컨테이너
        //@Bean(객체)를 관리
        //AppConfig의 설정 정보를 가지고 spring이 @Bean을 spring container에 등록해서 관리
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        //커맨드+옵션+v
        //Long 타입이라 1L
        memberService.join(member);

        Member findeMember = memberService.findeMember(1L);
        System.out.println("find Member = " + findeMember.getName());
        System.out.println("new member = " + member.getName());
        //soutv
    }
}
