package com.engkimbs.coffee.member.api;

import com.engkimbs.coffee.member.command.app.MemberService;
import com.engkimbs.coffee.member.command.domain.entity.Member;
import com.engkimbs.coffee.menu.api.MenuResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PutMapping("/point")
    public ResponseEntity<MemberResponse> chargePoint(@Valid @RequestBody MemberRequest memberRequest) {
        Member member = memberService.chargePoint(memberRequest);
        return ResponseEntity.ok().body(MemberResponse.of(member));
    }
}