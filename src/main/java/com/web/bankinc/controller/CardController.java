package com.web.bankinc.controller;

import com.web.bankinc.dto.Card;
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

}
