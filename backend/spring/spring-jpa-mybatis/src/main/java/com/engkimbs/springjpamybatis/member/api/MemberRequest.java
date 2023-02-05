package com.engkimbs.springjpamybatis.member.api;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
public class MemberRequest {

    @NotEmpty
    private final Long id;

    private final String name;

    private final Integer point;

    @Builder
    public MemberRequest(Long id, String name, Integer point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }
}
