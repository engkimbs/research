package com.engkimbs.coffee.member.query;

import com.engkimbs.coffee.common.converter.PointConverter;
import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.common.vo.Point;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class MemberData {

    @EmbeddedId
    private MemberId id;

    @Column(name="name")
    private String name;

    @Convert(converter = PointConverter.class)
    @Column(name="point")
    private Point point;

    @Builder
    public MemberData(MemberId id, String name, Point point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }
}