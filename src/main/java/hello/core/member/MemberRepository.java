package hello.core.member;

public interface MemberRepository {

    void save(Member member);//회원저장

    Member findByID(Long memberId);//회원 아이디 찾기
}
