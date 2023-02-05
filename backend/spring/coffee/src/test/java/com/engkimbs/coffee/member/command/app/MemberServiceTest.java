package com.engkimbs.coffee.member.command.app;

import com.engkimbs.coffee.member.api.MemberRequest;
import com.engkimbs.coffee.member.command.domain.entity.Member;
import com.engkimbs.coffee.member.command.domain.repository.MemberRepository;
import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.common.vo.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@Sql("classpath:init-test.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void updateMemberPoint() {
        // given
        // insert into member (member_id, name, point, version) values ('010-3354-5349', 'KBS', 44200, 1);
        MemberId memberId = MemberId.of("010-3354-5349");

        MemberRequest memberRequest = MemberRequest.builder()
                .id("010-3354-5349")
                .money(4000)
                .build();

        memberService.chargePoint(memberRequest);

        Optional<Member> member = memberRepository.findById(memberId);

        assertTrue(member.isPresent());
        assertEquals(Point.of(145000 + 4000), member.get().getPoint());
    }
}
