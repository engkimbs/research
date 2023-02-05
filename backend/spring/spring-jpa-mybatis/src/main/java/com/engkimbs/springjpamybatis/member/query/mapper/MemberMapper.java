package com.engkimbs.springjpamybatis.member.query.mapper;

import com.engkimbs.springjpamybatis.member.command.entity.Member;
import com.engkimbs.springjpamybatis.member.query.model.MemberModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    MemberModel getMember(Long id);

    List<MemberModel> getAllMemberList();
}
