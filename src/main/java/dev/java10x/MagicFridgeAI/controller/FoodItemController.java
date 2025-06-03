package dev.java10x.MagicFridgeAI.controller;


import dev.java10x.MagicFridgeAI.dto.FoodDTO;
import dev.java10x.MagicFridgeAI.service.FooditemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FooditemService fooditemService; // injetando a dependência

    public FoodItemController(FooditemService fooditemService) {
        this.fooditemService = fooditemService;
    }

  // 1. CRIAR
@PostMapping("/criar")
@Operation(summary = "Criar comidas", description = "Essa rota cria novas comidas para a geladeira!")
@ApiResponses({
        @ApiResponse(responseCode = "201", description = "Comida criada com sucesso!"),
        @ApiResponse(responseCode = "400", description = "erro na criação da comida!"),
        @ApiResponse(responseCode = "500", description = "Erro na criação da comida, alguma chave foi violada!")
})
public ResponseEntity<String> criarComida(@RequestBody @Valid FoodDTO food){
        FoodDTO novaComida = fooditemService.criarComida(food);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Comida Criada com sucesso  " + novaComida.getNome() + "(id) " + novaComida.getId() );
}
 // 2. LISTAR
    @GetMapping("/listar")
    @Operation(summary = "Listar comidas", description = "essa rota lista as comidas que estão na geladeira")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Comidas listadas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "erro para listar as comidas")
    })
    public ResponseEntity<List<FoodDTO>>listarComida(){
        List<FoodDTO> comida = fooditemService.ListarComida();
        return ResponseEntity.ok(comida);
    }
 // 3. LISTAR POR ID
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista comidas por id", description = "essa rota lista as comidas pelo seu Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comida encontrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "comida não encontrada")
    })
    public ResponseEntity<?> ListasComidaPorId(@PathVariable long id){
        FoodDTO comida = fooditemService.ListarComidaPorId(id);

        if (comida != null){
            return ResponseEntity.ok(comida);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Comida com o ID: " + id + "Não existe nos nossos registro");
        }
    }
    // 4. ALTERAR/ATUALIZAR
    @PutMapping("alterar/{id}")
    @Operation(summary = "Altera uma comida pelo Id", description = "Essa rota altera uma comida pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Comida alterada com sucesso!"),
            @ApiResponse (responseCode = "400", description = "comida não encontrada, não foi possível alterar.")
    })
    public ResponseEntity<?> alterarComidaPorId(
            @Parameter(description = "usuario manda o id no caminho da requisição")
            @PathVariable long id,
            @Parameter(description = "usuario manda os dados da comida para ser atualizada no corpo da requisição ")
            @RequestBody FoodDTO comidaAtualizada){

        FoodDTO alterarComida = fooditemService.Atualizarcomida(id, comidaAtualizada);

        if (alterarComida != null){
            return ResponseEntity.ok(alterarComida);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comida com o Id " + id + " Não existe!");
        }
    }
    // 5. Deletar
    @DeleteMapping("deletar/{id}")
    @Operation(summary = "deleta a comida por Id", description = "essa rota deleta o carro pelo seu Id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comida encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "comida não encontrada")
    })

    public ResponseEntity<String> deletarComidaPorId(@PathVariable long id){

        if(fooditemService.ListarComidaPorId(id) != null){
          fooditemService.deletarComidaPorId(id);
          return ResponseEntity.ok("Comida com o Id " + id + " deletada com sucesso!");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("a comida com o id " + id + " nao foi encontrada!");
        }
    }


}
