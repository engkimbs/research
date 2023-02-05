package com.engkimbs.springjpamybatis.member.command.service;

import com.engkimbs.springjpamybatis.member.api.MemberRequest;
import com.engkimbs.springjpamybatis.member.command.dao.MemberRepository;
import com.engkimbs.springjpamybatis.member.command.entity.Member;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberService {

    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member addMember(MemberRequest memberRequest) {
        return memberRepository.save(Member.of(memberRequest));
    }
}