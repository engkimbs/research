package com.engkimbs.springjpamybatis.member.query.service;

import com.engkimbs.springjpamybatis.member.query.mapper.MemberMapper;
import com.engkimbs.springjpamybatis.member.query.model.MemberModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberQueryService {

    MemberMapper memberMapper;

    public MemberQueryService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public MemberModel getMember(Long id) {
        return memberMapper.getMember(id);
    }

    public List<MemberModel> getAllMemberList() {
        return memberMapper.getAllMemberList();
    }
}
