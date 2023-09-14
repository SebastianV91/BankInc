package com.web.bankinc.repository;


import com.web.bankinc.dto.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CardRepository {

    @Autowired
    @Qualifier("jdbcMaster")
    private JdbcTemplate jdbcTemplate;

    public int activeCardRepository(Card card){

        Map<String, Object> respuesta = new HashMap<>();

        String sql = " UPDATE BANK_INC.CARD "
                + " SET STATUS=? "
                + "WHERE CARDID = ? ";

        int rows = jdbcTemplate.update(sql, card.getStatus(), card.getCardId());

        return rows;

    }

}
