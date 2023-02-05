package com.engkimbs.coffee.member.query;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@Sql("classpath:init-test.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberDataDaoTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberDataDao memberDataDao;

    @Test
    @DisplayName("memberRepository 적재 후, memberDataDao에서 fetch")
    void saveToMemberRepositoryAndFetchFromMemberDataDao() {
        MemberId memberId = MemberId.of("010-3354-5349");

        MemberData memberData = memberDataDao.findById(memberId);

        assertEquals(memberId, memberData.getId());
    }
}