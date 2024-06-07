package hello.tricount.service;

import hello.tricount.MemberContext;
import hello.tricount.model.Settlement;
import hello.tricount.repository.SettlementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SettlementRepository settlementRepository;

    public Settlement createSettlement(String settlementName) {
        Settlement settlement = settlementRepository.create(settlementName);
        settlementRepository.addParticipantToSettlement(settlement.getId(), MemberContext.getMember().getId());
        settlement.getParticipants().add(MemberContext.getMember());

        return settlement;
    }


}
