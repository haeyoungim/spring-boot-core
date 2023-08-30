package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    //구현체
    //DB확정이 안돼서 메모리로 작성
    //옵션+엔터

    private static Map<Long,Member> store = new HashMap<>();
    //HashMap 동시성 이슈 있음 동시에 접근을 하기 때문에
    //실무는 concurrentHashMap 사용함

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findByID(Long memberId) {
        return store.get(memberId);
    }
}
