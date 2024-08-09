package com.cracks.api.dtos;

import java.util.List;

import lombok.Data;
@Data
public class pushUserInterestDto {
    public Long userId;
    public List<Long> interest;
}
