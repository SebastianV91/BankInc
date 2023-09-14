package com.web.bankinc.service;

import com.web.bankinc.dto.Card;
import com.web.bankinc.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CardService extends CardRepository {

    public Map<String, Object> updateCard(Card card){

        Map<String, Object> response = new HashMap<String, Object>();

        try{

            if(activeCardRepository(card) == 1){
                response.put("mensaje","Active Card");
            }else{
                response.put("mensaje","The card could not be activated");
            }

        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("ERROR EN PROCESOS BACKEND::"+e.getMessage());
            response.put("mensaje", "Error en el backend");
        }

        return response;

    }

}
