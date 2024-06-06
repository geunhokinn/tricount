package hello.tricount.controller;

import hello.tricount.TricountConst;
import hello.tricount.model.LoginRequest;
import hello.tricount.model.Member;
import hello.tricount.model.SignupRequest;
import hello.tricount.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
                .loginId(signupRequest.getLoginId())
                .password(signupRequest.getPassword())
                .name(signupRequest.getName())
                .build();

        return new ResponseEntity<>(memberService.signup(member), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<Member> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        Member member = memberService.login(loginRequest.getLoginId(), loginRequest.getPassword());

        Cookie cookie = new Cookie(TricountConst.LOGIN_MEMBER_COOKIE, String.valueOf(member.getId()));
        response.addCookie(cookie);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("logout") // 쿠키만 없앰
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        // 쿠키는 꺼내서 없애는 방법이 없음
        Cookie cookie = new Cookie(TricountConst.LOGIN_MEMBER_COOKIE, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
