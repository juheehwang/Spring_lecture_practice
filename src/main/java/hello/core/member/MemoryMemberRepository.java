package hello.core.member;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
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
