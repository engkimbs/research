package com.engkimbs.springjpamybatis.member.command.dao;

import com.engkimbs.springjpamybatis.member.command.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}