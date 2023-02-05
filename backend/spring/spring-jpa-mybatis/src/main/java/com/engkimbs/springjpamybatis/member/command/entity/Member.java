package com.engkimbs.springjpamybatis.member.command.entity;

import com.engkimbs.springjpamybatis.member.api.MemberRequest;
import com.engkimbs.springjpamybatis.member.command.vo.Point;
import com.engkimbs.springjpamybatis.member.command.vo.converter.PointConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Access(AccessType.FIELD)
@EqualsAndHashCode(of = "id")
public class Member {

    @Id
    private Long id;

    private String name;

    @Convert(converter = PointConverter.class)
    private Point point;

    @Builder
    public Member(Long id, String name, Point point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public static Member of(MemberRequest memberRequest) {
        return Member.builder()
                .id(memberRequest.getId())
                .name(memberRequest.getName())
                .point(Point.of(memberRequest.getPoint()))
                .build()
                ;
    }
}
