package dev.java10x.MagicFridgeAI.controller;


import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import dev.java10x.MagicFridgeAI.service.FooditemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FooditemService fooditemService; // injetando a dependência

    public FoodItemController(FooditemService fooditemService) {
        this.fooditemService = fooditemService;
    }

    //POST
    public ResponseEntity<FoodItem> criar(@RequestBody FoodItem foodItem){
        FoodItem salvo = fooditemService.salvar(foodItem);
        return ResponseEntity.ok(salvo);
    }

    //GET


    //UPDATE

    //DELETE



}
