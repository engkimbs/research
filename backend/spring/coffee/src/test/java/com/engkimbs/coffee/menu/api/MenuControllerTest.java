package com.engkimbs.coffee.menu.api;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@Sql("classpath:init-test.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MenuControllerTest {

    private final MockMvc mockMvc;

    public MenuControllerTest(
            @Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("id 별 상품 데이터 요청")
    void getMenuById() throws Exception {
        mockMvc.perform(
                get("/api/menus/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("americano")))
                .andExpect(jsonPath("$.price", is(3500)))
        ;
    }

    @Test
    @DisplayName("id 별 상품 조회 실패")
    void failToGetMenuById() throws Exception {
        mockMvc.perform(
                        get("/api/menus/111")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.code", is("MENU_NOT_FOUND")))
                .andExpect(jsonPath("$.message", is("111 is not founded")));
        ;
    }

    @Test
    @DisplayName("최근 7일간 인기메뉴 3개 조회")
    void getPopularMenus() throws Exception {
        mockMvc.perform(
                        get("/api/menus/best-menus")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("americano")))
//                .andExpect(jsonPath("$.price", is(2000)))
        ;
    }
}
