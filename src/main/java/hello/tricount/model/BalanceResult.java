package hello.tricount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResult {

    private Long senderMemberId; // 보낼 사람 아이디
    private String senderMemberName; // 보낼 사람 이름
    private BigDecimal sendAmount; // 보낼 비용
    private Long receiverMemberId; // 받는 사람 아이디
    private String receiverMemberName; // 받는 사람 이름
}
