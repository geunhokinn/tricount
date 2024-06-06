package hello.tricount.intercptor;

import hello.tricount.MemberContext;
import hello.tricount.TricountConst;
import hello.tricount.exception.ForbiddenAccessException;
import hello.tricount.model.Member;
import hello.tricount.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    @Override // 컨트롤러 보다 먼저 수행
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) { // 쿠키가 비어있을 때
            throw new ForbiddenAccessException("로그인이 필요합니다.");
        }
        Map<String, Cookie> cookieMap = Arrays.stream(cookies).collect(Collectors.toMap(Cookie::getName, Function.identity()));
        // 쿠키 있음

        Cookie loginCookie = cookieMap.get(TricountConst.LOGIN_MEMBER_COOKIE);
        if (loginCookie == null) { // "loginMemberId" 쿠기가 없을 때
            throw new ForbiddenAccessException("로그인이 필요합니다.");
        }
        // loginMemberId 쿠키 있음

        try {
            // 쿠키의 값인 id가 실제 회원의 id 인지 조회
            Member member = memberService.findMemberByID(Long.valueOf(loginCookie.getValue()));
            // 멤버를 쓰레드 로컬에 저장
            MemberContext.setMember(member);
        } catch (Exception e) {
            throw new ForbiddenAccessException("회원 정보를 찾을 수 없습니다.");
        }

        return true;
    }
}
