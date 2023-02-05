package com.engkimbs.coffee.menu.command.domain.menu.vo;

import lombok.*;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of="id")
@Getter
@ToString
public class MenuId implements Serializable {

    @Column(name = "menu_id")
    private Long id;

    public MenuId(Long id) {
        this.id = id;
    }

    public static MenuId of(Long id) {
        return new MenuId(id);
    }
}
