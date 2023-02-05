package com.engkimbs.coffee.menu.query;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="MenuDataSummary")
@Immutable
@Getter
@ToString
public class MenuDataSummary {

    @Id
    @Column(name="menu_id")
    private Long menuId;

    @Column(name="menu_name")
    private String menuName;

    @Column(name="ranking")
    private Integer ranking;

    @Column(name="cnt")
    private Integer orderCount;
}