package hello.tricount.controller;

import hello.tricount.model.BalanceResult;
import hello.tricount.model.Settlement;
import hello.tricount.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SettlementController {

    private final SettlementService settlementService;

    @PostMapping("/settles/create")
    public ResponseEntity<Settlement> createSettlement(@RequestParam String settlementName) {
        Settlement settlement = settlementService.createSettlement(settlementName);
        return new ResponseEntity<>(settlement, HttpStatus.OK);
    }

    @PostMapping("/settles/{id}/join")
    public ResponseEntity<Void> joinSettlement(@PathVariable Long id) {
        settlementService.joinSettlement(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/settles/{id}/balance")
    public ResponseEntity<List<BalanceResult>> getSettlementBalanceResult(@PathVariable("id") Long settlementId) {
        return new ResponseEntity<>(settlementService.getBalanceResult(settlementId), HttpStatus.OK);
    }
}
