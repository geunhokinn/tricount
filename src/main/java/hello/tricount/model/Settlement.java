package hello.tricount.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Settlement {

    private Long id; // 정산방 아이디
    private String name; // 정산방 이름
    private List<Member> participants = new ArrayList<>(); // 정산방 참가자들
}
