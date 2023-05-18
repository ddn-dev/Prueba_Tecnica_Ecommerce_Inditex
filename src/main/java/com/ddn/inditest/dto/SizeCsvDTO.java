package com.ddn.inditest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeCsvDTO {

    private Integer id;
    private Integer productId;
    private String backSoon;
    private String special;
}
