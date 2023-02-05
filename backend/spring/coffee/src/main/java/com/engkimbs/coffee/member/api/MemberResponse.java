package com.engkimbs.coffee.member.api;

import com.engkimbs.coffee.member.command.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private final String id;

    private final String name;

    private final Integer point;

    @Builder
    public MemberResponse(String id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.point = price;
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getRawId())
                .name(member.getName())
                .price(member.getPoint().getValue())
                .build();
    }
}
