package com.web.bankinc.controller;

import com.web.bankinc.dto.Card;
import com.web.bankinc.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card/")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/enroll")
    public ResponseEntity<?> enroll(@RequestBody Card card){

        return new ResponseEntity(cardService.updateCard(card), HttpStatus.OK);

    }

}
