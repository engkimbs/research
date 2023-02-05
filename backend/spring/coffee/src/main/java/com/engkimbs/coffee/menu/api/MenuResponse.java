package com.engkimbs.coffee.menu.api;

import com.engkimbs.coffee.menu.query.MenuData;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuResponse {

    private Long id;

    private String name;

    private Integer price;

    @Builder
    public MenuResponse(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static MenuResponse of(MenuData menuData) {
        return MenuResponse.builder()
                .id(menuData.getMenuId().getId())
                .name(menuData.getMenuName())
                .price(menuData.getPrice().getValue())
                .build();
    }
}
