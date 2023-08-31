package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
