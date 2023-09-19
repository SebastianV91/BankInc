package com.web.bankinc.controller;

import com.web.bankinc.dto.Card;
import com.web.bankinc.dto.Transfer;
import com.web.bankinc.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@RestController
@RequestMapping("/card/")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/enroll")
    public ResponseEntity<?> enroll(@RequestBody Card card){

        return new ResponseEntity(cardService.activeCard(card), HttpStatus.OK);

    }

    @PostMapping("/number")
    public ResponseEntity<?> numberCard(@RequestBody Card card){

        return new ResponseEntity(cardService.generateNumberCard(card), HttpStatus.OK);

    }

    @GetMapping("/getbalance/")
    public ResponseEntity<?> getBalanceCard(@RequestBody Card card){
        try {
            return new ResponseEntity(cardService.selectBalance(card), HttpStatus.OK);
        }catch(SQLException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }catch(ResponseStatusException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/balance")
    public ResponseEntity<?> addBalanceCard(@RequestBody Transfer transfer){

        return new ResponseEntity(cardService.rechargeCard(transfer), HttpStatus.OK);

    }

}
