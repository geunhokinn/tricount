package hello.tricount.model;

import hello.tricount.MemberContext;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseRequest {

    private String name; // 지출 이름
    private Long settlementId; // 정산방 아이디
    private Long payerMemberId = MemberContext.getMember().getId(); // 비용을 지출한 멤버 아이디
    private BigDecimal amount; // 비용 금액
}
