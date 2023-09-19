package com.web.bankinc.service;

import com.web.bankinc.dto.Card;
import com.web.bankinc.dto.Transfer;
import com.web.bankinc.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CardService extends CardRepository {

    public Map<String, Object> activeCard(Card card){

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

    public Map<String, Object> generateNumberCard(Card card) {

        Map<String, Object> response = new HashMap<>();

        try{

            if(generateNumberCardRepository(card) == 1){
                response.put("mensaje", "Numeros de la tarjeta registrados exitosamente");
            }else{
                response.put("mensaje", "No se pudo registrar la informacion");
            }

        }catch(Exception e){
            // TODO: handle exception
            System.out.println("ERROR EN PROCESOS BACKEND::"+e.getMessage());
            response.put("mensaje", "Error en el backend");
        }

        return response;

    }

    public Map<String, Object> selectBalance(Card card) throws ResponseStatusException, SQLException {
        return selectBalanceRepository(card);
    }

    public Map<String, Object> rechargeCard(Transfer transfer){

        Map<String, Object> response = new HashMap<String, Object>();

        try {

            if(selectNumberCard(transfer)){

                if(rechargeCardRepository(transfer) == 1){
                    response.put("mensaje", "Saldo recargado");
                }else{
                    response.put("mensaje", "Error al recargar el saldo");
                }

            }else{
                response.put("mensaje", "Esta tarjeta no es existente");
            }

        }catch(Exception e){
            // TODO: handle exception
            System.out.println("ERROR EN PROCESOS BACKEND::"+e.getMessage());
            response.put("mensaje", "Error en el backend");
        }

        return response;

    }

}
