package com.engkimbs.coffee.member.command.domain.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class MemberId implements Serializable {

    @Column(name="member_id")
    private String id;

    public MemberId(String id) {
        this.id = id;
    }

    public static MemberId of(String id) {
        return new MemberId(id);
    }
}