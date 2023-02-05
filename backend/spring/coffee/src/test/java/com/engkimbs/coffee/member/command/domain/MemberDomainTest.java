package com.engkimbs.coffee.member.command.domain;

import com.engkimbs.coffee.member.command.domain.entity.Member;
import com.engkimbs.coffee.member.command.domain.repository.MemberRepository;
import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.common.vo.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@Sql("classpath:init-test.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberDomainTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("member entity 제대로 적재되는 지 확인")
    void saveMemberDomain() {
        memberRepository.deleteAll();

        MemberId memberId1 = MemberId.of("010-3354-5349");
        MemberId memberId2 = MemberId.of("010-91953-9531");

        Member member1 = Member.builder()
                .id(memberId1)
                .name("KBS")
                .point(Point.of(5000))
                .build();

        Member member2 = Member.builder()
                .id(memberId2)
                .name("SUNG")
                .point(Point.of(6000))
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
    }
}
