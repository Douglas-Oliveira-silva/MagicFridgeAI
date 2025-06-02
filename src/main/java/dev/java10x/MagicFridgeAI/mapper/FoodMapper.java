package dev.java10x.MagicFridgeAI.mapper;
import dev.java10x.MagicFridgeAI.dto.FoodDTO;
import dev.java10x.MagicFridgeAI.model.FoodItem;
import org.springframework.stereotype.Component;

@Component
public class FoodMapper {

    public FoodItem toModel(FoodDTO dto) {

        FoodItem FoodModel = new FoodItem();

        if (dto.getId() != null){
       FoodModel.setId(dto.getId());
    }
       FoodModel.setNome(dto.getNome());
       FoodModel.setQuantidade(dto.getQuantidade());
       FoodModel.setValidade(dto.getValidade());

       return FoodModel;
    }

    public FoodDTO toDTO(FoodItem model){
        FoodDTO foodDTO = new FoodDTO();

        foodDTO.setId(model.getId());
        foodDTO.setNome(model.getNome());
        foodDTO.setQuantidade(model.getQuantidade());
        foodDTO.setValidade(model.getValidade());
        return foodDTO;

    }

}
