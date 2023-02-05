package com.engkimbs.springjpamybatis.member.api;

import com.engkimbs.springjpamybatis.member.command.entity.Member;
import com.engkimbs.springjpamybatis.member.command.service.MemberService;
import com.engkimbs.springjpamybatis.member.query.model.MemberModel;
import com.engkimbs.springjpamybatis.member.query.service.MemberQueryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    MemberService memberService;

    MemberQueryService memberQueryService;

    public MemberController(MemberService memberService,
                            MemberQueryService memberQueryService) {
        this.memberService = memberService;
        this.memberQueryService = memberQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id) {
        MemberModel member = memberQueryService.getMember(id);
        return ResponseEntity.ok().body(MemberResponse.from(id, member));
    }

    @GetMapping
    public ResponseEntity<Result<List<MemberModel>>> getAllMember() {
        List<MemberModel> allMembers = memberQueryService.getAllMemberList();
        return ResponseEntity.ok().body(new Result<>(allMembers, allMembers.size()));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> addMember(@Valid @RequestBody MemberRequest memberRequest) {
        Member member = memberService.addMember(memberRequest);
        return ResponseEntity.ok().body(MemberResponse.of(member));
    }

    @Getter
    @Setter
    static class Result<T> {
        private T data;
        private int count;

        public Result(T data, int count) {
            this.data = data;
            this.count = count;
        }
    }
}