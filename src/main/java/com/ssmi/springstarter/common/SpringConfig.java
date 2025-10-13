package com.ssmi.springstarter.common;

import com.ssmi.springstarter.repository.JdbcMemberRepository;
import com.ssmi.springstarter.repository.MemberRepository;
import com.ssmi.springstarter.repository.MemoryMemberRepository;
import com.ssmi.springstarter.service.MemberService;
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

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
