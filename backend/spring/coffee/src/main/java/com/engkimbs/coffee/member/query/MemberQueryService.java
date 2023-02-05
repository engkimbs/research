package com.engkimbs.coffee.member.query;

import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.member.exception.MemberErrorCode;
import com.engkimbs.coffee.member.exception.MemberException;
import org.springframework.stereotype.Service;

@Service
public class MemberQueryService {

    private final MemberDataDao memberDataDao;

    public MemberQueryService(MemberDataDao memberDataDao) {
        this.memberDataDao = memberDataDao;
    }

    public MemberData getMemberData(MemberId memberId) {
        MemberData memberData = memberDataDao.findById(memberId);
        if (memberData == null) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND, memberId.getId() + " is not founded");
        }
        return memberData;
    }
}