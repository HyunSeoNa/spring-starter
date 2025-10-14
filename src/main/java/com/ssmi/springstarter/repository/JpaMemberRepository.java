package com.ssmi.springstarter.repository;

import com.ssmi.springstarter.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.ssmi.springstarter.repository
 * fileName       : JpaMemberRepository
 * author         : ssmihs
 * date           : 2025-10-14
 * description    :
 */
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return List.of();
    }
}
