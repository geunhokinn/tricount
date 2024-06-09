package hello.tricount.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ExpenseResult {

    private Long settlementId; // 정산방 아이디
    private Member payerMember; // 비용을 지출한 멤버
    private BigDecimal amount; // 비용 금액
}
