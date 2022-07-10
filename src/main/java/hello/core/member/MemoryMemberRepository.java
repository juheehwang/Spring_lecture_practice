package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> stored = new HashMap<>();
    //동시성 이슈로 인해 concurrentHashMap 사용해야함

    @Override
    public void save(Member member) {
        stored.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return stored.get(memberId);
    }
}
