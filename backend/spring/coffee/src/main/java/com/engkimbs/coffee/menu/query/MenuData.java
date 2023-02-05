package com.engkimbs.coffee.menu.query;

import com.engkimbs.coffee.common.converter.PointConverter;
import com.engkimbs.coffee.common.vo.Point;
import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="menu")
@Getter
@EqualsAndHashCode(of = {"menuId"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuData {

    @EmbeddedId
    private MenuId menuId;

    @Column(name = "menu_name")
    private String menuName;

    @Convert(converter = PointConverter.class)
    private Point price;

    public MenuData(MenuId menuId, String menuName, Point price) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
    }
}