package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //final이 되어있으면 기본으로 할당하든 생성자 통해서 할당이 돼야함
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;

    //수정자 의존관계
    //OrderServiceImplTest를 위한 코드!
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }



    //@RequiredArgsConstructor는 final이 붙은 애들를 파라미터로 받는 생성자를 만들어줌. 이 메서드 내용을
    //생성자
//    public OrderServiceImpl( MemberRepository memberRepository , DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    //조회 빈이 두개 이상일때
    //생성자 자동 주입예시 (@Qualifier)
//    public OrderServiceImpl( MemberRepository memberRepository , @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

        public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



    //주문 생성
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
       //회원정보 조회
       Member memeber = memberRepository.findByID(memberId);
       //할인정책에 회원을 넘김 ( Grade만 넘겨도 되고 member 자체를 넘겨도 됨 )
       int discountPrice = discountPolicy.discount(memeber, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
