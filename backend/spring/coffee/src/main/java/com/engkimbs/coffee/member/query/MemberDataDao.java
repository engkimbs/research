package com.engkimbs.coffee.member.query;

import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import org.springframework.data.repository.Repository;

public interface MemberDataDao extends Repository<MemberData, MemberId> {

    MemberData findById(MemberId memberId);
}
