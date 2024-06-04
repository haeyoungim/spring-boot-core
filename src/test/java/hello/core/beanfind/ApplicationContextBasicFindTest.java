package hello.core.beanfind;

import hello.core.discount.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        //memberServie가 MemberServiceImple 의 인스턴스면 성공
        //Assertions + 옵션 + 엔터 형태
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }


    @Test
    @DisplayName("빈 이름없이 타입으로만 조회")
    void findBeanByType() {
        //같은 타입이 여러개일 경우 단점,..
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        //구체적으로 적으면 X 역할과 구현을 구분 해야함, 역할에 의존 해야함
        //유연성이 떨어짐
        //이거는 구현에 의존
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void fineBeanByNameX(){
        //Test는 코드는 실패도 만들어야함
        //ac.getBean("xxx",MemeverService.class):
//        MemberService memberService = ac.getBean("xxx", MemberService.class);

        //오른쪽 () -> .... 을 실행하면 NoSuchBeanDefinitionException 예외가 터져야함
        //터지면 성공
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxx", MemberService.class));


    }

}
