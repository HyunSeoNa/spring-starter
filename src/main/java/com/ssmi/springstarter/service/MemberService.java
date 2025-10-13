package com.ssmi.springstarter.service;

import com.ssmi.springstarter.domain.Member;
import com.ssmi.springstarter.repository.MemberRepository;
import com.ssmi.springstarter.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

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

    // 회원 서비스가 메모리 회원 리포지토리 직접 생성
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원 서비스 코드를 DI 가능하게 변경
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
