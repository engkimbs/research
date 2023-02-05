package com.engkimbs.coffee.menu.command.domain.menu.entity;

import com.engkimbs.coffee.common.converter.PointConverter;
import com.engkimbs.coffee.common.vo.Point;
import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"id"})
public class Menu {

    @EmbeddedId
    private MenuId id;

    @Column(name = "menu_name")
    private String menuName;

    @Convert(converter = PointConverter.class)
    private Point price;

    @Builder
    public Menu(MenuId id, String menuName, Point price) {
        this.id = id;
        this.menuName = menuName;
        this.price = price;
    }
}