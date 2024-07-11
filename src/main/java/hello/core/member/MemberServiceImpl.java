package hello.core.member;

import hello.core.discount.DiscountPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    //구현체

    //회원을 찾으면 멤버 레파지토리 인터페이스가 필요
    //커맨드+쉬프트+엔터
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //추상화에만 의존
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;


    @Autowired(required = false)
    public DiscountPolicy setDiscountPolicy(DiscountPolicy discountPolicy) {
        return this.discountPolicy = discountPolicy;
    }

    @Autowired
    public MemberRepository setMemberRepository(MemberRepository memberRepository) {
        return this.memberRepository = memberRepository;
    }


    @Autowired //ac.getBean(MemberRepository.class) 기능을 한다
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findeMember(Long memberId) {
        return memberRepository.findByID(memberId);
    }
    //구현체 하나만 있을때는 대부분 뒤에 Impl 사용
    //인터페이스 생성 -> 구현체 생성

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
