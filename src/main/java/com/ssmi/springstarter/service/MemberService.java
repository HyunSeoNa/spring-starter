package com.ssmi.springstarter.service;

import com.ssmi.springstarter.domain.Member;
import com.ssmi.springstarter.repository.MemberRepository;
import com.ssmi.springstarter.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.ssmi.springstarter.service
 * fileName       : MemberService
 * author         : ssmihs
 * date           : 2025-10-13
 * description    :
 */
public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
