package hello.core.member;

public class MemberServiceImpl implements MemberService{
    //구현체


    //회원을 찾으면 멤버 레파지토리 인터페이스가 필요
    //커맨드+쉬프트+엔터
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //추상화에만 의존
    private final MemberRepository memberRepository;

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
