package dev.java10x.MagicFridgeAI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {

    private Long id;
    private String nome;
    private Integer quantidade;
    private LocalDateTime validade;

}
