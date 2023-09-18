package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;


public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    //final이 되어있으면 기본으로 할당하든 생성자 통해서 할당이 돼야함
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자
    public OrderServiceImpl( MemberRepository memberRepository , DiscountPolicy discountPolicy) {
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
}
