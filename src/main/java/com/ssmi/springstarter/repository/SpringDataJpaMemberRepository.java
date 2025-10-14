package com.ssmi.springstarter.repository;

import com.ssmi.springstarter.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *packageName    : com.ssmi.springstarter.repository
 * fileName       : SpringDataJpaMemberRepository
 * author         : ssmihs
 * date           : 2025-10-14
 * description    :
 */
 public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
