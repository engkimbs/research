package com.engkimbs.coffee.member.command.domain.entity;

import com.engkimbs.coffee.common.converter.PointConverter;
import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.common.vo.Point;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Access(AccessType.FIELD)
@EqualsAndHashCode(of = {"id"})
public class Member {

    @EmbeddedId
    private MemberId id;

    @Version
    private Long version;

    private String name;

    @Convert(converter = PointConverter.class)
    @Column(name="point")
    private Point point;

    @Builder
    public Member(MemberId id, String name, Point point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public String getRawId() {
        return id.getId();
    }

    public void addPoint(Point point) {
        this.point = this.point.add(point);
    }

    public void addPoint(Integer value) {
        this.point = this.point.add(value);
    }

    public void setRemainingPoint(Point remainingPoint) {
        this.point = Point.of(remainingPoint);
    }
}