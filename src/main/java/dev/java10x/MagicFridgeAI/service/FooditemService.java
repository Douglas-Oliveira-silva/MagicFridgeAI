package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;

import java.util.List;
import java.util.Optional;

public class FooditemService {

    private FoodItemRepository repository;

    public FooditemService(FoodItemRepository service) {
        this.repository = service;
    }

    public FoodItem salvar(FoodItem fooditem) {
        return repository.save(fooditem);
    }

    public List<FoodItem> listar(){
        return repository.findAll();
    }

    // 1.LISTAR POR ID
    public FoodItem ListarFoodPorID(Long id){
        return repository.findById(id);
    }
    // 2.ALTERAR

    // 3.DELETAR



}
