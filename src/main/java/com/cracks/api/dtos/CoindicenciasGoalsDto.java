package com.cracks.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoindicenciasGoalsDto {
    Long id;
    String title;

    public CoindicenciasGoalsDto(Long id, String title){
        this.id=id;
        this.title=title;
    }
}
