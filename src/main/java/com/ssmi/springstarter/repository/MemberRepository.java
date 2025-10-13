package com.ssmi.springstarter.repository;

import com.ssmi.springstarter.domain.Member;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.ssmi.springstarter.repository
 * fileName       : MemberRepository
 * author         : ssmihs
 * date           : 2025-10-13
 * description    :
 */
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
