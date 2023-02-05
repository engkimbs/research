package com.engkimbs.coffee.member.query;

import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@Sql("classpath:init-test.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberQueryServiceTest {

    @Autowired
    private MemberQueryService memberQueryService;

    @Test
    @DisplayName("sql 스크립트 실행 후 member data 가져오나 확인")
    public void afterSqlScriptExecutedCheckMemberData() {
        MemberId memberId = MemberId.of("010-3354-5349");
        MemberData memberData = memberQueryService.getMemberData(memberId);

        assertEquals(memberId, memberData.getId());
    }
}