package hello.tricount.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Expense {

    private Long id; // 비용 아이디
    private String name; // 비용 이름
    private Long settlementId; // 정산방 아이디
    private Long payerMemberId; // 비용을 지출한 멤버 아이디
    private BigDecimal amount; // 비용 금액
    private LocalDateTime expenseDateTime; // 비용 지출 날짜
}
