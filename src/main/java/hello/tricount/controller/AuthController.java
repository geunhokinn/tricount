package hello.tricount.controller;

import hello.tricount.model.Member;
import hello.tricount.model.SignupRequest;
import hello.tricount.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("signup")
    public ResponseEntity<Member> signup(@RequestBody SignupRequest signupRequest) {
        Member member = Member.builder()
                .loginId(signupRequest.getUserId())
                .password(signupRequest.getPassword())
                .name(signupRequest.getName())
                .build();

        return new ResponseEntity<>(memberService.signup(member), HttpStatus.OK);
    }

}
