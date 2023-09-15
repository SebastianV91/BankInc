package com.web.bankinc.repository;


import com.web.bankinc.dto.Card;
import com.web.bankinc.mapper.BalanceCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.*;

@Component
public class CardRepository {

    @Autowired
    @Qualifier("jdbcMaster")
    private JdbcTemplate jdbcTemplate;

    private Random random = new Random(System.currentTimeMillis());

    public int activeCardRepository(Card card){

        Map<String, Object> respuesta = new HashMap<>();

        String sql = " UPDATE BANK_INC.CARD "
                + " SET STATUS=? "
                + "WHERE CARDID = ? ";

        int rows = jdbcTemplate.update(sql, card.getStatus(), card.getCardId());

        return rows;

    }

    public int generateNumberCardRepository(Card card){

        Map<String, Object> respuesta = new HashMap<>();

        String sql = "INSERT INTO BANK_INC.CARD "
                + " (TYPEPRODUCT, CARDID) "
                + "VALUES (?, ?) ";

        String numberCard = "";

        //  1. Registro de tarjeta debito Maestro
        //  2. Registro de tarjeta de credito American Express
        if(card.getTypeProduct() == 1){
            numberCard = generate("530691");
        }else if(card.getTypeProduct() == 2){
            numberCard = generate("377844");
        }

        int rows = jdbcTemplate.update(sql, card.getTypeProduct(), numberCard);

        return rows;

    }

    public Map<String, Object> selectBalanceRepository(Card card) throws ResponseStatusException, SQLException {

        Map<String, Object> respuesta = new HashMap<>();
        List<BalanceCardMapper> listBalanceCard = new ArrayList<>();

        String sql = "SELECT CARDID, BALANCE "
                + " FROM BANK_INC.CARD B "
                + " WHERE B.CARDID = ? ";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, card.getCardId());

        if(rows != null){
            for(Map<String, Object> row: rows){
                BalanceCardMapper balanceCard = new BalanceCardMapper();
                balanceCard.setNumCuenta((String) row.get("CARDID"));
                balanceCard.setBalance((Integer) row.get("BALANCE"));
                listBalanceCard.add(balanceCard);
            }

            if(rows.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }else{
                respuesta.put("BalanceCard", listBalanceCard);
            }

        }

        return respuesta;

    }

    public String generate(String bin) {

        int randomNumberLength = 16 - (bin.length() + 1);

        StringBuilder builder = new StringBuilder(bin);
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = this.random.nextInt(10);
            builder.append(digit);
        }

        int checkDigit = this.getCheckDigit(builder.toString());
        builder.append(checkDigit);

        return builder.toString();
    }

    private int getCheckDigit(String number) {

        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }

}
