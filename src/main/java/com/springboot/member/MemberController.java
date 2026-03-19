package com.springboot.member;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members")
public class MemberController {
    private final Map<Long, Map<String, Object>> members = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> member1 = new HashMap<>();
        long memberId = 1L;
        member1.put("memberId", memberId);
        member1.put("email", "hgd@gmail.com");
        member1.put("name", "홍길동");
        member1.put("phone", "010-1234-5678");

        members.put(memberId, member1);
    }
    @GetMapping("/{member-id}")
    public String getMember(@PathVariable("member-id") long memberId, Model model) {
        System.out.println("# memberId: " + memberId);
        model.addAttribute("memberId", memberId);
        return "memberDetail"; // templates/memberDetail.html
    }

    @GetMapping // 별도 URI 설정이 없으므로 클래스 레벨 경로인 /v1/members 요청을 처리
    public String getMembers(Model model) {
        System.out.println("# get Members");
        return "memberList"; //templates.memberList.html
    }

    @PostMapping
    public String postMember(@RequestParam("email") String email,
                             @RequestParam("name") String name,
                             @RequestParam("phone") String phone,
                             Model model) {
        System.out.println("# email: " + email);
        System.out.println("# name: " + name);
        System.out.println("# phone: " + phone);

        // model: view(HTML)로 데이터를 전달하기 위해 스프링이 제공하는 바구니 객체
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);

        return "memberResult"; // templates/memberResult.html
    }

    //---------------- 여기서 부터 아래에 코드를 구현하세요! --------------------//
    // 1. 회원 정보 수정을 위한 핸들러 메서드 구현
    @PatchMapping("/{memberId}")
    public ResponseEntity<Map<String, Object>> patchMember(
            @PathVariable long memberId,
            @RequestParam String phone) {

        Map<String, Object> member = members.get(memberId);
        member.put("phone", phone);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Object> deleteMember(
            @PathVariable long memberId) {

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
