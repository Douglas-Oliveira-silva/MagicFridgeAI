package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.dto.FoodDTO;
import dev.java10x.MagicFridgeAI.mapper.FoodMapper;
import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FooditemService {

    private FoodItemRepository repository;
    private FoodMapper foodMapper;


    public FooditemService(FoodMapper foodMapper) {
        this.foodMapper = foodMapper;
    }

    public FooditemService(FoodItemRepository service) {
        this.repository = service;
    }

    // 1. CRIAR NOVA COMIDA

    /*
    Sem usar DTO ficaria assim:
    public FoodItem salvar(FoodItem fooditem) {
        return repository.save(fooditem);
        }
    */

    public FoodDTO criarComida(FoodDTO foodDTO){
        FoodItem food = foodMapper.toModel(foodDTO);
        food = repository.save(food);
        return foodMapper.toDTO(food);
    }

    // 2. LISTAR
  public List<FoodDTO> ListarComida(){
        List<FoodItem> food = repository.findAll();
                return food.stream()
                        .map(foodMapper::toDTO)
                        .collect(Collectors.toList());
  }
    // 3 .LISTAR POR ID
    public FoodDTO ListarComidaPorId(Long id){
        Optional<FoodItem> foodPorId = repository.findById(id);
        return foodPorId.map(foodMapper::toDTO).orElse(null);
    }
    // 4. ALTERAR/ATUALIZAR
    public FoodDTO Atualizarcomida(Long id, FoodDTO foodDTO){
        Optional<FoodItem> foodExistente = repository.findById(id);
        if (foodExistente.isPresent()){
            FoodItem foodAtualizada = foodMapper.toModel(foodDTO);
            foodAtualizada.setId(id);
            FoodItem foodSalva = repository.save(foodAtualizada);
            return foodMapper.toDTO(foodSalva);
        }
        return null;
    }

    // 5 .DELETAR
    public void deletarComidaporId(Long id){
        repository.deleteById(id);
    }

}
