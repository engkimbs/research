package com.engkimbs.springjpamybatis.member.api;

import com.engkimbs.springjpamybatis.member.command.entity.Member;
import com.engkimbs.springjpamybatis.member.query.model.MemberModel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private final Long id;

    private final String name;

    private final Integer price;

    @Builder
    public MemberResponse(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .price(member.getPoint().getValue())
                .build();
    }

    public static MemberResponse from(Long id, MemberModel memberModel) {
        return MemberResponse.builder()
                .id(id)
                .name(memberModel.getName())
                .price(memberModel.getPoint())
                .build();
    }

    public static MemberResponse of(MemberModel memberModel) {
        return MemberResponse.builder()
                .name(memberModel.getName())
                .price(memberModel.getPoint())
                .build();
    }
}
