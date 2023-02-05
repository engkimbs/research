package com.engkimbs.coffee.member.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ActiveProfiles("test")
@Sql("classpath:init-test.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    public MemberControllerTest(@Autowired MockMvc mockMvc,
                                @Autowired ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @DisplayName("포인트 충전 API")
    void chargePoint() throws Exception {
        // given
//        insert into member (member_id, name, point, version) values ('010-3354-5349', 'KBS', 44200, 1);
//        insert into member (member_id, name, point, version) values ('010-9195-5349', 'SEUNG', 92200, 1);
        MemberRequest memberRequest = MemberRequest.builder()
                .id("010-3354-5349")
                .name("KBS")
                .money(4000)
                .build();

        String request = objectMapper.writeValueAsString(memberRequest);

        mockMvc.perform(put("/api/members/point")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(jsonPath("$.id", is("010-3354-5349")))
                .andExpect(jsonPath("$.name", is("daniel kim")))
                .andExpect(jsonPath("$.point", is(145000 + 4000)))
        ;
    }
}