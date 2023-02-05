package com.engkimbs.coffee.order.api;

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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@Sql("classpath:init-test.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    public OrderControllerTest(@Autowired MockMvc mockMvc,
                               @Autowired ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @DisplayName("커피 단건 주문하기")
    void singleOrder() throws Exception {
        // given

        mockMvc.perform(post("/api/orders/single_order")
                        .param("member_id", "010-3354-5349")
                        .param("menu_id", "1"))
                .andDo(print())
                .andExpect(status().isCreated())
                //.andExpect(jsonPath("$.order_no", is(1)))
                .andExpect(jsonPath("$.orderer", is("010-3354-5349")))
                .andExpect(jsonPath("$.payment", is(3500)))
                .andExpect(jsonPath("$.remaining_points", is(145000 - 3500)))
        ;
    }

    @Test
    @DisplayName("커피 주문하기")
    void order() throws Exception {
        // given
        OrderLineRequest orderLine1 = OrderLineRequest.builder()
                .menuId(1L)
                .price(2000)
                .quantity(4)
                .build();

        OrderLineRequest orderLine2 = OrderLineRequest.builder()
                .menuId(2L)
                .price(2200)
                .quantity(4)
                .build();

        List<OrderLineRequest> orderLineList = new ArrayList<>();
        orderLineList.add(orderLine1);
        orderLineList.add(orderLine2);

        OrderRequest orderRequest = OrderRequest.builder()
                .orderer("010-3354-5349")
                .orderLineList(orderLineList)
                .build();

        String orderRequestContent = objectMapper.writeValueAsString(orderRequest);

        int requestTotalPrice = orderRequest.getOrderLineList()
                .stream()
                .mapToInt(e -> e.getPrice()*e.getQuantity())
                .sum();

        mockMvc.perform(post("/api/orders/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderRequestContent))
                .andDo(print())
                .andExpect(status().isCreated())
                // .andExpect(jsonPath("$.order_no", is(1)))
                .andExpect(jsonPath("$.orderer", is("010-3354-5349")))
                .andExpect(jsonPath("$.payment", is(requestTotalPrice)))
                .andExpect(jsonPath("$.remaining_points", is(145000 - requestTotalPrice)))
        ;
    }
}