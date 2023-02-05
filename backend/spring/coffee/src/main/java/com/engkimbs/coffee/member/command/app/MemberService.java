package com.engkimbs.coffee.member.command.app;

import com.engkimbs.coffee.common.vo.Point;
import com.engkimbs.coffee.member.api.MemberRequest;
import com.engkimbs.coffee.member.command.domain.entity.Member;
import com.engkimbs.coffee.member.command.domain.repository.MemberRepository;
import com.engkimbs.coffee.member.command.domain.vo.MemberId;
import com.engkimbs.coffee.member.exception.MemberException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.engkimbs.coffee.member.exception.MemberErrorCode.MEMBER_NOT_FOUND;

@Service
public class MemberService {

    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberById(MemberId memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND, memberId.getId() + " member is not founded"));
    }

    public Member updateMemberPoint(Member member, Point orderTotalPoint) {

        Point remainingPoint = member.getPoint().minus(orderTotalPoint);
        member.setRemainingPoint(remainingPoint);
        return memberRepository.save(member);
    }

    @Transactional
    public Member chargePoint(MemberRequest memberRequest) {
        Member member = memberRepository.findById(MemberId.of(memberRequest.getId()))
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND, memberRequest.getId() + " member is not founded"));
        member.addPoint(memberRequest.getMoney());
        return memberRepository.save(member);
    }
}