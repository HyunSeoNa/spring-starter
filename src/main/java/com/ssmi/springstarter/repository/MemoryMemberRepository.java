package com.ssmi.springstarter.repository;

import com.ssmi.springstarter.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * packageName    : com.ssmi.springstarter.repository
 * fileName       : MemoryMemberRepository
 * author         : ssmihs
 * date           : 2025-10-13
 * description    :
 */
@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    /* 저장소 비우기 */
    public void clearStore() {
        store.clear();
    }
}
