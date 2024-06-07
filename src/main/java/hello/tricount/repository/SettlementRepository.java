package hello.tricount.repository;

import hello.tricount.model.Settlement;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@RequiredArgsConstructor
public class SettlementRepository {

    private final JdbcTemplate jdbcTemplate;

    public Settlement create(String name) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("settlement").usingGeneratedKeyColumns("id");

        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));

        Settlement settlement = new Settlement();
        settlement.setId(key.longValue());
        settlement.setName(name);

        return settlement;
    }

    public void addParticipantToSettlement(Long settlementId, Long memberId) {
        jdbcTemplate.update("INSERT INTO settlement_participant (settlement_id, member_id) VALUES (?, ?)",
            settlementId, memberId);
    }
}
