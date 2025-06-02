package dev.java10x.MagicFridgeAI.controller;


import dev.java10x.MagicFridgeAI.dto.FoodDTO;
import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import dev.java10x.MagicFridgeAI.service.FooditemService;
import jakarta.validation.Valid;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FooditemService fooditemService; // injetando a dependência

    public FoodItemController(FooditemService fooditemService) {
        this.fooditemService = fooditemService;
    }

  // 1. CRIAR
public ResponseEntity<String> criarComida(@RequestBody @Valid FoodDTO food){
        FoodDTO novaComida = fooditemService.criarComida(food);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Comida Criada com sucesso + " novaComida.getNome() + "(id) " + novaComida.getId() );
}
 // 2. LISTAR
    public ResponseEntity<List<FoodDTO> listarComida(){
        List<FoodDTO> comida = fooditemService.ListarComida();
        return ResponseEntity.ok(comida);
    }
 // 3. LISTAR POR ID
    public ResponseEntity<?> ListasComidaPorId(@PathVariable long id){
        FoodDTO comida = fooditemService.ListarComidaPorId(id);

        if (comida != null){
            return ResponseEntity.ok(comida);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Carro co o ID: " + id + "Não existe nos nossos registro");
        }
    }

    // 4. ALTERAR
    
}
