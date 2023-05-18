package com.ddn.inditest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SizeDTO {

    private Integer id;
    private Boolean backSoon;
    private Boolean special;
    private Integer stock;
}
