package hello.tricount;

import hello.tricount.model.Member;

public class MemberContext {

    // 하나의 쓰레드에서 전역으로 사용할 수 있는 변수
    // 스프링 mvc 는 request 각각이 하나의 쓰레드에서 동작하기 때문에
    // 어느 요청이든 쓰레드 로컬에 접근하면 같은 데이터를 볼 수 있다.
    private static final ThreadLocal<Member> memberThreadLocal = new ThreadLocal<>();

    public static void setMember(Member member) {
        memberThreadLocal.set(member);
    }

    public static Member getMember() {
        return memberThreadLocal.get();
    }

}
