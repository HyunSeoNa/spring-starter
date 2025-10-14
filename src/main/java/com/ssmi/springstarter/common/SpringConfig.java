package com.ssmi.springstarter.common;

import com.ssmi.springstarter.repository.*;
import com.ssmi.springstarter.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * packageName    : com.ssmi.springstarter.common
 * fileName       : SpringConfig
 * author         : ssmihs
 * date           : 2025-10-13
 * description    :
 */
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
